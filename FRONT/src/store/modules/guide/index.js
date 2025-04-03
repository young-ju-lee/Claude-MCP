
import { guideService } from '../../api'

// state
const state = {
    eglList: [],       // eglList : egl 가이드라인 데이터
}
// getters
const getters = {

}

// actions
const actions = {
    // egl : AI 가이드라인 조회
    async egl(context, payload){
        let resp = await guideService.egl(payload)
        context.commit('EGL', resp.data);
    },
    
}

// mutations
const mutations = {
    // egl : AI 가이드라인 조회
    EGL(state, res){
        state.eglList = res.data
        console.log(state.eglList)
    }, 
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}