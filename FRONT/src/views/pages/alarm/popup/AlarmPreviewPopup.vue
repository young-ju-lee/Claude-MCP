<template>
  <!-- consult -->
  <div class="popup" style="background:none;">
    <div>
      <div class="title_box">
        <h1 class="cmplitHist">{{ this.custNm }} 고객</h1>
      </div>
    </div>
    <div class="consult consult_popup oy_s">
      <!-- Left Group -->
      <div class="group" style="position: relative;">
        <!-- ConsultNetDiagram : 댁내 망구성도 -->
        <consult-net-diagram ref="ConsultNetDigram" />
        <!--// ConsultNetDiagram -->
        <!-- ConsultGuidelines : AI 가이드라인 -->
        <consult-guidelines ref="ConsultGuidelines" />
        <!--// ConsultGuidelines -->
      </div>
      <!--// Left Group -->

      <!-- Right Group -->
      <div class="group">
        <!-- ConsultAccptInfo : 고장접수 정보 -->
        <consult-accpt-info ref="ConsultAccptInfo" @searchLink="searchLink"  />
        <!--// ConsultAccptInfo -->
        <!-- ConsultCmpltHist : 고장이력 정보 -->
        <consult-cmplt-hist ref="ConsultCmpltHist" />
        <!--// ConsultCmpltHist -->
        <!-- ConsultHist : 비대면 응대이력 -->
        <consult-hist ref="ConsultHist" />
        <!--// ConsultHist -->
      </div>
      <!--// Right Group -->
    </div>
    <!--// consult -->
    <div class="popup_close st01">
    <button @click="popupClose">닫기</button>
  </div>
</div>
</template>

<script>
  import ConsultNetDiagram from '../../consult/ConsultNetDiagram'; // 댁내 망구성도
  import ConsultGuidelines from '../../consult/ConsultGuidelines'; // AI 가이드라인
  import ConsultAccptInfo from '../../consult/ConsultAccptInfo'; // 고장접수 정보
  import ConsultCmpltHist from '../../consult/ConsultCmpltHist'; // 고장이력 정보
  import ConsultHist from '../../consult/ConsultHist'; // 비대면 응대이력

  import StringUtil from '../../../../utils/StringUtil';

  export default {
    name: 'App',
    components: {
      ConsultNetDiagram, // 댁내 망구성도
      ConsultGuidelines, // AI 가이드라인
      ConsultAccptInfo, // 고장접수 정보
      ConsultCmpltHist, // 고장이력 정보
      ConsultHist, // 비대면 응대이력
    },
    data() {
      return {
        custNm : ""
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
      searchLink(){
        // 전화번호 세팅 후 조회 필요
        this.$refs.ConsultCmpltHist.getHistList();
        this.custNm = this.consultTarget.targetName;
      },
      StringUtil(){
        var obj = StringUtil;
        return obj;        
      },
      popupClose(){
        window.close();
      }
    },
    mounted(){ 
      this.consultTarget.currTtID = this.$route.query.ttId;
      this.consultTarget.popupYn = "Y";
      // ttId로 조회 가능한 것들
      this.$refs.ConsultNetDigram.getHdmsDgrmList();
      this.$refs.ConsultGuidelines.getEgl();
      this.$refs.ConsultAccptInfo.getAccptInfo();
      this.$refs.ConsultHist.getConsultHistList();
    }
  }
</script>