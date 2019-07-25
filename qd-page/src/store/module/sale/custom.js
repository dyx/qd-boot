import * as Api from '@/api/module/sale/custom'
export default {
  state: {},
  mutations: {},
  actions: {
    getCustomPage ({ commit }, params) {
      return new Promise((resolve, reject) => {
        Api.getCustomPage(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getCustomById ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.getCustomById(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    saveCustom ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.saveCustom(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateCustom ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.updateCustom(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeCustom ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.removeCustom(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveCustom ({ commit }, idList) {
      return new Promise((resolve, reject) => {
        Api.batchRemoveCustom(idList).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
