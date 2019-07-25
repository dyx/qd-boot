import Cookies from 'js-cookie'
import config from '@/config'
import { forEach, objEqual } from '@/libs/tools'
const { title, useI18n } = config

const USER_TOKEN = 'token'
const LOCAL_STORAGE_KEY_USER_MENU = 'userMenu'
const LOCAL_STORAGE_KEY_USER_PAGE_ELEMENT = 'userPageElement'
const LOCAL_STORAGE_KEY_ID = 'id'
const LOCAL_STORAGE_KEY_NAME = 'name'
const LOCAL_STORAGE_KEY_DEPT_ID = 'deptId'

const localSave = (key, value) => {
  localStorage.setItem(key, value)
}

const localRead = (key) => {
  return localStorage.getItem(key) || ''
}

const setToken = (token) => {
  let time = new Date(new Date().getTime() + (10 * 60 * 60 * 1000))
  Cookies.set(USER_TOKEN, token, { expires: time })
}

const getToken = () => {
  return Cookies.get(USER_TOKEN)
}

const removeToken = () => {
  return Cookies.remove(USER_TOKEN)
}

const setId = (id) => {
  localSave(LOCAL_STORAGE_KEY_ID, id)
}

const getId = () => {
  return localRead(LOCAL_STORAGE_KEY_ID)
}

const setName = (name) => {
  localSave(LOCAL_STORAGE_KEY_NAME, name)
}

const getName = () => {
  return localRead(LOCAL_STORAGE_KEY_NAME)
}

const setDeptId = (deptId) => {
  localSave(LOCAL_STORAGE_KEY_DEPT_ID, deptId)
}

const getDeptId = () => {
  return localRead(LOCAL_STORAGE_KEY_DEPT_ID)
}

/**
 * 存储后台菜单数据
 * @param menuList
 */
const setUserMenu = (menuList) => {
  if (menuList) {
    localSave(LOCAL_STORAGE_KEY_USER_MENU, JSON.stringify(menuList))
  }
}

const getUserMenu = () => {
  let userMenuStr = localRead(LOCAL_STORAGE_KEY_USER_MENU)
  return userMenuStr ? JSON.parse(userMenuStr) : []
}

/**
 * 存储后台页面元素权限数据
 * @param pageElementMap
 */
const setUserPageElement = (pageElementMap) => {
  if (pageElementMap) {
    localSave(LOCAL_STORAGE_KEY_USER_PAGE_ELEMENT, JSON.stringify(pageElementMap))
  }
}

const getUserPageElementMap = () => {
  let pageElementStr = localRead(LOCAL_STORAGE_KEY_USER_PAGE_ELEMENT)
  return pageElementStr ? objToMap(JSON.parse(pageElementStr)) : []
}

const hasChild = (item) => {
  return item.children && item.children.length !== 0
}

/**
 * @param {Array} list 通过路由列表得到菜单列表
 * @returns {Array}
 */
const getMenuByRouter = (list) => {
  let res = []
  forEach(list, item => {
    if (!item.meta || (item.meta && !item.meta.hideInMenu)) {
      let obj = {
        icon: (item.meta && item.meta.icon) || '',
        name: item.name,
        meta: item.meta
      }
      if ((hasChild(item) || (item.meta && item.meta.showAlways))) {
        obj.children = getMenuByRouter(item.children)
      }
      if (item.meta && item.meta.href) obj.href = item.meta.href
      res.push(obj)
    }
  })
  return res
}

/**
 * @param {Array} routeMetched 当前路由metched
 * @returns {Array}
 */
