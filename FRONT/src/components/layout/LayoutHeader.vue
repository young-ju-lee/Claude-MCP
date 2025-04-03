<template>
<!-- header -->
<header>
	<div class="header_box">
		<h1 class="logo">
			<img src="../../assets/images/img_logo_02.png" alt="">
			<span>AI 비대면 자가조치</span>			
		</h1>
		<nav>
			<ul>
				<li :class="this.$route.name == 'alarm' ? 'on' : ''">
					<a href="/alarm">알림<span>톡</span>전송</a>
				</li>
				<li :class="this.$route.name == 'ConsultPage' ? 'on' : ''">
					<a href="/consult">상담<span>톡</span>전송</a>
				</li>
			</ul>
		</nav>
		<div class="gnb">
			<ul>
				<li v-text="'build info : ' + this.server_info" style="padding-right:20px; display:inline; color:#ccc" v-if="this.server_info != 'production'" />
				<li class="login_user">
					<span>{{StringUtil().cusNmFormatter(userInfo.userName)}}({{userInfo.deptName}}) 님</span>
				</li>
				<li class="gnb_menu">
					<ul>
						<li @click="logout()"><a href="#this">로그아웃</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</header>
<!-- header -->
</template>
<script>
 import StringUtil from '../../utils/StringUtil';

export default {
  name: 'App',
  data() {
    return {
		server_info : process.env.VUE_APP_MODE,
    }
  },
	computed: {
		userInfo() {
		// 로그인 성공 여부 & 에러 메시지
			return this.$store.state.auth.userInfo;
		}
	},
  
  methods: {
	logout(){
		// localStorage 초기화
		localStorage.clear();
		// logout 호출
		this.$store.dispatch("auth/signout").then(() => {            
			// 로그아웃 성공
			this.$router.push({path:'/login'});
		})
		.catch(error => {
			// 로그아웃 호출 실패. 로그인 페이지로 이동
			console.log(error);
			this.$router.push({path:'/login'});
		});
	},
	StringUtil(){
        var obj = StringUtil;
        return obj;        
    }   
  }
}
</script>