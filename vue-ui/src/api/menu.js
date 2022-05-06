import request from './request'

export const menuList = () => {
  return request({
    url: '/menu',
    method: 'get'
  })
}
