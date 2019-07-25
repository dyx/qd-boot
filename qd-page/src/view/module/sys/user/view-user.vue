<template>
  <qd-view :visible="currentVisible"
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
        <view-panel :data="form"></view-panel>
      </TabPane>
    </template>
  </qd-view>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import QdView from '@/components/customize/qd-view'
import ViewPanel from '@/components/customize/qd-view/components/view-panel'
export default {
  name: 'ViewUser',
  components: { ViewPanel, QdView },
  props: {
    visible: false,
    id: [Number, String],
    title: ''
  },
  data () {
    return {
      currentVisible: this.visible,
      form: []
    }
  },
  computed: {
    ...mapGetters([
      'genderDesc'
    ])
  },
  methods: {
    ...mapActions([
      'getUserById'
    ]),
    init () {
      this.getUserById(this.id).then(res => {
        this.column2row(res)
      })

      this.currentVisible = true
    },
    close () {
      this.currentVisible = false
      this.form = []
      this.$emit('on-close')
    },
    update () {
      this.$emit('on-update', this.id)
    },
    remove () {
      this.$Modal.confirm({
        title: '提示',
        content: `确定删除这条数据么？`,
        onOk: () => {
          this.$emit('on-remove', this.id)
          this.currentVisible = false
        }
      })
    },
    column2row (data) {
      this.form = []
      this.form.push([{ label: '用户名', value: data.username }, { label: '姓名', value: data.fullName }])
      this.form.push([{ label: '性别', value: this.genderDesc(data.gender) }])
      this.form.push([{ label: '公司', value: data.companyName }, { label: '部门', value: data.deptName }])
      this.form.push([{ label: '创建人', value: data.createUserName }, { label: '创建时间', value: data.createTime }])
      this.form.push([{ label: '修改人', value: data.updateUserName }, { label: '修改时间', value: data.updateTime }])
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
