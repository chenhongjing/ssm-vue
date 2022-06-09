import request from './request'

// users info

export const getUsers = (params) => {
  return request({
    url: '/management/user',
    method: 'get',
    params: params
  })
}

export const editUser = (data) => {
  return request({
    url: `/management/user/${data.userId}`,
    method: 'put',
    data: data
  })
}

export const deleteUser = (id) => {
  return request({
    url: `/management/user/${id}`,
    method: 'delete'
  })
}

// logs

export const getLogs = (params) => {
  return request({
    url: '/management/log',
    method: 'get',
    params: params
  })
}
