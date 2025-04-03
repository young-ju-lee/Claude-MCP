
import { consultService } from '../../api'

// state
const state = {
    consultHist : [],       // consultHist : 비대면 응대 이력 상세
    consultHistList : [],   // consultHistList : 비대면 응대 이력 리스트
    consultDgrmArray : [],  // consultDgrmArray : 망구성도
    consultDone : [],       // consultDone : 상담 완료 처리
    consulting : [],        // consulting : 진행중인 상담 목록
    consultStatus : [],     // consultStatus : 상담 상태 변경 처리
    consultTarget : {       // consultTarget : 상담 관리용 객체
        consultArray : [],
        socketArray : [],
        reiveMessgeArray : [],
        currConsultId : "",
        currTtID : "",
        currUserKey : "",
        currTargetIdx : 0,
        alarmSendCheck : 0,
        cnvrst_sttus : "W",
        svcMainClsCode : "",
        sndNm : "",
        sndTeam : "",
        targetName : "",
        targetNum : "",
        popupYn : "N"
    },
}

// getters
const getters = {

}

// actions
const actions = {
    // consultHist : 비대면 응대 이력 상세 호출
    async consultHist(context, payload){
        let resp = await consultService.consultHist(payload)
        context.commit('CONULT_HISTORY', resp.data);
    },
    // consultHistList : 비대면 응대 이력 리스트 호출
    async consultHistList(context, payload){
        let resp = await consultService.consultHistList(payload)
        context.commit('CONULT_HISTORY_LIST', resp.data);
    },
    async consultDgrm(context, payload){
        let resp = await consultService.consultDgrm(payload)
        context.commit('CONULT_DGRM', resp.data);
    },
    async consultDone(context, payload){
        let resp = await consultService.consultDone(payload)
        context.commit('CONULT_DONE', resp.data);
    },
    async consulting(context, payload){
        let resp = await consultService.consulting(payload)
        context.commit('CONULTING', resp.data);
    },
    async consultStatus(context, payload){
        let resp = await consultService.consultStatus(payload)
        context.commit('CONULT_STATUS', resp.data);
    },
    async consultReservation(context, payload){
        let resp = await consultService.consultReservation(payload)
        context.commit('CONULT_RESERVATION', resp.data);
    },
}

// mutations
const mutations = {
    // consultHist : 비대면 응대 이력 상세 응답 처리
    CONULT_HISTORY(state, res){
        state.consultHist = res.data
        console.log(state.consultHist)
    },
    // consultHistList : 비대면 응대 이력 리스트 응답 처리
    CONULT_HISTORY_LIST(state, res){
        state.consultHistList = res.data
        console.log(state.consultHistList)
    },
    // consultDgrm : 비대면 응대 이력 리스트 응답 처리
    CONULT_DGRM(state, res){
        console.log(res)
        state.consultDgrmArray = [];
        console.log(state.consultHistList)
    },
    // consultDgrm : 비대면 응대 이력 리스트 응답 처리
    CONULT_DONE(state, res){
        console.log(res)
        state.consultDone = res.data;
        console.log(state.consultDone)
    },
    CONULTING(state, res){
        state.consulting = [];
        console.log(res)
        let result =  res.data.sort(function(a, b){
            return a.id - b.id;
        });
        state.consulting = result;
        console.log("#!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        console.log(state.consulting)
    },
    CONULT_STATUS(state, res){
        console.log(res)
        state.consultStatus = res.data;
        console.log(state.consultStatus)
    },
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}