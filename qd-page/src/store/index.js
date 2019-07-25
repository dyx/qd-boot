import Vue from 'vue'
import Vuex from 'vuex'

import app from './module/app'
import routers from './module/routers'
import login from './module/sys/login'
import user from './module/sys/user'
import dictCache from './module/sys/dict-cache'
import dictType from './module/sys/dict-type'
import dict from './module/sys/dict'
import menu from './module/sys/menu'
import pageElement from './module/sys/page-element'
import role from './module/sys/role'
import company from './module/sys/company'
import dept from './module/sys/dept'
import custom from './module/sale/custom'
import org from './module/sys/org'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //
  },
  mutations: {
    //
  },
  actions: {
    //
  },
  modules: {
    org,
    custom,
    dept,
    company,
    role,
    pageElement,
    menu,
    dict,
    dictType,
    dictCache,
    user,
    login,
    routers,
    app
  }
})
