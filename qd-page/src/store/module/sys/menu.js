import * as Api from '@/api/module/sys/menu'
export default {
  state: {},
  mutations: {},
  actions: {
    getMenuTree ({ commit }, params) {
      return new Promise((resolve, reject) => {
        Api.getMenuTree(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getMenuById ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.getMenuById(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    saveMenu ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.saveMenu(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateMenu ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.updateMenu(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeMenu ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.removeMenu(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveMenu ({ commit }, idList) {
      return new Promise((resolve, reject) => {
        Api.batchRemoveMenu(idList).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })``
      })
    }
  }
}
