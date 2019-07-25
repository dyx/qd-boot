<template>
  <qd-form title="新建顶级菜单"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text label="菜单名称" prop="menuName" v-model="form.menuName"></form-text>
      <form-text label="前端路由名称" prop="pageRouterName" v-model="form.pageRouterName"></form-text>
      <form-number label="排序" prop="sortNum" v-model="form.sortNum" :min="0" :max="1000" :precision="0"></form-number>
    </template>
  </qd-form>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
import FormNumber from '@/components/customize/qd-form/components/form-number'
export default {
  name: 'SaveRootMenu',
  components: { FormNumber, FormText, QdForm },
  props: {
    visible: false
  },
  data () {
    return {
      currentVisible: this.visible,
      form: { type: 1 },
      rules: {
        menuName: [
          { required: true, message: '菜单名称不能为空', trigger: 'blur' },
          { min: 2, max: 10, message: '长度2到10个字符', trigger: 'blur' }],
        pageRouterName: [
          { required: true, message: '前端路由名称不能为空', trigger: 'blur' },
          { min: 2, max: 30, message: '长度2到30个字符', trigger: 'blur' }]
      }
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'saveMenu'
    ]),
    init () {
      // 设置默认值
      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      this.saveMenu(this.form).then(res => {
        this.currentVisible = false
        this.$emit('on-success', res)
      })
    }
  },
  watch: {
    visible (val) {
      if (val) {
        this.init()
      }
    }
  }
}
</script>
