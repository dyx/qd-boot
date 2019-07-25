<template>
  <qd-form title="新建"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text disabled label="上级名称" v-model="parentName"></form-text>
      <form-text label="名称" prop="companyName" v-model="form.companyName"></form-text>
    </template>
  </qd-form>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
export default {
  name: 'SaveCompany',
  components: { FormText, QdForm },
  props: {
    visible: false,
    parentId: 0,
    parentName: ''
  },
  data () {
    return {
      currentVisible: this.visible,
      form: {},
      rules: {
        companyName: [
          { required: true, message: '名称不能为空', trigger: 'blur' },
          { min: 2, max: 50, message: '长度2到50个字符', trigger: 'blur' }]
      }
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'saveCompany'
    ]),
    init () {
      this.form.parentId = this.parentId
      // 设置默认值
      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      this.saveCompany(this.form).then(res => {
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
