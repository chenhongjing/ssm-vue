import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/styles/index.scss'
import 'element-plus/dist/index.css'
import '@/router/permission'
import * as ElIcons from '@element-plus/icons-vue'
import VueUeditorWrap from 'vue-ueditor-wrap'

const app = createApp(App)
for (const iconName in ElIcons) {
  app.component(iconName, ElIcons[iconName])
}

app.use(store).use(router).use(VueUeditorWrap).mount('#app')
