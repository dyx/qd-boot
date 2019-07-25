import * as Api from '@/api/module/sys/company'
export default {
  state: {},
  mutations: {},
  actions: {
    getCompanyTree ({ commit }) {
      return new Promise((resolve, reject) => {
        Api.getCompanyTree().then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getCompanyRefTree ({ commit }) {
      return new Promise((resolve, reject) => {
        Api.getCompanyRefTree().then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getCompanyById ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.getCompanyById(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    saveCompany ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.saveCompany(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateCompany ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.updateCompany(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeCompany ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.removeCompany(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
