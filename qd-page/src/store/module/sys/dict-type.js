import * as Api from '@/api/module/sys/dict-type'

export default {
  state: {},
  getters: {},
  mutations: {},
  actions: {
    getDictTypePage ({ commit }, params) {
      return new Promise((resolve, reject) => {
        Api.getDictTypePage(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDictTypeById ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.getDictTypeById(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    saveDictType ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.saveDictType(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateDictType ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.updateDictType(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeDictType ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.removeDictType(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
