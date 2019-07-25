<template>
  <div>
    <!--查询条-->
    <search-bar v-if="$scopedSlots.searchItem"
                :labelWidth="labelWidth"
                :showMore="showMore"
                @on-search="search"
                @on-reset="reset">
      <template #searchItem>
        <slot name="searchItem"></slot>
      </template>
    </search-bar>
    <!--操作区-->
    <div v-if="$scopedSlots.operation" style="float: right;">
      <slot name="operation"></slot>
    </div>
    <div style="clear: both"></div>
    <!--列表-->
    <page-table :columns="columns"
                :data="data"
                :total="total"
                :page="page"
                :size="size"
                @on-change-page="changePage"
                @on-row-click="rowClick"
                @on-row-dblclick="dblRowClick"
                @on-selection-change="selectionChange"
                @on-sort-change="sortChange"></page-table>
  </div>
</template>

<script>
import SearchBar from './components/search-bar'
import PageTable from './components/page-table'

export default {
  name: 'QdPage',
  components: { SearchBar, PageTable },
  props: {
    data: Array,
    columns: Array,
    page: Number,
    size: Number,
    total: Number,
    showMore: false,
    labelWidth: Number
  },
  data () {
    return {}
  },
  methods: {
    search () {
      this.$emit('on-search')
    },
    reset () {
      this.$emit('on-reset')
    },
    changePage (page, size) {
      this.$emit('on-change-page', page, size)
    },
    changePageSize (page, size) {
      this.$emit('on-change-page', page, size)
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
    sortChange (column, key, order) {
      this.$emit('on-sort-change', column, key, order)
    }
  }
}
</script>

<style>

</style>
