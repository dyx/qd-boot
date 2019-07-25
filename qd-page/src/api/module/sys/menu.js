import axios from '@/libs/api.request'

export const getMenuTree = params => {
  return axios.request({
    url: 'menu/tree',
    method: 'get',
    params
  })
}

export const getMenuById = id => {
  return axios.request({
    url: 'menu/' + id,
    method: 'get'
  })
}

export const saveMenu = data => {
  return axios.request({
    url: 'menu',
    method: 'post',
    data
  })
}

export const updateMenu = (id, data) => {
  return axios.request({
    url: 'menu/' + id,
    method: 'put',
    data
  })
}

export const removeMenu = id => {
  return axios.request({
    url: 'menu/' + id,
    method: 'delete'
  })
}

export const batchRemoveMenu = idList => {
  return axios.request({
    url: 'menu/batch',
    method: 'delete',
    data: idList
  })
}
