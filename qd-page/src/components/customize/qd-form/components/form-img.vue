<template>
  <FormItem :label="label" :prop="prop">
    <img v-if="readonly" class="img" :src="currentValue" @click="viewVisible=true"/>

    <div v-else="readonly">
      <Upload v-if="uploadVisible"
              ref="upload"
              :show-upload-list="false"
              :on-success="handleSuccess"
              :format="['jpg','jpeg','png', 'gif']"
              :max-size="10240"
              :on-format-error="handleFormatError"
              :on-exceeded-size="handleMaxSize"
              type="drag"
              :action="action"
              class="upload">
        <div class="upload-icon">
          <Icon type="md-camera" size="25"></Icon>
        </div>
      </Upload>
      <div v-else class="panel">
        <img :src="currentValue" class="img">
        <div class="img-cover">
          <Icon type="ios-eye-outline" @click.native="viewVisible=true"></Icon>
          <Icon type="ios-trash-outline" @click.native="remove"></Icon>
        </div>
      </div>
    </div>

    <qd-img-modal :visible="viewVisible" :src="currentValue" @on-close="viewVisible=false"></qd-img-modal>

  </FormItem>
</template>

<script>
import QdImgModal from '../../qd-modal/qd-img-modal'

export default {
  name: 'FormImg',
  components: {QdImgModal},
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
        desc: '图片上传失败'
      })
    },
    handleFormatError (file) {
      this.$Notice.warning({
        desc: '图片只支持 [ jpg, jpeg, png, gif ]'
      })
    },
    handleMaxSize (file) {
      this.$Notice.warning({
        desc: '图片大小不得超过10MB'
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
    width: 100px;
    height: 100px;
    text-align: center;
    line-height: 100px;
    border: 1px solid transparent;
    border-radius: 4px;
    overflow: hidden;
    background: #fff;
    position: relative;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
  }

  .img {
    width: 100px;
    height: 100px;
    cursor: pointer;
  }

  .img-cover {
    display: none;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, .6);
  }

  .panel:hover .img-cover {
    display: block;
  }

  .img-cover i {
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    margin: 0 2px;
  }

  .upload {
    display: inline-block;
    width: 100px;
  }

  .upload-icon {
    width: 100px;
    height: 100px;
    line-height: 100px;
  }
</style>
