import axios from 'axios'
import { ElMessage } from 'element-plus'
// import { diffTokenTime } from '@/utils/auth'
// import store from '@/store'

const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: 5000
})

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // if (localStorage.getItem('token')) {
    //   if (diffTokenTime()) {
    //     // 退出登录
    //     store.dispatch('app/logout')
    //     return Promise.reject(new Error('token expired!'))
    //   }
    // }

    // 给每一次request都加上token和username
    config.headers.Authorization = localStorage.getItem('token')
    config.headers.Username = localStorage.getItem('username')
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (response) => {
    // const { data, meta } = response.data
    // if (meta.status === 200 || meta.status === 201) {
    //   return data
    // } else {
    //   ElMessage.error(meta.msg)
    //   return Promise.reject(new Error(meta.msg))
    // }
    console.log('response:')
    console.log(response)
    if (response.status === 200 || response.status === 201) {
      return response.data
    } else {
      ElMessage.error('响应拦截器：错误的状态码')
      return Promise.reject(new Error('响应拦截器：错误的状态码'))
    }
  },
  (error) => {
    console.log('error response:')
    console.log(error.response)
    error.response && ElMessage.error('Error')
    return Promise.reject(new Error(error.response.data))
  }
)

export default service
