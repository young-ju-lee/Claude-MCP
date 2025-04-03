
import { authService } from '../../api'

// state
const state = {
    userLoginData: [],  // userLoginData : 사용자 로그인 관련 정보
    userLoginInfo:[],   // userLoginInfo : 로그인 시도 정보
    userInfo:[],        // userInfo : 사용자 정보(로그인 성공시에만 저장)
    userOtp:[],         // userOtp : Otp 정보
    ossInfo:[],          // ossInfo : oss통한 접속 시 정보
    rsaKey:[],
    renewalToken : []
}

// getters
const getters = {

}

// actions
const actions = {
    // signin : 로그인 호출
    async signin(context, payload) {
        // 로그인 시도 정보 저장
        state.userLoginInfo = payload;
        let resp = await authService.signin(payload);
        context.commit('SIGNIN', resp.data);
    },
    async skipotp(context, payload) {
        // OTP 인승 스킵
        state.userLoginInfo = payload;
        let resp = await authService.skipOtp(payload);
        context.commit('SKIPOTP', resp.data);
    },
    // authOtp : OTP 호출
    async authOtp(context, payload) {
        let resp = await authService.authOtp(payload);
        context.commit('AUTH_OTP', resp.data);
    },
    // signout : 로그아웃
    async signout(context) {
        let resp = await authService.signout();
        // #TOFIX : 로그아웃 후처리 필요할 수 있으니 일단 추가
        context.commit('SIGNOUT', resp.data);               
    },
    // renewalToken : 토큰 갱신
    async renewalToken(context) {
        let resp = await authService.renewalToken();
        context.commit('RENEWAL_TOKEN', resp.data);
    },
    // userInfo : 로그인 복구용 데이터 확인
    async userInfo(context) {
        let resp = await authService.userInfo();
        context.commit('USER_INFO', resp.data);
    },
    async rsaGetKey(context) {
        let resp = await authService.rsaGetKey();
        context.commit('RSA_GET_KEY', resp.data);
    },
}

// mutations
const mutations = {
    // signin : 로그인 응답 처리
    SIGNIN(state, res) {
        // res.resultCode == 1 : 성공
        if(res.resultCode == 1){
            state.userInfo = res.data
            // console.log("userInfo set");
            // console.log(state.userInfo);
        }else{
            state.userInfo = [];
        }
        state.userLoginData = res;
        // console.log(state.userLoginData);
    },
    // skipotp : OTP SKIP 응답 처리
    SKIPOTP(state, res) {
        // res.resultCode == 1 : 성공
        if(res.resultCode == 1){
            state.userInfo = res.data
            // console.log("userInfo set");
            // console.log(state.userInfo);
        }else{
            state.userInfo = [];
        }
        state.userLoginData = res;
        // console.log(state.userLoginData);
    },
    // authOtp : OTP 응답 처리
    AUTH_OTP(state, res) {
        state.userOtp = res;
        // console.log(res);
        // console.log("--");
        // console.log(res.headers);
        
    },
    // signout : 로그아웃 응답 처리
    SIGNOUT(state) {
        // 로직의 처리 순서 변경 필요.
        // #TOFIX : 보안상 서버단 로그인 호출이 실패했을 경우에도 로그아웃시 제거 필요함
        state.userInfo = [];
        state.userLoginData = [];

    },
    // renewalToken : 토큰 갱신 응답 처리
    RENEWAL_TOKEN(state, res) {
        state.renewalToken = res;
    },
    // userInfo : 로그인 복구용 데이터 확인 응답 처리
    USER_INFO(state, res){
        // console.log(res);
        // 기본 로그인이랑 맞춰야함.
        state.userInfo = res.data;
    },
    RSA_GET_KEY(state, res){
        state.rsaKey = res.data;
    },
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}