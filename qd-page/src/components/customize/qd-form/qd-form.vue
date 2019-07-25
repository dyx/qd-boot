<template>
  <Modal
    :title="title"
    :width="width"
    :mask-closable="false"
    v-model="currentVisible"
    @on-visible-change="visibleChange">
    <qd-loading></qd-loading>
    <Form ref="form" :label-width="labelWidth" :rules="rules" :model="form">
      <slot name="formItem"></slot>
      <Collapse simple v-if="showMoreItem">
        <Panel>
          更多选项
          <template #content>
            <slot name="moreItem"></slot>
          </template>
        </Panel>
      </Collapse>
      <slot name="relInfo"></slot>
    </Form>
    <template #footer>
      <Button @click="currentVisible = false">取消</Button>
      <Button type="primary" @click="submit">保存</Button>
    </template>
  </Modal>
</template>

<script>
import { LABEL_WIDTH, ITEM_WIDTH, MODAL_SPACE } from './consts'
import QdLoading from '../qd-loading/qd-loading'
export default {
  name: 'QdForm',
  components: { QdLoading },
  props: {
    visible: false,
    title: '',
    form: {},
    rules: {},
    showMoreItem: false
  },
  data () {
    return {
      currentVisible: this.visible,
      labelWidth: LABEL_WIDTH,
      width: ITEM_WIDTH + MODAL_SPACE
    }
  },
  methods: {
    visibleChange (visible) {
      if (!visible) {
        this.$refs.form.resetFields()
        this.$emit('on-close')
      }
    },
    submit () {
      if (this.form) {
        this.$refs.form.validate(valid => {
          if (valid) {
            this.$emit('on-submit')
          }
        })
      } else {
        this.$emit('on-submit')
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

<style>
  <!--重写iview样式，去除padding-->
  .ivu-collapse-content {
    color: #515a6e;
    padding: 0;
    background-color: #fff;
  }
</style>
