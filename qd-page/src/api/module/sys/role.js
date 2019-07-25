import axios from '@/libs/api.request'

export const getRolePage = params => {
  return axios.request({
    url: 'role',
    method: 'get',
    params
  })
}

export const getRoleById = id => {
  return axios.request({
    url: 'role/' + id,
    method: 'get'
  })
}

export const saveRole = data => {
  return axios.request({
    url: 'role',
    method: 'post',
    data
  })
}

export const updateRole = (id, data) => {
  return axios.request({
    url: 'role/' + id,
    method: 'put',
    data
  })
}

export const removeRole = id => {
  return axios.request({
    url: 'role/' + id,
    method: 'delete'
  })
}

export const getUserPageByRoleId = (id, params) => {
  return axios.request({
    url: 'role/' + id + '/user',
    method: 'get',
    params
  })
}

export const getResourceByRoleId = (id) => {
  return axios.request({
    url: 'role/' + id + '/resource',
    method: 'get'
  })
}

export const getResourceCheckedByRoleId = (id) => {
  return axios.request({
    url: 'role/' + id + '/resource/checked',
    method: 'get'
  })
}

export const assignRoleResource = (id, data) => {
  return axios.request({
    url: 'role/' + id + '/resource',
    method: 'post',
    data
  })
}

export const getRoleDataCheckedByRoleId = (id) => {
  return axios.request({
    url: 'role/' + id + '/data/checked',
    method: 'get'
  })
}

export const assignRoleData = (id, data) => {
  return axios.request({
    url: 'role/' + id + '/data',
    method: 'post',
    data
  })
}
