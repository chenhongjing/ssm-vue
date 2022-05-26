import request from './request'

export const addOrganRecord = (data) => {
  return request({
    url: '/organ',
    method: 'post',
    data: data
  })
}

export const getOrganRecords = (params) => {
  return request({
    url: '/organ',
    method: 'get',
    params: params
  })
}

export const deleteOrganRecord = (id) => {
  return request({
    url: `/organ/${id}`,
    method: 'delete'
  })
}

export const editOrganRecord = (data) => {
  return request({
    url: `/organ/${data.organId}`,
    method: 'put',
    data: data
  })
}
