<template>
  <FormItem :label="label" :prop="prop">
    <div class="form-item">
      <draggable :value="uploadList" :group="prop" @change="handleDraggableChange">
        <div class="panel" v-for="(item, index) in uploadList" :key="index">
          <template v-if="item.status === 'finished'">
            <img :src="item.url">
            <div class="img-cover">
              <Icon type="ios-eye-outline" @click.native="handleView(item.url)"></Icon>
              <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
            </div>
          </template>
          <template v-else>
            <Progress v-if="item.showProgress" :percent="item.percentage" hide-info></Progress>
          </template>
        </div>
      </draggable>
      <Upload ref="multiUpload"
              multiple
              type="drag"
              class="upload"
              :show-upload-list="false"
              :on-success="handleSuccess"
              :format="['jpg','jpeg','png', 'gif']"
              :max-size="10240"
              :on-format-error="handleFormatError"
              :on-exceeded-size="handleMaxSize"
              :before-upload="handleBeforeUpload"
              :action="action">
        <div class="upload-icon">
          <Icon type="md-camera" size="25"></Icon>
        </div>
      </Upload>
    </div>

    <qd-img-modal :visible="viewVisible" :src="selectImgUrl" @on-close="viewVisible=false"></qd-img-modal>

  </FormItem>
</template>
<script>
import draggable from 'vuedraggable'
import QdImgModal from '../../qd-modal/qd-img-modal'

export default {
  name: 'FormMultiImg',
  components: {QdImgModal, draggable},
  props: {
    label: '',
    prop: '',
    defaultUrlList: Array,
    limit: 0,
    action: '',
    isClear: false
  },
  data () {
    return {
      viewVisible: false,
      uploadList: [],
      selectImgUrl: '',
      tempIndex: 0
    }
  },
  methods: {
    handleView (url) {
      this.selectImgUrl = url
      this.viewVisible = true
    },
    handleRemove (file) {
      let fileList = this.$refs.multiUpload.fileList
      let index = fileList.indexOf(file)
      this.$refs.multiUpload.fileList.splice(index, 1)
      this.$emit('on-change', this.getUrlList())
    },
    handleSuccess (res, file) {
      if (res) {
        if (res.code > 0) {
          file.url = res.data.accessUrl
          this.$emit('on-change', this.getUrlList())
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
        title: '只支持 [jpg, jpeg, png, gif] 类型图片'
      })
    },
    handleMaxSize (file) {
      this.$Notice.warning({
        title: '图片大小不得超出10MB'
      })
    },
    handleBeforeUpload () {
      let check = true
      if (this.limit > 0) {
        check = this.uploadList.length < this.limit
        if (!check) {
          this.$Notice.warning({
            title: '最多上传' + this.limit + '张图片'
          })
        }
      }
      return check
    },
    handleDraggableChange (val) {
      let oldIndex = val.moved.oldIndex
      let newIndex = val.moved.newIndex
      let [...cloneList] = this.uploadList
      this.uploadList.splice(oldIndex, 1, cloneList[newIndex])
      this.uploadList.splice(newIndex, 1, cloneList[oldIndex])
      this.$emit('on-change', this.getUrlList())
    },
    getUrlList () {
      let list = []
      if (this.uploadList) {
        this.uploadList.forEach(item => {
          list.push(item.url)
        })
      }
      return list
    }
  },
  mounted () {
    this.uploadList = this.$refs.multiUpload.fileList
  },
  watch: {
    defaultUrlList: {
      immediate: true,
      handler (urlList) {
        if (urlList) {
          urlList.forEach(url => {
            this.uploadList.push({
              url,
              status: 'finished',
              percentage: 100,
              uid: Date.now() + this.tempIndex++
            })
          })
        }
      }
    },
    isClear (val) {
      if (val) {
        this.uploadList = []
        this.$refs.multiUpload.clearFiles()
      } else {
        this.uploadList = this.$refs.multiUpload.fileList
      }
    }
  }
}
</script>
<style>
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
    margin: 0 4px;
  }

  .panel img {
    width: 100%;
    height: 100%;
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
    margin: 0 4px;
  }

  .upload-icon {
    width: 100px;
    height: 100px;
    line-height: 100px;
  }
</style>
