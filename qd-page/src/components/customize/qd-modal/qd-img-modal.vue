<template>
  <Modal footer-hide width="1000" v-model="currentVisible" @on-visible-change="close">
    <Row type="flex" justify="center">
      <Col>
        <img :src="src" style="max-width: 1000px;"/>
      </Col>
    </Row>
  </Modal>
</template>

<script>
export default {
  name: 'QdImgModal',
  props: {
    visible: false,
    src: ''
  },
  data () {
    return {
      currentVisible: false
    }
  },
  methods: {
    close (val) {
      if (!val) {
        this.$emit('on-close')
      }
    }
  },
  watch: {
    visible (val) {
      if (val && this.src) {
        this.currentVisible = true
      }
    }
  }
}
</script>
