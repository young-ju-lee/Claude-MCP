<template>
  <form>
    <dl>
      <dt><label for="loginId">아이디</label></dt>
      <dd>
        <input type="text" class="input_st_01" id="loginId" placeholder="아이디" value="">
        <div :class="'save_id ' + save_id_yn" @click="saveId">
          <span class="sic"></span>
          <span class="sit">저장</span>
        </div>
      </dd>
    </dl>
    <dl>
      <dt><label for="loginPwd">비밀번호</label></dt> 
      <dd><input type="password" class="input_st_01" id="loginPwd" placeholder="비밀번호" value="" @keyup.enter="signin()" autocomplete="false"></dd>
    </dl>
    <button @click="signin()" id="btn_login">로그인</button>
    <p class="error_txt">{{ loginErrorText }}</p>
    <p v-show="devtest">
      build info : test
    </p>
  </form>
</template>

<script>
import RSAKey from '../../../utils/rsa/rsa';

export default {
  name: 'LoginForm',
  emits : ["setUiToggleNum"],
  data() {
    return {
      uiToggleNum : 1, // #TOFIX 임시 화면 전환용
      devtest : false, // #TODEL 개발 완료시 삭제 필요
      loginErrorText : "", // 로그인 에러
      save_id_yn : "",
    }
  },
  computed: {
    userLoginData() {
      // 로그인 성공 여부 & 에러 메시지
      return this.$store.state.auth.userLoginData;
    },
    userInfo() {
      // 로그인 사용자 정보
      return this.$store.state.auth.userInfo;
    },
    userOtp() {
      // Otp 정보 수신
      return this.$store.state.auth.userOtp;
    },
    ossInfo() {
      // Otp 정보 수신
      return this.$store.state.auth.ossInfo;
    },
    rsaKey() {
      return this.$store.state.auth.rsaKey;
    },
    userLoginInfo(){
      return this.$store.state.auth.userLoginInfo;
    }
  },
  created() {
    // #TODEL test용 - 기본값 false
    if(process.env.VUE_APP_MODE == "test"){
      this.devtest = true;
    }
  },
  methods: {
    // 로그인 버튼
    signin(){
      let btn_login = document.getElementById("btn_login");
      if(btn_login.disabled){
        return;
      }
      // 로그인 버튼 비활성화. #서버 요청 반복 처리 방지용 함수 추가 필요.
      btn_login.disabled = true;
      if(this.save_id_yn == "on"){
        this.save_id_yn = ""
        this.saveId();
      }
      // login 아이디 가져오는 부분
      let loginId = document.getElementById("loginId").value;
      let loginPwd = document.getElementById("loginPwd").value;
      // #TOFIX 로그 관련해서 공통 처리 모듈 필요 - 빌드 종류로 로그인 방식 구분
      // console.log("#TEST_LOG : 로그인 시도 : " + loginId + "/" + loginPwd);


      // #TODO 기본 유효성 검증 추가되야되는 부분
      // rsa 처리
      this.$store.dispatch("auth/rsaGetKey").then(() => {
        const rsa = new RSAKey();

        rsa.setPublic(this.rsaKey.public_key_modulus, this.rsaKey.public_key_exponent);

        let payload = {
            "loginId": rsa.encrypt(loginId), 
            "loginPwd": rsa.encrypt(loginPwd)
        };
        // $store 로그인 호출
        this.$store.dispatch("auth/signin", payload).then(() => {
            // 로그인 성공시
            if(this.userLoginData.resultCode == 1){
              // 기존값으로 재 세팅
              this.userLoginInfo.loginId = loginId;
              this.userLoginInfo.loginPwd = loginPwd;
              this.$emit('setUiToggleNum', 2, 'pass');
            }else{
              // #TOFIX 반복 부분 함수화, URL 변수화
              btn_login.disabled = false;
              if(this.userLoginData.resultCode == 5){
                this.loginErrorText = "내부 시스템 에러입니다."
              }else if(this.userLoginData.resultCode == 95){
                // #TOFIX 팝업 열리는 부분 공통 변수화
                let options = "top=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no";
                window.open("http://ktsso.kt.com/ssologin/guide/pwdTab.html", "비밀번호 변경", options);
              }else{
                this.loginErrorText = this.userLoginData.resultMessage;
                //2024-01-23 :: otpSkip 호출  
                if(this.userLoginData.data.isSkip == "true"){ 
                  this.$store.dispatch("auth/skipotp", payload).then(() => {
                    if(this.userLoginData.resultCode == 99){
                      this.userLoginInfo.loginId = loginId;
                      this.userLoginInfo.loginPwd = loginPwd;

                      this.$router.push({path:'/alarm'});
                    }
                  }).catch(error => {
                    console.log(error);
                  });
                }
                //2024-01-23 :: otpSkip 호출
              }
            }
        }).catch(error => {
          console.log(error);
        });
      }).catch(error => {
        console.log(error);
      });
    },
    // #TOFIX : 쿠키 가져오기 : 추후 보안 강화하고 공통 함수로 변경
    getSaveId(){
      let cName = 'save_id=';
      let cookieData = document.cookie;
      let start = cookieData.indexOf(cName);
      let cValue = '';
      if(start != -1){
        start += cName.length;
        let end = cookieData.indexOf(';', start);
        if(end == -1){
          end = cookieData.length;
        }
        cValue = cookieData.substring(start, end);
      }
      //unescape(cValue);
      if(cValue != null && cValue != ''){
        document.getElementById("loginId").value = unescape(cValue);
        this.save_id_yn = "on";
      }
    },
    saveId(){
      if(this.save_id_yn == "on"){
        this.deleteSaveId();  
        return;
      }
      let value = document.getElementById("loginId").value;
      if(value == ""){
        return;
      }
      let cookieName = "save_id";
      let exdate = new Date();
      exdate.setDate(exdate.getDate() + 60);
      let cookieValue = escape(value) + "; expires=" + exdate.toGMTString();
      document.cookie = cookieName + "=" + cookieValue;
      this.save_id_yn = "on";
    },
    deleteSaveId(){
      this.save_id_yn = "";
      this.$forceUpdate();
      let exdate = new Date();
      exdate.setDate(exdate.getDate() - 1);
      document.cookie = "save_id= ; expires=" + exdate.toGMTString();
    },
    ossChecker(){
      if(this.$route.query.userId != undefined){
        document.getElementById("loginId").value = this.$route.query.userId;
        this.ossInfo.userId = this.$route.query.userId;
        if(this.$route.query.TTID != undefined){
          this.ossInfo.TTID = this.$route.query.TTID;
        }
      }
    },
  },
  mounted(){
    this.getSaveId();
    this.ossChecker();
  }
}
</script>