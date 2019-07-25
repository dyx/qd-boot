import util from '@/libs/util'

export default {
  inserted: (el, binding) => {
    if (!el.__vue__) {
      alert('绑定指令[v-auth="\'' + binding.value + '\'"]的标签错误，可选标签为[Button, QdAButton, TabPane]')
      return
    }
    let menuId = el.__vue__._routerRoot._route.meta.id
    let tag = el.__vue__.$vnode.tag.replace(/vue-component-\d+-/g, '')
    let hasPermission = util.hasPermission(menuId, binding.value)
    if (tag === 'Button' || tag === 'QdAButton') {
      // 禁用按钮
      if (!hasPermission) {
        el.setAttribute('disabled', true)
      }
    } else if (tag === 'TabPane') {
      // 隐藏页签
      if (!hasPermission) {

      }
    }
  }
}
