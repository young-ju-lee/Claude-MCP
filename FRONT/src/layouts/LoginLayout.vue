<template>
  <div class="login_wrap">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
		server_info : process.env.VUE_APP_MODE,
		userInfo : {}
    }
  },
  beforeMount(){
	// 1차적으로 login 확인(UI 접근 차단). 토큰 만료시 서버에서 로그인으로 리다이렉트
	// 
	


  },
  methods: {

  }
}
</script>