<template>
  <qd-form title="新建"
           :visible="currentVisible"
           :form="form"
           :rules="rules"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <form-text label="角色名称" prop="roleName" v-model="form.roleName"></form-text>
    </template>
  </qd-form>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form'
import FormText from '@/components/customize/qd-form/components/form-text'
export default {
  name: 'SaveRole',
  components: { FormText, QdForm },
  props: {
    visible: false
  },
  data () {
    return {
      currentVisible: this.visible,
      form: {},
      rules: {
        roleName: [
          { required: true, message: '角色名称不能为空', trigger: 'blur' },
          { min: 2, max: 30, message: '长度2到30个字符', trigger: 'blur' }]
      }
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'saveRole'
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
      this.saveRole(this.form).then(res => {
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
