import axios from 'axios'
import store from '@/store'

// 创建axios实例
const request = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: 'http://localhost:8080/system_admin/',
    // 超时
    timeout: 10000,
    // 设置Content-Type，规定了前后端的交互使用json
    headers: { 'Content-Type': 'application/json;charset=utf-8' }
})

// 添加请求拦截器
request.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
   
}, function (error) {
    // 对请求错误做些什么
    
});

export default request
 // 不全局安装因为：不建议在组件中直接使用axios对象发送请求