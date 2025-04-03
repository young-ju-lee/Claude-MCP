import HttpRequest from './base/http_request'

class AuthService extends HttpRequest {
  // signin : 로그인 호출
  signin(payload){
    return this.post(`/auth/signin`, payload)
  }
  // skipotp : OPT 스킵 호출
  skipOtp(payload){
    return this.post(`/auth/skipOtp`, payload)
  }
  // authOtp : OTP 호출
  authOtp(payload){
    return this.post(`/auth/authOtp`, payload)
  }  
  // signout : 로그아웃 호출
  signout(){
    return this.post(`/auth/signout`)
  }
  // renewalToken : 토큰 갱신 호출
  renewalToken(){
    return this.get(`/auth/renewal-token`)
  }
  // /auth/user-info
  userInfo(){
    return this.get(`/auth/user-info`)
  }
  // /auth/user-info
  rsaGetKey(){
    return this.get(`/auth/public-key`)
  }
}

export default AuthService