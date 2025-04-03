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
    <div class="error_wrap">
      <div class="error_box">
        <p class="error_txt">잘못된 접근입니다.</p>
        <button @click="this.$router.push({path:'/'});">사이트로 이동</button>
      </div>
    </div>
    <!--// login_form -->
  </div>
	<!--// login_box -->
</template>

<script>

export default {
  name: 'App',
  components: {
  },
  data() {
    return {
      uiToggleNum : 1, // #TOFIX 임시 화면 전환용
      devtest : false, // #TODEL 개발 완료시 삭제 필요
    }
  },
  computed: {
  },
  created() {
  },
  methods: {
  },
  mounted(){
  }
}
</script>