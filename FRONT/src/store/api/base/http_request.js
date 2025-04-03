import axios from 'axios'
import router from '../../../router'

let axiosInstance = axios.create({
    baseURL: process.env.VUE_APP_API_URL,
    timeout: 120000
})


axiosInstance.interceptors.request.use(function (config) {
    //axios.defaults.withCredentials = true; 
    config.withCredentials = true; 
    // API 별 토큰 설정 필요 : localStorage 세팅은 임시로만.....
    //let token = localStorage.getItem('user-token')
    //if (token) config.headers['x-access-token'] = token
    //let accessToken = localStorage.getItem('accessToken')
    //if (accessToken) config.headers['Authorization'] = 'Bearer ' + accessToken
    return config
}, function (error) {
    return Promise.reject(error)
})

axiosInstance.interceptors.response.use(function (response) {
    /* eslint-disable */
    //debugger;
    // 응답 헤더에 설정 필요한 값(api에서 작업 필요)
    // Access-Control-Allow-Credentials : true
    // Access-Control-Allow-Origin : 반드시 설정되어있어야 함, 단 와일드 카드는 불가
    // Access-Control-Allow-Methods, Headers : 와일드 카드 불가
    return response
}, function (error) {

    console.log(error)
    if (error.response.status === 403 || error.response.status === 401) {
        console.log('unauthorized, logging out ...')
        //auth.logout()
        router.replace('/login')
    }

    return Promise.reject(error)
})

class HttpRequest {
    constructor() {
        this.axios = axios
    }

    setHeader(header) {
        axiosInstance.defaults.headers.common[header.key] = header.value
        axiosInstance.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
    }

    setAuthorizationHeader(accessToken) {
        axiosInstance.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`
    }

    get(methodName, data) {
        return axiosInstance.get(methodName, {
            params: data
        })
    }

    post(methodName, data) {
        return axiosInstance.post(methodName, data)
    }

    put(methodName, data) {
        return axiosInstance.put(methodName, data)
    }

    delete(methodName, id) {
        return axiosInstance.delete(methodName, { params: { id: id } })
    }

    request(type, url, data) {
        let promise = null
        switch (type) {
            case 'GET':
                promise = axios.get(url, { params: data }) 
                break
            case 'POST': 
                promise = axios.post(url, data) 
                break
            case 'PUT': 
                promise = axios.put(url, data) 
                break
            case 'DELETE': 
                promise = axios.delete(url, data) 
                break
            default: 
                promise = axios.get(url, { params: data }) 
                break
        }
        return promise
    }
}

export default HttpRequest