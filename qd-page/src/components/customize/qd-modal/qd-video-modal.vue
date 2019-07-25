<template>
  <Modal footer-hide width="800" v-model="currentVisible" @on-visible-change="close">
    <Row type="flex" justify="center">
      <Col>
        <video class="video" :src="src" controls>
          您的浏览器不支持视频播放
        </video>
      </Col>
    </Row>
  </Modal>
</template>

<script>
export default {
  name: 'QdVideoModal',
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

<style scoped>
  .video {
    width: 600px;
    height: 400px;
    outline: none;
    border: 1px solid #e8eaec;
  }
</style>
