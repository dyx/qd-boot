import axios from '@/libs/api.request'

export const getCompanyTree = () => {
  return axios.request({
    url: 'org/company/tree',
    method: 'get'
  })
}

export const getCompanyRefTree = () => {
  return axios.request({
    url: 'org/company/ref/tree',
    method: 'get'
  })
}

export const getCompanyById = id => {
  return axios.request({
    url: 'org/company/' + id,
    method: 'get'
  })
}

export const saveCompany = data => {
  return axios.request({
    url: 'org/company',
    method: 'post',
    data
  })
}

export const updateCompany = (id, data) => {
  return axios.request({
    url: 'org/company/' + id,
    method: 'put',
    data
  })
}

export const removeCompany = id => {
  return axios.request({
    url: 'org/company/' + id,
    method: 'delete'
  })
}
