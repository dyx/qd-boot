<template>
  <div>
    <qd-tree-view treeTitle="角色"
                  :treeData="pageData.records"
                  :treeTotal="pageData.total"
                  :treePage="pageData.page"
                  :treeSize="pageData.size"
                  @on-tree-init="search"
                  @on-tree-select="treeSelect"
                  @on-tree-change-page="treeChangePage">

      <template #treeButtonItem>
        <qd-a-button v-auth="'saveBtn'"
                     name="新建"
                     iconType="md-add"
                     @on-click="saveVisible=true"></qd-a-button>
        <qd-a-button v-auth="'updateBtn'"
                     name="编辑"
                     iconType="md-create"
                     @on-click="updateVisible=true"></qd-a-button>
        <qd-a-button v-auth="'removeBtn'"
                     name="删除"
                     iconType="md-trash"
                     @on-click="remove"></qd-a-button>
      </template>

      <template #treeSearchItem>
        <Input clearable size="small" class="search-item" placeholder="请输入名称" v-model="params.roleName"></Input>
      </template>

      <template #viewTabItem>
        <TabPane label="功能权限">
          <role-resource :roleId="selectId"></role-resource>
        </TabPane>
        <TabPane label="数据权限">
          <role-data :roleId="selectId"></role-data>
        </TabPane>
        <TabPane label="用户">
          <role-user :roleId="selectId"></role-user>
        </TabPane>
      </template>

    </qd-tree-view>

    <save-role :visible="saveVisible"
               @on-close="saveVisible = false"
               @on-success="saveSuccess"></save-role>

    <update-role :visible="updateVisible"
                 :id="selectId"
                 @on-close="updateVisible = false"
                 @on-success="updateSuccess"></update-role>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import QdTreeView from '@/components/customize/qd-tree-view/qd-tree-view'
import QdAButton from '@/components/customize/qd-button/qd-a-button'
import SaveRole from './save-role'
import UpdateRole from './update-role'
import RoleUser from './components/role-user'
import RoleResource from './components/role-resource'
import RoleData from './components/role-data'
export default {
  name: 'Role',
  components: {RoleData, RoleResource, RoleUser, UpdateRole, SaveRole, QdAButton, QdTreeView},
  props: {

  },
  data () {
    return {
      params: { page: 1, size: 30 },
      pageData: [],
      viewData: [],
      form: {},
      selectId: '',
      saveVisible: false,
      updateVisible: false
    }
  },
  methods: {
    ...mapActions([
      'getRolePage',
      'getRoleById',
      'removeRole'
    ]),
    search () {
      this.getRolePage(this.params).then(res => {
        if (res) {
          this.pageData = res
          this.pageData.records.forEach((item, index) => {
            if (index === 0) {
              this.selectId = item.id
              this.$set(item, 'selected', true)
            }
            item.title = item.roleName
          })
        }
      })
    },
    treeSelect (node) {
      this.selectId = node.id
    },
    treeChangePage (page) {
      this.params.page = page
      this.search()
    },
    saveSuccess () {
      this.params.page = 1
      this.search()
    },
    updateSuccess () {
      this.search()
    },
    remove () {
      this.$Modal.confirm({
        title: '提示',
        content: `确定删除这条数据么？`,
        onOk: () => {
          this.removeRole(this.selectId).then(res => {
            this.search()
          })
        }
      })
    }
  }
}
</script>
