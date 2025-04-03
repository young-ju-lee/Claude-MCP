<template>
  <!-- consult -->
  <div class="consult">
    <!-- consult_head : 상담중인 고객 리스트 -->
    <div class="consult_head">
      <dl>
        <dt>상담중인 고객 리스트</dt>
        <dd>
          <ul>
            <li v-for="(item, idx) in consultTarget.consultArray" 
              :key="idx" 
              :id="'consult_id_' + item.id"
              :class="item.id == consultTarget.currTargetIdx ? 'curr' : ''" 
              @click="item.id != consultTarget.currTargetIdx ? getConsoltContent(item, idx) : ''" 
            >
              <span :class="'bg_st_' + item.cnvrst_sttus" :title="item.consult_id">
                {{ item.cnvrst_sttus == 'S' ? '상담보류' : (item.cnvrst_sttus == 'A' ? '진행중' : '대기중')}}
              </span>
              {{item.cust_nm != null ? StringUtil().cusNmFormatter(item.cust_nm) : '이름없음' }}
            </li>
          </ul>
        </dd>
      </dl>
    </div>
    <!--// consult_head -->
    <!-- Left Group -->
    <div class="group" style="position: relative;">
      <!-- ConsultNetDiagram : 댁내 망구성도 -->
      <consult-net-diagram ref="ConsultNetDigram" />
      <!--// ConsultNetDiagram -->
      <!-- ConsultGuidelines : AI 가이드라인 -->
      <consult-guidelines @showImg="showImg" ref="ConsultGuidelines" />
      <!--// ConsultGuidelines -->
    </div>
    <!--// Left Group -->

    <!-- Center Group -->
    <div class="group">
      <!-- ConsultChat : 메인 채팅창 : #TODO : 탭 구성하고 반복문 돌려야 될 듯-->
      <consult-chat ref="ConsultChat" @getConsulting="getConsulting" @showImg="showImg"  />
      <!--// ConsultChat -->
    </div>
    <!--// Center Group -->

    <!-- Right Group -->
    <div class="group">
      <!-- ConsultAccptInfo : 고장접수 정보 -->
      <consult-accpt-info ref="ConsultAccptInfo" />
      <!--// ConsultAccptInfo -->
      <!-- ConsultCmpltHist : 고장이력 정보 -->
      <consult-cmplt-hist ref="ConsultCmpltHist" />
      <!--// ConsultCmpltHist -->
      <!-- ConsultHist : 비대면 응대이력 -->
      <consult-hist ref="ConsultHist"/>
      <!--// ConsultHist -->
    </div>
    <!--// Right Group -->
  </div>
  <!--// consult -->
  <!-- layer : layer ccomponents 호출 영역 -->
  <div class="img_layer" v-if="showLayer">
    <consult-send-img-layer @closeImg="closeImg" @sendImg="sendImg" :childValue="sendImgObj"/>
  </div>
  <!--// layer -->
  
  
</template>

