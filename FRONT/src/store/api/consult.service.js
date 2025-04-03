import HttpRequest from './base/http_request'

class ConsultService extends HttpRequest {
  // #TODEL : restTest : 테스트 함수
  restTest() {
      return this.post(`/alarm`)
  }
  // #TODEL : sendConsultTalk : 상담톡 메시지 전송 호출
  sendConsultTalk(payload){
    return this.post(`/consult/send/`, payload)
  }
  // consultHistList : 비대면 응대 이력 리스트 호출
  consultHistList(payload){    
    return this.get(`/consult/history/` + payload)
  }
  // consultHistList : 비대면 응대 이력 상세 호출
  consultHist(payload){    
    return this.get(`/consult/history/` + payload.consult_id + `/` + payload.id + `/chat` )
  }
  // consultHistList : 비대면 응대 이력 상세 호출
  consultDone(payload){    
    return this.post(`/consult/done`, payload)
  }
  // consultHistList : 비대면 응대 이력 상세 호출
  consulting(payload){    
    return this.get(`/consult/consulting?csttCmpNo=` + payload)
  }
  // consultStatus : 상담 상태 업데이트
  consultStatus(payload){    
    return this.post(`/consult/status`, payload)
  }
  
  consultReservation(payload){
    return this.post(`/reservation`, payload)
  }
}

export default ConsultService