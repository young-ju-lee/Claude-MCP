<template>
  <LayoutHeader />
  <div class="contents">
    <div class="content_box" v-if="this.userInfo.loginId != null">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import LayoutHeader from '@/components/layout/LayoutHeader.vue'

export default {
  name: 'App',
  components : {
    LayoutHeader,
  },
  computed: {
    userInfo() {
      // 로그인 성공 여부 & 에러 메시지
      return this.$store.state.auth.userInfo;
    }
  },
  created(){
    // 1차적으로 login 확인(UI 접근 차단). 토큰 만료시 서버에서 로그인으로 리다이렉트  
    this.$store.dispatch("auth/userInfo").then(() => {            
    
    // 일단 호출 먼저 진행
    if(this.userInfo == null){
      alert("로그인이 필요합니다.");
      this.$router.push({path:'/login'});
    }
    // 실패하던 성공하던 한번만 세팅 되어야함
    }).catch(error => {
      // userInfo 호출 실패
      console.log(error);
      // 실패하던 성공하던 한번만 세팅 되어야함
    });
  },
}
</script>