<script>
  import ConsultNetDiagram from './ConsultNetDiagram'; // 댁내 망구성도
  import ConsultGuidelines from './ConsultGuidelines'; // AI 가이드라인
  import ConsultChat from './ConsultChat'; // 메인 채팅창
  import ConsultAccptInfo from './ConsultAccptInfo'; // 고장접수 정보
  import ConsultCmpltHist from './ConsultCmpltHist'; // 고장이력 정보
  import ConsultHist from './ConsultHist'; // 비대면 응대이력
  // ** Layer ** //
  import ConsultSendImgLayer from './layer/ConsultSendImgLayer'; // AI 가이드라인 이미지 Layer
  import StringUtil from '../../../utils/StringUtil';
  //import ConsultGuideTextPopup from './popup/ConsultGuideTextPopup' ; //상세가이드 팝업

  export default {
    name: 'App',
    components: {
      ConsultNetDiagram, // 댁내 망구성도
      ConsultGuidelines, // AI 가이드라인
      ConsultChat, // 메인 채팅창
      ConsultAccptInfo, // 고장접수 정보
      ConsultCmpltHist, // 고장이력 정보
      ConsultHist, // 비대면 응대이력
      ConsultSendImgLayer, 
      // AI 가이드라인 이미지 호출
     // ConsultGuideTextPopup //상세 가이드 팝업
    },
    data() {
      return {
        showLayer : false,
        sendImgObj : {
          imgUrl : "",
          imgType : ""
        },
        consultingList : [],
        checkItemId : ""
      }
    },
    created() {
    },
    computed: {
      roomInfo() {
        return this.$store.state.alarm.roomInfo;
      },
      userInfo() {
      // 로그인 사용자 정보
        return this.$store.state.auth.userInfo;
      },
      consulting() {
        return this.$store.state.consult.consulting
      },
      consultTarget() {
      // 로그인 사용자 정보
        return this.$store.state.consult.consultTarget;
      },
      consultHist(){
        return this.$store.state.consult.consultHist;
      },
      renewalToken(){
        return this.$store.state.auth.renewalToken;
      }
    },
    methods: {
      showImg(imgUrl, imgType){
        this.sendImgObj.imgUrl = imgUrl;
        this.sendImgObj.imgType = imgType;
        this.showLayer = true;
      },
      closeImg(){
        this.showLayer = false; 
      },
      sendImg(imgType2){
        console.log(this.sendImgObj.imgUrl);
        this.$refs.ConsultChat.sendImg(this.sendImgObj.imgUrl, this.sendImgObj.imgType, imgType2);
        this.closeImg();
      },
      getConsulting() {
        // 토큰 갱신 확인 후 로직 진행
        this.$store.dispatch("auth/renewalToken").then(() => {     
          if(this.renewalToken.data){
            // this.consulting init
            this.consulting = []
            // TTID 조회
            let item = this.userInfo.loginId;
            this.$store.dispatch("consult/consulting", item).then(() => {
              // 초기화
              // 알림톡 전송 (로직 선 처리)
              let targetName = this.consultTarget.targetName;
              if(targetName != null && targetName != ''){
                this.sendAlarmTalk();
              }else{
                this.setgetConsultingList('setting');
              }
            })
            .catch(error => {
              console.log(error);
            });
          }else{
            alert("로그인이 만료되었습니다. 다시 로그인해주세요.");
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
          }
        })
    
      },
      setgetConsultingList(type){
        if(this.consulting.length == 0){
          alert("진행중인 상담이 없습니다.");
        }
        // 기준값
        //let showDataListNum = 10;
        // 기준값 리스트값 비교 후 신규 배열 생성
        //let currConsultCnt = (showDataListNum < this.consulting.length ? showDataListNum : this.consulting.length);
        for(let i = 0; i < this.consulting.length; i++){
          this.consultTarget.consultArray.push(this.consulting[i]);
          //this.consultTarget.socketArray[i] = [];
          this.consultTarget.reiveMessgeArray[i] = [];
          this.consultTarget.consultArray[i].histArray = [];
          if(type == 'alarm' && i == 0){
            this.consultTarget.currTargetIdx = 0;
            this.$refs.ConsultChat.connect(this.consulting[i], i);
          }else{
            this.$refs.ConsultChat.reConnect(this.consulting[i], i);
          }
        }

        
        if(this.consultTarget.currTargetIdx == ""){
          this.consultTarget.currTargetIdx = this.consultTarget.consultArray[0].id;
          this.getConsoltContent(this.consultTarget.consultArray[0], 0);
          this.$refs.ConsultGuidelines.openConsultGuideTextPopup();
        }
        this.$forceUpdate();
        console.log(this.consultTarget);
        // this.$refs.ConsultChat.reConnect(item);
        // 신규 배열 생성되면 생성된 수 만큼 소캣 연결
      },
      getConsoltContent(item, idx){
        // 현재 상담 세팅
        this.consultTarget.currTargetIdx = item.id;
        this.consultTarget.currConsultId = item.consult_id;
        this.consultTarget.currUserKey = item.user_key;
        this.consultTarget.currTtID = item.tt_id;
        this.consultTarget.targetNum = item.tel_num;
        this.consultTarget.cnvrst_sttus = item.cnvrst_sttus;

        // histArray가 없을 경우 histArray 세팅
        if(this.consultTarget.consultArray[idx].histArray == undefined){
          this.consultTarget.consultArray[idx].histArray = [];
        }
        // histArray에 내용이 없고 대기중이 아닐시 consultHist 조회
        if(this.consultTarget.consultArray[idx].histArray.length == 0 && item.cnvrst_sttus != "W"){
          let payload = {
            id : item.id,
            consult_id : item.consult_id,
          }
          this.$store.dispatch("consult/consultHist", payload).then(() => {
            // console.log(this.consultHist);
            this.consultTarget.consultArray[idx].histArray = this.consultHist;
            this.$forceUpdate();
            //this.$refs.ConsultChat.reConnect(item);
            //d84e77c4ee0a4e779e4055dcc959d579
          })
          .catch(error => {
            console.log(error);
          });
        }

        this.$refs.ConsultNetDigram.getHdmsDgrmList();
        this.$refs.ConsultGuidelines.getEgl();
        this.$refs.ConsultAccptInfo.getAccptInfo();
        this.$refs.ConsultCmpltHist.getHistList();
        this.$refs.ConsultHist.getConsultHistList();

      },

      sendAlarmTalk(){
        // 알림톡 전달
        if(this.consultTarget.alarmSendCheck == 0){
          this.setgetConsultingList('setting');
          return;
        }
        // 스토리지 저장 방식 다 바꿔야함
        let ttId = this.consultTarget.currTtID; // localStorage.getItem('ttId');
        let svcMainClsCode = this.consultTarget.svcMainClsCode; // localStorage.getItem('svcMainClsCode'); 
        let sndNm = this.consultTarget.sndNm; // localStorage.getItem('sndNm'); 
        let sndTeam = this.consultTarget.sndTeam; // localStorage.getItem('sndTeam');
        let targetNum = this.consultTarget.targetNum;
        let targetName = this.consultTarget.targetName;

        if(this.consulting.length >= 10){
          alert("최대 10명까지 상담 진행이 가능합니다.");
          this.consultTarget.alarmSendCheck = 0;
          this.setgetConsultingList('setting');
          return;
        }

        if(targetNum != "" && targetNum != null){
          if(confirm( targetNum + "로 알림톡을 발송합니다.")){
            let custNum = targetNum;
            if(targetNum.startsWith("0")) {
                targetNum = "82" + targetNum.substring(1);
            }
            // - 제거
            targetNum = targetNum.replace(/-/g, '');

            for(let i in this.consulting){
              if(this.consulting[i].tel_num == targetNum){
                this.consultTarget.alarmSendCheck = 0;
                alert(custNum + "으로 이미 상담이 진행중입니다.");
                this.setgetConsultingList('setting');
                return;
              }
            }
            // let payload = {
            //   "to": num
            // };
            //20230309 정민규 수정                 
            let payload = {
              "trouble": {
                "tt_id": ttId,
                "svc_main_cls_code" : svcMainClsCode
              },
              "sender" : {
                "snd_nm" : sndNm,
                "snd_team" : sndTeam,
                "snd_cmp_no" : this.userInfo.loginId
              },
              "customer" : {
                "cust_nm" : targetName,
                "cust_tel_no" : targetNum
              }
            } 
            console.log("알림톡 발송 파람::",payload);
            this.$store.dispatch("alarm/sendAlarmTalk", payload).then(() => {            
              // 알림톡 -> 전환 관련 처리
              this.consultTarget.alarmSendCheck = 0;
              console.log(this.roomInfo);
              let timeElapsed = Date.now();
              let today = new Date(timeElapsed);
              // 상담중인 고객 리스트 추가
              let newConsult = {
                cnvrst_sttus : 'W',
                consult_datetime : today.toUTCString(),
                consult_id : this.roomInfo.consult_id,
                cust_nm : targetName,
                tel_num : targetNum,
                tt_id : ttId,
                id : 0
              }
              this.consulting.unshift(newConsult);
              this.setgetConsultingList('alarm');
              
            })
            .catch(error => {
              alert("알림톡이 발송실패. - 서버 응답 없음");
              console.log(error);
            });
          }else{
            this.consultTarget.alarmSendCheck = 0;
            this.setgetConsultingList('setting');
          }
        }
      },
      StringUtil(){
        var obj = StringUtil;
        return obj;        
      }
    },
    mounted(){ 
      this.getConsulting();
    }
  }
</script>