
<template>
  <div class="popup">
    <div>
      <div class="title_box">
        <h1 class="cmplitHist">고장이력 정보 상세</h1>
      </div>
    </div>
    <div class="popup_box st01 oy_s">
      <table class="table_st02">
        <colgroup>
          <col style="width:33.3%">
          <col style="width:33.3%">
          <col>
        </colgroup>
        <thead>
          <tr>
            <th>상품</th>
            <th>출동여부</th>
            <th>수리완료일시</th>
          </tr>
        </thead>
        <tbody>
          <tr>	
            <td>
              <span :class="'type_product ' + (cmpltHistInfo.svcMainTypeCodeDesc == 'TV' ? 'st01' : 'st02')">{{cmpltHistInfo.svcMainTypeCodeDesc}}</span>
            </td>
            <td>
              <span :class="'type_na_a ' + (cmpltHistInfo.outdoorTypeCodeDesc == '출동' ? 'st01' : 'st02')">{{cmpltHistInfo.outdoorTypeCodeDesc}}</span>
            </td>
            <td>{{StringUtil().dateFormatter1(cmpltHistInfo.workFinishDatetime)}}</td>
          </tr>
        </tbody>
      </table>
      <div class="opinion_box">
        <dl>
        <dt>현장의견</dt>
        <dd>{{cmpltHistInfo.workContent}}</dd>
        </dl>
      </div>
      <div class="detail_box">
        <dl>
          <dt>조치내용</dt>
          <dd>
            <dl>
              <dt>수리 내용</dt>
              <dd>{{cmpltHistInfo.asFixMainClsCodeDesc}} - {{cmpltHistInfo.asFixSubClsCodeDesc}} - {{cmpltHistInfo.repairCodeDesc}}</dd>
            </dl>
            <dl>
              <dt>원인</dt>
              <dd>{{cmpltHistInfo.asCauseMainClsCodeDesc}} - {{cmpltHistInfo.asCauseSubClsCodeDesc}} - {{cmpltHistInfo.troubCauseDesc}}</dd>
            </dl>
            <dl>
              <dt>시설</dt>
              <dd>{{cmpltHistInfo.asEquipMainClsCodeDesc}} - {{cmpltHistInfo.asEquipSubClsCodeDesc}} - {{cmpltHistInfo.troubFacilityDesc}}</dd>
            </dl>			
          </dd>
        </dl>
      </div>
    </div>
    <div class="popup_close st01">
      <button @click="popupClose">닫기</button>
    </div>
  </div>        
</template>

<script>
  import StringUtil from '../../../../utils/StringUtil';
  export default {
    name: 'ConsultCmpltHist',
    data() {
      return {
      }
    },
    created() {

    },
    computed: {
      cmpltHistInfo(){
        return this.$store.state.trouble.cmpltHistInfo;
      }
    },
    methods: {
      popupClose(){
        window.close();
      },
      getCmpltHistInfo(){
        let item = {
          ttId : this.$route.query.ttId,
          lscn : this.$route.query.lscn,
        }
        console.log("item #################################");
        console.log(item);
        this.$store.dispatch("trouble/cmpltHistInfo", item).then(() => {
          console.log(this.cmpltHistInfo);
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
      this.getCmpltHistInfo();
    }
  }
</script>