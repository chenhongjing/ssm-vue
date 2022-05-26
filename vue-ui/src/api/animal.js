import request from './request'

export const addAnimalRecord = (data) => {
  return request({
    url: '/animal',
    method: 'post',
    data: data
  })
}

export const getAnimalRecords = (params) => {
  return request({
    url: '/animal',
    method: 'get',
    params: params
  })
}

export const deleteAnimalRecord = (id) => {
  return request({
    url: `/animal/${id}`,
    method: 'delete'
  })
}

export const editAnimalRecord = (data) => {
  return request({
    url: `/animal/${data.animalId}`,
    method: 'put',
    data: data
  })
}
