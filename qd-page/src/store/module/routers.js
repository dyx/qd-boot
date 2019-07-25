import Main from '@/components/main'
import util from '@/libs/util'

export default {
  state: {
    defaultRouters: [
      {
        path: '/login',
        name: 'login',
        meta: {
          title: '登录',
          hideInMenu: true
        },
        component: () => import('@/view/login/login.vue')
      },
      {
        path: '/',
        name: '_home',
        redirect: '/home',
        component: Main,
        meta: {
          hideInMenu: true,
          notCache: true
        },
        children: [
          {
            path: '/home',
            name: 'home',
            meta: {
              hideInMenu: true,
              title: '首页',
              notCache: true,
              icon: 'md-home'
            },
            component: () => import('@/view/home')
          }
        ]
      }
    ],
    errorRouters: [
      {
        path: '/401',
        name: 'error_401',
        meta: {
          hideInMenu: true
        },
        component: () => import('@/view/error-page/401.vue')
      },
      {
        path: '/500',
        name: 'error_500',
        meta: {
          hideInMenu: true
        },
        component: () => import('@/view/error-page/500.vue')
      },
      {
        path: '*',
        name: 'error_404',
        meta: {
          hideInMenu: true
        },
        component: () => import('@/view/error-page/404.vue')
      }
    ]
  },
  getters: {
    getRouters: (state) => () => [...state.defaultRouters, ...util.buildUserRoute(), ...state.errorRouters]
  },
  mutations: {

  }
}
