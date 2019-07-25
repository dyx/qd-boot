<template>
  <qd-view :visible="currentVisible"
           :data="data"
           @on-close="close">
    <template #title>
      {{title}}
    </template>
    <template #button>
      <Button v-auth="'updateBtn'" type="primary" @click="update">编辑</Button>
      <Button v-auth="'removeBtn'" type="error" @click="remove">删除</Button>
    </template>
    <template #tabs>
      <TabPane label="基础信息">
        <view-panel :data="data"></view-panel>
      </TabPane>
    </template>
  </qd-view>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import QdView from '@/components/customize/qd-view'
import ViewPanel from '@/components/customize/qd-view/components/view-panel'
export default {
  name: 'ViewCustom',
  components: { ViewPanel, QdView },
  props: {
    visible: false,
    id: [Number, String],
    title: ''
  },
  data () {
    return {
      currentVisible: this.visible,
      data: []
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'getCustomById'
    ]),
    init () {
      this.getCustomById(this.id).then(res => {
        this.column2row(res)
      })

      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.data = []
      this.$emit('on-close')
    },
    update () {
      this.$emit('on-update', this.id)
    },
    remove () {
      this.$Modal
        .confirm({
          title: '提示',
          content: '确定删除这条数据么？',
          onOk: () => {
            this.$emit('on-remove', this.id)
            this.currentVisible = false
          }
        })
    },
    column2row (form) {
      this.data = []
      this.data.push([{ label: '姓名', value: form.customName }, { label: '手机', value: form.phone }])
      this.data.push([{ label: '所属人', value: form.ownerName }, { label: '所属部门', value: form.ownerDeptName }])
      this.data.push([{ label: '创建人', value: form.createUserName }, { label: '创建时间', value: form.createTime }])
      this.data.push([{ label: '修改人', value: form.updateUserName }, { label: '修改时间', value: form.updateTime }])
    }
  },
  watch: {
    visible (val) {
      if (val) {
        this.init()
      }
    }
  }
}
</script>
