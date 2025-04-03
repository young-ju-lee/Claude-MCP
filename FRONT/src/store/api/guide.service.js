import HttpRequest from './base/http_request'

class GuideService extends HttpRequest {
  // accptInfo : 고장접수정보 조회
  egl(payload){
    return this.get(`/guide/egl?tt_id=` + payload)
  }
}

export default GuideService