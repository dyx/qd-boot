import axios from '@/libs/api.request'

export const getOrgTree = () => {
  return axios.request({
    url: 'org/tree',
    method: 'get'
  })
}
