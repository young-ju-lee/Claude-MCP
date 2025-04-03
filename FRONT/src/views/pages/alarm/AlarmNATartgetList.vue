<template>
<div class="search_box">
  <ul class="search_st01">
    <li>
      <label for="officesCode">국사</label>
      <div class="multiselect_box">
         <multiselect id="officesCode" ref="getData" :change="selectedOfficeCode(this.$store.state.alarm.values)" v-model="this.$store.state.alarm.values" tag-placeholder="aaa" placeholder="전체" label="name" track-by="code" :options="options" noResultText="전체" noOptionsText="" :multiple="true" :taggable="false">          
         </multiselect>
      </div>
    </li>
    <li>
      <dl>
        <dt>상품</dt>
        <dd><input type="checkbox" id="chk2" name="product" value="3000" @click="productChk();"> <label for="chk2">인터넷</label></dd>
        <dd><input type="checkbox" id="chk3" name="product" value="IPM0" @click="productChk();"> <label for="chk3">TV</label></dd>
      </dl>
    </li>
    <li>
      <label for="custName">고객명</label>
      <input type="text" id="custName" @keyup.enter="getNonMoveTgtList(0)">
    </li>
    <li>
      <label for="svcId">접속번호</label>
      <input type="text" id="svcId" @keyup.enter="getNonMoveTgtList(0)">
    </li>
    <!-- <li>
      <label for="troubReceiptDt">접수일</label>
      <input type="date" id="troubReceiptDt">
    </li> -->
    <li>
      <label for="rsvDt">희망일</label>
      <input type="date" id="rsvFromDt" @change="chkDatealt('rfd');"> ~ <input type="date" id="rsvToDt" @change="chkDatealt('rtd');">
    </li>
    <li>
      <button @click="getNonMoveTgtList(0)">조회</button>
    </li>
  </ul>
</div>
<div class="list_box" style="position: relative;">
  <p>전체건수 : <span id="totalCnt">0</span>건</p>    
  <table>
    <colgroup>
      <col style="width:6%">
      <col style="width:8%">
      <col style="width:8%">
      <col style="width:10%">
      <col style="width:10%">
      <col style="width:10%">
      <col style="width:10%">
      <col style="width:8%">
      <col style="width:calc(30% - 360px)">
      <col style="width:180px">
      <col style="width:180px">
    </colgroup>
    <thead>
      <tr>
        <th>조치여부</th>
        <th>고객명</th>
        <th>상품명</th>
        <th>접속번호</th>
        <th>연락전화번호1</th>
        <th>연락전화번호2</th>
        <!-- <th>접수일시</th> -->
        <th>희망일</th>
        <th>국사</th>
        <th>신고내용</th>
        <th>예약일시</th>
        <th></th>
      </tr>
    </thead>
    <tbody>      
      <!-- no-data-area -->
      <tr v-if="nonMoveTgtList.totalElements == 0">
        <td class="no_data" colspan="11">조회 결과가 없습니다.</td>
      </tr>      
      <tr v-else v-for="(item, idx) in nonMoveTgtList.content" :key="idx" @click="openAlarmPreviewPopup(item)"> 
        <td>{{item.cnvrstSttusTxt}}</td>       
        <td>{{StringUtil().cusNmFormatter(item.custName)}}</td>                  
        <td>{{item.svcMainTypeCodeDesc}}</td>                          
        <td>{{StringUtil().phoneFormatter(item.svcId)}}</td>
        <td>{{StringUtil().phoneFormatter(item.reporterTelNum1)}}</td>
        <td>{{StringUtil().phoneFormatter(item.reporterTelNum2)}}</td>
        <!-- <td>{{item.troubReceiptDt}}</td> -->
        <td>{{StringUtil().dateFormatter(item.rsvDt, item.rsvTime)}}</td>
        <td>{{item.officesCodeDesc}}</td>
        <td :title="item.reporterOpinion">{{item.reporterOpinion}}</td>
        <td><div class="rsv_date" @click.stop="" @click="showReservation(item)">{{item.reservationTime == null ? "예약일시 등록" : rsvStr(item.reservationTime) }}</div></td> 
        <td><div @click.stop="" @click="this.$emit('openLayerPop', this.nonMoveTgtList.content[idx])"><span></span>알림톡 발송</div></td>
      </tr>
      
    </tbody>
  </table>
  <ul class="paging">
    <li class="left_page" v-show="prevBlock" @click="getPrevBlock()"></li>
    <li class="left_num" v-show="prevPage" @click="getNonMoveTgtList(this.pageNumber-1)"></li>    
    <li :class="{'on': idx === this.pageNumber}"                 
      v-show="( idx >= (this.blockPageNumber) * this.blockPerPageSize && idx < (this.blockPageNumber+1) * this.blockPerPageSize )" 
      v-for="(item, idx) in nonMoveTgtList.totalPages" :key="idx" @click="getNonMoveTgtList(idx)">{{idx+1}}</li>                            
    <li class="right_num" v-show="nextPage" @click="getNonMoveTgtList(this.pageNumber+1)"></li>
    <li class="right_page" v-show="nextBlock" @click="getNextBlock()"></li>
  </ul>
  <loading-area v-show="loading"/>
