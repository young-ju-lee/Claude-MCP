<template>
  <div class="popup"> 
    <div>
      <div class="title_box">
        <h1 class="cmplitHist">상세 가이드</h1>
      </div>
    </div>
    <div class="popup_box st01 oy_s">
      <div class="detail_box st02">
        <textarea readonly="readonly" id="guideText">
        </textarea>
      </div>
    </div>
    <div class="popup_close st01">
      <button @click="popupClose">닫기</button>
    </div>
  </div>        
</template>

<script>
// 공통 텍스트
const BASE_TEXT = 
`ㅇ[인사멘트] 고객님 안녕하세요. KT서비스 입니다.
고객님께서 고장 접수해 주셔서 확인해보니
기사님 방문전에 간단한 방법으로 해결 가능할것 같아 연락드렸습니다.
ㅇ[카톡연결] 저희가 고객님의 카톡으로 안내 이미지를 보내드리려고 하는데요.
카톡에서 ‘상담하기’ 버튼을 눌러주시고 통화는 스피커폰 모드로 바꿔주세요.


`;

  export default {
    name: 'ConsultGuideTextPopup',
    data() {
      return {
        guideText : "",
        main : 0,
        sub : 0,
      }
    },
    created() {
    },
    computed: {
      eglList(){
        return this.$store.state.guide.eglList;
      },
    },
    methods: {
      popupClose(){
        window.close();
      },
      getEgl(){
        // TTID로 조회
        let item = this.$route.query.ttId;
        this.main = this.$route.query.main
        this.sub = this.$route.query.sub
        this.$store.dispatch("guide/egl", item).then(() => {
          if(this.eglList.guide_lines.length > 0){ 
            console.log(this.eglList);
            this.guideText = this.eglList.guide_lines[this.main].guides[this.sub].description_detail;
            document.getElementById("guideText").value = BASE_TEXT + this.guideText;
          }
        })
        .catch(error => {
          console.log(error);
        });
      },
    },
    mounted(){
        document.getElementById("guideText").value = BASE_TEXT;
        this.getEgl();
    }
  }
</script>