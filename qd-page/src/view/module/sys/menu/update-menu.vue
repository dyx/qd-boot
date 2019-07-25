<template>
  <qd-form title="编辑"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text label="菜单名称" prop="menuName" v-model="form.menuName"></form-text>
      <form-text label="前端路由名称" prop="pageRouterName" v-model="form.pageRouterName"></form-text>
      <form-text label="前端页面路径" prop="pagePath" v-model="form.pagePath"></form-text>
      <form-text label="首页请求" prop="indexUrl" v-model="form.indexUrl"></form-text>
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
  name: 'UpdateMenu',
  components: { FormNumber, FormText, QdForm },
  props: {
    visible: false,
    id: [Number, String]
  },
  data () {
    return {
      currentVisible: this.visible,
      form: {},
      rules: {
        menuName: [
          { required: true, message: '菜单名称不能为空', trigger: 'blur' },
          { min: 2, max: 10, message: '长度2到10个字符', trigger: 'blur' }],
        pageRouterName: [
          { required: true, message: '前端路由名称不能为空', trigger: 'blur' },
          { min: 2, max: 30, message: '长度2到30个字符', trigger: 'blur' }],
        pagePath: [
          { max: 255, message: '最长255个字符', trigger: 'blur' }],
        indexUrl: [
          { max: 255, message: '最长255个字符', trigger: 'blur' }]
      }
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'updateMenu',
      'getMenuById'
    ]),
    init () {
      this.getMenuById(this.id).then(res => {
        this.form = res
      })

      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      this.updateMenu({ id: this.id, data: this.form }).then(res => {
        this.currentVisible = false
        this.$emit('on-success')
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
