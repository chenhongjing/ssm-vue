import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/styles/index.scss'
import 'element-plus/dist/index.css'
import '@/router/permission'
import * as ElIcons from '@element-plus/icons-vue'
import VueUeditorWrap from 'vue-ueditor-wrap'
import '../public/Ueditor/ueditor.config.js'
import '../public//Ueditor/ueditor.all.min.js'
import '../public//Ueditor/lang/zh-cn/zh-cn.js'
import '../public/Ueditor/ueditor.parse.min.js'
// import VueUeditor from '@/components/VueUEditor'

const app = createApp(App)
for (const iconName in ElIcons) {
  app.component(iconName, ElIcons[iconName])
}
// app.use(store).use(router).mount('#app')

app.use(store).use(router).use(VueUeditorWrap).mount('#app')
