import HttpRequest from './base/http_request'

class TroubleService extends HttpRequest {
  
  //-------------------------------------
  // 고장접수정보 조회
  //-------------------------------------
  accptInfo(payload){
    return this.get(`/trouble/accptInfo?ttId=` + payload)
  }
  //-------------------------------------
  // 고장이력정보 조회
  //-------------------------------------
  histList(payload){    
    return this.get(`/trouble/cmpltHistList?ttId=` + payload)
  }  
  //-------------------------------------
  // 고장이력정보 상세 조회
  //-------------------------------------
  cmpltHistInfo(payload){    
    return this.get(`/trouble/cmpltHistInfo/?ttId=` + payload.ttId + `&lscn=` + payload.lscn )
  }  
  //-------------------------------------
  // 망구성도
  //-------------------------------------
  hdmsDgrmList(payload){    
    return this.get(`/trouble/hdmsDgrmList/?ttId=` + payload.ttId)
  }  
  //-------------------------------------
  // 망구성도 팝업
  //-------------------------------------
  hdmsResultInfo(payload){    
    return this.get(`/trouble/hdmsResultInfo/?ttId=` + payload.ttId + `&scn=` + payload.scn )
  }
  //-------------------------------------
  // AI 무출동 대상조회 POST
  //-------------------------------------  
  nonMoveTgtList(param){        
    let pageNo = param.pageNo;
    let recordSize = param.recordSize;
    let custName = param.custName;
    let svcId = param.svcId;
    let ttId = param.ttId;
    //let troubReceiptDt = param.troubReceiptDt;
    let svcMainClsCode = param.svcMainClsCode;
    let officesCode = param.officesCode;   
    let userId = param.userId;
    let rsvDt = param.rsvDt;
    let rsvFromDt = param.rsvFromDt;
    let rsvToDt = param.rsvToDt;
    let payLoad = {
      "page" : pageNo,
      "size" : recordSize,
      "userId" : userId
    };
    // 상품
    if(svcMainClsCode != null && svcMainClsCode != ''){      
      payLoad.svcMainClsCode = svcMainClsCode;
    }
    // 고객명
    if(custName != null && custName != ''){      
      payLoad.custName = custName;
    }
    // 접속번호
    if(ttId != null && ttId != ''){      
      payLoad.TTID = ttId;
    }
    // 접속번호
    if(svcId != null && svcId != ''){      
      payLoad.svcId = svcId;
    }
    // 접수일자
    // if(troubReceiptDt != null && troubReceiptDt != ''){      
    //   payLoad.troubReceiptDt = troubReceiptDt;
    // }    
    if(officesCode != null && officesCode != ''){      
      payLoad.officesCode = officesCode;
    }    
    if(rsvDt != null && rsvDt != ''){      
      payLoad.rsvDt = rsvDt;
    }    
    if(rsvFromDt != null && rsvFromDt != ''){      
      payLoad.rsvFromDt = rsvFromDt;
    }  
    if(rsvToDt != null && rsvToDt != ''){      
      payLoad.rsvToDt = rsvToDt;
    }  
    let url = '/trouble/nonMoveTgtListP';
    return this.post(url, payLoad);       
  }
  //-------------------------------------
  // 알림톡 발송이력 POST
  //-------------------------------------  
  alarmTalkSndHistList(param){
    let pageNo = param.pageNo;
    let recordSize = param.recordSize;    
    let sndCmpNo = param.sndCmpNo;
    let custName = param.custName;
    let custTelNo = param.custTelNo;
    let sndFromDt = param.sndFromDt;
    let sndToDt = param.sndToDt;
    let payLoad = {
      "page" : pageNo,
      "size" : recordSize
    };
    // 발송자 ID
    if(sndCmpNo != null && sndCmpNo != ''){      
      payLoad.sndCmpNo = sndCmpNo;
    }
    // 고객명
    if(custName != null && custName != ''){      
      payLoad.custName = custName;
    }
    // 고객 전화번호
    if(custTelNo != null && custTelNo != ''){      
      payLoad.custTelNo = custTelNo;
    }
    // 발송일시 From
    if(sndFromDt != null && sndFromDt != ''){      
      payLoad.sndFromDt = sndFromDt;
    }
    //발송일시 To
    if(sndToDt != null && sndToDt != ''){      
      payLoad.sndToDt = sndToDt;
    }
    let url = '/alarm/alarmTalkSndHistListP';
    return this.post(url, payLoad);
  }
  //-------------------------------------
  // 국사정보 조회
  //-------------------------------------  
  officeCodeList(payLoad){    
    return this.get(`/trouble/officeCodeList`, payLoad);
  }
}

export default TroubleService