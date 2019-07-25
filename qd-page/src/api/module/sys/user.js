import axios from '@/libs/api.request'

export const getUserPage = params => {
  return axios.request({
    url: 'user',
    method: 'get',
    params
  })
}

export const getUserRefPage = params => {
  return axios.request({
    url: 'user/ref',
    method: 'get',
    params
  })
}

export const getUserById = id => {
  return axios.request({
    url: 'user/' + id,
    method: 'get'
  })
}

export const saveUser = data => {
  return axios.request({
    url: 'user',
    method: 'post',
    data
  })
}

export const updateUser = (id, data) => {
  return axios.request({
    url: 'user/' + id,
    method: 'put',
    data
  })
}

export const removeUser = id => {
  return axios.request({
    url: 'user/' + id,
    method: 'delete'
  })
}

export const batchRemoveUser = idList => {
  return axios.request({
    url: 'user/batch',
    method: 'delete',
    data: idList
  })
}

export const getUserAssignRoleList = id => {
  return axios.request({
    url: 'user/' + id + '/role/assign',
    method: 'get'
  })
}

export const assignUserRole = (id, roleIdList) => {
  return axios.request({
    url: 'user/' + id + '/role/assign',
    method: 'put',
    data: roleIdList
  })
}
