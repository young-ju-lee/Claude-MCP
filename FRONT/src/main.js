import { createApp } from 'vue'
import App from './App.vue'

// Vuex Store
import store from './store/store'

// Vue Router
import router from "./router"

// Common CSS for UI
import "./assets/css/fonts.css";
import "./assets/css/base.css";
import "./assets/css/common.css";
import "./assets/css/styles.css";
import "./assets/css/vue-multiselect.css";
//import "./assets/css/styles.css";

const app = createApp(App)
app.use(router)
app.use(store)
app.mount('#app')