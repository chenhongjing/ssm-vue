import request from './request'

export const getUserInfo = () => {
  return request({
    url: '/user',
    method: 'get'
  })
}

export const editUserInfo = (data) => {
  return request({
    url: '/user',
    method: 'put',
    data: data
  })
}
