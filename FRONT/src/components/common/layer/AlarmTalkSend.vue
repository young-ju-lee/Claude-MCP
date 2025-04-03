<template>        
    <div class="layerPopBox" v-show="onLayer">
      <div class="alarm_box">
        <h1>알림톡 발송</h1>
        <ul>
          <li>
            <label for="">고객명</label>
            <input type="text" value="이준영" id="targetName">
          </li>
          <li>
            <label for="">전화번호</label>
            <input type="text" value="01058583987" id="targetNum">
          </li>
          <li>
            <label for="">TTID</label>
            <input type="text" value="2023021500000009" id="ttId">
          </li>
          <li>
            전화번호는 숫자만 입력 가능합니다.
          </li>
        </ul>
        <div class="pop_btn_group">
          <ul>
            <li><button @click="sendAlarmTalk()">알림톡 발송</button></li>
            <li><button @click="closeLayerPopup()">취소</button></li>
          </ul>
        </div>
        <p v-show="false">발송 결과 : </p>
      </div>
    </div>
</template>
<script>

export default {
  name: 'App',
  data() {
    return {
      testPageNum : 1,
      onLayer : false,
    }
  },
  computed: {
    roomInfo() {
      return this.$store.state.kakao.roomInfo;
    },
  },
  created() {
  },
  methods: {
    setTestPageNum() {
      this.testPageNum = this.testPageNum + 1;
    },
    openLayerPop(){
      this.onLayer = true;
    },
    closeLayerPopup(){
      this.onLayer = false;
    },
    // 상담톡 화면에서 전송되어야함
    sendAlarmTalk(){
      let num = document.getElementById("targetNum").value;
      let ttId = document.getElementById("ttId").value;
      localStorage.setItem('targetNum', num);
      localStorage.setItem('ttId', ttId);
      this.$router.push({path:'/consult'});
    }
  }
}
</script>