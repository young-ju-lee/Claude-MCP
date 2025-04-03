<template>
  <div class="group_item">
    <h1>댁내 망구성도</h1>
    <!--ul class="case_info_st01">
      <li><span class="bg_st01"></span>정상</li>
      <li><span class="bg_st02"></span>비정상</li>
      <li><span class="bg_st03"></span>상태없음</li>
    </ul-->
    <div class="diagram_box oy_s">
      <div class="net_diagram_no_data" v-if="consultDgrmArray.length == 0">
        <p>망구성도 데이터가 없습니다.</p>
      </div>
      <div class="net_diagram">
        <ul v-for="(item1, idx) in consultDgrmArray" :key="idx">
          <li v-for="(item2, idx1) in item1" :key="idx1" :class="item2.typeClass + ' ' + ((item2.typeClass == 'none') && (idx1 == 0) ? 'first_none' : '')">
            <span :class="'bg_type_' + item2.termlType" @click="openConsultHdmsResultInfoPopup(item2)" v-if="item2.typeClass != 'none'">
              {{ item2.seq == '' ? item2.termlType : '' }}
              {{ item2.termlType == 'H' ? '홈허브' : '' }}
              {{ item2.termlType == 'G' ? '기가지니' : '' }}
              {{ item2.termlType == 'GA' ? '기가지니A' : '' }}
              {{ item2.termlType == 'S' ? 'STB' : '' }}
              {{ item2.termlType == 'E' ? '사설장치' : '' }}
            </span>
            <span v-if="item2.typeClass == 'none'"></span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>


