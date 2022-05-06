import { login as loginApi } from '@/api/login'
import { logout as logoutApi } from '@/api/logout'
import router from '@/router'
import { setTokenTime } from '@/utils/auth'

export default {
  namespaced: true,
  state: () => ({
    token: localStorage.getItem('token') || '',
    siderType: true,
    username: ''
  }),
  mutations: {
    setToken(state, token) {
      state.token = token
      localStorage.setItem('token', token)
    },
    changeSiderType(state) {
      state.siderType = !state.siderType
    },
    setUsername(state, username) {
      state.username = username
      localStorage.setItem('username', username)
    }
  },
  actions: {
    login({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        loginApi(userInfo)
          .then((res) => {
            console.log('successful login response:')
            console.log(res)
            // 登录成功，设置token，跳转到首页
            commit('setToken', res.token)
            commit('setUsername', res.user.username)
            setTokenTime()
            router.replace('/')
            resolve()
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
    logout({ commit }) {
      return new Promise((resolve, reject) => {
        logoutApi()
          .then((res) => {
            console.log('successful logout response:')
            console.log(res)
            commit('setToken', '')
            commit('setUsername', '')
            localStorage.clear()
            router.replace('/login')
            resolve()
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
  }
}
