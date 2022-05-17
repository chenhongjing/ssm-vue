import request from './request'

export const addDynamicParamRecord = (category, data) => {
  return request({
    url: `/dynamic_param/${category}`,
    method: 'post',
    data: data
  })
}

export const getDynamicParamRecords = (category, params) => {
  return request({
    url: `/dynamic_param/${category}`,
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
