<template>
<div class="img_layer_box">
  <div class="title_box st01">
    <h1 class="cmplitHist">상세이미지</h1>
  </div>
  <div class="img_layer_content">
    <img :src="childValue.imgUrl" alt="상세이미지" v-if="childValue.imgType == 'IM-BASE64'">
    <img :src="'/nas/' + childValue.imgUrl + toggleImgType" :alt="상세이미지" v-if="childValue.imgType == 'IM'">
    <img :src="childValue.imgUrl" :alt="상세이미지" v-if="childValue.imgType == 'IM-RESEND'">
    
    <ul class="img_layer_ctl">
      <li><button @click="this.$emit('closeImg');">{{childValue.imgType != 'IM-BASE64' ? "취소" : "닫기"}}</button></li>
      <li v-if="childValue.imgType != 'IM-RESEND' && childValue.imgType != 'IM-BASE64'"><button style="width:100px" @click="imgTypeChagne();">{{toggleImgType == '.gif' ? '이미지보기' : '동영상보기'}}</button></li>
      <li v-if="childValue.imgType != 'IM-BASE64'"><button @click="this.$emit('sendImg', toggleImgType);" class="btn_send">전송</button></li>
    </ul>
  </div>
</div>
</template>


<script>
  export default {
    name: 'SendImgLayer',
    emits : ["closeImg", "sendImg"],
    data() {
      return {
        toggleImgType: '.png'
      }
    },
    props : {
      childValue : Object
    },
    created() {

    },
    computed: {

    },
    methods: {
      imgTypeChagne(){
        if(this.toggleImgType == '.png'){
          this.toggleImgType = '.gif'
        }else{
           this.toggleImgType = '.png'
        }
      }
    },
    mounted(){

    }
  }
</script>