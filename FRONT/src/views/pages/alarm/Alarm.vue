<template>
    <!-- ct_03:인증 -->
  <div class="contents">
    <div class="content_box">
      <div class="alarm">
        <div class="header_tab">
          <ul>
            <li :class="tabNum == 1 ? 'on' : ''" @click="setMenuOnToggle(1)"><a href="#this">AI 무출동 대상 조회</a></li>
            <li :class="tabNum == 2 ? 'on' : ''" @click="setMenuOnToggle(2)"><a href="#this">알림톡 발송 이력</a></li>
          </ul>
        </div>
        <div>
          <alarm-n-a-tartget-list @openLayerPop="openLayerPop" v-if="tabNum == 1" />
          <alarm-send-hist-list @openLayerPop="openLayerPop" v-if="tabNum == 2" />
        </div>
        <!--// group -->
      </div>
    </div>
  </div>
  <div class="layer_popup" v-show="onLayer">
    <div class="alarm_box">
      <h1>알림톡 발송 정보 입력</h1>
      <div class="layer_box">
        <p class="fc_st02">고장 접수된 고객명과 전화번호로 발송 가능하니<br>발송시 유의해 주시기 바랍니다.</p>
        <dl class="dl_st_01">
          <dt><label for="user_id">고객명</label></dt>
          <dd>
            <input type="text" class="input_st_01" id="targetName" placeholder="고객명" value="" disabled>
            <div class="input_ctl">
              <span class="modify" @click="inputModify('targetName')">수정</span>
            </div>
          </dd>
        </dl>
        <dl>
          <dt><label for="user_pw">전화번호</label></dt>
          <dd>
            <input type="text"  class="input_st_01" id="targetNum" placeholder="전화번호" value="" disabled>
            <div class="input_ctl">
              <span class="modify" @click="inputModify('targetNum')">수정</span>
            </div>
          </dd>
        </dl>
        <button @click="sendAlarmTalk()">알림톡 발송</button>
        <p class="error_txt">{{errorTxt}}</p>
      </div>
      <p class="layer_close" @click="closeLayerPopup()"></p>
    </div>
  </div>
</template>

