import * as Api from '@/api/module/sys/role'
export default {
  state: {},
  mutations: {},
  actions: {
    getRolePage ({ commit }, params) {
      return new Promise((resolve, reject) => {
        Api.getRolePage(params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getRoleById ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.getRoleById(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    saveRole ({ commit }, data) {
      return new Promise((resolve, reject) => {
        Api.saveRole(data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateRole ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.updateRole(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeRole ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.removeRole(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getUserPageByRoleId ({ commit }, { id, params }) {
      return new Promise((resolve, reject) => {
        Api.getUserPageByRoleId(id, params).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getResourceByRoleId ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.getResourceByRoleId(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getResourceCheckedByRoleId ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.getResourceCheckedByRoleId(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    assignRoleResource ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.assignRoleResource(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getRoleDataCheckedByRoleId ({ commit }, id) {
      return new Promise((resolve, reject) => {
        Api.getRoleDataCheckedByRoleId(id).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    assignRoleData ({ commit }, { id, data }) {
      return new Promise((resolve, reject) => {
        Api.assignRoleData(id, data).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
