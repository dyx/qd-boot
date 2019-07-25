import axios from '@/libs/api.request'

export const getCustomPage = params => {
  return axios.request({
    url: 'custom',
    method: 'get',
    params
  })
}

export const getCustomById = id => {
  return axios.request({
    url: 'custom/' + id,
    method: 'get'
  })
}

export const saveCustom = data => {
  return axios.request({
    url: 'custom',
    method: 'post',
    data
  })
}

export const updateCustom = (id, data) => {
  return axios.request({
    url: 'custom/' + id,
    method: 'put',
    data
  })
}

export const removeCustom = id => {
  return axios.request({
    url: 'custom/' + id,
    method: 'delete'
  })
}

export const batchRemoveCustom = idList => {
  return axios.request({
    url: 'custom/batch',
    method: 'delete',
    data: idList
  })
}
