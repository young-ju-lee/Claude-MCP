<template>
  <div class="group_item">
    <!-- box : 지정 가능하게 구현되어야 함 -->
    <h1>비대면 응대이력</h1>
    <div :class="0 >= nullTableDate ? 'table_box oy_s' : 'table_box oy_s ov_h'">
      <table class="table_st01">
        <colgroup>
          <col style="width:50%">
        </colgroup>
        <thead>
          <tr>
            <th>일시</th>
            <th>상담 결과</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, idx) in consultHistList" :key="idx" @click="openConsultHistPopup(item)" class="tr_st01">
            <td>{{StringUtil().dateFormatter1(item.consult_datetime)}}</td>              
            <td>{{ item.go_yn == 'Y' ? "출동 처리 완료" : (item.go_yn == 'N' ? "무출동 처리 완료" : (item.cnvrst_sttus == 'D' ? '상담완료' : '') )}}</td>
          </tr>
          <tr v-for="index in nullTableDate" :key="index">
            <td>&nbsp;</td>
            <td>&nbsp;</td>                
          </tr>
        </tbody>
      </table>
    </div>
    <!--// box -->
  </div>
</template>


<script>
  import StringUtil from '../../../utils/StringUtil';

  export default {
    name: 'ConsultHist',
    data() {
      return {
        selectedId : "",
        selectedConsultId : "",
        nullTableDate : 6
      }
    },
    created() {
     
    },
    computed: {
      consultHistList(){
        return this.$store.state.consult.consultHistList;
      },
      consultTarget() {
      // 로그인 사용자 정보
        return this.$store.state.consult.consultTarget;
      },
    },
    methods: {
      // 비대면 응대이력 조회
      getConsultHistList(){
        if(this.consultTarget.targetNum != ''){
          let num = this.consultTarget.targetNum;//localStorage.getItem('targetNum');
          if(num.startsWith("0")) {
              num = "82" + num.substring(1);
          }
          this.$store.dispatch("consult/consultHistList", num).then(() => {
            console.log(this.consultHistList);
            this.nullTableDate = 6 - this.consultHistList.length;
            this.nullTableDate = this.nullTableDate < 0 ? 0 : this.nullTableDate;
            this.$forceUpdate();
          })
          .catch(error => {
            console.log(error);
          });
        }
      },
      openConsultHistPopup(item){
        let options = "top=10, width=500, height=540, status=no, menubar=no, toolbar=no, resizable=no";
        window.open("/popup/ConsultHistPopup?id=" + item.id + "&consult_id=" + item.consult_id + "&cstt_nm=" + item.cstt_nm + "&cust_nm=" + item.cust_nm + "&tel_num=" + item.tel_num + "&consult_datetime=" + item.consult_datetime + "&go_reason=" + item.go_reason, "ConsultHistPopup", options);
      },
      StringUtil(){
        var obj = StringUtil;
        return obj;        
      }
    }
  }
</script>