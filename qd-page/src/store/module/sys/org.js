import * as Api from '@/api/module/sys/org'
export default {
  state: {},
  mutations: {},
  actions: {
    getOrgTree ({ commit }) {
      return new Promise((resolve, reject) => {
        Api.getOrgTree().then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
