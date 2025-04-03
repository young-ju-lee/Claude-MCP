<template>
  <div class="group_item chat_item" v-for="(item1, idx1) in consultTarget.consultArray" :key="idx1" v-show="consultTarget.currTargetIdx == item1.id">
    <h1>{{item1.cust_nm != null ? StringUtil().cusNmFormatter(item1.cust_nm) : '이름없음' }}님과의 상담창 <span>{{StringUtil().dateFormatter1(item1.consult_datetime)}} ~</span></h1>
    <p class="chat_state bg_st02"  v-if="item1.user_key == ''">상담대기</p>
    <p :class="'chat_state bg_st_' + item1.cnvrst_sttus" v-if="item1.user_key != ''">
      {{ item1.cnvrst_sttus == 'S' ? '상담보류' : (item1.cnvrst_sttus == 'A' ? '진행중' : '대기중')}}
    </p>
    <div class="fc_st02" style="text-align:center; padding:10px; background:#f0f4f5;">상담시 개인정보가 유출되지 않도록 유의해 주시기 바랍니다</div>
    <div 
      :class="'chat_box oy_s ' + (isDragging ? 'drag':'')" 
      :id="'chat_box_' + item1.id"
      @drop.prevent="dropInputTag"
      @dragover.prevent
      @dragover="dragover"
      @dragleave="dragleave"
    >
      <input ref="image" id="input" type="file" name="image" @change="uploadImage(this)" style="visibility: hidden; display:none;">
      <!-- 이력 추가되는 부분 -->
      <div v-for="(item, idx) in item1.histArray" :key="idx">
        <div class="get" v-if="item.sender_type == 'CT'">
          <dl>
            <dt>{{item1.cust_nm != null ? StringUtil().cusNmFormatter(item1.cust_nm) : '이름없음' }}<br>고객</dt>
            <dd class="msg" v-html="item.content.replace(/\n/gi, '<br>')" v-if="item.content_type != 'IM' && item.content_type != 'VI'"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'VI'"><embed :src="'data:video/mp4;base64,' + item.content" style="width:100%; border-radius: 5px; margin-top: 5px; cursor: pointer; min-height:400px"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'IM'"><img :src="'data:image/jpeg;base64,' + item.content" alt="" style="width:100%; border-radius: 5px; margin-top: 5px;"></dd>
            <dd class="time">{{item.chat_time}}</dd>
          </dl>
        </div>
        <div class="send" v-if="item.sender_type == 'CS'">
          <dl>
            <dt></dt>
            <dd class="time">{{item.chat_time}}</dd>
            <dd class="msg" v-html="item.content.replace(/\n/gi, '<br>')" v-if="item.content_type == 'TX'"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'IM-BASE64'"><img :src="item.file_name + ',' + item.content" alt="" style="width:100%; border-radius: 5px; margin-top: 5px;"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'IM'"><img :src="'/nas/' + item.content" :alt="item.file_name" style="width:100%; border-radius: 5px; margin-top: 5px;"></dd>
          </dl>
        </div>
      </div>
      <!-- 상담 추가되는 부분 -->
      <div v-for="(item, idx) in consultTarget.reiveMessgeArray[idx1]" :key="idx">
        <div class="get" v-if="item.sender_type == 'CT'">
          <dl>
            <dt>{{item1.cust_nm != null ? StringUtil().cusNmFormatter(item1.cust_nm) : '이름없음' }}<br>고객</dt>
            <dd class="msg" v-html="item.content.replace(/\n/gi, '<br>')" v-if="item.content_type != 'IM' && item.content_type != 'VI'"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'VI'"><embed :src="'data:video/mp4;base64,' + item.content" style="width:100%; border-radius: 5px; margin-top: 5px; cursor: pointer; min-height:400px"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'IM'"><img :src="'data:image/jpeg;base64,' + item.content" @click="this.$emit('showImg', 'data:image/jpeg;base64,' + item.content, 'IM-BASE64');" alt="" style="width:100%; border-radius: 5px; margin-top: 5px; cursor: pointer;"></dd>
            <dd class="time">{{item.chat_time}}</dd>
          </dl>
        </div>
        <div class="send" v-if="item.sender_type == 'CS'">
          <dl>
            <dt></dt>
            <dd class="time">{{item.chat_time}}</dd>
            <dd class="msg" v-html="StringUtil().cleanXSS(item.content.replace(/\n/gi, '<br>'))" v-if="item.content_type == 'TX'"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'IM-BASE64'"><img :src="item.file_name + ',' + item.content" @click="this.$emit('showImg', 'data:image/jpeg;base64,' + item.content, 'IM-BASE64');" alt="" style="width:100%; border-radius: 5px; margin-top: 5px;"></dd>
            <dd class="msg" style="text-align: center;" v-if="item.content_type == 'IM'"><img :src="item.content" :alt="item.file_name" style="width:100%; border-radius: 5px; margin-top: 5px;" @click="this.$emit('showImg', item.content, 'IM-RESEND');"></dd>
          </dl>
        </div>
        <div class="error" v-if="item.sender_type == 'NT'">
          <p v-html="item.content"></p>
          <ul style="display:none">
            <li><button>알림톡 재전송</button></li>
          </ul>
        </div>
      </div>
    </div>
    <div v-show="item1.cnvrst_sttus == 'W'">
      <p class="chat_wait">
        상담 대상자가 아직 메시지를 확인하지 않았습니다.<br>
        10분동안 상담이 시작되지 않으면 상담이 종료됩니다.
      </p>
    </div>
    <div v-show="item1.cnvrst_sttus == 'S'">
      <p class="chat_hold">
        상담이 보류되었습니다.
      </p>
    </div>
    <div class="chat_input_group" v-show="item1.cnvrst_sttus == 'A'">
      <textarea class="chat_input" v-model="message"></textarea>
      <button class="chat_button" @click="sendMessage(item1)">전송</button>
    </div>
    <div class="chat_ctrl_group" v-show="item1.cnvrst_sttus != 'W'">
      <ul>
        <li @click="setConsultStatus(item1, 1, idx1)">{{ item1.cnvrst_sttus == 'S' ? '보류해제' : '상담보류'}}</li>
        <li class="bg_st_R" @click="showReservation(item1)">예약일시 입력</li>
        <!-- <li @click="setConsultStatus(item1, 2, idx1)">상담완료</li> -->
      </ul>
    </div>
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
  import Stomp from 'webstomp-client';
  import SockJS from 'sockjs-client';
  import StringUtil from '../../../utils/StringUtil';
  
  export default {
    name: 'ConsultChat',
    emits : ["getConsulting", "showImg"],
    data() {
      return {
        message: "",
        recvList: [],
        user_key: "",
        imgPathUrl : "",
        file_name : "",
        isDragging : false,
        reConnectCnt : 0,        
        reporterOpinion : "",     //신고내용
        socketArray : [],
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
        errorTxt : ""
      }
    },
    created() {
      // this.connect()
      // App.vue가 생성되면 소켓 연결을 시도합니다.
    },
    computed: {
      roomInfo() {
        return this.$store.state.alarm.roomInfo;
      },
      //----------------------------------
      // 고장접수 정보
      //----------------------------------
      accptInfo(){
        return this.$store.state.trouble.accptInfo;
      },
      userInfo() {
      // 로그인 사용자 정보
        return this.$store.state.auth.userInfo;
      },
      consultHist(){
        return this.$store.state.consult.consultHist;
      },
      consultTarget() {
      // 로그인 사용자 정보
        return this.$store.state.consult.consultTarget;
      },
      consultStatus(){
        return this.$store.state.consult.consultStatus;
      }
    },
    methods: {
      // 메시지 전송
      sendMessage(item1) {
        this.consultTarget.currConsultId = item1.consult_id;
        // #TODO 이 부분에 유효성 검증 처리가 들어가야 함
        // 서버로 요청하기전에 1차적인 검증 필요 부분
        // #TODO 메시지 공격 부분도 처리 필요할 수도 있음. 디테일 한 부분
        if(this.message.length > 1000){
          alert("1000글자 이하로 작성 가능합니다. ("+ this.message.length +"/1000)");
          return
        }
        if (this.message !== '') {
          this.sendConsultTalk()
        }
      },
      sendConsultTalk(){
        console.log("보내는 메시지 : " + this.messageTemplet("TX"));
        //if (this.consultTarget.socketArray[this.consultTarget.currTargetIdx] && this.consultTarget.socketArray[this.consultTarget.currTargetIdx].connected) {
          this.consultTarget.socketArray[0].send("/pub/message", this.messageTemplet("TX"), {});
          this.message = ''
        //}else{
          // #TODO 소캣 정보가 없을시 연결에 대한 오류 처리
        //}
      },
      showImg(value, type){
        console.log(value);
        this.$emit('showImg', value, type);
      },
      // 보내는 메시지 json 공통화
      messageTemplet(msgType){
        // eslint-disable-next-line 
        //debugger;
        console.log(msgType);
        const msg = {
          consult_id: this.consultTarget.currConsultId,
          user_key: this.consultTarget.currUserKey,
          sender_type : "CS",
          content: this.message,
          content_type : msgType,
          file_name : this.file_name
        };
        return JSON.stringify(msg);
      },
      // #COMMON : 이미지 변환에 대한 공통 함수 처리 필요
      toDataURL(url, callback){
        let xhr = new XMLHttpRequest();
        xhr.onload = function(){
          let reader = new FileReader();
          reader.onloadend = function(){
            // 처리 된 후에 작동하도록 collback 처리. result에서 첫번째 , 까지 제거하는 처리 필요 . 기본 타입은 UI에서 제어
            callback(reader.result);
          }
          reader.readAsDataURL(xhr.response);
        }
        // blob 방식으로 변환 - Base64
        xhr.open('GET', url);
        xhr.responseType = 'blob';
        xhr.send();
      },
      sendImg(imgUrl, imgType, imgType2){
        if(this.consultTarget.cnvrst_sttus != 'A'){
          alert("상담이 시작되지 않았음으로 이미지 전송이 불가합니다.\n상담이 시작된 후 전송해 주세요")
        }else{
          if(imgType == 'IM-RESEND'){
            this.file_name = imgUrl.substr(5);
            this.message = imgUrl
            this.consultTarget.socketArray[0].send("/pub/message", this.messageTemplet("IM"), {});
            this.file_name = "";
            this.message = "";
          }else{
            this.file_name = imgUrl + imgType2;
            this.message = '/nas/' + imgUrl + imgType2
            this.consultTarget.socketArray[0].send("/pub/message", this.messageTemplet("IM"), {});
            this.file_name = "";
            this.message = "";
          }
        }
      },

      uploadImage(input){
        if(input.files && input.files[0]){
          console.log("input");
          let reader = new FileReader();
          let self = this;
          reader.onload = function(e) {
            self.toDataURL(e.target.result, function(dataUrl) {
              let fileBlock = dataUrl.split(",");
              console.log(dataUrl);
              self.file_name = fileBlock[0];
              self.message = fileBlock[1];
              self.consultTarget.socketArray[0].send("/pub/message", self.messageTemplet("IM-BASE64"), {});
              self.file_name = "";
              self.message = "";
              self.isDragging = false;
            })
          }
          reader.readAsDataURL(input.files[0]);
        }else{
           console.log("input?");
        }
      },

      dropInputTag(e){
        let file = Array.from(e.dataTransfer.files, v => v)[0]
        this.$refs.image.files = e.dataTransfer.files;
        //this.uploadImage(file);
        console.log(file);
        if(this.consultTarget.cnvrst_sttus != 'A'){
          alert("상담이 시작되지 않았음으로 이미지 전송이 불가합니다.\n상담이 시작된 후 전송해 주세요")
          this.isDragging = false;
          //#TODO 3MB 스트림방식으로 보냈을떄 증가하는 값 고려 - 소캣, 복호화 관련해서 수호씨한테 질문
        }else if(file.size > 2560224){
          alert("3MB 넘는 파일은 전송하실 수 없습니다.");
          this.isDragging = false;
        }else{
          this.file_name = file.name;
          this.uploadImage(this.$refs.image);
          console.log(file);
          this.isDragging = false;
        }
      },
      dragover(){
        this.isDragging = true;
      },
      dragleave(){
        this.isDragging = false;
      },
      connect() {
        const serverURL = process.env.VUE_APP_API_URL + "/web-socket"
        let socket = new SockJS(serverURL);
        this.stompClient = Stomp.over(socket);
        this.consultTarget.consultArray[0].consult_id;
        this.consultTarget.currConsultId = this.consultTarget.consultArray[0].consult_id;
        this.consultTarget.socketArray.unshift(Stomp.over(socket));
        console.log(`소켓 연결을 시도. 서버 주소: ${serverURL}`)
        this.consultTarget.socketArray[0].connect({},
          frame => {
            // 소켓 연결 성공
            this.connected = true;
            console.log('소켓 연결 성공 : ' + frame);
            this.consultTarget.socketArray[0].subscribe("/sub/consult/" + this.consultTarget.consultArray[0].consult_id, res => {
              // 현재 id가 아닐 경우에만 class 추가 = connect는 0고정
              if(document.getElementById('consult_id_0') != null){
                if(0 != this.consultTarget.currTargetIdx){
                  document.getElementById('consult_id_0').classList.add("new");
                }
              }
              console.log('받은 메시지 : ' + res.body);
              // 서버에서 받아온 메시지를 JSON 형식으로 변환해서 화면에 세팅
              // #TODO 메시지 암호화 처리 들어가면 이부분에서 복호화 필요
              let reiveMessge = JSON.parse(res.body);
              console.log(reiveMessge);
              
              // 최초 메시지 수신시(reference) user_Key 세팅 : user_key로 수신, 발신 구분
              if(this.consultTarget.consultArray[0].cnvrst_sttus == 'W'){
                this.consultTarget.consultArray[0].cnvrst_sttus = 'A';
                this.consultTarget.cnvrst_sttus = 'A';
                this.consultTarget.currUserKey = reiveMessge.user_key;
                this.$forceUpdate();
              }
              
              this.consultTarget.reiveMessgeArray[0].push(reiveMessge);
              // 스크롤 갱신 처리 : 처리 지연 개선 필요. DOM 기준으로 변경
              setTimeout(function(){
                let chat_box = document.getElementById("chat_box_0");
                chat_box.scrollTop = chat_box.scrollHeight;
              }, 300)
            });
          },
          error => {
            // 소켓 연결 실패
            console.log('소켓 연결 실패', error);
            this.consultTarget.socketArray[0].connect()
            this.connected = false;
            if(this.reConnectCnt < 5){
              this.reConnectCnt = this.reConnectCnt + 1
              this.connect();
            }else{
              alert("재연결 5회 실패 - api 서버 연결 안됨");
              console.log('소켓 연결 5회 실패', error);
            }
            //this.connect();
          }
        );
      }, 
      reConnect(item, idx) {
        const serverURL = process.env.VUE_APP_API_URL + "/web-socket?idx=" + idx
        this.user_key = item.user_key;
        this.roomInfo.consult_id = item.consult_id;
        //this.consultStatus = item.cnvrst_sttus;
        //this.accptInfo.userName = item.cust_nm;
        let socket = new SockJS(serverURL);
        this.consultTarget.socketArray.push(Stomp.over(socket));
        //this.stompClient = Stomp.over(socket);
        console.log(`소켓 재연결을 시도. 서버 주소: ${serverURL}`)
        //let chat_box = document.getElementById("chat_box");
        //chat_box.scrollTop = chat_box.scrollHeight;
        
        this.consultTarget.socketArray[idx].connect({},
          frame => {
            // 소켓 연결 성공
            this.connected = true;
            console.log('소켓 재연결 성공 : ' + frame + "/" + item.consult_id);
            this.consultTarget.socketArray[idx].subscribe("/sub/consult/" + item.consult_id, res => {
              if(document.getElementById('consult_id_' + item.id) != null){
                if(item.id != this.consultTarget.currTargetIdx){
                  document.getElementById('consult_id_' + item.id).classList.add("new");
                }
              }
              console.log('받은 메시지 :' + res.body);
              
              // 서버에서 받아온 메시지를 JSON 형식으로 변환해서 화면에 세팅
              // #TODO 메시지 암호화 처리 들어가면 이부분에서 복호화 필요
              let reiveMessge = JSON.parse(res.body);
              console.log(reiveMessge);
              /*if(reiveMessge.user_key != '' && this.user_key == ''){
                this.user_key = reiveMessge.user_key;
                this.recvList.push(reiveMessge);
              }else{
                this.recvList.push(reiveMessge);
              }*/

              // 최초 메시지 수신시(reference) user_Key 세팅 : user_key로 수신, 발신 구분
              if(this.consultTarget.consultArray[idx].cnvrst_sttus == 'W'){
                this.consultTarget.consultArray[idx].cnvrst_sttus = 'A';
                this.consultTarget.cnvrst_sttus = 'A';
                this.consultTarget.currUserKey = reiveMessge.user_key;
                this.$forceUpdate();
              }
              
              this.consultTarget.reiveMessgeArray[idx].push(reiveMessge);
              //this.recvList.push(reiveMessge);
              // 스크롤 갱신 처리 : 처리 지연 개선 필요. DOM 기준으로 변경
              setTimeout(function(){
                let chat_box = document.getElementById("chat_box_" + item.id);
                chat_box.scrollTop = chat_box.scrollHeight;
              }, 300)
            });
          },
          error => {
            // 소켓 연결 실패
            console.log('소켓 연결 실패', error);
            this.consultTarget.socketArray[idx].connect()
            this.connected = false;
            if(this.reConnectCnt < 5){
              this.reConnectCnt = this.reConnectCnt + 1
              this.connect();
            }else{
              alert("재연결 5회 실패 - api 서버 연결 안됨");
              console.log('소켓 연결 5회 실패', error);
            }
            //this.connect();
          }
        );
      },
      showReservation(item){
        this.show_reservation = true;
        this.consultTarget.currConsultId = item.consult_id
        this.consultTarget.currUserKey = item.user_key

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
        if(confirm(rsv_time + "로 예약 하시겠습니까? 예약시 상담이 종료됩니다.")){
          let payload = {
            tt_id : this.consultTarget.currTtID, //this.roomInfo.consult_id,
            status : 'reservation',
            rsv_time : reservation_date + "T" + reservation_time + ":00"
          }
          this.$store.dispatch("consult/consultReservation", payload).then(() => {
            // this.$emit('getConsulting');
            let payload = {
              consult_id : this.consultTarget.currConsultId, //this.roomInfo.consult_id,
              status : 'done',
              user_key : this.consultTarget.currUserKey//this.user_key

            }
            this.$store.dispatch("consult/consultStatus", payload).then(() => {
              this.consultTarget.consultArray = [];
              alert("상담이 예약되었습니다.");
              this.$router.push({path:'/alarm'});
            })
            .catch(error => {
              console.log(error);
            });
          })
          .catch(error => {
            console.log(error);
            this.errorTxt = "시스템 에러입니다.";
          });
        }
      },

      setConsultStatus(item, type, idx){
        let setStatus = 'pending'

        if(type == 2){
          setStatus = 'done'
        }else if(item.cnvrst_sttus == 'S'){
          setStatus = 'consulting'
        }
        let payload = {
          consult_id : item.consult_id, //this.roomInfo.consult_id,
          status : setStatus,
          user_key : item.user_key//this.user_key
        }
        this.$store.dispatch("consult/consultStatus", payload).then(() => {
          //this.consultStatus = status;
          //
          // this.consultTarget.currTargetIdx = item.id;
          //
          this.consultTarget.cnvrst_sttus = this.consultStatus.cnvrst_sttus;
          if(this.consultTarget.cnvrst_sttus == 'D'){
            this.consultTarget.consultArray = [];
            // this.$emit('getConsulting');
            alert("상담이 완료되었습니다.");
            this.$router.push({path:'/alarm'});
          }else{
            this.consultTarget.consultArray[idx].cnvrst_sttus = this.consultStatus.cnvrst_sttus;
          }
          
          this.$forceUpdate();
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
      // list는 있으나 선택된 값이 없을 시 0번째 값 선택
      if(this.consultTarget.currTargetIdx == '' && this.consultTarget.consultArray.length != 0){
        this.consultTarget.currTargetIdx = this.consultTarget.consultArray[0].id;
        this.$forceUpdate();
      }
    }
  }
</script>