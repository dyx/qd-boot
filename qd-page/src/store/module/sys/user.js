import * as Api from '@/api/module/sys/user'
export default {
  state: {},
  mutations: {},
  actions: {
    getUserPage ({ commit }, params) {
      return new Promise((resolve, reject) => {
        Api.getUserPage(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getUserRefPage ({ commit }, params) {
      return new Promise((resolve, reject) => {
        Api.getUserRefPage(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getUserById ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.getUserById(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    saveUser ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.saveUser(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateUser ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.updateUser(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeUser ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.removeUser(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveUser ({ commit }, idList) {
      return new Promise((resolve, reject) => {
        Api.batchRemoveUser(idList).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getUserAssignRoleList ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.getUserAssignRoleList(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    assignUserRole ({ commit }, { id, roleIdList }) {
      return new Promise((resolve, reject) => {
        Api.assignUserRole(id, roleIdList).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
