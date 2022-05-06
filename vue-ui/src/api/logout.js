import request from './request'

export const logout = (data) => {
  return request({
    url: '/logout',
    method: 'get'
  })
}
