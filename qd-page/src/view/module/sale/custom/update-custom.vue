<template>
  <qd-form title="编辑"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text label="姓名" prop="customName" v-model="form.customName"></form-text>
      <form-text label="手机" prop="phone" v-model="form.phone"></form-text>
      <form-ref label="所属人"
                prop="ownerName"
                v-model="form.ownerName"
                @on-clear="form.ownerId=null"
                @on-click="viewUserRefVisible=true"></form-ref>
      <ref-user :visible="viewUserRefVisible"
                @on-select="selectUserRef"
                @on-close="viewUserRefVisible=false"></ref-user>
    </template>
  </qd-form>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
import RefUser from '@/components/ref/ref-user'
import FormRef from '@/components/customize/qd-form/components/form-ref'
export default {
  name: 'UpdateCustom',
  components: {FormRef, RefUser, FormText, QdForm},
  props: {
    visible: false,
    id: [Number, String]
  },
  data () {
    return {
      currentVisible: this.visible,
      form: {},
      rules: {
        customName: [
          { required: true, message: '姓名不能为空', trigger: 'blur' },
          { max: 30, message: '最长30个字符', trigger: 'blur' }],
        phone: [
          { required: true, message: '手机不能为空', trigger: 'blur' },
          { max: 16, message: '最长16个字符', trigger: 'blur' }],
        ownerName: [
          { required: true, message: '所属人不能为空', trigger: 'blur' }]
      },
      viewUserRefVisible: false
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'updateCustom',
      'getCustomById'
    ]),
    init () {
      this.getCustomById(this.id).then(res => {
        this.form = res
      })

      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      this.updateCustom({ id: this.id, data: this.form }).then(res => {
        this.currentVisible = false
        this.$emit('on-success')
      })
    },
    selectUserRef (row) {
      this.form.ownerId = row.id
      this.form.ownerDeptId = row.deptId
      this.$set(this.form, 'ownerName', row.fullName)
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
