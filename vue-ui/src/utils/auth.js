import { TOKEN_TIME, TOKEN_TIME_VALUE } from './constant'

// 设置登录时间
export const setTokenTime = () => {
  localStorage.setItem(TOKEN_TIME, Date.now())
}

// 获取token
export const getTokenTime = () => {
  return localStorage.getItem(TOKEN_TIME)
}

// 判断token是否已经过期
export const diffTokenTime = () => {
  const currentTime = Date.now()
  const tokenTime = getTokenTime()
  return currentTime - tokenTime > TOKEN_TIME_VALUE
}
