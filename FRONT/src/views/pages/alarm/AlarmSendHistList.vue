<template>
<div class="search_box">
  <ul class="search_st01">
    <li>
      <label for="">고객명</label>
      <input type="text" id="custName" @keyup.enter="getAlarmTalkSndHistList(0)">
    </li>  
    <li>
      <label for="">전화번호</label>
      <input type="text" id="custTelNo" @keyup.enter="getAlarmTalkSndHistList(0)">
    </li>
    <li>
      <label for="">발송일</label>
      <input type="date" id="sndFromDt"> ~ <input type="date" id="sndToDt">
    </li>
    <li>
      <button @click="getAlarmTalkSndHistList(0)">조회</button>
    </li>
    
  </ul>
</div>
<div class="list_box" style="position:relative;">
  <p>전체건수 : <span id="totalCnt">0</span>건</p>    
  <table>
      <colgroup>
      <col style="width:12%">
      <col style="width:12%">
      <col style="width:15%">
      <col style="width:15%">
      <col style="width:calc(46% - 180px)">
      <col style="width:180px">
    </colgroup>
    <thead>
      <tr>        
        <th>고객명</th>
        <th>전화번호</th>
        <th>상품</th>
        <th>접수일자</th>
        <th>신고내용</th>      
        <th></th>            
      </tr>
    </thead>
    <tbody>      
      <!-- no-data-area -->
      <tr v-if="alarmTalkSndHistList.totalElements == 0">
        <td class="no_data" colspan="6">조회된 데이터가 없습니다.</td>
      </tr>

      <tr v-else v-for="(item, idx) in alarmTalkSndHistList.content" :key="idx">      
        <td>{{StringUtil().cusNmFormatter(item.customer.custNm)}}</td>                  
        <td>{{StringUtil().phoneFormatter(item.customer.custTelNo)}}</td>                  
        <td>{{item.svcMainClsCodeDesc}}</td>
        <td>{{item.troubReceiptDt}}</td>
        <td :title="item.reporterOpinion">{{item.reporterOpinion}}</td>
        <td><div @click="this.$emit('openLayerPop', this.alarmTalkSndHistList.content[idx])"><span></span>알림톡 발송</div></td>
      </tr>
    </tbody>
  </table>  
  <ul class="paging">
    <li class="left_page" v-show="prevBlock" @click="getPrevBlock()"></li>
    <li class="left_num" v-show="prevPage" @click="getAlarmTalkSndHistList(this.pageNumber-1)"></li>    
    <li :class="{'on': idx === this.pageNumber}"                 
      v-show="( idx >= (this.blockPageNumber) * this.blockPerPageSize && idx < (this.blockPageNumber+1) * this.blockPerPageSize )" 
      v-for="(item, idx) in alarmTalkSndHistList.totalPages" :key="idx" @click="getAlarmTalkSndHistList(idx)">{{idx+1}}</li>                            
    <li class="right_num" v-show="nextPage" @click="getAlarmTalkSndHistList(this.pageNumber+1)"></li>
    <li class="right_page" v-show="nextBlock" @click="getNextBlock()"></li>
  </ul>
  <loading-area v-show="loading"/>
</div>
</template>

<script>
  import StringUtil from '../../../utils/StringUtil';
  import LoadingArea from '@/components/common/layer/LoadingArea'; // 로딩 레이어

  export default {
    name: 'AlarmSendHistList',
    emits : ["openLayerPop"],
    components: {
      LoadingArea
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
        loading : false
      }
    },
    created() {

    },
    computed: {
      // 알림톡 발송이력 조회
      alarmTalkSndHistList() {                
        return this.$store.state.trouble.alarmTalkSndHistList;            
      },
      userInfo() {
      // 로그인 사용자 정보
        return this.$store.state.auth.userInfo;
      }
    },
    methods: {
      getAlarmTalkSndHistList(pageNo){
        
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
        let custTelNo = document.getElementById("custTelNo").value;   //고객전화번호
        let sndFromDt = document.getElementById("sndFromDt").value;   //발송일시 From           
        let sndToDt = document.getElementById("sndToDt").value;   //발송일시 To                 
        if(custTelNo.indexOf("0") == 0){          
          custTelNo = "82" + custTelNo.substr(1, custTelNo.length);
        }
        
        // 유효성 체크
        if(!this.isValidation()){
          return;
        }

        let param = {
          "pageNo" : pageNo,
          "recordSize" : this.recordSize,        
          "sndCmpNo" : this.userInfo.loginId,   //발송자 ID
          "custName" : custName,
          "custTelNo" : custTelNo,
          "sndFromDt" : sndFromDt,
          "sndToDt" : sndToDt
        };        
        this.loading = true;
        this.$store.dispatch("trouble/alarmTalkSndHistList", param).then(() => {                      
          //----------------------------------------------------------------
          // 페이징 처리        
          //----------------------------------------------------------------
          let listObj = this.alarmTalkSndHistList;          
          this.pageNumber = listObj.pageable.pageNumber;  //페이지 번호
          let totalPages = listObj.totalPages;    //총페이지 수
          let totalCnt = listObj.totalElements; //총 건수
          document.getElementById("totalCnt").innerHTML = totalCnt.toLocaleString();
          this.pagination(this.pageNumber, totalPages, totalCnt);
          this.loading = false;
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
        this.getAlarmTalkSndHistList(pageNumber);
      },
      getNextBlock(pageNo){      
        this.blockPageNumber = this.blockPageNumber + 1;      
        let pageNumber = this.blockPageNumber * this.blockPerPageSize;
        if(pageNo != null){
          pageNumber = pageNo;
        }      
        this.getAlarmTalkSndHistList(pageNumber);
      },
      //발송일자 셋팅
      setSndDt(){

        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth()+1;
        var day = now.getDate();        

        if(month <=9){
          month = "0" + month;
        }        

        if(day <=9){
          day = "0" + day;
        }    

        var today = year + "-" + month + "-" + day;  
               
        document.getElementById("sndFromDt").value = StringUtil.strtDay(today.replace(/-/gi,''), 2);
        document.getElementById("sndToDt").value = today;

      },
      // 발송기간 유효성 체크      
      isValidation(){
        let sndFromDt = document.getElementById("sndFromDt").value;   //발송일시 From           
        let sndToDt = document.getElementById("sndToDt").value;       //발송일시 To   
        if(sndFromDt == ''){
          alert("발송기간을 선택 하세요");
          document.getElementById("sndFromDt").focus();
          return false;
        }
        if(sndToDt == ''){
          alert("발송기간을 선택 하세요");
          document.getElementById("sndToDt").focus();
          return false;
        }
        if(new Date(sndToDt).getTime() - new Date(sndFromDt).getTime() < 0){
          alert("이전날짜가 더 클 수 없습니다.");
          return false;
        }
        return true;
      },
      StringUtil(){
        var obj = StringUtil;
        return obj;        
      }                 
    },
    
    mounted(){
      this.setSndDt();
      this.getAlarmTalkSndHistList(0);
    }
  }
</script>