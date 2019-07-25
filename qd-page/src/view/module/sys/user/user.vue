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
        <search-text label="姓名" v-model="params.fullName"></search-text>
        <search-text label="用户名" v-model="params.username"></search-text>
        <search-select label="性别" v-model="params.gender" :items="genderDict"></search-select>
        <search-date-range label="创建时间"
                           :start="params.startCreateTime"
                           :end="params.endCreateTime"
                           @on-change="changeCreateTime"></search-date-range>
      </template>
      <template #operation>
        <Button v-auth="'saveBtn'" type="primary" @click="saveVisible = true">新建</Button>
        <Button v-auth="'batchRemoveBtn'" type="error" style="margin-left: 8px" @click="batchRemove">删除</Button>
      </template>
    </qd-page>

    <view-user ref="view"
               :visible="viewVisible"
               :id="selectId"
               :title="selectViewTitle"
               @on-update="showUpdate"
               @on-remove="remove"
               @on-close="viewVisible = false"></view-user>
    <save-user :visible="saveVisible"
               @on-close="saveVisible = false"
               @on-success="saveSuccess"></save-user>
    <update-user :visible="updateVisible"
                 :id="selectId"
                 @on-close="updateVisible = false"
                 @on-success="updateSuccess"></update-user>
    <assign-role :visible="assignRoleVisible"
                      :id="selectId"
                      @on-close="assignRoleVisible = false"
                      @on-success="search"></assign-role>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import QdPage from '@/components/customize/qd-page'
import SearchText from '@/components/customize/qd-page/components/search-text'
import SearchSelect from '@/components/customize/qd-page/components/search-select'
import SearchDateRange from '@/components/customize/qd-page/components/search-date-range'
import ViewUser from './view-user'
import SaveUser from './save-user'
import UpdateUser from './update-user'
import AssignRole from './assign-role'

export default {
  name: 'User',
  components: { AssignRole, UpdateUser, SaveUser, ViewUser, SearchDateRange, SearchSelect, SearchText, QdPage },
  data () {
    return {
      params: { ...this.$config.initPageObj },
      pageData: {},
      selectIds: [],
      selectId: '',
      selectViewTitle: '',
      columns: [
        { type: 'selection', width: 60, align: 'center' },
        {
          title: '姓名',
          key: 'fullName',
          minWidth: 150,
          render: (h, { row }) => {
            return h('div', [h('a', {
              on: {
                click: () => {
                  this.selectId = row.id
                  this.viewVisible = true
                  this.selectViewTitle = row.fullName
                }
              }
            }, row.fullName)])
          }
        },
        { title: '用户名', key: 'username', width: 150 },
        { title: '性别', key: 'gender', render: (h, { row }) => h('span', this.genderDesc(row.gender)), width: 100 },
        { title: '公司', key: 'companyName', width: 150 },
        { title: '部门', key: 'deptName', width: 150 },
        { title: '角色', key: 'roleNames', width: 200 },
        { title: '创建时间', key: 'createTime', width: 150, sortable: 'custom' },
        {
          title: '操作',
          width: 210,
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
              h('Button', {
                props: { type: 'primary', size: 'small' },
                directives: [{ name: 'auth', value: 'assignRoleBtn' }],
                style: { marginLeft: '8px' },
                on: {
                  click: () => {
                    this.selectId = row.id
                    this.assignRoleVisible = true
                  }
                }
              }, '分配角色'),
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
      updateVisible: false,
      assignRoleVisible: false
    }
  },
  computed: {
    ...mapGetters([
      'genderDict',
      'genderDesc'
    ])
  },
  methods: {
    ...mapActions([
      'getUserPage',
      'removeUser',
      'batchRemoveUser'
    ]),
    search () {
      this.getUserPage(this.params).then(res => {
        if (res) {
          this.pageData = res
          // 刷新详情页标题
          if (this.viewVisible && this.selectViewTitle) {
            for (let item of this.pageData.records) {
              if (item.id === this.selectId) {
                this.selectViewTitle = item.fullName
                break
              }
            }
          }
        }
      })
    },
    changeCreateTime (dates) {
      this.params.startCreateTime = dates[0]
      this.params.endCreateTime = dates[1]
    },
    resetSearch () {
      for (let key of Object.keys(this.params)) {
        if (key === 'page' || key === 'size') {
          continue
        }
        // 置为'' 触发日期范围控件的watch
        if (key === 'startCreateTime' || key === 'endCreateTime') {
          this.$set(this.params, key, '')
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

      this.$Modal.confirm({
        title: '提示',
        content: `确定删除这${this.selectIds.length}条数据么？`,
        onOk: () => {
          this.batchRemoveUser(this.selectIds).then(res => {
            this.search()
            this.$Message.success(`成功删除${this.selectIds.length}条数据`)
          })
        }
      })
    },
    remove (id) {
      this.removeUser(id).then(res => {
        this.search()
      })
    }
  },
  mounted () {
    this.search()
  }
}
</script>
