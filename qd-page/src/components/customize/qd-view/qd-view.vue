<template>
    <Drawer width="1000" v-model="currentVisible" @on-visible-change="visibleChange">
      <template #header>
        <Row type="flex">
          <qd-loading></qd-loading>
          <Col span="8" class="qd-view-title">
            <slot name="title"></slot>
          </Col>
          <Col span="16" class="qd-view-button">
            <ButtonGroup size="small">
              <slot name="button"></slot>
            </ButtonGroup>
          </Col>
        </Row>
      </template>
      <Row>
        <Col>
          <slot name="summary"></slot>
        </Col>
      </Row>
      <Tabs>
        <slot name="tabs"></slot>
      </Tabs>
    </Drawer>
</template>

<script>
import QdLoading from '../qd-loading/qd-loading'
export default {
  name: 'QdView',
  components: { QdLoading },
  props: {
    visible: false
  },
  data () {
    return {
      currentVisible: this.visible
    }
  },
  methods: {
    visibleChange (visible) {
      if (!visible) {
        this.$emit('on-close')
      }
    }
  },
  watch: {
    visible (val) {
      this.currentVisible = val
    }
  }
}
</script>

<style lang="less">
  .qd-view-{
    &title{
      font-size: 14px;
      font-weight: bold;
      line-height: 30px;
      overflow:hidden;
      text-overflow:ellipsis;
      white-space:nowrap;
    }
    &button{
      text-align: right;
      padding-right: 40px;
    }
  }
</style>
