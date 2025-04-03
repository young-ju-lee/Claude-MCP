<template>
  <!-- group_item -->
  <div class="group_item">
    <h1>AI 조치 가이드</h1>
    <!-- egl_box -->
    <div class="egl_box">
      <div class="egl_guide_no_data" v-if="checkNone">
        <p>AI 조치 가이드 데이터가 없습니다.</p>
      </div>
      <div class="egl_guide" v-if="eglList.length != 0">
        <ul :class="'menu_line' + ' st0' + eglList.guide_lines.length">
          <li v-for="(item, idx) in eglList.guide_lines" :key="idx" :class="'egl_menu_' + item.terml_type + ' ' + (on_main_num == idx ? 'on' : '')" @click="changeMainNum(idx)">
            <span>{{item.terml_type}}</span>
          </li>
        </ul>
        <div v-for="(item, idx) in eglList.guide_lines" :key="idx" v-show="on_main_num == idx" class="oy_s" :id="'on_main_' + idx">
          <ul :class="'guide_line' + ' st0' + item.guides.length + ' ' + (item.guides.length > 6 ? 'sc' : '')">
            <li v-for="(item1, idx1) in item.guides" :key="idx1" :class="(on_sub_num == idx1 ? 'on' : '')"  @click="changeSubNum(idx1)">
              <dl>
                <dt>step0{{idx1 + 1}}</dt>
                <dd :title="item1.title">{{item1.title}}</dd>
              </dl>
            </li>
          </ul>
          <div v-for="(item1, idx1) in item.guides" :key="idx1" :class="'guide_box oy_s' + (on_sub_num == idx1 ? ' on' : '') + ' idx_' + idx1 + ' img_group_' + item1.file" v-show="on_sub_num == idx1"  :style="'margin-top:' + (idx1 > 5 ? (idx1 - 5) * 40 : 0) + 'px'">
            <div>
              <div>
                <div>
                  <p class="guide_img"><img :src="'/nas/' + item1.file + '.png'" :alt="item1.file" @error="hideErrorImg(item1.file)"></p>
                </div>
                <ul class="guide_detail">
                  <li class="guide_img_view" @click="showImg(item1.file, 'IM')" v-show="this.consultTarget.popupYn == 'N'">상세이미지</li>
                  <li class="guide_text_view" @click="openConsultGuideTextPopup()">상세가이드</li>
                </ul>
                <p>
                  {{item1.description}}
                </p>
              </div>
              <div class="noneImgText">다음 조치로 넘어가주세요.</div>
            </div>
          </div>
        </div>
        
        <div class="ctl_line" v-if="!checkNone">
          <button @click="nextEgl()" v-show="next_show">다음조치</button>
          <ul v-show="this.consultTarget.popupYn == 'N'">
            <li><button @click="btnConsultDone('N')">무출동 처리 완료</button></li>
            <li><button @click="showGoReason">출동 요청</button></li>
          </ul>
        </div>
      </div>
    </div>
    <!--// egl_box -->
  </div>
  <!--// group_item -->
  <div class="layer_popup" v-show="show_go_reason">
    <div class="go_reason_box">
      <h1>출동 처리 사유 입력</h1>
      <div class="layer_box">
        <textarea class="ta_box oy_s" id="go_reason" v-text="go_reason"></textarea>
        <button @click="btnConsultDone('Y')">출동 요청 완료</button>
        <p class="error_txt">{{errorTxt}}</p>
      </div>
      <p class="layer_close" @click="closeGoReason"></p>
    </div>
  </div>
</template>


