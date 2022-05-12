import request from './request'

export const addSubstituteRecord = (data) => {
  return request({
    url: '/substitute',
    method: 'post',
    data: data
  })
}

export const getSubstituteRecords = (params) => {
  return request({
    url: '/substitute',
    method: 'get',
    params: params
  })
}

export const deleteSubstituteRecord = (id) => {
  return request({
    url: `/substitute/${id}`,
    method: 'delete'
  })
}

export const editSubstituteRecord = (data) => {
  return request({
    url: `/substitute/${data.materialId}`,
    method: 'put',
    data: data
  })
}
