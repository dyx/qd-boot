import * as Api from '@/api/module/sys/page-element'
export default {
  state: {},
  mutations: {},
  actions: {
    getPageElementByMenuId ({ commit }, { menuId, params }) {
      return new Promise((resolve, reject) => {
        Api.getPageElementByMenuId(menuId, params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getPageElementById ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.getPageElementById(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    savePageElement ({ commit }, { menuId, data }) {
      return new Promise((resolve, reject) => {
        Api.savePageElement(menuId, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updatePageElement ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.updatePageElement(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removePageElement ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.removePageElement(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemovePageElement ({ commit }, idList) {
      return new Promise((resolve, reject) => {
        Api.batchRemovePageElement(idList).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
