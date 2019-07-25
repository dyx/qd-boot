<template>
  <qd-form title="新建"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text disabled label="所属公司" prop="companyName" v-model="companyName"></form-text>
      <form-text disabled label="上级" prop="parentName" v-model="parentName"></form-text>
      <form-text label="名称" prop="deptName" v-model="form.deptName"></form-text>
    </template>
  </qd-form>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
export default {
  name: 'SaveDept',
  components: { FormText, QdForm },
  props: {
    visible: false,
    companyId: 0,
    companyName: '',
    parentId: 0,
    parentName: ''
  },
  data () {
    return {
      currentVisible: this.visible,
      form: {},
      rules: {
        deptName: [
          { required: true, message: '名称不能为空', trigger: 'blur' },
          { min: 2, max: 50, message: '长度2到50个字符', trigger: 'blur' }]
      }
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'saveDept'
    ]),
    init () {
      // 设置默认值
      this.form.companyId = this.companyId
      this.form.parentId = this.parentId
      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      this.saveDept(this.form).then(res => {
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
