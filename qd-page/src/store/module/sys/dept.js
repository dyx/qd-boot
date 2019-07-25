import * as Api from '@/api/module/sys/dept'
export default {
  state: {},
  mutations: {},
  actions: {
    getDeptTreeByCompanyId ({ commit }, companyId) {
      return new Promise((resolve, reject) => {
        Api.getDeptTreeByCompanyId(companyId).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDeptRefTreeByCompanyId ({ commit }, companyId) {
      return new Promise((resolve, reject) => {
        Api.getDeptRefTreeByCompanyId(companyId).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDeptById ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.getDeptById(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    saveDept ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.saveDept(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateDept ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.updateDept(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeDept ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.removeDept(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
