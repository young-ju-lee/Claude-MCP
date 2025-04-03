export default {
    install(Vue){
        Vue.config.globalProperties.$fnAlert = function(str){
            alert(str);
        }
    }
}