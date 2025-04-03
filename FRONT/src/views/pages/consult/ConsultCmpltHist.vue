<template>
  <!-- group : on-off 가능하게 구현되어야 함 -->
  <div class="group_item">
    <!-- box : 지정 가능하게 구현되어야 함 -->
    <h1>고장이력 정보</h1>
    <div :class="0 >= nullTableDate ? 'table_box oy_s' : 'table_box oy_s ov_h'" id="CmpltHistBox">
      <table class="table_st01">
        <!--  스크롤 테이블 구현 필요 -->
        <thead>
          <tr>                
            <th>상품</th>
            <th>수리완료일시</th>
            <th>출동여부</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, idx) in histList" :key="idx" @click="openConsultCmpltHistPopup(item)" class="tr_st01">
            <td>{{item.svcMainTypeCodeDesc}}</td>
            <td>{{StringUtil().dateFormatter1(item.workFinishDatetime)}}</td>            
            <td>{{item.outdoorTypeCodeDesc}}</td>                
          </tr>
          <tr v-for="index in nullTableDate" :key="index">
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>                
          </tr>
        </tbody>
      </table>
    </div>
    <!--// box -->
  </div>
  <!--// group -->
</template>


<script>
  import StringUtil from '../../../utils/StringUtil';
  export default {
    name: 'ConsultCmpltHist',
    data() {
      return {
        nullTableDate : 6
      }
    },
    created() {

    },
    computed: {
      //----------------------------------
      // 고장이력 정보
      //----------------------------------
      histList(){
        return this.$store.state.trouble.histList;
      },
      consultTarget() {
      // 로그인 사용자 정보
        return this.$store.state.consult.consultTarget;
      },
    },
    methods: {
      //--------------------------------
      // 고장이력정보 조회 
      //--------------------------------
      getHistList() {
        this.ttId = this.consultTarget.currTtID;//localStorage.getItem('ttId');        
        this.$store.dispatch("trouble/histList", this.ttId).then(() => {        
          //console.log("고장이력정보 RES::::::",this.histList);
          this.nullTableDate = 6 - this.histList.length;
          this.nullTableDate = this.nullTableDate < 0 ? 0 : this.nullTableDate;
          this.$forceUpdate();
        })
        .catch(error => {
          console.log(error);
        });


      },
      // 고장이력정보 상세 팝업 오픈
      openConsultCmpltHistPopup(item){
        let options = "top=10, width=500, height=505, status=no, menubar=no, toolbar=no, resizable=no";
        window.open("/popup/ConsultCmpltHistPopup?ttId=" + item.ttId + "&lscn=" + item.lscn, "ConsultCmpltHistPopup", options);
      },
      StringUtil(){
        var obj = StringUtil;
        return obj;        
      }
    }
  }
</script>