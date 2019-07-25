<template>
  <Row>
    <Col :span="labelSpan" class="view-panel-label">{{label}}</Col>
    <Col v-if="type==='img'" :span="valueSpan" class="view-panel-img">
      <img v-if="value" class="value-img" :src="value" @click="showImg">
    </Col>
    <Col v-else-if="type==='imgList'" :span="valueSpan" class="view-panel-img">
      <Row v-if="value" type="flex" :gutter="8">
        <Col v-for="(url, index) in value" :key="index">
          <img class="value-img" :src="url" @click="showImg">
        </Col>
      </Row>
    </Col>
    <Col v-else-if="type==='video'" :span="valueSpan" class="view-panel-img">
      <video v-if="value" class="value-video" :src="value" @click="showVideo">
        您的浏览器不支持视频播放
      </video>
    </Col>
    <Col v-else-if="type==='a'" :span="valueSpan" class="view-panel-value">
      <a v-if="value" target="_blank" :href="value">{{value}}</a>
    </Col>
    <Col v-else-if="type==='custom'"
         :span="valueSpan"
         class="view-panel-value">
      <view-label-custom-value :render="handleRender"></view-label-custom-value>
    </Col>
    <Col v-else :span="valueSpan" class="view-panel-value">{{value}}</Col>
    <qd-img-modal :visible="imgVisible" :src="selectUrl" @on-close="imgVisible=false"></qd-img-modal>
    <qd-video-modal :visible="videoVisible" :src="selectUrl" @on-close="videoVisible=false"></qd-video-modal>
  </Row>
</template>

<script>
import './index.less'
import QdImgModal from '../../qd-modal/qd-img-modal'
import QdVideoModal from '../../qd-modal/qd-video-modal'
import ViewLabelCustomValue from './view-label-custom-value'

export default {
  name: 'ViewLabel',
  components: { ViewLabelCustomValue, QdVideoModal, QdImgModal },
  props: {
    type: '',
    label: '',
    value: '',
    render: Function,
    labelSpan: {
      type: Number,
      default: 6
    },
    valueSpan: {
      type: Number,
      default: 18
    }
  },
  data () {
    return {
      imgVisible: false,
      videoVisible: false,
      selectUrl: ''
    }
  },
  methods: {
    showImg (e) {
      this.selectUrl = e.target.src
      this.imgVisible = true
    },
    showVideo (e) {
      this.selectUrl = e.target.src
      this.videoVisible = true
    },
    handleRender (h) {
      return this.render(h)
    }
  }
}
</script>

<style scoped>
  .value-img {
    width:100px;
    height:100px;
    border: 1px solid #e8eaec;
    cursor: pointer;
  }
  .value-video {
    width:250px;
    height:150px;
    outline: none;
    border: 1px solid #e8eaec;
    cursor: pointer;
  }
</style>
