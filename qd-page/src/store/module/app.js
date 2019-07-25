import router from '@/router'
import config from '@/config'
import util from '@/libs/util'
const { homeName } = config

const closePage = (state, route) => {
  const nextRoute = util.getNextRoute(state.tagNavList, route)
  state.tagNavList = state.tagNavList.filter(item => {
    return !util.routeEqual(item, route)
  })
  router.push(nextRoute)
}

export default {
  state: {
    breadCrumbList: [],
    tagNavList: [],
    homeRoute: {},
    local: util.localRead('local'),
    showRequestLoading: false
  },
  getters: {
    menuList: (state, getters, rootState, rootGetters) => () => util.getMenuByRouter(rootGetters.getRouters())
  },
  mutations: {
    setBreadCrumb (state, route) {
      state.breadCrumbList = util.getBreadCrumbList(route, state.homeRoute)
    },
    setHomeRoute (state, routes) {
      state.homeRoute = util.getHomeRoute(routes, homeName)
    },
    setTagNavList (state, list) {
      let tagList = []
      if (list) {
        tagList = [...list]
      } else tagList = util.getTagNavListFromLocalstorage() || []
      if (tagList[0] && tagList[0].name !== homeName) tagList.shift()
      let homeTagIndex = tagList.findIndex(item => item.name === homeName)
      if (homeTagIndex > 0) {
        let homeTag = tagList.splice(homeTagIndex, 1)[0]
        tagList.unshift(homeTag)
      }
      state.tagNavList = tagList
      util.setTagNavListInLocalstorage([...tagList])
    },
    closeTag (state, route) {
      let tag = state.tagNavList.filter(item => util.routeEqual(item, route))
      route = tag[0] ? tag[0] : null
      if (!route) return
      closePage(state, route)
    },
    addTag (state, { route, type = 'unshift' }) {
      let router = util.getRouteTitleHandled(route)
      if (!util.routeHasExist(state.tagNavList, router)) {
        if (type === 'push') state.tagNavList.push(router)
        else {
          if (router.name === homeName) state.tagNavList.unshift(router)
          else state.tagNavList.splice(1, 0, router)
        }
        util.setTagNavListInLocalstorage([...state.tagNavList])
      }
    },
    setLocal (state, lang) {
      util.localSave('local', lang)
      state.local = lang
    },
    setShowRequestLoading (state, visible) {
      state.showRequestLoading = visible
    }
  },
  actions: {}
}
