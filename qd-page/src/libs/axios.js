import axios from 'axios'
import { Notice } from 'iview'
import util from '@/libs/util'
import router from '@/router'
import store from '@/store'

let requestArray = []
let delayTimer = null

class HttpRequest {
  constructor (baseUrl = baseURL) {
    this.baseUrl = baseUrl
    this.queue = {}
  }
  getInsideConfig () {
    const config = {
      baseURL: this.baseUrl,
      headers: {
        Authorization: util.getToken()
      }
    }
    return config
  }
  destroy (url) {
    delete this.queue[url]
  }
  interceptors (instance, url) {
    // 请求拦截
    instance.interceptors.request.use(config => {
      this.showLoading()
      this.queue[url] = true
      return config
    }, error => {
      this.hideLoading()
      return Promise.reject(error)
    })
    // 响应拦截
    instance.interceptors.response.use(res => {
      this.destroy(url)
      this.hideLoading()
      if (res && res.data) {
        if (res.data.code >= 1) {
          return res.data.data
        } else {
          this.errorNotice(res.data.msg)
          return Promise.reject(res)
        }
      } else {
        this.errorNotice('请求异常')
        return Promise.reject(res)
      }
    }, error => {
      this.destroy(url)
      this.hideLoading()
      if (error.response.status === 401) {
        localStorage.clear()
        router.push({ name: 'login' })
      }
      return Promise.reject(error)
    })
  }
  request (options) {
    const instance = axios.create()
    options = Object.assign(this.getInsideConfig(), options)
    this.interceptors(instance, options.url)
    return instance(options)
  }
  errorNotice (msg) {
    Notice.error({ title: '错误提示', desc: msg, duration: 5 })
  }
  showLoading () {
    if (requestArray.length === 0) {
      // 延迟显示
      delayTimer = setTimeout(() => {
        store.commit('setShowRequestLoading', true)
      }, 300)
    }
    requestArray.push(1)
  }
  hideLoading () {
    if (requestArray.length === 1) {
      if (delayTimer) {
        clearTimeout(delayTimer)
        delayTimer = null
      }
      store.commit('setShowRequestLoading', false)
    }
    requestArray.pop()
  }
}
export default HttpRequest
