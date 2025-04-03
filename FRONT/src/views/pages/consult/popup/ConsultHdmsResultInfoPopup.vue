<template>
  <div class="popup">
    <div>
      <div class="title_box">
        <h1 class="cmplitHist">단말 진단 상세</h1>
      </div>
    </div>
    <div class="popup_box st01 oy_s">
      <div class="detail_box st01">
        <dl>
          <dt>진단내용</dt>
          <!-- ONT -->
          <dd v-if="termlType == 'ONT' || termlType == 'MODEM'">  
            <dl>
              <dt>포트상태</dt>
              <dd>{{hdmsResultInfo.portSttus}}</dd>
            </dl>
            <dl>
              <dt>장애진단</dt>
              <dd>{{hdmsResultInfo.equipAlarmDagReslt}}</dd>
            </dl>
            <dl>
              <dt>syslog진단</dt>
              <dd>{{hdmsResultInfo.equipSyslogDagReslt}}</dd>
            </dl>
            <dl>
              <dt>CRC유실진단</dt>
              <dd>{{hdmsResultInfo.crcDagReslt}}</dd>
            </dl>
            <dl>
              <dt>트래픽진단</dt>
              <dd>{{hdmsResultInfo.trafficDagReslt}}</dd>
            </dl>
            <dl>
              <dt>광세기진단</dt>
              <dd>{{hdmsResultInfo.oltOntOptiDagReslt}}</dd>
            </dl>
          </dd>
          <!-- UTP4(L2 포함) -->
          <dd v-if="termlType == 'UTP4' || termlType == 'L2' || termlType == 'UTP'" >
            <dl>
              <dt>포트상태</dt>
              <dd>{{hdmsResultInfo.portSttus}}</dd>
            </dl>
            <dl>
              <dt>연결호스트수</dt>
              <dd>{{hdmsResultInfo.hostNum}}</dd>
            </dl>
            <dl>
              <dt>MAC제한수</dt>
              <dd>{{hdmsResultInfo.macRstrtnNum}}</dd>
            </dl>
            <dl>
              <dt>장애진단</dt>
              <dd>{{hdmsResultInfo.equipAlarmDagReslt}}</dd>
            </dl>
            <dl>
              <dt>syslog진단</dt>
              <dd>{{hdmsResultInfo.equipSyslogDagReslt}}</dd>
            </dl>
            <dl>
              <dt>CRC유실진단</dt>
              <dd>{{hdmsResultInfo.crcDagReslt}}</dd>
            </dl>
            <dl>
              <dt>트래픽진단</dt>
              <dd>{{hdmsResultInfo.trafficDagReslt}}</dd>
            </dl>
          </dd>

          <!-- AP -->
          <dd v-if="termlType == 'H'">
            <dl>
              <dt>CPU/메모리 진단</dt>
              <dd>{{hdmsResultInfo.cpuDagReslt}}</dd>
            </dl>
            <dl>
              <dt>하위 단말 연결상태 진단</dt>
              <dd>{{hdmsResultInfo.lowTermlCnctSttusDagReslt}}</dd>
            </dl>
            <dl>
              <dt>IP할당모드</dt>
              <dd>{{hdmsResultInfo.ipMode}}</dd>
            </dl>
            <dl>
              <dt>무선진단</dt>
              <dd>{{hdmsResultInfo.wlessDagReslt}}</dd>
            </dl>
            <dl>
              <dt>WIPS 침임탐지 진단</dt>
              <dd>{{hdmsResultInfo.wipsDagReslt}}</dd>
            </dl>            
          </dd>

          <!-- STB(기가지니 포함) -->
          <dd v-if="termlType == 'STB' || termlType == 'S' || termlType == 'G' || termlType == 'GA'">
            <dl>
              <dt>서비스 인증상태 진단</dt>
              <dd>{{hdmsResultInfo.svcSttusDagReslt}}</dd>
            </dl>
            <dl>
              <dt>무선품질 진단</dt>
              <dd>{{hdmsResultInfo.wlessQatDagReslt}}</dd>
            </dl>
            <dl>
              <dt>TV 연결정보</dt>
              <dd>{{hdmsResultInfo.tvCnctDagReslt}}</dd>
            </dl>            
          </dd>

        </dl>
      </div>
    </div>
    <div class="popup_close st01">
      <button @click="popupClose">닫기</button>
    </div>
  </div>        
</template>

<script>
  export default {
    name: 'ConsultHdmsResultInfo',
    data() {
      return {
        termlType : ''
      }
    },
    created() {
      this.termlType = this.$route.query.termlType;
    },
    computed: {
      hdmsResultInfo(){
        return this.$store.state.trouble.hdmsResultInfo;
      }
    },
    methods: {
      popupClose(){
        window.close();
      },
      getHdmsResultInfo(){
        
        let item = {
          ttId : this.$route.query.ttId,
          scn : this.$route.query.scn
        }
        console.log(item);
        this.$store.dispatch("trouble/hdmsResultInfo", item).then(() => {
          console.log(this.hdmsResultInfo);
        })
        .catch(error => {
          console.log(error);
        });
      },
    },
    mounted(){
      this.getHdmsResultInfo();
    }
  }
</script>