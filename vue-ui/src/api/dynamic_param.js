import request from './request'

export const addDynamicParamRecord = (data) => {
  return request({
    url: '/dynamic_param',
    method: 'post',
    data: data
  })
}

export const getDynamicParamRecords = (params) => {
  return request({
    url: '/dynamic_param',
    method: 'get',
    params: params
  })
}

export const deleteDynamicParamRecord = (id) => {
  return request({
    url: `/dynamic_param/${id}`,
    method: 'delete'
  })
}

export const editDynamicParamRecord = (data) => {
  return request({
    url: `/dynamic_param/${data.paramId}`,
    method: 'put',
    data: data
  })
}
