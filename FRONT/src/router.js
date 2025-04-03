import { createWebHistory, createRouter } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'Main',
        component : () => import('@/layouts/LoginLayout'),
        children : [
            {
                path:'',
                name:'mainPage',
                component: () => import('@/views/pages/auth/Login'),
            }
        ]
    },
    {
        path: '/login',
        name: 'Login',
        component : () => import('@/layouts/LoginLayout'),
        children : [
            {
                path:'',
                name:'loginPage',
                component: () => import('@/views/pages/auth/Login'),
            }
        ]
    },
    {
        path: '/alarm',
        name: 'AlarmPage',
        component : () => import('@/layouts/PageLayout'),
        children : [
            {
                path:'',
                name:'alarm',
                component: () => import('@/views/pages/alarm/Alarm'),
            }
        ]
    },
    {
        path: '/consult',
        name: 'pageLayout',
        component : () => import('@/layouts/PageLayout'),
        children : [
            {
                path:'',
                name:'ConsultPage',
                component: () => import('@/views/pages/consult/Consult'),
            }
        ]
    },
    {
        path: '/popup',
        name: 'popupLayout',
        component : () => import('@/layouts/PopupLayout'),
        children : [
            {
                path:'ConsultHistPopup',
                name:'ConsultHistPopup',
                component: () => import('@/views/pages/consult/popup/ConsultHistPopup'),
            },
            {
                path:'ConsultCmpltHistPopup',
                name:'ConsultCmpltHistPopup',
                component: () => import('@/views/pages/consult/popup/ConsultCmpltHistPopup'),
            },
            {
                path:'ConsultHdmsResultInfoPopup',
                name:'ConsultHdmsResultInfoPopup',
                component: () => import('@/views/pages/consult/popup/ConsultHdmsResultInfoPopup'),
            },
            {
                path:'ConsultGuideTextPopup',
                name:'ConsultGuideTextPopup',
                component: () => import('@/views/pages/consult/popup/ConsultGuideTextPopup'),
            },
            {
                path:'AlarmPreviewPopup',
                name:'AlarmPreviewPopup',
                component: () => import('@/views/pages/alarm/popup/AlarmPreviewPopup'),
            },
            
            
        ]
    },
    {
        path : '/:pathMatch(.*)*',
        name:'notFound',
        component : () => import('@/layouts/LoginLayout'),
        children : [
            {
                path:'',
                name:'errorPage',
                component: () => import('@/views/pages/error/Error'),
            }
        ]
    }
]

const router = new createRouter({
    history: createWebHistory(),
    routes
});

export default router
