import { createApp } from 'vue'
import App from './App.vue'
// @表示src目录
import router from '@/router'
import store from '@/store'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/assets/style/common.css'

let app = createApp(App)
// 全局安装路由组件
// 安装vuex
app.use(router).use(store).use(ElementPlus).mount('#app')