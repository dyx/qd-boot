<template>
  <div>
    <qd-page :columns="columns"
             :data="pageData.records"
             :total="pageData.total"
             :page="params.page"
             :size="params.size"
             @on-reset="resetSearch"
             @on-search="search"
             @on-change-page="changePage"
             @on-selection-change="selectionChange"
             @on-sort-change="sortChange">
      <template #searchItem>
        <search-text label="姓名" v-model="params.customName"></search-text>
      </template>
      <template #operation>
        <Button v-auth="'saveBtn'" type="primary" @click="saveVisible = true">新建</Button>
        <Button v-auth="'batchRemoveBtn'" type="error" style="margin-left: 8px" @click="batchRemove">删除</Button>
      </template>
    </qd-page>

    <view-custom ref="view"
               :visible="viewVisible"
               :id="selectId"
               :title="selectViewTitle"
               @on-update="showUpdate"
               @on-remove="remove"
               @on-close="viewVisible = false"></view-custom>
    <save-custom :visible="saveVisible"
               @on-close="saveVisible = false"
               @on-success="saveSuccess"></save-custom>
    <update-custom :visible="updateVisible"
                 :id="selectId"
                 @on-close="updateVisible = false"
                 @on-success="updateSuccess"></update-custom>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import QdPage from '@/components/customize/qd-page'
import ViewCustom from './view-custom.vue'
import SaveCustom from './save-custom.vue'
import UpdateCustom from './update-custom.vue'
import QdLoading from '@/components/customize/qd-loading/qd-loading'
import SearchText from '@/components/customize/qd-page/components/search-text'

export default {
  name: 'Custom',
  components: {SearchText, QdLoading, UpdateCustom, SaveCustom, ViewCustom, QdPage},
  data () {
    return {
      params: { ...this.$config.initPageObj },
      pageData: [],
      selectIds: [],
      selectId: '',
      selectViewTitle: '',
      columns: [
        { type: 'selection', width: 60, align: 'center' },
        {
          title: '姓名',
          key: 'customName',
          render: (h, { row }) => {
            return h('div', [h('a', {
              on: {
                click: () => {
                  this.selectId = row.id
                  this.viewVisible = true
                  this.selectViewTitle = row.customName
                }
              }
            }, row.customName)])
          }
        },
        { title: '手机', key: 'phone' },
        { title: '所属人', key: 'ownerName' },
        {
          title: '操作',
          width: 150,
          align: 'center',
          render: (h, { row }) => {
            return h('div', [
              h('Button', {
                props: { type: 'primary', size: 'small' },
                directives: [{ name: 'auth', value: 'updateBtn' }],
                style: { marginLeft: '8px' },
                on: {
                  click: () => {
                    this.showUpdate(row.id)
                  }
                }
              }, '编辑'),
              h('Poptip', {
                props: {
                  confirm: true,
                  title: '确定要删除么',
                  transfer: true
                },
                on: {
                  'on-ok': () => {
                    this.remove(row.id)
                  }
                }
              }, [h('Button', {
                props: { type: 'error', size: 'small' },
                directives: [{ name: 'auth', value: 'removeBtn' }],
                style: { marginLeft: '8px' }
              }, '删除')])
            ])
          }
        }],
      viewVisible: false,
      saveVisible: false,
      updateVisible: false
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'getCustomPage',
      'removeCustom',
      'batchRemoveCustom'
    ]),
    search () {
      this.getCustomPage(this.params).then(res => {
        if (res) {
          this.pageData = res
          // 刷新详情页标题
          if (this.viewVisible && this.selectViewTitle) {
            for (let item of this.pageData.records) {
              if (item.id === this.selectId) {
                this.selectViewTitle = item.id
                break
              }
            }
          }
        }
      })
    },
    resetSearch () {
      for (let key of Object.keys(this.params)) {
        if (key === 'page' || key === 'size') {
          continue
        }

        this.$delete(this.params, key)
      }
    },
    sortChange (column, key, order) {
      this.params.sortField = key
      this.params.sortOrder = order
      this.search()
    },
    changePage (page, size) {
      this.params.page = page
      this.params.size = size
      this.search()
    },
    selectionChange (selection) {
      this.selectIds = []
      if (selection && selection.length > 0) {
        selection.forEach(row => this.selectIds.push(row.id))
      }
    },
    saveSuccess () {
      // 返回第一页
      this.resetSearch()
      this.params.page = 1
      this.search()
    },
    showUpdate (id) {
      this.selectId = id
      this.updateVisible = true
    },
    updateSuccess () {
      this.search()

      // 刷新详情页
      if (this.viewVisible) {
        this.$refs.view.init()
      }
    },
    batchRemove () {
      if (!this.selectIds || this.selectIds.length === 0) {
        this.$Message.error('至少选中一条记录')
        return
      }
      this.$Modal
        .confirm({
          title: '提示',
          content: `确定删除这${this.selectIds.length}条数据么？`,
          onOk: () => {
            this.batchRemoveCustom(this.selectIds).then(res => {
              this.search()
              this.$Message
                .success(`成功删除${this.selectIds.length}条数据`)
            })
          }
        })
    },
    remove (id) {
      this.removeCustom(id).then(res => {
        this.search()
      })
    }
  },
  mounted () {
    this.search()
  }
}
</script>
