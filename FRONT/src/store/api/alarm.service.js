import HttpRequest from './base/http_request'

class AlarmService extends HttpRequest {
  // sendAlarmTalk : 알림톡 전송 호출
  sendAlarmTalk(payload){
    return this.post(`/alarm/send/`, payload)
  } 
}

export default AlarmService