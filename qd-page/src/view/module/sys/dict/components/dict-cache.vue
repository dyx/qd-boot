<template>
  <TabPane v-auth="'pageElementTab'" label="字典缓存">
    <Row type="flex" justify="end">
      <Col>
        <Button v-auth="'updateCacheBtn'"
                size="small" type="primary"
                :loading="syncBtnLoading"
                @click="update">{{updateBtnText}}</Button>
      </Col>
    </Row>
    <page-table :columns="columns" :data="data"></page-table>
  </TabPane>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import PageTable from '@/components/customize/qd-page/components/page-table'
export default {
  name: 'DictCache',
  components: { PageTable },
  props: {
    typeCode: String,
    typeDesc: String,
    refresh: false
  },
  data () {
    return {
      currentTypeCode: '',
      columns: [
        { title: '值', key: 'value' },
        { title: '描述', key: 'desc' }
      ],
      data: [],
      params: { ...this.$config.initPageObj },
      updateBtnText: '更新',
      syncBtnLoading: false
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'getDictCacheListByTypeCode',
      'updateDictCacheByTypeCode'
    ]),
    search () {
      this.getDictCacheListByTypeCode(this.currentTypeCode).then(res => {
        if (res) {
          this.data = res
        } else {
          this.data = []
        }
        this.$emit('on-refresh')
      })
    },
    changePage (page, size) {
      this.params.page = page
      this.params.size = size
      this.search()
    },
    update () {
      this.syncBtnLoading = true

      let second = 10
      this.updateBtnText = `${second}秒`
      let interval = setInterval(() => {
        second -= 1
        if (second === 0) {
          this.updateBtnText = '更新'
          this.syncBtnLoading = false
          clearInterval(interval)
        } else {
          this.updateBtnText = `${second}秒`
        }
      }, 1000)

      this.updateDictCacheByTypeCode(this.currentTypeCode).then(res => {
        this.search()
      }).catch(() => { this.syncBtnLoading = false })
    }
  },
  watch: {
    typeCode (newVal, oldVal) {
      if (newVal && newVal !== oldVal) {
        this.currentTypeCode = newVal
        this.search()
      }
    },
    refresh (val) {
      if (val) {
        this.search()
      }
    }
  }
}
</script>