<script>
  //import guide from '@/store/modules/guide';
  export default {
    name: 'ConsultGuidelines',
    emits : ["showImg"],
    data() {
      return {
        on_main_num : 0,
        on_sub_num : 0,
        next_show : true,
        checkNone : false,
        done : 0,
        go_reason : "",
        show_go_reason : false,
        errorTxt : "",
        errorImgList : [],
      }
    },
    created() {
      
    },
    computed: {
      eglList(){
        return this.$store.state.guide.eglList;
      },
      roomInfo() {
        return this.$store.state.alarm.roomInfo;
      },
      consultDone() {
        return this.$store.state.consult.consultDone;
      },
      consultTarget() {
      // 로그인 사용자 정보
        return this.$store.state.consult.consultTarget;
      },
    },
    methods: {
      getEgl(){
        // 초기화.
        let target = document.querySelectorAll(".guideNoneImg");
        for(let j in target){
          if(!isNaN(j))
          target[j].classList.remove("guideNoneImg");
        }
        this.on_main_num = 0;
        this.on_sub_num = 0;
        // TTID로 조회
        let item = this.consultTarget.currTtID; //localStorage.getItem('ttId'); //"2023031600000013";
        this.$store.dispatch("guide/egl", item).then(() => {
          if(this.eglList.guide_lines.length > 0){ 
            console.log(this.eglList);
            this.checkNone = false;
            this.$forceUpdate();
            // 신규로 추가된 이미지 에러 안나는 부분 처리 추가
            if(this.errorImgList.length != 0){
              for(let i in this.errorImgList){
                let target = document.querySelectorAll(".img_group_" + this.errorImgList[i] + " > div");
                for(let j in target){
                  if(!isNaN(j))
                  target[j].classList.add("guideNoneImg");
                }
              }

            }
          }else{
            this.checkNone = true;
          }
          
        })
        .catch(error => {
          console.log(error);
        });
      },
      nextEgl(){
        // eslint-disable-next-line
        //debugger;
        if(this.on_sub_num + 1 < this.eglList.guide_lines[this.on_main_num].guides.length){
          this.on_sub_num++;
        }else{
          if(this.eglList.guide_lines[this.on_main_num + 1] != undefined){
            this.on_sub_num = 0;
            this.on_main_num++;
          } else {
            //this.next_show = false;
            // 초기화 시키는 로직
            this.on_sub_num = 0;
            this.on_main_num = 0;
          }
        }
      },
      changeMainNum(idx){
        this.on_sub_num = 0;
        this.on_main_num = idx;
      },
      changeSubNum(idx){
        this.on_sub_num = idx;
      },
      changeScroll(idx){
        if(idx > 5){
          document.getElementById("on_main_" + this.on_main_num).scrollTop = idx > 5 ? (idx - 5) * 40 : 0
        }else{
          document.getElementById("on_main_" + this.on_main_num).scrollTop = 0;
        }
      },
      showImg(value, type){
        console.log(value);
        this.$emit('showImg', value, type);
      },
      showGoReason(){
        this.show_go_reason = true;
      },
      closeGoReason(){
        this.errorTxt = "";
        this.go_reason = "";
        document.getElementById("go_reason").value = "";
        this.show_go_reason = false;
      },
      //https://dev.ap.csai.kt.co.kr:8080/api/consult/done
      btnConsultDone(value){
        // 상담 진행 여부 판단 필요.
        // 출동 사유 글자수 확인
        this.go_reason = document.getElementById("go_reason").value;
        if(value == "Y" && this.go_reason.length > 1000){
          this.errorTxt = "1000글자 이하로 작성 가능합니다. ("+ this.go_reason.length +"/1000)"
          return;
        }
        let doneTypeName = value == 'Y' ? "출동 요청" : "무출동 처리 완료";
        if(confirm(doneTypeName + " 하시겠습니까? 완료시 상담이 종료됩니다.")){
          // AI 조치 가이드 코드 생성
          let fileCode = "";
          if(this.eglList.guide_lines[this.on_main_num].guides[this.on_sub_num].file != null){
            fileCode = this.eglList.guide_lines[this.on_main_num].guides[this.on_sub_num].file.split("_");
            fileCode = fileCode[0] + '_' + fileCode[fileCode.length - 1];
          }
          let item = {
            consult_id : this.consultTarget.currConsultId,
            last_guide_code : fileCode,
            scn : this.eglList.guide_lines[this.on_main_num].guides[this.on_sub_num].scn,
            go_reason : this.go_reason,
            go_yn : value
          };
          // 출동 또는 무출동 처리 완료
          this.$store.dispatch("consult/consultDone", item).then(() => {
            if(this.consultDone.errorCode == null){ 
              // 상담 초기화
              this.consultTarget.consultArray = [];
              alert(doneTypeName + ", 상담이 종료되었습니다.");
              this.$router.push({path:'/alarm'});
            }else{
              alert(this.consultDone.message);
            }
          })
          .catch(error => {
            console.log(error);
          });
        }
      },
      hideErrorImg(idx){
        if(this.errorImgList.indexOf(idx) == -1 ){
          this.errorImgList.push(idx);
          console.log(this.errorImgList);
        }
        // 동일한 이미지 포함 전체 처리
        let target = document.querySelectorAll(".img_group_" + idx + " > div");
        for(let j in target){
          if(!isNaN(j))
          target[j].classList.add("guideNoneImg");
        }
      },
      openConsultGuideTextPopup(){
        let rigthPosition = (document.body.offsetWidth) - 730;
        rigthPosition += window.screenLeft;

        let options = "top=10, left=" + rigthPosition +", width=900, height=900, status=no, menubar=no, toolbar=no, resizable=no";
        window.open("/popup/ConsultGuideTextPopup?ttId=" + this.consultTarget.currTtID + "&main=" + this.on_main_num + "&sub=" + this.on_sub_num, "ConsultHdmsResultInfoPopup", options);
      },
    },

    updated(){
      if(document.getElementById("on_main_" + this.on_main_num) != null){
        this.changeScroll(this.on_sub_num);
      }
      // 다음 조치. class 처리
    }
  }
</script>