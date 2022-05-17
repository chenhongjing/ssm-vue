import router from './index'
import store from '@/store'

// 白名单，没有登录的时候也可以访问的页面
const whiteList = ['/login', '/register']

// 路由守卫
router.beforeEach((to, from, next) => {
  if (store.getters.token) {
    // 如果已经登录了还去登录页面，就跳转到首页去
    if (to.path === '/login') {
      next('/')
    } else {
      next()
    }
  } else {
    // 如果没有登录
    if (whiteList.includes(to.path)) {
      next()
    } else {
      next('/login')
    }
  }
})
