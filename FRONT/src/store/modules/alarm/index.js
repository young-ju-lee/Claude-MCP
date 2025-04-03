
import { alarmService } from '../../api'

// state
const state = {
    roomInfo: [],   // roomInfo : 방 정보 ({consult_id})
    values: [], // 23.10월 선택국사값 저장 정보 
}

// getters
const getters = {

}

// actions
const actions = {
    // sendAlarmTalk : 알림톡 전송 호출
    async sendAlarmTalk(context, payload){
        let resp = await alarmService.sendAlarmTalk(payload)
        context.commit('SEND_ALARM_TALK', resp.data);
    },
    //23.10월 : 국사선택시 state.values에 업데이트 
    updatevalueaction({commit},values) {
        commit('updatevalue',values)
    },
    //23.10월 : 로컬스토리지에 있는 선택국사값을 읽어옮
    doReadStateFromStorage({commit}){
        commit('readStateFromStorage');
    }
}

// mutations
const mutations = {
    // sendAlarmTalk : 알림톡 전송 호출
    SEND_ALARM_TALK(state, res){
        state.roomInfo = res;
        console.log(state.roomInfo)
    },
    //23.10월 : 국사선택시 해당국사값을 저장
    updatevalue(state,values) {
        state.values.push(values);
       // localStorage.setItem("values.name",state.values.name);
       // localStorage.setItem("values.code",state.values.code);

      //  console.log("store value 점검"+JSON.stringify(values)); 
    },
    //23.10월 : 로컬스토리지에 있는 선택된 국사값을 state.values에 저장 
    readStateFromStorage(state){
       if (localStorage.getItem("values")!=null) {
       // console.log("storage 점검"+ JSON.parse(state.values));
        state.values=JSON.parse(localStorage.getItem("values"));  
        //state.values=JSON.stringify(localStorage.getItem("values.code"));      
       }
   }

}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}