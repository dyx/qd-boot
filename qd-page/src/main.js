import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import iView from 'iview'
import i18n from '@/locale'
import config from '@/config'
import util from '@/libs/util'
import importDirective from '@/directive'
import { directive as clickOutside } from 'v-click-outside-x'
import './index.less'
import '@/assets/icons/iconfont.css'

Vue.use(iView, {
  i18n: (key, value) => i18n.t(key, value)
})
/**
 * @description 生产环境关掉提示
 */
Vue.config.productionTip = false
/**
 * @description 全局注册应用配置
 */
Vue.prototype.$config = config
/**
 * @description 全局注册工具类
 */
Vue.prototype.$util = util
/**
 * 注册指令
 */
importDirective(Vue)
Vue.directive('clickOutside', clickOutside)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  i18n,
  store,
  router,
  render: h => h(App)
})
