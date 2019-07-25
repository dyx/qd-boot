<template>
  <qd-form title="编辑"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text label="用户名" prop="username" v-model="form.username"></form-text>
      <form-text label="姓名" prop="fullName" v-model="form.fullName"></form-text>
      <form-radio-group label="性别"
                        prop="gender"
                        v-model="form.gender"
                        :items="genderDict" ></form-radio-group>

      <form-ref label="公司"
                prop="companyName"
                v-model="form.companyName"
                @on-clear="form.companyId=''"
                @on-click="viewRefCompanyVisible=true"></form-ref>
      <form-ref label="部门"
                prop="deptName"
                v-model="form.deptName"
                @on-clear="form.deptId=''"
                @on-click="viewRefDeptVisible=true"></form-ref>

      <ref-company-tree :visible="viewRefCompanyVisible"
                        @on-select="selectCompany"
                        @on-close="viewRefCompanyVisible=false"></ref-company-tree>
      <ref-dept-tree :visible="viewRefDeptVisible"
                     :companyId="form.companyId"
                     @on-select="selectDept"
                     @on-close="viewRefDeptVisible=false"></ref-dept-tree>
    </template>
  </qd-form>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
import FormRadioGroup from '@/components/customize/qd-form/components/form-radio-group'
import FormRef from '@/components/customize/qd-form/components/form-ref'
import RefCompanyTree from '@/components/ref/ref-company-tree'
import RefDeptTree from '@/components/ref/ref-dept-tree'
export default {
  name: 'UpdateUser',
  components: { RefDeptTree, RefCompanyTree, FormRef, FormRadioGroup, FormText, QdForm },
  props: {
    visible: false,
    id: [Number, String]
  },
  data () {
    return {
      currentVisible: this.visible,
      form: {},
      rules: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { min: 4, max: 15, message: '长度4到15个字符', trigger: 'blur' }],
        fullName: [
          { required: true, message: '姓名不能为空', trigger: 'blur' },
          { min: 2, max: 15, message: '长度2到15个字符', trigger: 'blur' }],
        companyName: [
          { required: true, message: '公司不能为空', trigger: 'blur' }],
        deptName: [
          { required: true, message: '部门不能为空', trigger: 'blur' }]
      },
      viewRefCompanyVisible: false,
      viewRefDeptVisible: false
    }
  },
  computed: {
    ...mapGetters([
      'genderDict'
    ])
  },
  methods: {
    ...mapActions([
      'updateUser',
      'getUserById'
    ]),
    init () {
      this.getUserById(this.id).then(res => {
        this.form = res
      })

      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      this.updateUser({ id: this.id, data: this.form }).then(res => {
        this.currentVisible = false
        this.$emit('on-success')
      })
    },
    selectCompany (node) {
      this.form.companyId = node.id
      this.$set(this.form, 'companyName', node.title)

      this.form.deptId = 0
      this.$set(this.form, 'deptName', '')
    },
    selectDept (node) {
      this.form.deptId = node.id
      this.$set(this.form, 'deptName', node.title)
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