<script>
  import AlarmNATartgetList from './AlarmNATartgetList.vue'; // AI 무출동 대상 조회
  import AlarmSendHistList from './AlarmSendHistList.vue'; // 알림톡 발송 이력
  import StringUtil from '../../../utils/StringUtil';

  export default {
    name: 'App',
    data() {
      return {
        testPageNum : 1,
        onLayer : false,
        tabNum : 1,
        errorTxt : "",
        ttId : "",
        svcMainClsCode : "",
        sndNm : "",
        sndTeam : "",
        custNm : "",
        custTelNo : "",
        oriCustNm : "",
        oriCustTelNo : ""
      }
    },
    components: {
      AlarmNATartgetList, // AI 무출동 대상 조회
      AlarmSendHistList, // 알림톡 발송 이력
    },
    computed: {
      roomInfo() {
        return this.$store.state.kakao.roomInfo;
      },
      userInfo() {
      // 로그인 사용자 정보
        return this.$store.state.auth.userInfo;
      },
      consultTarget() {
      // 로그인 사용자 정보
        return this.$store.state.consult.consultTarget;
      },
      renewalToken(){
        return this.$store.state.auth.renewalToken;
      }
    },
    created() {
    },
    methods: {
      setTestPageNum() {
        this.testPageNum = this.testPageNum + 1;
      },
      openLayerPop(item){
                
        this.onLayer = true;
        let userInfoData = this.userInfo;  // 로그인한 사용자 정보        
        let custName = '';
        let reporterTelNum1 = '';
        let reporterTelNum2 = '';
        let telNum = '';
        let ttId = '';
        let svcMainClsCode = '';
        let sndNm = '';
        let sndTeam = '';
        let tabNum = this.tabNum;        
        // 무출동 대상조회
        if(tabNum == 1){
          custName = item.custName;
          reporterTelNum1 = item.reporterTelNum1;
          reporterTelNum2 = item.reporterTelNum2;
          telNum = '';
          ttId = item.ttId;
          svcMainClsCode = item.svcMainClsCode;
          sndNm = userInfoData.userName;   
          sndTeam = userInfoData.deptName;
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
        } 
        // 알림톡 발송이력
        else {
          custName = item.customer.custNm;
          telNum = item.customer.custTelNo;
          ttId = item.alarmTalkId.ttId;
          svcMainClsCode = item.svcMainClsCode;                
          sndNm = userInfoData.userName;   
          sndTeam = userInfoData.deptName;
        }                
        
        // 국가지역번호 처리        
        if(telNum.indexOf("82") == 0){          
          telNum = "0" + telNum.substr(2, telNum.length);
        }                 
        this.oriCustNm = custName;
        this.oriCustTelNo = telNum;
        
        // 고객명 전화번호 마스킹 처리
        document.getElementById("targetName").value = StringUtil.cusNmFormatter(custName);
        document.getElementById("targetNum").value = StringUtil.phoneFormatter(telNum);        
        
        this.ttId = ttId;
        this.svcMainClsCode = svcMainClsCode;
        this.sndNm = sndNm;
        this.sndTeam = sndTeam;        

      },
      closeLayerPopup(){
        document.getElementById("targetName").disabled = true;
        document.getElementById("targetNum").disabled = true;
        this.onLayer = false;
      },
      // 상담톡 화면에서 전송되어야함
      sendAlarmTalk(){

        // 유효성 검사 임시
        let targetName = document.getElementById("targetName");
        let targetNum = document.getElementById("targetNum");
        
        if(targetName.value == ""){
          this.errorTxt = "고객명은 필수 입력값입니다.";
          targetName.focus();
          return false;
        }
        if(targetNum.value == ""){
          this.errorTxt = "전화번호는 필수 입력값입니다.";
          targetNum.focus();
          return false;
        }        

        if(targetName.value.indexOf("*") > -1){
          this.custNm = this.oriCustNm;
        } else {
          this.custNm = targetName.value;
        }
        if(targetNum.value.indexOf("*") > -1){
          this.custTelNo = this.oriCustTelNo;
        } else {
          this.custTelNo = targetNum.value;
        }

        // 발송정보 스토리지 정보 셋팅
        this.setStorageInfo();
        this.$router.push({path:'/consult'});
      },
      setMenuOnToggle(num){
        this.tabNum = num;
      },
      inputModify(target){
        document.getElementById(target).disabled = false;
      },
      // 발송정보 스토리지 정보 셋팅
      setStorageInfo(){
        this.consultTarget.currTtID = this.ttId;
        this.consultTarget.svcMainClsCode = this.svcMainClsCode;
        this.consultTarget.sndNm = this.sndNm;
        this.consultTarget.sndTeam = this.sndTeam;
        this.consultTarget.targetName = this.custNm;
        this.consultTarget.targetNum = this.custTelNo;
        this.consultTarget.alarmSendCheck = 1;
        /* #TODEL
        localStorage.setItem('ttId', this.ttId);
        localStorage.setItem('svcMainClsCode', this.svcMainClsCode);
        localStorage.setItem('sndNm', this.sndNm);
        localStorage.setItem('sndTeam', this.sndTeam);
        localStorage.setItem('targetName', this.custNm);
        localStorage.setItem('targetNum', this.custTelNo);
        */
      },
      // 발송정보 스토리지 정보 삭제
      removeStorageInfo(){
        this.consultTarget.currTtID = "";
        this.consultTarget.svcMainClsCode = "";
        this.consultTarget.sndNm = "";
        this.consultTarget.sndTeam = "";
        this.consultTarget.targetName = "";
        this.consultTarget.targetNum = "";
        /* #TODEL
        localStorage.removeItem("ttId");
        localStorage.removeItem("svcMainClsCode");
        localStorage.removeItem("sndNm");
        localStorage.removeItem("sndTeam");
        localStorage.removeItem("targetName");
        localStorage.removeItem("targetNum");
        */
        // token 확인 진행
        this.getRenewalToken();
      },
      getRenewalToken() {
        // 토큰 갱신 확인 후 로직 진행
        this.$store.dispatch("auth/renewalToken").then(() => {     
          if(!this.renewalToken.data){
            alert("로그인이 만료되었습니다. 다시 로그인해주세요.");
            // localStorage 초기화
            localStorage.clear();
            // logout 호출
            this.$store.dispatch("auth/signout").then(() => {    
              //23.10 로그아웃 or 페이지 종료시 로컬스토리지 초기화       
              //this.$store.state.select.values = [];
              
              // 로그아웃 성공              
              this.$router.push({path:'/login'});
            })
            .catch(error => {
              // 로그아웃 호출 실패. 로그인 페이지로 이동
              console.log(error);
              this.$router.push({path:'/login'});
            });
          }
        })
        
      },               
    },
    mounted(){
      //발송정보 스토리지 정보 삭제
      this.removeStorageInfo();
    }
  }
</script>