<template>
  <Modal footerHide
         :title="title"
         :width="width"
         v-model="currentVisible"
         @on-visible-change="close">
    <Form :label-width="60" inline>
      <div @keyup.enter="search">
        <slot name="searchItem"></slot>
        <Button v-if="$scopedSlots.searchItem" type="primary" @click="search">查询</Button>
      </div>
    </Form>
    <page-table :columns="columns"
                :data="data"
                :total="total"
                :page="params.page"
                :size="params.size"
                @on-row-dblclick="select"
                @on-search="search"
                @on-change-page="changePage">
    </page-table>
  </Modal>
</template>

<script>
import PageTable from '@/components/customize/qd-page/components/page-table'

export default {
  name: 'QdRefPage',
  components: {PageTable},
  props: {
    visible: false,
    title: '',
    width: Number,
    data: Array,
    columns: Array,
    total: Number,
    initLoad: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      params: {page: 1, size: 10},
      currentVisible: false
    }
  },
  methods: {
    search () {
      this.$emit('on-search', {page: 1, size: this.params.size})
    },
    changePage (page, size) {
      this.$emit('on-search', {page, size})
    },
    select (row, index) {
      this.currentVisible = false
      this.$emit('on-select', row)
    },
    close (val) {
      if (!val) {
        this.$emit('on-close')
      }
    }
  },
  watch: {
    visible (val) {
      if (val) {
        if (this.initLoad) {
          this.search()
        }
        this.currentVisible = true
      }
    }
  }
}
</script>
