<template>
  <div class="personal-center-avatar-dropdown">
    <Dropdown @on-click="handleClick">
      <Badge :dot="!!messageUnreadCount">
        <Avatar :src="userAvatar"/>
      </Badge>
      <span style="padding: 0 4px 0 6px;">{{$util.getName()}}</span>
      <Icon :size="18" type="md-arrow-dropdown"></Icon>
      <DropdownMenu slot="list">
        <DropdownItem name="personal-center">个人中心</DropdownItem>
        <DropdownItem name="modify-password">修改密码</DropdownItem>
        <DropdownItem name="logout">退出登录</DropdownItem>
      </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
import './personal-center.less'
import { mapActions } from 'vuex'
export default {
  name: 'PersonalCenter',
  props: {
    userAvatar: {
      type: String,
      default: ''
    },
    messageUnreadCount: {
      type: Number,
      default: 0
    }
  },
  methods: {
    ...mapActions([
      'handleLogOut'
    ]),
    logout () {
      this.handleLogOut().then(() => {
        this.$router.push({
          name: 'login'
        })
      })
    },
    message () {
      this.$router.push({
        name: 'message_page'
      })
    },
    handleClick (name) {
      switch (name) {
        case 'logout': this.logout()
          break
        case 'message': this.message()
          break
      }
    }
  }
}
</script>
