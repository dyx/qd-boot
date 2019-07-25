<template>
  <div>
    <qd-tree-view treeTitle="字典类型"
                  :treeData="pageData.records"
                  :treeTotal="pageData.total"
                  :treePage="pageData.page"
                  :treeSize="pageData.size"
                  @on-tree-init="search"
                  @on-tree-select="treeSelect"
                  @on-tree-change-page="treeChangePage"
                  viewTitle="详情"
                  :viewData="viewData">

      <template #viewButtonItem>
        <qd-a-button v-auth="'saveTypeBtn'"
                     name="新建"
                     iconType="md-add"
                     @on-click="saveVisible=true"></qd-a-button>
        <qd-a-button v-auth="'editTypeBtn'"
                     name="编辑"
                     iconType="md-create"
                     @on-click="updateVisible=true"></qd-a-button>
        <qd-a-button v-auth="'removeTypeBtn'"
                     name="删除"
                     iconType="md-trash"
                     @on-click="remove"></qd-a-button>
      </template>

      <template #treeSearchItem>
        <Input clearable size="small" class="search-item" placeholder="请输入描述" v-model="params.typeDesc"></Input>
      </template>

      <template #viewTabItem>
        <dict :typeCode="selectCode"
              :typeDesc="selectDesc"
              @on-success="refreshCacheFlag = true"></dict>
        <dict-cache :typeCode="selectCode"
                    :typeDesc="selectDesc"
                    :refresh="refreshCacheFlag"
                    @on-refresh="refreshCacheFlag = false"></dict-cache>
      </template>

    </qd-tree-view>

    <save-dict-type :visible="saveVisible"
                    @on-close="saveVisible = false"
                    @on-success="saveSuccess"></save-dict-type>

    <update-dict-type :visible="updateVisible"
                      :id="selectId"
                      @on-close="updateVisible = false"
                      @on-success="updateSuccess"></update-dict-type>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import QdPage from '@/components/customize/qd-page'
import QdTreeView from '@/components/customize/qd-tree-view'
import UpdateDictType from './update-dict-type'
import SaveDictType from './save-dict-type'
import Dict from './components/dict'
import DictCache from './components/dict-cache'
import QdAButton from '@/components/customize/qd-button/qd-a-button'

export default {
  name: 'DictType',
  components: { QdAButton, DictCache, Dict, SaveDictType, UpdateDictType, QdTreeView, QdPage },
  data () {
    return {
      params: { page: 1, size: 30 },
      pageData: [],
      viewData: [],
      form: {},
      selectId: '',
      selectCode: '',
      selectDesc: '',
      saveVisible: false,
      updateVisible: false,
      refreshCacheFlag: false
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'getDictTypePage',
      'getDictTypeById',
      'removeDictType'
    ]),
    search () {
      this.getDictTypePage(this.params).then(res => {
        if (res) {
          this.pageData = res
          this.pageData.records.forEach((item, index) => {
            if (index === 0) {
              this.selectId = item.id
              this.selectCode = item.typeCode
              this.selectDesc = item.typeDesc
            }
            item.title = item.typeDesc
          })
        }
      })
    },
    saveSuccess () {
      // 返回第一页
      this.params.page = 1
      this.search()
    },
    updateSuccess () {
      this.search()
    },
    treeSelect (node) {
      this.selectId = node.id
      this.selectCode = node.typeCode
      this.selectDesc = node.typeDesc
    },
    treeChangePage (page) {
      this.params.page = page
      this.search()
    },
    viewInit () {
      this.getDictTypeById(this.selectId).then(res => {
        if (res) {
          this.form = res
          this.viewData = []
          this.viewData.push([{ label: '编码', value: this.form.typeCode }, { label: '名称', value: this.form.typeDesc }])
          this.viewData.push([{ label: '备注', value: this.form.remark }])
          this.viewData.push([{ label: '创建人', value: this.form.createUserName }, { label: '创建时间', value: this.form.createTime }])
          this.viewData.push([{ label: '修改人', value: this.form.updateUserName }, { label: '修改时间', value: this.form.updateTime }])
        }
      })
    },
    remove () {
      this.$Modal.confirm({
        title: '提示',
        content: `确定删除这条数据么？`,
        onOk: () => {
          this.removeDictType(this.selectId).then(res => {
            this.search()
          })
        }
      })
    }
  },
  watch: {
    selectId (newVal, oldVal) {
      if (newVal && newVal !== oldVal) {
        this.viewInit()
      }
    }
  }
}
</script>
