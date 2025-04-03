
import { troubleService } from '../../api'

// state
const state = {
    accptInfo: [],       // accptInfo : 고장접수정보 조회 데이터
    histList : [],       // histList : 고장이력정보 조회 데이터
    cmpltHistInfo : [],   // cmpltHistInfo : 고장이력 정보 상세 조회 데이터
    nonMoveTgtList : [],    //AI 무출동 대상 조회 데이터
    alarmTalkSndHistList : [],   //알림톡 발송이력 조회 데이터
    hdmsResultInfo : [],         //망구성도 팝업
    hdmsDgrmList : [],   // 망구성도
    officeCodeList : [] //국사정보
    //,officeCodeSelect : [] //국사정보 - 멀티셀랙트
}

// getters
const getters = {

}

// actions
const actions = {
    // accptInfo : 고장접수정보 조회 호출
    async accptInfo(context, payload){
        let resp = await troubleService.accptInfo(payload)
        context.commit('ACCPTINFO', resp.data);
    }, 
    // histList : 고장이력정보 조회 호출
    async histList(context, payload){
        let resp = await troubleService.histList(payload)
        context.commit('HISTLIST', resp.data);
    },
    // cmpltHistInfo : 고장이력 정보 상세 조회 호출
    async cmpltHistInfo(context, payload){
        let resp = await troubleService.cmpltHistInfo(payload)
        context.commit('CMPLT_HIST_INFO', resp.data);
    },
    // AI 무출동 대상조회
    async nonMoveTgtList(context, param){        
        let resp = await troubleService.nonMoveTgtList(param)
        context.commit('NONMOVE_TGT_LIST', resp.data);
    },
    // 알림톡 발송이력
    async alarmTalkSndHistList(context, param){          
        let resp = await troubleService.alarmTalkSndHistList(param)
        context.commit('ALARMTAKL_SND_LIST', resp.data);
    },
    //망구성도
    async hdmsDgrmList(context, param){          
        let resp = await troubleService.hdmsDgrmList(param)
        context.commit('HDMS_DGRM_LIST', resp.data);
    },
    //망구성도 팝업
    async hdmsResultInfo(context, param){          
        let resp = await troubleService.hdmsResultInfo(param)
        context.commit('HDMS_RESULT_INFO', resp.data);
    },
    //국사정보
    async officeCodeList(context, param){          
        let resp = await troubleService.officeCodeList(param)
        context.commit('OFFICE_CODE_LIST', resp.data);
    }    
}

// mutations
const mutations = {
    // accptInfo : 고장접수정보 조회 응답 처리
    ACCPTINFO(state, res){
        state.accptInfo = res;        
    }, 
    // histList : 고장이력정보 조회 응답 처리
    HISTLIST(state, res){
        state.histList = res;        
    },
    // cmpltHistInfo : 고장이력 정보 상세 조회 응답 처리
    CMPLT_HIST_INFO(state, res){
        state.cmpltHistInfo = res;        
    },
    // AI 무출동 대상조회
    NONMOVE_TGT_LIST(state, res){ 
        state.nonMoveTgtList = res;        
    },
    // 알림톡 발송이력
    ALARMTAKL_SND_LIST(state, res){
        state.alarmTalkSndHistList = res;
    },
    //망구성도 팝업
    HDMS_RESULT_INFO(state, res){
        state.hdmsResultInfo = res; 
    },
    HDMS_DGRM_LIST(state, res){
        state.hdmsDgrmList = res; 
    },
    OFFICE_CODE_LIST(state, res){
        state.officeCodeList = res;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}