const getBreadCrumbList = (route, homeRoute) => {
  let homeItem = { ...homeRoute, icon: homeRoute.meta.icon }
  let routeMetched = route.matched
  if (routeMetched.some(item => item.name === homeRoute.name)) return [homeItem]
  let res = routeMetched.filter(item => {
    return item.meta === undefined || !item.meta.hideInBread
  }).map(item => {
    let meta = { ...item.meta }
    if (meta.title && typeof meta.title === 'function') {
      meta.__titleIsFunction__ = true
      meta.title = meta.title(route)
    }
    let obj = {
      icon: (item.meta && item.meta.icon) || '',
      name: item.name,
      meta: meta
    }
    return obj
  })
  res = res.filter(item => {
    return !item.meta.hideInMenu
  })
  return [{ ...homeItem, to: homeRoute.path }, ...res]
}

const getRouteTitleHandled = (route) => {
  let router = { ...route }
  let meta = { ...route.meta }
  let title = ''
  if (meta.title) {
    if (typeof meta.title === 'function') {
      meta.__titleIsFunction__ = true
      title = meta.title(router)
    } else title = meta.title
  }
  meta.title = title
  router.meta = meta
  return router
}

const showTitle = (item, vm) => {
  let { title, __titleIsFunction__ } = item.meta
  if (!title) return
  if (useI18n) {
    if (title.includes('{{') && title.includes('}}') && useI18n) title = title.replace(/({{[\s\S]+?}})/, (m, str) => str.replace(/{{([\s\S]*)}}/, (m, _) => vm.$t(_.trim())))
    else if (__titleIsFunction__) title = item.meta.title
    else title = vm.$t(item.name)
  } else title = (item.meta && item.meta.title) || item.name
  return title
}

/**
 * @description 本地存储和获取标签导航列表
 */
const setTagNavListInLocalstorage = list => {
  localStorage.tagNaveList = JSON.stringify(list)
}
/**
 * @returns {Array} 其中的每个元素只包含路由原信息中的name, path, meta三项
 */
const getTagNavListFromLocalstorage = () => {
  const list = localStorage.tagNaveList
  return list ? JSON.parse(list) : []
}

/**
 * @param {Array} routers 路由列表数组
 * @description 用于找到路由列表中name为home的对象
 */
const getHomeRoute = (routers, homeName = 'home') => {
  let i = -1
  let len = routers.length
  let homeRoute = {}
  while (++i < len) {
    let item = routers[i]
    if (item.children && item.children.length) {
      let res = getHomeRoute(item.children, homeName)
      if (res.name) return res
    } else {
      if (item.name === homeName) homeRoute = item
    }
  }
  return homeRoute
}

/**
 * @param {*} list 现有标签导航列表
 * @param {*} newRoute 新添加的路由原信息对象
 * @description 如果该newRoute已经存在则不再添加
 */
const getNewTagList = (list, newRoute) => {
  const { name, path, meta } = newRoute
  let newList = [...list]
  if (newList.findIndex(item => item.name === name) >= 0) return newList
  else newList.push({ name, path, meta })
  return newList
}

/**
 * @param {String} url
 * @description 从URL中解析参数
 */
const getParams = url => {
  const keyValueArr = url.split('?')[1].split('&')
  let paramObj = {}
  keyValueArr.forEach(item => {
    const keyValue = item.split('=')
    paramObj[keyValue[0]] = keyValue[1]
  })
  return paramObj
}

/**
 * @param {Array} list 标签列表
 * @param {String} name 当前关闭的标签的name
 */
const getNextRoute = (list, route) => {
  let res = {}
  if (list.length === 2) {
    res = getHomeRoute(list)
  } else {
    const index = list.findIndex(item => routeEqual(item, route))
    if (index === list.length - 1) res = list[list.length - 2]
    else res = list[index + 1]
  }
  return res
}

/**
 * @param {Number} times 回调函数需要执行的次数
 * @param {Function} callback 回调函数
 */
const doCustomTimes = (times, callback) => {
  let i = -1
  while (++i < times) {
    callback(i)
  }
}

/**
 * @param {Object} file 从上传组件得到的文件对象
 * @returns {Promise} resolve参数是解析后的二维数组
 * @description 从Csv文件中解析出表格，解析成二维数组
 */