<script>
  export default {
    name: 'ConsultNetDiagram',
    data() {
      return {
        consultDgrmArray : []
      }
    },
    created() {

    },
    computed: {
      hdmsDgrmList(){
        return this.$store.state.trouble.hdmsDgrmList;
      },
      consultTarget() {
      // 로그인 사용자 정보
        return this.$store.state.consult.consultTarget;
      },
    },
    methods: {
      getHdmsDgrmList(){
        this.consultDgrmArray = [];
        let item = {
          ttId : this.consultTarget.currTtID//localStorage.getItem('ttId')
        }
        this.$store.dispatch("trouble/hdmsDgrmList", item).then(() => {
          if((this.hdmsDgrmList).length > 0){
            this.getConsultDgrm();
          }
        })
        .catch(error => {
          console.log(error);
        });
      },
      getConsultDgrm(){
        
        // 이전 블록 공백 계산용 (이전 블록 == 부모값이 다른 전 루프 데이터)
        let beforeBlockArray = 1;
        // 같은 부모 블록 계산용 (이전 블록 == 부모값이 다른 데이터)
        let sameBlockCnt = 0;
        // hdmsDgrmList -> consultDgrmArray UI용 데이터 배열 생성(자릿수로 push)
        for(let i = 0; i < this.hdmsDgrmList.length; i++){
          console.log(this.hdmsDgrmList[i].seq);
          // seq가 null이 아닐때만 put
          if(this.hdmsDgrmList[i].seq != null){
            // 모뎀 세팅 (seq == 0)
            if(this.hdmsDgrmList[i].seq == "0"){
              // seq 삭제해서 자릿수 변경
              this.hdmsDgrmList[i].seq = "";
            }
            // 사설장비 세팅
            if(this.hdmsDgrmList[i].seq.indexOf("E") >= 0){
              // seq 삭제해서 자릿수 변경
              //if(this.hdmsDgrmList[i].seq.substr(1).length < 2){
                let newHdms = Object.assign({}, this.hdmsDgrmList[i]);
                newHdms.seq = 'Z1'
                newHdms.typeClass = "first_block";
                this.hdmsDgrmList[i].termlType = "E";
                this.hdmsDgrmList.splice(i + 1, 0, newHdms);
                this.hdmsDgrmList[i].seq = 'Z';
                
              // }else{
              //   this.hdmsDgrmList[i].seq = 'Z1';
              //   // 부모값이 같은지 확인해서 같을시에는 별도 클레스 처리
              //   if(this.hdmsDgrmList[i].seq == this.hdmsDgrmList[i - 1].seq){
              //     this.hdmsDgrmList[i].typeClass = "link_block";
              //   }
              // }
            }

            // css용 클래스 객체 생성
            if(this.hdmsDgrmList[i].seq.indexOf("Z") == -1){
              this.hdmsDgrmList[i].typeClass = "";
            }
            // 자릿수 계산 (ex : '' = 0, '1' = 1, '11' = 2)
            let tempNum = this.hdmsDgrmList[i].seq.length;
            if(this.consultDgrmArray[tempNum] == undefined){
              // 값이 없을시 초기값 배열로 할당
              this.consultDgrmArray[tempNum] = []
            }
            // 받아온 데이터를 자릿수에 맞는 배열로 추가
            this.consultDgrmArray[tempNum].push(this.hdmsDgrmList[i]);

            // 현재 자리수의 배열 길이
            let lenNum = this.consultDgrmArray[tempNum].length  - 1;
            // 자릿수가 2자리 이상이고, 배열의 2번째 항목일 때 처리하는 분기
            if(tempNum > 1 && lenNum > 0){
              // 빈값 확인. error 방지용 분기
              if(this.consultDgrmArray[tempNum][lenNum - 1].seq == undefined){
                // 0번제 데이터가 없음으로 이전 블록 공백은 0 == 추가될 값은 1 세팅 
                beforeBlockArray = 1;
              }
              // 이전 블럭(부모값과)과 자리수가 같지 않을 때 - first_block 클래스 추가 (css 처리용)
              else if((this.consultDgrmArray[tempNum][lenNum - 1].seq).slice(0, -1) != (this.consultDgrmArray[tempNum][lenNum].seq).slice(0, -1)){
                this.hdmsDgrmList[i].typeClass = "first_block";
                // 이전 블록 개수를 저장 후 반복문 처리
                if(sameBlockCnt != 0){
                  for(let j = 0; j < sameBlockCnt; j++){
                    // beforeBlockArray 부모 배열 이전 블록 위치에 공백 추가 
                    this.consultDgrmArray[tempNum - 1].splice(beforeBlockArray, 0, {"typeClass":"none"});        
                  }
                }
                // 루프가 끝났음으로 같은 블록값 초기화
                sameBlockCnt = 0;
                // 블록 처리가 하나 끝났음으로 이전 블록 처리값 증가
                beforeBlockArray++;
              }else{
                // 이전 블록과 같은 값일때는 같은 블록값 증가
                sameBlockCnt++;
              }

            }
            // 현재 라인(같은 다차원 하위 배열 넘버) 공백 처리용. error 방지용 분기
            if(this.consultDgrmArray[tempNum - 1] != undefined){
              // seq 2자리수부터 처리 진행 && error 방지용 분기
              if(1 < tempNum && this.consultDgrmArray[tempNum - 1][lenNum] != undefined){
                // 현재 배열의 라인의 부모값과 부모 배열의 라인에 데이터가 일치하는지 확인하고 불일치의 경우 공백 처리
                if((this.consultDgrmArray[tempNum][lenNum].seq).slice(0, -1) != this.consultDgrmArray[tempNum - 1][lenNum].seq){
                  // 이전 블록이 없음으로 첫번째 first_block 처리 분기를 못탔을 것임으로 first_block class 추가
                  this.hdmsDgrmList[i].typeClass = "first_block";
                  // 현재의 부모값 - 같은 라인의 부모 배열 값 = 라인 차이 계산값
                  let cnt = 0;
                  if((this.consultDgrmArray[tempNum][lenNum].seq).slice(0, -1) == 'Z'){
                    cnt = 1
                  }else{
                    cnt = ((this.consultDgrmArray[tempNum][lenNum].seq).slice(0, -1) - this.consultDgrmArray[tempNum - 1][lenNum].seq) * 1 
                  }
                  
                  // 공백 추가 위치 계산용 변수
                  let lenup = 0;
                  //  라인 차이 계산값만큼 공백 추가용 루프
                  for(let k = 0; k < cnt; k++){
                    this.consultDgrmArray[tempNum].splice(lenNum + lenup, 0, {"typeClass":"none"});
                    beforeBlockArray++;
                    // 블록 처리가 하나 끝났음으로 이전 블록 처리값 증가
                    lenup++;
                  }
                }
              }
            }
          }
        }
      },
      openConsultHdmsResultInfoPopup(item){
        if(item.seq != 'Z'){
          let options = "top=10, width=600, height=450, status=no, menubar=no, toolbar=no, resizable=no";
          window.open("/popup/ConsultHdmsResultInfoPopup?ttId="+ item.ttId + "&scn=" + item.scn + "&termlType=" + item.termlType, "ConsultHdmsResultInfoPopup", options);
        }
      }
    }
  }
</script>