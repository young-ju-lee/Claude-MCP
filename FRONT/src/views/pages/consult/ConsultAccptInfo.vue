<template>
  <div class="group_item">
    <h1>고장접수 정보</h1>
    <div class="box" style="padding:10px 20px">
      <table>
        <colgroup>
          <col style="width:25%">
          <col style="width:25%">
          <col style="width:25%">
          <col>
        </colgroup>
        <tbody>
          <tr>
            <th>접수유형</th>
            <td>{{accptInfo.troubReceiptTypecodeDesc}}</td>
            <th>신고유형</th>
            <td>{{accptInfo.reportTypeCodeDesc}}</td>
          </tr>
          <tr>
            <th>고객명</th>
            <td>{{StringUtil().cusNmFormatter(accptInfo.custName)}}</td>
            <th>접속번호</th>
            <td>{{StringUtil().phoneFormatter(accptInfo.svcId)}}</td>
          </tr>              
          <tr>
            <th>고객연락처1</th>
            <td>{{StringUtil().phoneFormatter(accptInfo.reporterTelNum1)}}</td>
            <th>고객연락처2</th>
            <td>{{StringUtil().phoneFormatter(accptInfo.reporterTelNum2)}}</td>
          </tr>
          <tr>
            <th>상품</th>
            <td colspan="3">{{accptInfo.svcMainTypeCodeDesc}}</td>
          </tr>
          <tr>
            <th>접수일시</th>
            <td colspan="3">{{accptInfo.troubReceiptDt}}</td>
          </tr>
          <tr>
            <th>예약일시</th>            
            <td colspan="3">{{StringUtil().dateFormatter(accptInfo.rsvDt, accptInfo.rsvTime)}}</td>            
          </tr>
          <tr>
            <th>신고내용</th>
            <td colspan="3" :title="reporterOpinion" class="reporterOpinion">{{accptInfo.reporterOpinion}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>


<script>
  import StringUtil from '../../../utils/StringUtil';
  export default {
    name: 'ConsultAccptInfo',
    emits : ["searchLink"],
    data() {
      return {
        reporterOpinion : "",
      }
    },
    created() {

    },
    computed: {
      accptInfo(){
        return this.$store.state.trouble.accptInfo;
      },
      consultTarget() {
      // 로그인 사용자 정보
        return this.$store.state.consult.consultTarget;
      },
    },
    methods: {
      getAccptInfo() {
        // TTID 조회                
        this.ttId = this.consultTarget.currTtID;//localStorage.getItem('ttId');                
        this.$store.dispatch("trouble/accptInfo", this.ttId).then(() => {
          // 알림톡 -> 전환 관련 처리
          this.reporterOpinion = this.accptInfo.reporterOpinion;
          // 팝업일 경우(분기 처리 해서) 이부분에서 전화번호 넘겨주는 처리
          if(this.consultTarget.popupYn == "Y"){
            // #TOFIX : indexOf 처리 검증 오류 확률 높음
            let telNum = "";
            let reporterTelNum1 = this.accptInfo.reporterTelNum1;
            let reporterTelNum2 = this.accptInfo.reporterTelNum2;    

            if(reporterTelNum1 != ""){                                
              if(reporterTelNum1.indexOf("010") == 0 || reporterTelNum1.indexOf("010") == 1 || 
                reporterTelNum1.indexOf("017") == 0 || reporterTelNum1.indexOf("017") == 1 ||
                reporterTelNum1.indexOf("016") == 0 || reporterTelNum1.indexOf("016") == 1 ||
                reporterTelNum1.indexOf("011") == 0 || reporterTelNum1.indexOf("011") == 1){                  
                telNum = reporterTelNum1;
              } else {
                telNum = reporterTelNum2;                             
              }
            } else {
              telNum = reporterTelNum2;                        
            }

            if(telNum.indexOf("010") != 0 && telNum.indexOf("010") != 1 &&
              telNum.indexOf("017") != 0 && telNum.indexOf("017") != 1 &&
              telNum.indexOf("016") != 0 && telNum.indexOf("016") != 1 &&
              telNum.indexOf("011") != 0 && telNum.indexOf("011") != 1){                  
              telNum = "";
            }
            if(telNum.length == 12){
              telNum = telNum.substr(1, telNum.length);
            }
            // 실 세팅
            this.consultTarget.targetName = this.StringUtil().cusNmFormatter(this.accptInfo.custName);
            this.consultTarget.targetNum = telNum;
            
            this.$emit('searchLink');
          }
        })
        .catch(error => {
          console.log(error);
        });
      },
      StringUtil(){
        var obj = StringUtil;
        return obj;        
      }
    },
    mounted(){
      this.getAccptInfo();
    }
  }
</script>