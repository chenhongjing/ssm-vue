import request from './request'

export const getSubstitute = (data) => {
  return request({
    url: '/substitute',
    method: 'get'
  })
}
