<template>
  <qd-form title="新建"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text readonly label="类型" v-model="typeName"></form-text>
      <form-number label="值" prop="dictValue" v-model="form.dictValue" :precision="0" :min="1" :max="1000" ></form-number>
      <form-text label="描述" prop="dictDesc" v-model="form.dictDesc"></form-text>
      <form-number label="顺序" prop="sortNum" v-model="form.sortNum" :precision="0" :min="0" :max="1000" ></form-number>
      <form-text-area label="备注" prop="remark" v-model="form.remark"></form-text-area>
    </template>
  </qd-form>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
import FormNumber from '@/components/customize/qd-form/components/form-number'
import FormTextArea from '@/components/customize/qd-form/components/form-text-area'
export default {
  name: 'SaveDict',
  components: { FormTextArea, FormNumber, FormText, QdForm },
  props: {
    visible: false,
    typeCode: String,
    typeName: String
  },
  data () {
    return {
      currentVisible: this.visible,
      form: { dictValue: 1 },
      rules: {
        dictValue: [
          { required: true, type: 'number', message: '值不能为空', trigger: 'blur' }],
        dictDesc: [
          { required: true, message: '描述不能为空', trigger: 'blur' },
          { max: 30, message: '最长30个字符', trigger: 'blur' }]
      }
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'saveDict'
    ]),
    init () {
      this.form.typeCode = this.typeCode
      // 设置默认值
      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      this.saveDict(this.form).then(res => {
        this.currentVisible = false
        this.$emit('on-save-success')
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
