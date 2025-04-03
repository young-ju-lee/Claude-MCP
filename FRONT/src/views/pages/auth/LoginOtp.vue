<template>
  <dl>
    <dt><label for="user_id">OTP 인증</label></dt>
    <dd>
      <input type="text" class="input_st_01" id="ontmPwd" placeholder="인증번호" @keyup.enter="authOtp()">
    </dd>
  </dl>
  <p style="margin-bottom:40px">{{mainOtpText}}</p>
  <p style="float:left">남은시간 : {{timer.text}}</p>
  <p style="float:right; cursor: pointer; border:1px solid #ccc; padding:2px 5px; border-radius:5px; vertical-align:bottom;" @click="resetOtp()" v-show="this.userLoginInfo.loginPwd != null">재발송</p>
  <button @click="authOtp()" id="btn_otp">인증번호 확인</button>
  <p class="error_txt">{{errerText}}</p>
</template>

<script>
import RSAKey from '../../../utils/rsa/rsa';

export default {
  name: 'LoginOtp',
  emits : ["setUiToggleNum"],
  data() {
    return {
      devtest : false, // #TODEL 개발 완료시 삭제 필요
      timer : {
        base : 180, // 기준 시간 - 초기화용
        cont : 180, // 실제 처리용 시간
        text : "03:00", // 화면에 표시될 시간
        interval : "" // interval 세팅용 변수
      },
      mainOtpText : "OTP 번호를 SMS 문자로 발송했습니다.",
      errerText : "", // UI : 공통 에러 표시
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
    userLoginInfo(){
      return this.$store.state.auth.userLoginInfo;
    },
    rsaKey() {
      return this.$store.state.auth.rsaKey;
    }
  },
  created() {
    // #TODEL test용 - 기본값 false
    this.timerStart();
    if(process.env.VUE_APP_MODE == "test"){
      this.devtest = true;
    }
  },
  methods: {
    // #TOFIX : 쿠키 가져오기 : 추후 보안 강화하고 공통 함수로 변경
    getCookie(cName){
      cName = cName + '=';
      let cookieData = document.cookie;
      let start = cookieData.indexOf(cName);
      let cValue = '';
      if(start != -1){
        start += cName.length;
        let end = cookieData.indexOf(';', start);
        if(end == -1){
          end == cookieData.length;
        }
        cValue = cookieData.substring(start, end);
      }
      return unescape(cValue);
    },
    // 인증번호 카운트 다운 - 시작
    timerStart(){
      this.timer.text = "03:00";
      this.timer.cont = this.timer.base;
      this.timer.interval = setInterval(() => {
        this.timer.cont--;
        this.timer.text = this.timerSetText();
        if(this.timer.cont <= 0) {
          this.timerStop();
        }
      }, 1000);
    },
    // 인증번호 카운트 다운 - 시간 종료
    timerStop(){
      clearInterval(this.timer.interval);
      //let btn_otp = document.getElementById("btn_otp");
      //btn_otp.disabled = true;
      this.errerText = "인증 시간이 만료되었습니다.";
      this.timer.cont = 0;
    },
    // 인증번호 카운트 다운 - 남은 시간 화면에 텍스트로 추가
    timerSetText(){
      let time = this.timer.cont / 60;
      let minutes = parseInt(time);
      let secondes = Math.round((time - minutes) * 60);
      return minutes.toString().padStart(2, "0") + ":" + secondes.toString().padStart(2, "0")
    },
    // OTP 확인
    resetOtp(){
      this.timerStop();
      // rsa 처리
      this.$store.dispatch("auth/rsaGetKey").then(() => {
        const rsa = new RSAKey();
        rsa.setPublic(this.rsaKey.public_key_modulus, this.rsaKey.public_key_exponent);
        let loginId = this.userLoginInfo.loginId;
        let loginPwd = this.userLoginInfo.loginPwd;
        let payload = {
            "loginId": rsa.encrypt(loginId), 
            "loginPwd": rsa.encrypt(loginPwd)
        };            
        // $store 로그인 호출
        this.$store.dispatch("auth/signin", payload).then(() => {
            // 로그인 성공시
            if(this.userLoginData.resultCode == 1){

              this.userLoginInfo.loginId = loginId;
              this.userLoginInfo.loginPwd = loginPwd;              
              this.errerText = "";
              document.getElementById("ontmPwd").value = "";
              this.mainOtpText = "OTP 번호를 SMS 문자로 재발송했습니다."
              this.timerStart();
            }else{
              // #TOFIX 반복 부분 함수화, URL 변수화
              if(this.userLoginData.resultCode == 5){
                this.errerText = "내부 시스템 에러입니다."
              }else if(this.userLoginData.resultCode == 95){
                // #TOFIX 팝업 열리는 부분 공통 변수화
                let options = "top=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no";
                window.open("http://ktsso.kt.com/ssologin/guide/pwdTab.html", "비밀번호 변경", options);
              }else{ 
                this.errerText = this.userLoginData.resultMessage
                // this.$emit('setUiToggleNum', 2, 'pass');
              }
            }
        }).catch(error => {
          console.log(error);
        });
      }).catch(error => {
        console.log(error);
      });

    },
    authOtp(){
      // #TOCHK 값 value값 가져오는게 이게 최선인가? - 컨포넌트 환경에서 안맞는 기법인듯 함.
      if(this.userInfo.resCd != 1){
        alert("잘못된 접근입니다.");
        this.$emit('setUiToggleNum', 1, 'all');
        return false;
      }

      let ontmPwd = document.getElementById("ontmPwd").value;        
      if(ontmPwd == ""){
        alert("OTP번호를 입력해주세요");
        document.getElementById("ontmPwd").focus;
      }
      // rsa 처리
      this.$store.dispatch("auth/rsaGetKey").then(() => {
        const rsa = new RSAKey();
        rsa.setPublic(this.rsaKey.public_key_modulus, this.rsaKey.public_key_exponent);
        let loginId = this.userLoginInfo.loginId;
        let loginPwd = this.userLoginInfo.loginPwd;
        // #TODO 기본 유효성 검증 추가되야되는 부분
        let payload = {
            "loginId": rsa.encrypt(loginId), 
            "loginPwd": rsa.encrypt(loginPwd),
            "ossAthnInntNo": this.userInfo.ossAthnInntNo, 
            "ontmPwd": ontmPwd
        };
        // $store OTP 인증 호출
        this.$store.dispatch("auth/authOtp", payload).then(() => {
          // otp 성공시
          if(this.userOtp.resultCode == 1){
            this.userLoginInfo.loginId = loginId;
            this.userLoginInfo.loginPwd = loginPwd;
            // #TOFIX : 보안에 몹시 위배됨으로 보안 처리할 때 개선 필요
            //localStorage.setItem('userInfo', JSON.stringify(this.$store.state.auth.userInfo));
            // #TODO : 쿠키값에서 토큰 값 추출
            // getCookie(토큰명). 각 호출마다 refresh 토큰은 갱신해 줘야함 - 상태관리 공통 함수로 추가
            // #TOFIX : 받아온 토큰 정보 입력
            // console.log("accessToken = " + this.getCookie("accessToken"));
            // console.log("refreshToken = " + this.getCookie("refreshToken"));
            //localStorage.setItem('accessToken', "받아온 토큰 정보 입력"); //this.getCookie()
            // 페이지 이동 : 알람톡
            this.$router.push({path:'/alarm'});
          }else{            
            // #TOFIX 반복 부분 함수화, URL 변수화
            if(this.userOtp.resultCode == 5){
              this.loginErrorText = "내부 시스템 에러입니다."
            }else if(this.userOtp.resultCode == 9){
              // 재발송 관련된 에러 처리
              //let btn_otp = document.getElementById("btn_otp");
              //btn_otp.disabled = true;              
              this.errerText = this.userOtp.resultMessage;
            }else if(this.userOtp.resultCode == 95){
              // #TOFIX 팝업 열리는 부분 공통 변수화
              let options = "top=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no";
              window.open("http://ktsso.kt.com/ssologin/guide/pwdTab.html", "비밀번호 변경", options);
            }else{
              this.errerText = this.userOtp.resultMessage;
            }
          }
        }).catch(error => {
          console.log(error);
        });
      }).catch(error => {
        console.log(error);
      });
    }
  }
}
</script>