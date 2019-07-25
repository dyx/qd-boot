import { getDictCacheMap } from '@/api/module/sys/dict'

const getDictDesc = (dict, value) => {
  if (dict) {
    let item = dict.find(item => item.value === value)
    return item ? item.desc : ''
  }
  return ''
}

/**
 * 字典结构
 * code: {
 *  list: [
 *    { value: 1, name: '描述1' },
 *    { value: 2, name: '描述2' }
 *  ]
 * }
 */
export default {
  state: {
    gender: [],
    menuType: [],
    pageElementMethod: [],
    pageElementType: []
  },
  getters: {
    genderDict: state => state.gender,
    genderDesc: state => (value) => getDictDesc(state.gender, value),
    menuTypeDict: state => state.menuType,
    menuTypeDesc: state => (value) => getDictDesc(state.menuType, value),
    pageElementMethodDict: state => state.pageElementMethod,
    pageElementMethodDesc: state => (value) => getDictDesc(state.pageElementMethod, value),
    pageElementTypeDict: state => state.pageElementType,
    pageElementTypeDesc: state => (value) => getDictDesc(state.pageElementType, value)
  },
  mutations: {
    setDictData (state, dictData) {
      Object.assign(state, dictData)
    }
  },
  actions: {
    initDictData ({ state, commit }) {
      return new Promise((resolve, reject) => {
        getDictCacheMap(Object.keys(state)).then(res => {
          commit('setDictData', res)
          resolve()
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
