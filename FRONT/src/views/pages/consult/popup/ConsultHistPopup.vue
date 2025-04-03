<template>

  <div class="popup">
    <div>
		<div class="title_box">
			<h1 class="cmplitHist">비대면 응대이력</h1>
		</div>
      <div class="popup_box st02">
		<div class="hist_info">
			<dl>
				<dt>고객명</dt>
				<dd>{{StringUtil().cusNmFormatter($route.query.cust_nm)}}</dd>
			</dl>
			<dl>
				<dt>번호</dt>
				<dd>{{StringUtil().phoneFormatter($route.query.tel_num)}}</dd>
			</dl>
			<dl>
				<dt>상담자</dt>
				<dd>{{StringUtil().cusNmFormatter($route.query.cstt_nm)}}</dd>
			</dl>
			<dl>
				<dt>상담일시</dt>
				<dd style="word-break: keep-all;">{{StringUtil().dateFormatter1($route.query.consult_datetime)}}</dd>
			</dl>
      <dl v-show="$route.query.go_reason != 'null'">
        <dt>출동사유</dt>
        <dd>{{$route.query.go_reason}}</dd>
      </dl>
		</div>
		<div class="hist_chat oy_s">
      <div v-for="(item, idx) in consultHist" :key="idx">
        <div class="get" v-if="item.sender_type == 'CT'">
          <dl>
            <dt></dt>
            <dd class="msg" v-html="item.content" v-if="item.content_type != 'IM'"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'IM'"><img :src="'data:image/png;base64,' + item.content" alt="" style="width:80%"></dd>
            <dd class="time">{{item.chat_time}}</dd>
          </dl>
        </div>
        <div class="send" v-if="item.sender_type == 'CS'">
          <dl>
            <dt></dt>
            <dd class="msg" v-html="item.content" v-if="item.content_type == 'TX'"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'IM-BASE64'"><img :src="item.file_name + ',' + item.content" alt="" style="width:100%; border-radius: 5px; margin-top: 5px;"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'IM'"><img :src="'/nas/' + item.content" :alt="item.file_name" style="width:100%; border-radius: 5px; margin-top: 5px;"></dd>
            <dd class="time">{{item.chat_time}}</dd>
          </dl>
        </div>
      </div>
			<!-- <div class="send">
				<dl>
					<dt></dt>
					<dd class="time">오후 02:18</dd>
					<dd class="msg">안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.</dd>
				</dl>
			</div>
			<div class="error">
				<p>알림톡 전송에 실패했습니다.</p>
			</div>
			<div class="error">
				<p>서버와 연결이 끊겼습니다.</p>
			</div>
			<div class="send">
				<dl>
					<dt></dt>
					<dd class="time">오후 02:18</dd>
					<dd class="msg">안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.</dd>
				</dl>
			</div> -->
		</div>
      </div>
      <div class="popup_close st02">
        <button @click="popupClose">닫기</button>
      </div>
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
      consultHist(){
        return this.$store.state.consult.consultHist;
      },
    },
    methods: {
      popupClose(){
        window.close();
      },
      getConsoltHist(){
        let item = {
          id : this.$route.query.id,
          consult_id : this.$route.query.consult_id,
        }
        this.$store.dispatch("consult/consultHist", item).then(() => {
          console.log(this.consultHist);
        })
        .catch(error => {
          console.log(error);
          alert("상담 내용이 없습니다.");
        });
      },
      StringUtil(){
        var obj = StringUtil;
        return obj;        
      }
    },
    mounted(){
      this.getConsoltHist();
    }
  }
</script>