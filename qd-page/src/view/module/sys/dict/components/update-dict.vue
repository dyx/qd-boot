<template>
  <qd-form title="编辑"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text readonly label="类型" v-model="form.typeName"></form-text>
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
import FormTextArea from '@/components/customize/qd-form/components/form-text-area'
import FormNumber from '@/components/customize/qd-form/components/form-number'
export default {
  name: 'UpdateDict',
  components: { FormNumber, FormTextArea, FormText, QdForm },
  props: {
    visible: false,
    id: [Number, String]
  },
  data () {
    return {
      currentVisible: this.visible,
      form: {},
      rules: {
        dictValue: [
          { required: true, type: 'number', message: '值不能为空', trigger: 'blur' }],
        dictDesc: [
          { required: true, message: '描述不能为空', trigger: 'blur' },
          { min: 2, max: 30, message: '长度2到30个字符', trigger: 'blur' }]
      }
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'updateDict',
      'getDictById'
    ]),
    init () {
      this.getDictById(this.id).then(res => {
        this.form = res
      })

      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      this.updateDict({ id: this.id, data: this.form }).then(res => {
        this.currentVisible = false
        this.$emit('on-update-success')
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
