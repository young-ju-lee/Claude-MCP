import {createStore} from 'vuex'

// modules
import auth from '@/store/modules/auth';        // 접속 권한
import alarm from '@/store/modules/alarm';      // 알림톡
import consult from '@/store/modules/consult';  // 상담톡
import trouble from '@/store/modules/trouble';  // 고장 접수, 이력
import guide from '@/store/modules/guide';  // 고장 접수, 이력

export default createStore({
    modules: {
        auth,
        alarm,
        consult,
        trouble,
        guide
    }    
});