const getArrayFromFile = (file) => {
  let nameSplit = file.name.split('.')
  let format = nameSplit[nameSplit.length - 1]
  return new Promise((resolve, reject) => {
    let reader = new FileReader()
    reader.readAsText(file) // 以文本格式读取
    let arr = []
    reader.onload = function (evt) {
      let data = evt.target.result // 读到的数据
      let pasteData = data.trim()
      arr = pasteData.split((/[\n\u0085\u2028\u2029]|\r\n?/g)).map(row => {
        return row.split('\t')
      }).map(item => {
        return item[0].split(',')
      })
      if (format === 'csv') resolve(arr)
      else reject(new Error('[Format Error]:你上传的不是Csv文件'))
    }
  })
}

/**
 * @param {Array} array 表格数据二维数组
 * @returns {Object} { columns, tableData }
 * @description 从二维数组中获取表头和表格数据，将第一行作为表头，用于在iView的表格中展示数据
 */
const getTableDataFromArray = (array) => {
  let columns = []
  let tableData = []
  if (array.length > 1) {
    let titles = array.shift()
    columns = titles.map(item => {
      return {
        title: item,
        key: item
      }
    })
    tableData = array.map(item => {
      let res = {}
      item.forEach((col, i) => {
        res[titles[i]] = col
      })
      return res
    })
  }
  return {
    columns,
    tableData
  }
}

const findNodeUpper = (ele, tag) => {
  if (ele.parentNode) {
    if (ele.parentNode.tagName === tag.toUpperCase()) {
      return ele.parentNode
    } else {
      return findNodeUpper(ele.parentNode, tag)
    }
  }
}

const findNodeUpperByClasses = (ele, classes) => {
  let parentNode = ele.parentNode
  if (parentNode) {
    let classList = parentNode.classList
    if (classList && classes.every(className => classList.contains(className))) {
      return parentNode
    } else {
      return findNodeUpperByClasses(parentNode, classes)
    }
  }
}

const findNodeDownward = (ele, tag) => {
  const tagName = tag.toUpperCase()
  if (ele.childNodes.length) {
    let i = -1
    let len = ele.childNodes.length
    while (++i < len) {
      let child = ele.childNodes[i]
      if (child.tagName === tagName) return child
      else return findNodeDownward(child, tag)
    }
  }
}

/**
 * @description 根据name/params/query判断两个路由对象是否相等
 * @param {*} route1 路由对象
 * @param {*} route2 路由对象
 */
const routeEqual = (route1, route2) => {
  const params1 = route1.params || {}
  const params2 = route2.params || {}
  const query1 = route1.query || {}
  const query2 = route2.query || {}
  return (route1.name === route2.name) && objEqual(params1, params2) && objEqual(query1, query2)
}

/**
 * 判断打开的标签列表里是否已存在这个新添加的路由对象
 */
const routeHasExist = (tagNavList, routeItem) => {
  let len = tagNavList.length
  let res = false
  doCustomTimes(len, (index) => {
    if (routeEqual(tagNavList[index], routeItem)) res = true
  })
  return res
}

// scrollTop animation
const scrollTop = (el, from = 0, to, duration = 500, endCallback) => {
  if (!window.requestAnimationFrame) {
    window.requestAnimationFrame = (
      window.webkitRequestAnimationFrame ||
      window.mozRequestAnimationFrame ||
      window.msRequestAnimationFrame ||
      function (callback) {
        return window.setTimeout(callback, 1000 / 60)
      }
    )
  }
  const difference = Math.abs(from - to)
  const step = Math.ceil(difference / duration * 50)

  const scroll = (start, end, step) => {
    if (start === end) {
      endCallback && endCallback()
      return
    }

    let d = (start + step > end) ? end : start + step
    if (start > end) {
      d = (start - step < end) ? end : start - step
    }

    if (el === window) {
      window.scrollTo(d, d)
    } else {
      el.scrollTop = d
    }
    window.requestAnimationFrame(() => scroll(d, end, step))
  }
  scroll(from, to, step)
}

