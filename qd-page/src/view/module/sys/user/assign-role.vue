<template>
  <qd-form title="分配角色"
           :visible="currentVisible"
           @on-close="close"
           @on-submit="submit">
    <template #formItem>
      <Tree show-checkbox check-directly :data="roleList"></Tree>
    </template>
  </qd-form>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form/qd-form'
export default {
  name: 'AssignUserRole',
  components: { QdForm },
  props: {
    visible: false,
    id: ''
  },
  data () {
    return {
      currentVisible: this.visible,
      roleList: []
    }
  },
  methods: {
    ...mapActions([
      'getUserAssignRoleList',
      'assignUserRole'
    ]),
    init () {
      this.getUserAssignRoleList(this.id).then(res => {
        this.roleList = res
      })

      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.$emit('on-close')
    },
    submit () {
      let roleIdList = []
      this.roleList.forEach(item => {
        if (item.checked) {
          roleIdList.push(item.id)
        }
      })

      if (roleIdList.length === 0) {
        this.$Message.error('至少选中一个角色')
        return
      }

      this.assignUserRole({ id: this.id, roleIdList }).then(res => {
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