</div>
<div class="layer_popup" v-show="show_reservation">
  <div class="reservation_box">
    <h1>예약일시 입력</h1>
    <div class="layer_box">
      <dl class="dl_st_01">
        <dt><label for="user_id">예약일</label></dt>
        <dd>
          <input type="date" class="input_st_01" id="reservation_date" placeholder="예약일" value="이준영">
        </dd>
      </dl>
      <dl>
        <dt><label for="user_pw">예약 시간</label></dt>
        <dd>
          <select class="reservation_select" id="reservation_time">
            <option v-for="(item, idx) in reservationDate" :key="idx" :value="item.value">{{item.value}}</option>
          </select>
        </dd>
      </dl>
      <button @click="setConsultReservation">저장</button>
      <p class="error_txt">{{errorTxt}}</p>
    </div>
    <p class="layer_close" @click="closeReservation"></p>
  </div>
</div>
</template>

<script>
  import StringUtil from '../../../utils/StringUtil';
  import LoadingArea from '@/components/common/layer/LoadingArea'; // 로딩 레이어
  import Multiselect from 'vue-multiselect';
  import Vuex from 'vuex'

  const {mapActions, mapState} =Vuex;

  let officesCodeArray = new Array(); 

  export default {
    name: 'AlarmNATartgetList',
    emits : ["openLayerPop"],
    components: {
      LoadingArea,
      Multiselect
    },
    data() {
      return {
        //------------------------------------------------------------
        // 페이지 처리 
        //------------------------------------------------------------
        pageNumber : 0,           //페이지 번호
        recordSize : 10,          //한페이지당 보여질 리스트 갯수
        prevPage : false,
        nextPage : false,
        //------------------------------------------------------------
        // 페이지 블럭 처리 
        //------------------------------------------------------------
        blockPerPageSize : 10,    //블럭당 보여질 페이지 갯수
        blockPageNumber : 0,      //블럭 페이지번호
        blockPageCnt : 0,         //페이지 블럭 갯수
        prevBlock : false,
        nextBlock : false,
        loading : false,
        value : [
          // {name : '방배국사', code : 'gcode02'},
        ],
        options: [
          // {name : '과천국사', code : 'gcode03'},
          // {name : '목동국사', code : 'gcode04'},
          // {name : '태백국사', code : 'gcode05'},
          // {name : '화곡국사', code : 'gcode06'},
          // {name : '수내국사', code : 'gcode07'},
          // {name : '동대문국사', code : 'gcode08'},
          // {name : '을지로국사', code : 'gcode09'},
          // {name : '신림국사', code : 'gcode10'},
          // {name : '신도림국사', code : 'gcode11'},
        ],
        show_reservation : false,
        reservationDate : [
          {value:"09:00"},
          {value:"10:00"},
          {value:"11:00"},
          {value:"12:00"},
          {value:"13:00"},
          {value:"14:00"},
          {value:"15:00"},
          {value:"16:00"},
          {value:"17:00"},
        ],
        reservationTtId : "",
        errorTxt :"",
      }
    },    
    created() {

    },
    computed: {
      // AI 무출동 정보 조회    
      nonMoveTgtList(){      
        return this.$store.state.trouble.nonMoveTgtList;            
      },
      userInfo() {
      // 로그인 사용자 정보
        return this.$store.state.auth.userInfo;
      },
      ossInfo() {
      // 로그인 사용자 정보
        return this.$store.state.auth.ossInfo;
      },
      // 국사정보 조회
      officeCodeList(){
        return this.$store.state.trouble.officeCodeList;            
      },
      // 국사정보 조회 - store state values값 정보
      ...mapState(['values'])
    },
    methods: {
      
      //23년 10월 : store state values update action 
      ...mapActions(['updatevalueaction']),
      //--------------------------------
      // 국사정보 조회
      //--------------------------------
      getOfficeCodeList(){
        let param = {
          "userId" : this.userInfo.loginId  //사용자 ID
        };          
        this.$store.dispatch("trouble/officeCodeList", param).then(() => {           
          for(var i=0; i<this.officeCodeList.length; i++){
            var item = {};
            item.name = this.officeCodeList[i].officesCodeDesc;
            item.code = this.officeCodeList[i].officesCode;
            this.options.push(item);
          }          
          this.getNonMoveTgtList(0);          
        })
        .catch(error => {          
          console.log(error); 
        });
      },
      //--------------------------------
      // AI 무출동 대상조회
      //--------------------------------
      getNonMoveTgtList(pageNo) {
        
        if(pageNo < this.blockPageNumber * this.blockPerPageSize){
          this.getPrevBlock(pageNo);
          return;
        }
        if(pageNo == (this.blockPageNumber+1) * this.blockPerPageSize){
          this.getNextBlock(pageNo);
          return;
        }

        //----------------------------------------------------------------
        // Param Info
        //----------------------------------------------------------------
        let custName = document.getElementById("custName").value;   //고객명
        let svcId = document.getElementById("svcId").value;         //접속번호
        //★★★★★★★★★★★★★★ 추후 확인필요
        if(svcId != null && svcId != ''){          
          svcId = '0' + svcId;
          if(svcId.indexOf('02') == 1){
            svcId = '0' + svcId;
          }
        }        
        //let troubReceiptDt = document.getElementById("troubReceiptDt").value;   //접수일시    
        //let rsvDt = document.getElementById("rsvDt").value;                
        let rsvFromDt = document.getElementById("rsvFromDt").value;  
        let rsvToDt = document.getElementById("rsvToDt").value;  
        let svcMainClsCodeArray = this.productChk();          

        if(rsvFromDt == ''){
          alert("희망 시작일을 선택 하세요");
          //document.getElementById("rsvDt").focus();
          document.getElementById("rsvFromDt").focus();

          return;
        } 
        if(rsvToDt == ''){
          alert("희망 종료일을 선택 하세요");          
          document.getElementById("rsvToDt").focus();

          return;
        }
        rsvFromDt = rsvFromDt.replace(/-/g, '');                     
        rsvToDt = rsvToDt.replace(/-/g, '');      

        let ttId = "";
        // TTID 값이 URL에 포함될 경우
        if(this.chkNull(this.ossInfo.TTID)){
          //document.getElementById("rsvDt").value = ""
          ttId = this.ossInfo.TTID
          //rsvDt = '';
        }

        let param = {
          "pageNo" : pageNo,
          "recordSize" : this.recordSize,        
          "svcMainClsCode" : svcMainClsCodeArray,
          "custName" : custName,
          "svcId" : svcId,
          "ttId" : ttId,
          //"troubReceiptDt" : troubReceiptDt,
          //"rsvDt" : rsvDt,
          "rsvFromDt" : rsvFromDt,
          "rsvToDt" : rsvToDt,
          "userId" : this.userInfo.loginId,  //사용자 ID
          "officesCode" : officesCodeArray
        };

        this.loading = true;
        this.$store.dispatch("trouble/nonMoveTgtList", param).then(() => {               
          //----------------------------------------------------------------
          // 페이징 처리        
          //----------------------------------------------------------------
          let listObj = this.nonMoveTgtList;          
          this.pageNumber = listObj.pageable.pageNumber;  //페이지 번호
          let totalPages = listObj.totalPages;    //총페이지 수
          let totalCnt = listObj.totalElements; //총 건수          
          document.getElementById("totalCnt").innerHTML = totalCnt.toLocaleString();
          this.pagination(this.pageNumber, totalPages, totalCnt);                
          this.loading = false;
          if(this.chkNull(this.ossInfo.TTID)){
            if(this.nonMoveTgtList.content.length != 0){
              //document.getElementById("troubReceiptDt").value = this.nonMoveTgtList.content[0].troubReceiptDt.split(" ")[0]
              var rsvDt = this.nonMoveTgtList.content[0].rsvDt;
              var cnvRsvDt = rsvDt.substr(0,4) + "-" + rsvDt.substr(4,2) + "-" + rsvDt.substr(6,2);              
              //document.getElementById("rsvDt").value = cnvRsvDt;      
              document.getElementById("rsvFromDt").value = cnvRsvDt;
              document.getElementById("rsvToDt").value = cnvRsvDt;        
              this.$emit('openLayerPop', this.nonMoveTgtList.content[0]);
              this.ossInfo.TTID = "";
            }
          }
        })
        .catch(error => {
          this.loading = false;
          console.log(error); 
        });
      },    
      pagination(pageNumber, totalPages, totalCnt){
        //--------------------------------------
        // 페이지 네비게이션 처리
        //--------------------------------------        
        if(totalCnt == 0){
            this.prevPage = false;      
            this.nextPage = false;
        } else {
          if(pageNumber == 0){
            this.prevPage = false;                  
            if(totalPages == 1){
              this.nextPage = false;
            } else {
              this.nextPage = true;
            }
          } else {
            if(pageNumber == totalPages-1){          
              this.prevPage = true;      
              this.nextPage = false;
            } else {
              this.prevPage = true;     
              this.nextPage = true;       
            }
          }
        }
        
        //--------------------------------------
        // 페이지의 페이지 네비게이션 처리
        //--------------------------------------        
        let blockPageCnt = 0;
        let blockPerPageSize = this.blockPerPageSize;
        if(totalPages % blockPerPageSize == 0){
            blockPageCnt = totalPages / blockPerPageSize;
        } else {
          blockPageCnt = Math.floor((totalPages / blockPerPageSize)) + 1;
        }                
        this.blockPageCnt = blockPageCnt;            
        
        if(this.blockPageNumber == 0){
          this.prevBlock = false;
          if(this.blockPageCnt == 1){
            this.nextBlock = false;
          } else {
            if((this.blockPageNumber+1) <= this.blockPageCnt){
              this.nextBlock = true;
            }
          }          
        } else {
          this.prevBlock = true;          
          if((this.blockPageNumber+1) == this.blockPageCnt){
            this.nextBlock = false;
          } else {
            this.nextBlock = true;
          }
        }

      },
      getPrevBlock(pageNo){
        this.blockPageNumber = this.blockPageNumber - 1;
        let pageNumber = this.blockPageNumber * this.blockPerPageSize;
        if(pageNo != null){
          pageNumber = pageNo;
        }      
        this.getNonMoveTgtList(pageNumber);
      },
      getNextBlock(pageNo){      
        this.blockPageNumber = this.blockPageNumber + 1;      
        let pageNumber = this.blockPageNumber * this.blockPerPageSize;
        if(pageNo != null){
          pageNumber = pageNo;
        }      
        this.getNonMoveTgtList(pageNumber);
      },
      productChk(){      
        let productArray = new Array();      
        let arrProduct = document.getElementsByName("product");
        for(var i=0; i<arrProduct.length; i++){
          if(arrProduct[i].checked){            
              productArray.push(arrProduct[i].value);
          }
        }      
        return productArray;
      },       
      //접수일자 셋팅
      setReceiptDt(){
        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth()+1;
        var day   = now.getDate();

        if(month <= 9){
          month = "0" + month;
        }
        
        if(day <= 9){
          day = "0" + day;
        }      
        var today = year + "-" + month + "-" + day;        
        //document.getElementById("troubReceiptDt").value = today;            
        //document.getElementById("rsvDt").value = today;
        document.getElementById("rsvFromDt").value = today;
        document.getElementById("rsvToDt").value = StringUtil.lastDay(today.replace(/-/gi,''),5);        
        
      },
      //변경일자 기간 체크 
      chkDatealt(val){
        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth()+1;              
        var day   = now.getDate();        
        
        if(day < 9 ){
          day = "0"+day;  //이번달
        }        
        if(month < 9 ){
          month = "0"+month;
        }

        var today = year+"-"+month+"-"+day;  //이번달
        
        var prevyear, prevmonth, nxtyear, nxtmonth;          

        if(parseInt(month)-1 == 0 ){
          prevyear = year-1;
          prevmonth = 12;          
        } else {
          prevyear = year;
          prevmonth = (parseInt(month)-1);
          if(prevmonth <= 9 ){
            prevmonth = "0"+prevmonth;
          }
        }
        var dt1 = prevyear+"-"+prevmonth+"-"+day //이전달
        
        if(parseInt(month)+1 > 12){
          nxtyear = year+1;
          nxtmonth = "01";
        } else {
          nxtyear = year;
          nxtmonth = (parseInt(month)+1);
          if(nxtmonth <= 9 ){
            nxtmonth = "0"+nxtmonth;
          }
        }
        var dt2 = nxtyear+"-"+nxtmonth+"-"+day  //다음달 
                

        var sdt = document.getElementById("rsvFromDt").value.replace(/-/gi,''); //replaceAll정규식처리        

        if(val == 'rtd'){
          var edt = document.getElementById("rsvToDt").value.replace(/-/gi,''); //replaceAll정규식처리
          if(edt < sdt){
            alert("희망 시작일보다 이전일자를 선택할수 없습니다.");  
            document.getElementById("rsvToDt").value = StringUtil.lastDay(sdt.replace(/-/gi,''),5);
            return;      
          }

          if(edt > dt2.replace(/-/gi,'')){
            alert("희망 시작일( "+year+"-"+month+"-"+day+" ) 기준 1개월을 넘길수 없습니다.");              
            document.getElementById("rsvToDt").value = StringUtil.lastDay(sdt.replace(/-/gi,''),5);
            return;      
          }                    
        }

        if(val == 'rfd'){
          if(dt1.replace(/-/gi,'') > sdt || dt2.replace(/-/gi,'') < sdt ){
            alert("희망 시작일( "+year+"-"+month+"-"+day+" ) 기준 1개월을 넘길수 없습니다.");
            document.getElementById("rsvFromDt").value = year+"-"+month+"-"+day;          
            document.getElementById("rsvToDt").value = StringUtil.lastDay(today.replace(/-/gi,''),5);
            return;
          }

          document.getElementById("rsvToDt").value =  StringUtil.lastDay(sdt,5); //parseInt(sdt)+5;
        }
      },
      StringUtil(){
        var obj = StringUtil;
        return obj;        
      },      
      selectedOfficeCode(item){        

        officesCodeArray = new Array(); 
        for(var i =0; i<item.length; i++){
          officesCodeArray.push(item[i].code);         
        }               
        //----------- 23.10 추가 :: 선택한 국사값 스토리지에 저장 및 유지  -----------------        
        //1.
        if(JSON.stringify(this.$store.state.alarm.values)!='[]'){
          localStorage.setItem("values",JSON.stringify(this.$store.state.alarm.values));          
        }               
        
        //국사선택값 '전체'일경우 localstorage 초기화
        //console.log(this.$store.state.alarm.values);        
        if(document.getElementsByClassName('multiselect__tags').length > 0 ){
          if(this.$store.state.alarm.values.length  == 0){
            localStorage.clear();
          }
        }        
        //----------------------------------
      }      ,
      chkNull(val){
        if(val != null && val != undefined && val != ""){
          return true;
        }else{
          return false;
        }
      },
      openAlarmPreviewPopup(item){
        let options = "top=10, width=1200, height=950, status=no, menubar=no, toolbar=no, resizable=no";
        window.open("/popup/AlarmPreviewPopup?ttId=" + item.ttId, "AlarmPreviewPopup", options);
      },
      rsvStr(str){
        return str.split(" ")[0] + " " + str.split(" ")[1].substr(0, 5)
      },
      showReservation(item){
        this.reservationTtId = item.ttId;
        this.show_reservation= true;
        if(item.reservationTime != null){
          document.getElementById("reservation_date").value = item.reservationTime.split(" ")[0];
          document.getElementById("reservation_time").value = item.reservationTime.split(" ")[1].substr(0, 5)
        } else {
          var now = new Date();
          var year = now.getFullYear();
          var month = now.getMonth()+1;              
          var day   = now.getDate(); 

          if(day < 9 ){
            day = "0"+day;  //이번달
          }         
          
          if(month < 9 ){
            month = "0"+month;
          }
          
          document.getElementById("reservation_date").value = year+"-"+month+"-"+day;
        }

      },
      closeReservation(){
        this.errorTxt = "";
        this.show_reservation = false;
      },
      setConsultReservation(){
        let reservation_date = document.getElementById("reservation_date").value;
        
        if(reservation_date == ""){
          this.errorTxt = "예약일을 입력해주세요.";
          return;
        }
        this.errorTxt = "";
        let reservation_time = document.getElementById("reservation_time").value;
        let rsv_time = reservation_date + " " + reservation_time;
        if(confirm(rsv_time + "로 예약 하시겠습니까?")){
          let payload = {
            tt_id : this.reservationTtId,
            status : 'reservation',
            rsv_time : reservation_date + "T" + reservation_time + ":00"
          }
          this.$store.dispatch("consult/consultReservation", payload).then(() => {
            alert("상담이 예약되었습니다.");
            this.show_reservation = false;
            this.getNonMoveTgtList(0);
          })
          .catch(error => {
            console.log(error);
            this.errorTxt = "시스템 에러입니다.";
          });
        }
      },
    },
    mounted(){
      this.setReceiptDt();        //접수일자 셋팅
      this.getOfficeCodeList();   //수용국사 조회
      this.$store.dispatch('alarm/doReadStateFromStorage');      
    }
  }
</script>