/**
 * @description 根据当前跳转的路由设置显示在浏览器标签的title
 * @param {Object} routeItem 路由对象
 * @param {Object} vm Vue实例
 */
const setTitle = (routeItem, vm) => {
  const handledRoute = getRouteTitleHandled(routeItem)
  const pageTitle = showTitle(handledRoute, vm)
  const resTitle = pageTitle ? `${title} - ${pageTitle}` : title
  window.document.title = resTitle
}

/**
 * 根据后台菜单数据构造路由树
 * @returns {Array}
 */
const buildUserRoute = () => {
  let menuList = getUserMenu()
  if (!menuList || menuList.length === 0) {
    return []
  }
  let routerArray = []
  for (let item of menuList) {
    let router = { meta: {} }
    router.meta.title = item.menuName
    router.meta.icon = item.iconName
    router.meta.id = item.id
    router.name = item.pageRouterName
    router.path = '/' + item.pageRouterName
    router.component = () => import('@/components/main')
    router.children = getUserChildrenRoute(item.children)
    routerArray.push(router)
  }
  return routerArray
}

const getUserChildrenRoute = (menuList) => {
  if (!menuList) {
    return []
  }
  let routerArray = []
  for (let item of menuList) {
    let router = { meta: {} }
    router.meta.title = item.menuName
    router.meta.id = item.id
    router.name = item.pageRouterName
    router.path = item.pageRouterName
    let pagePath = item.pagePath
    if (pagePath) {
      pagePath = (pagePath.startsWith('/') ? '' : '/') + pagePath + (pagePath.endsWith('/') ? '' : '/')
      router.component = () => import('@/view/module' + pagePath + item.pageRouterName + '.vue')
    }
    router.children = getUserChildrenRoute(item.children)
    routerArray.push(router)
  }
  return routerArray
}

const objToMap = (obj) => {
  let resultMap = new Map()
  for (let [k, v] of Object.entries(obj)) {
    if (v instanceof Object && !(v instanceof Array)) {
      resultMap.set(k, objToMap(v))
    } else {
      resultMap.set(k, v)
    }
  }
  return resultMap
}

/**
 * 是否有页面元素权限
 * @param menuId
 * @param elementCode
 * @returns {*}
 */
const hasPermission = (menuId, elementCode) => {
  let pageElementMap = getUserPageElementMap()
  if (pageElementMap) {
    let elementList = pageElementMap.get(menuId)
    return elementList && elementList.indexOf(elementCode) >= 0
  }
  return false
}

const util = {}
util.localSave = localSave
util.localRead = localRead
util.setToken = setToken
util.getToken = getToken
util.removeToken = removeToken
util.setId = setId
util.getId = getId
util.setName = setName
util.getName = getName
util.setDeptId = setDeptId
util.getDeptId = getDeptId
util.setUserMenu = setUserMenu
util.setUserPageElement = setUserPageElement
util.getMenuByRouter = getMenuByRouter
util.getBreadCrumbList = getBreadCrumbList
util.getRouteTitleHandled = getRouteTitleHandled
util.showTitle = showTitle
util.setTagNavListInLocalstorage = setTagNavListInLocalstorage
util.getTagNavListFromLocalstorage = getTagNavListFromLocalstorage
util.getHomeRoute = getHomeRoute
util.getNewTagList = getNewTagList
util.getNextRoute = getNextRoute
util.getArrayFromFile = getArrayFromFile
util.getTableDataFromArray = getTableDataFromArray
util.findNodeUpper = findNodeUpper
util.findNodeUpperByClasses = findNodeUpperByClasses
util.routeEqual = routeEqual
util.routeHasExist = routeHasExist
util.scrollTop = scrollTop
util.setTitle = setTitle
util.buildUserRoute = buildUserRoute
util.hasPermission = hasPermission

export default util
