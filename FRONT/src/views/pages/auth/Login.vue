<template>
  <!-- login_box -->
	<div class="login_box">
    <!-- login_visual -->
    <div class="login_visual">
      <div class="logo">
        <img src="@/assets/images/img_logo_01.png" alt="Kt OSS">
        <p>AI 비대면 자가조치</p>
      </div>
    </div>
    <!--// login_visual -->

    <!-- login_form -->
    <div class="login_form">
			<div class="form_box">
        <login-form v-if="this.uiToggleNum == 1" @setUiToggleNum="setUiToggleNum" />
        <login-otp v-if="this.uiToggleNum == 2" @setUiToggleNum="setUiToggleNum" />
      </div>
    </div>
    <!--// login_form -->
  </div>
	<!--// login_box -->
</template>

<script>
import LoginForm from './LoginForm.vue';
import LoginOtp from './LoginOtp.vue';

export default {
  name: 'App',  
  components: {
    LoginForm,
    LoginOtp
  },
  data() {
    return {
      uiToggleNum : 1, // #TOFIX 임시 화면 전환용
      devtest : false, // #TODEL 개발 완료시 삭제 필요
    }
  },
  computed: {
    userLoginData() {
      // 로그인 성공 여부 & 에러 메시지
      return this.$store.state.auth.userLoginData;
    },
    userInfo() {
		// 로그인 성공 여부 & 에러 메시지
		return this.$store.state.auth.userInfo;
		}
  },
  created() {
    // #TODEL test용 - 기본값 false

    if(process.env.VUE_APP_MODE == "test"){
      this.devtest = true;
    }
  },
  methods: {    
    // Login - Otp 화면 전환
    setUiToggleNum(num , type) {
      // 화면 none-block 처리
      this.uiToggleNum = num

      if(type == "pass"){
        // OTP 화면 전환시
        if(this.uiToggleNum == 2){
          // if : OTP(2) 화면일 경우 검증 로직 실행
          if(this.userLoginData.resultCode != 1){
            // if : Login 성공이 아닌 상태에서 진입 시
            // 로그인 UI로 다시 변경
            alert("잘못된 접근입니다.");
            this.uiToggleNum = 1;
          }
        }
      }
    },
    loginChecker(){
      this.$store.dispatch("auth/userInfo").then(() => {            
        if(this.userInfo != null){
          this.$router.push({path:'/alarm'});
        }
      }).catch(error => {
        // userInfo 호출 실패
        console.log(error);
        // 실패하던 성공하던 한번만 세팅 되어야함
      });
    },
  },
  mounted(){
    // 기존 로그인 확인
    this.loginChecker();
  }
}
</script>