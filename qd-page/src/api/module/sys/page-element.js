import axios from '@/libs/api.request'

export const getPageElementByMenuId = (menuId, params) => {
  return axios.request({
    url: 'menu/' + menuId + '/page-element',
    method: 'get',
    params
  })
}

export const getPageElementById = id => {
  return axios.request({
    url: 'menu/page-element/' + id,
    method: 'get'
  })
}

export const savePageElement = (menuId, data) => {
  return axios.request({
    url: 'menu/' + menuId + '/page-element',
    method: 'post',
    data
  })
}

export const updatePageElement = (id, data) => {
  return axios.request({
    url: 'menu/page-element/' + id,
    method: 'put',
    data
  })
}

export const removePageElement = id => {
  return axios.request({
    url: 'menu/page-element/' + id,
    method: 'delete'
  })
}

export const batchRemovePageElement = idList => {
  return axios.request({
    url: 'menu/page-element/batch',
    method: 'delete',
    data: idList
  })
}
