<style lang="less">
  @import './login.less';
</style>

<template>
  <div class="login">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <login-form :loading="loading" @on-success-valid="handleSubmit"></login-form>
          <p class="login-tip"></p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import LoginForm from '@/components/login-form'
import { mapActions } from 'vuex'
export default {
  components: {
    LoginForm
  },
  data () {
    return {
      loading: false
    }
  },
  methods: {
    ...mapActions([
      'handleLogin'
    ]),
    handleSubmit ({ username, password }) {
      this.loading = true
      this.handleLogin({ username, password }).then(res => {
        this.$router.addRoutes(this.$store.getters.getRouters())
        this.$router.push({
          name: this.$config.homeName
        })
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    }
  }
}
</script>

<style>

</style>
