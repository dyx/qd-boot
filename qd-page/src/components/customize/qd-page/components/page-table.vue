<template>
  <div>
    <Row style="margin-top: 8px;">
      <qd-loading></qd-loading>
      <Col>
        <Table border
               highlight-row
               stripe
               size="small"
               ref="pageTable"
               :columns="columns"
               :data="data"
               :height="height"
               @on-row-click="rowClick"
               @on-row-dblclick="dblRowClick"
               @on-selection-change="selectionChange"
               @on-sort-change="sortChange">
        </Table>
      </Col>
    </Row>
    <!--分页条-->
    <Row style="margin-top: 8px;">
      <Col>
        <Page show-total
              show-sizer
              show-elevator
              transfer
              v-if="total"
              :total="total"
              :page-size="currentSize"
              :current="currentPage"
              :page-size-opts="pageSizeOpts"
              @on-change="changePage"
              @on-page-size-change="changePageSize"></Page>
      </Col>
    </Row>
  </div>
</template>

<script>
import QdLoading from '../../qd-loading'

export default {
  name: 'PageTable',
  components: {QdLoading},
  props: {
    data: Array,
    columns: Array,
    page: Number,
    size: Number,
    total: Number,
    fixedHeader: true
  },
  data () {
    return {
      pageSizeOpts: this.$config.defaultPageSizeOpts,
      height: 0,
      currentPage: this.page,
      currentSize: this.size
    }
  },
  methods: {
    changePage (page) {
      this.currentPage = page
      this.change()
    },
    changePageSize (pageSize) {
      this.setHeight(pageSize)
      this.currentSize = pageSize
      this.change()
    },
    change () {
      this.$emit('on-change-page', this.currentPage, this.currentSize)
    },
    rowClick (row, index) {
      this.$emit('on-row-click', row, index)
    },
    dblRowClick (row, index) {
      this.$emit('on-row-dblclick', row, index)
    },
    selectionChange (selection) {
      this.$emit('on-selection-change', selection)
    },
    sortChange ({column, key, order}) {
      this.$emit('on-sort-change', column, key, order)
    },
    setHeight (size) {
      let rowHeight = 39 + 1
      let headerHeight = 30
      let tableBorderHeight = 1 + 1
      this.height = size * rowHeight + headerHeight + tableBorderHeight
    }
  },
  created () {
    this.columns.forEach(item => {
      item.tooltip = true
    })
  },
  mounted () {
    this.setHeight(this.size)
  },
  watch: {
    page (val) {
      this.currentPage = val
    },
    size (val) {
      this.currentSize = val
    }
  }
}
</script>
