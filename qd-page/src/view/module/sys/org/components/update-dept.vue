<template>
  <qd-form title="编辑"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text disabled label="所属公司" prop="companyName" v-model="form.companyName"></form-text>
      <form-text v-if="form.parentId && form.parentId > 0"
                 disabled
                 label="上级"
                 prop="parentName"
                 v-model="form.parentName"></form-text>
      <form-text label="名称" prop="deptName" v-model="form.deptName"></form-text>
    </template>
  </qd-form>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
export default {
  name: 'UpdateDept',
  components: { FormText, QdForm },
  props: {
    visible: false,
    id: [Number, String]
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
      'updateDept',
      'getDeptById'
    ]),
    init () {
      this.getDeptById(this.id).then(res => {
        this.form = res
      })

      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      this.updateDept({ id: this.id, data: this.form }).then(res => {
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
