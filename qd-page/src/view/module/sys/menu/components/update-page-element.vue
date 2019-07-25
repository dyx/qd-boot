<template>
  <qd-form title="编辑"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text label="元素编码" prop="elementCode" v-model="form.elementCode"></form-text>
      <form-text label="元素名称" prop="elementName" v-model="form.elementName"></form-text>
      <form-select label="元素类型" prop="elementType" v-model="form.elementType" :items="pageElementTypeDict"></form-select>
      <form-select label="请求方法" prop="method" v-model="form.method" :items="pageElementMethodDict"></form-select>
      <form-text label="请求url" prop="url" v-model="form.url"></form-text>
    </template>
  </qd-form>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
import FormSelect from '@/components/customize/qd-form/components/form-select'
export default {
  name: 'UpdatePageElement',
  components: { FormSelect, FormText, QdForm },
  props: {
    visible: false,
    id: [Number, String]
  },
  data () {
    return {
      currentVisible: this.visible,
      form: {},
      rules: {
        elementName: [
          { required: true, message: '元素名称不能为空', trigger: 'blur' },
          { min: 2, max: 10, message: '长度2到10个字符', trigger: 'blur' }],
        elementCode: [
          { required: true, message: '元素编码不能为空', trigger: 'blur' },
          { min: 2, max: 30, message: '长度2到30个字符', trigger: 'blur' }],
        url: [
          { max: 255, message: '最长255个字符', trigger: 'blur' }]
      }
    }
  },
  computed: {
    ...mapGetters([
      'pageElementTypeDict',
      'pageElementMethodDict'
    ])
  },
  methods: {
    ...mapActions([
      'updatePageElement',
      'getPageElementById'
    ]),
    init () {
      this.getPageElementById(this.id).then(res => {
        this.form = res
      })

      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      this.updatePageElement({ id: this.id, data: this.form }).then(res => {
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
