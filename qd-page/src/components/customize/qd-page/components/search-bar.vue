<template>
  <div class="search-bar" :style="{height:searchBarHeight}" @keyup.enter="search">
    <div ref="searchItem" class="search-form">
      <Form :label-width="labelWidth" inline>
        <slot name="searchItem"></slot>
      </Form>
    </div>
    <div class="search-button">
      <Button type="primary" @click="search">查询</Button>
      <Button type="primary" style="margin-left: 8px" @click="reset">重置</Button>
      <Button v-if="showMore" style="margin-left: 8px" type="primary" @click="switchState">{{switchButtonName}}</Button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchBar',
  props: {
    showMore: false,
    labelWidth: {
      type: Number,
      default: 60
    }
  },
  data () {
    return {
      switchButtonName: '更多',
      initSearchBarHeight: '57px',
      searchBarHeight: '57px'
    }
  },
  methods: {
    search () {
      this.$emit('on-search')
    },
    reset () {
      this.$emit('on-reset')
    },
    switchState () {
      if (this.searchBarHeight === this.initSearchBarHeight) {
        this.switchButtonName = '收起'
        this.searchBarHeight = 'auto'
      } else {
        this.switchButtonName = '更多'
        this.searchBarHeight = this.initSearchBarHeight
      }
    }
  }
}
</script>

<style>
  .search-bar {
    position: relative;
    width: 100%;
    overflow: hidden;
  }

  .search-button {
    width: 192px;
    float: left;
    text-align: right;
  }

  .search-form {
    width: calc(100% - 192px);
    float: left;
  }
</style>
