import AuthService from './auth.service'
import AlarmService from './alarm.service'
import ConsultService from './consult.service'
import TroubleService from './trouble.service'
import GuideService from './guide.service'

// authService : BACKEND : /auth : 접속 권한
export const authService = new AuthService('auth')
// consultService : BACKEND : /alarm : 알림톡
export const alarmService = new AlarmService('alarm')
// consultService : BACKEND : /consult : 상담톡
export const consultService = new ConsultService('consult')
// troubleService : BACKEND : /trobule : 고장 접수, 이력
export const troubleService = new TroubleService('trouble')
// guideService : BACKEND : /guide : AI 가이드라인
export const guideService = new GuideService('guide')

