<template>
  <FormItem :label="label" :prop="prop">
    <video v-if="readonly" class="video" :src="currentValue" @click="viewVisible=true">
      您的浏览器不支持视频播放
    </video>

    <div v-else="readonly">
      <Upload v-if="uploadVisible"
              ref="upload"
              :show-upload-list="false"
              :on-success="handleSuccess"
              :format="['mp4','avi']"
              :max-size="1048576"
              :on-format-error="handleFormatError"
              :on-exceeded-size="handleMaxSize"
              type="drag"
              :action="action"
              class="upload">
        <div class="upload-icon">
          <Icon type="md-videocam" size="25"></Icon>
        </div>
      </Upload>
      <div v-else class="panel">
        <video class="video" :src="currentValue">
          您的浏览器不支持视频播放
        </video>
        <div class="video-cover">
          <Icon type="ios-eye-outline" @click.native="viewVisible=true"></Icon>
          <Icon type="ios-trash-outline" @click.native="remove"></Icon>
        </div>
      </div>
    </div>

    <qd-video-modal :visible="viewVisible" :src="currentValue" @on-close="viewVisible=false"></qd-video-modal>

  </FormItem>
</template>

<script>
import QdVideoModal from '../../qd-modal/qd-video-modal'

export default {
  name: 'FormVideo',
  components: {QdVideoModal},
  props: {
    value: '',
    label: '',
    prop: '',
    action: '',
    readonly: false
  },
  data () {
    return {
      currentValue: this.value,
      viewVisible: false,
      uploadVisible: false
    }
  },
  methods: {
    setCurrentValue (value) {
      this.uploadVisible = !value
      if (value === this.currentValue) return
      this.currentValue = value
    },
    remove () {
      this.currentValue = ''
      this.uploadVisible = true
      this.$emit('input', '')
    },
    handleSuccess (res, file) {
      if (res) {
        if (res.code > 0) {
          this.currentValue = res.data.accessUrl
          this.uploadVisible = false
          this.$emit('input', this.currentValue)
          return
        } else if (res.code === 0) {
          this.$Notice.error({
            desc: res.msg
          })
          return
        }
      }
      this.$Notice.error({
        desc: '视频上传失败'
      })
    },
    handleFormatError (file) {
      this.$Notice.warning({
        desc: '视频只支持 [ mp4, avi ]'
      })
    },
    handleMaxSize (file) {
      this.$Notice.warning({
        desc: '视频大小不得超过1GB'
      })
    }
  },
  mounted () {
    this.uploadVisible = true
  },
  watch: {
    value (val) {
      this.setCurrentValue(val)
    }
  }
}
</script>

<style scoped>
  .panel {
    display: inline-block;
    width: 250px;
    height: 150px;
    text-align: center;
    line-height: 150px;
    border: 1px solid transparent;
    border-radius: 4px;
    overflow: hidden;
    background: #fff;
    position: relative;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
  }

  .video {
    width: 250px;
    height: 150px;
    cursor: pointer;
    outline: none;
    border: 1px solid #e8eaec;
  }

  .video-cover {
    display: none;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, .6);
  }

  .panel:hover .video-cover {
    display: block;
  }

  .video-cover i {
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    margin: 0 2px;
  }

  .upload {
    display: inline-block;
    width: 250px;
  }

  .upload-icon {
    width: 250px;
    height: 150px;
    line-height: 150px;
  }
</style>
