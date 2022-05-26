import request from './request'

export const register = (data) => {
  return request({
    url: '/register',
    method: 'post',
    data: data
  })
}
