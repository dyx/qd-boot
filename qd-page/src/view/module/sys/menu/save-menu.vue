<template>
  <qd-form title="新建"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text readonly label="上级菜单" v-model="parentName"></form-text>
      <form-text label="菜单名称" prop="menuName" v-model="form.menuName"></form-text>
      <form-text label="前端路由名称" prop="pageRouterName" v-model="form.pageRouterName"></form-text>
      <form-text label="前端页面路径" prop="pagePath" v-model="form.pagePath"></form-text>
      <form-text label="首页请求" prop="indexUrl" v-model="form.indexUrl"></form-text>
      <form-number label="排序" prop="sortNum" v-model="form.sortNum" :min="0" :max="1000" :precision="0"></form-number>
    </template>
  </qd-form>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
import FormNumber from '@/components/customize/qd-form/components/form-number'
export default {
  name: 'SaveMenu',
  components: { FormNumber, FormText, QdForm },
  props: {
    visible: false,
    parentId: '',
    parentName: ''
  },
  data () {
    return {
      currentVisible: this.visible,
      form: { type: 2 },
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
      this.form.parentId = this.parentId
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
