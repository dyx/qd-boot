import axios from '@/libs/api.request'

export const getDictTypePage = params => {
  return axios.request({
    url: 'dict/type',
    method: 'get',
    params
  })
}

export const getDictTypeById = id => {
  return axios.request({
    url: 'dict/type/' + id,
    method: 'get'
  })
}

export const saveDictType = data => {
  return axios.request({
    url: 'dict/type',
    method: 'post',
    data
  })
}

export const updateDictType = (id, data) => {
  return axios.request({
    url: 'dict/type/' + id,
    method: 'put',
    data
  })
}

export const removeDictType = id => {
  return axios.request({
    url: 'dict/type/' + id,
    method: 'delete'
  })
}
