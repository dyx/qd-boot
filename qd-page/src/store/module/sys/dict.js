import * as Api from '@/api/module/sys/dict'

export default {
  state: {},
  getters: {},
  mutations: {},
  actions: {
    getDictPage ({ commit }, params) {
      return new Promise((resolve, reject) => {
        Api.getDictPage(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDictPageByTypeCode ({ commit }, { typeCode, params }) {
      return new Promise((resolve, reject) => {
        Api.getDictPageByTypeCode(typeCode, params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDictById ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.getDictById(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    saveDict ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.saveDict(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateDict ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.updateDict(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeDict ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.removeDict(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDictCacheListByTypeCode ({ commit }, typeCode) {
      return new Promise((resolve, reject) => {
        Api.getDictCacheListByTypeCode(typeCode).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateDictCacheByTypeCode ({ commit }, typeCode) {
      return new Promise((resolve, reject) => {
        Api.updateDictCacheByTypeCode(typeCode).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
