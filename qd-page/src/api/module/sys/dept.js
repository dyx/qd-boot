import axios from '@/libs/api.request'

export const getDeptTreeByCompanyId = companyId => {
  return axios.request({
    url: 'org/dept/tree/company/' + companyId,
    method: 'get'
  })
}

export const getDeptRefTreeByCompanyId = companyId => {
  return axios.request({
    url: 'org/dept/ref/tree/company/' + companyId,
    method: 'get'
  })
}

export const getDeptById = id => {
  return axios.request({
    url: 'org/dept/' + id,
    method: 'get'
  })
}

export const saveDept = data => {
  return axios.request({
    url: 'org/dept',
    method: 'post',
    data
  })
}

export const updateDept = (id, data) => {
  return axios.request({
    url: 'org/dept/' + id,
    method: 'put',
    data
  })
}

export const removeDept = id => {
  return axios.request({
    url: 'org/dept/' + id,
    method: 'delete'
  })
}
