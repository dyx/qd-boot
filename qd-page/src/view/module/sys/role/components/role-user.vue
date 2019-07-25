<template>
  <page-table :columns="columns"
              :data="pageData.records"
              :total="pageData.total"
              :page="params.page"
              :size="params.size"
              @on-search="search"
              @on-change-page="changePage"></page-table>
</template>

<script>
import { mapActions } from 'vuex'
import PageTable from '@/components/customize/qd-page/components/page-table'
export default {
  name: 'RoleUser',
  components: { PageTable },
  props: {
    roleId: ''
  },
  data () {
    return {
      currentRoleId: '',
      pageData: {},
      params: { ...this.$config.initPageObj },
      columns: [
        { title: '用户名', key: 'username' },
        { title: '姓名', key: 'fullName' }
      ]
    }
  },
  methods: {
    ...mapActions([
      'getUserPageByRoleId'
    ]),
    search () {
      this.getUserPageByRoleId({ id: this.currentRoleId, params: this.params }).then(res => {
        if (res) {
          this.pageData = res
        }
      })
    },
    changePage (page) {
      this.params.page = page
      this.search()
    }
  },
  watch: {
    roleId (newVal, oldVal) {
      if (newVal && newVal !== oldVal) {
        this.currentRoleId = newVal
        this.search()
      }
    }
  }
}
</script>
