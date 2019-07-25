<template>
  <qd-form title="新建类型"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text label="编码" prop="typeCode" v-model="form.typeCode"></form-text>
      <form-text label="名称" prop="typeDesc" v-model="form.typeDesc"></form-text>
      <form-text label="备注" prop="remark" v-model="form.remark"></form-text>
    </template>
  </qd-form>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
export default {
  name: 'SaveDictType',
  components: { FormText, QdForm },
  props: {
    visible: false
  },
  data () {
    return {
      currentVisible: this.visible,
      form: {},
      rules: {
        typeCode: [
          { required: true, message: '编码不能为空', trigger: 'blur' },
          { min: 2, max: 32, message: '长度2到32个字符', trigger: 'blur' }],
        typeDesc: [
          { required: true, message: '名称不能为空', trigger: 'blur' },
          { min: 2, max: 30, message: '长度2到30个字符', trigger: 'blur' }]
      }
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'saveDictType'
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
      this.saveDictType(this.form).then(res => {
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
