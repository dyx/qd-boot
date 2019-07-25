import axios from '@/libs/api.request'

export const getDictPage = params => {
  return axios.request({
    url: 'dict',
    method: 'get',
    params
  })
}

export const getDictPageByTypeCode = (typeCode, params) => {
  return axios.request({
    url: 'dict/code/' + typeCode,
    method: 'get',
    params
  })
}

export const getDictById = id => {
  return axios.request({
    url: 'dict/' + id,
    method: 'get'
  })
}

export const saveDict = data => {
  return axios.request({
    url: 'dict',
    method: 'post',
    data
  })
}

export const updateDict = (id, data) => {
  return axios.request({
    url: 'dict/' + id,
    method: 'put',
    data
  })
}

export const removeDict = id => {
  return axios.request({
    url: 'dict/' + id,
    method: 'delete'
  })
}

export const getDictCacheMap = codeList => {
  return axios.request({
    url: 'dict/page/cache',
    method: 'post',
    data: codeList
  })
}

export const getDictCacheListByTypeCode = typeCode => {
  return axios.request({
    url: 'dict/code/' + typeCode + '/cache',
    method: 'get'
  })
}

export const updateDictCacheByTypeCode = typeCode => {
  return axios.request({
    url: 'dict/code/' + typeCode + '/cache',
    method: 'put'
  })
}
