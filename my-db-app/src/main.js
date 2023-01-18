import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// import moment from 'moment'
// import VueMoment from 'vue-momentjs'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import './assets/mycss.css'

createApp(App).use(router).mount('#app')
