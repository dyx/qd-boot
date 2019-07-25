<template>
  <div>
    <qd-tree-view treeTitle="菜单树"
                  :treeData="treeData"
                  @on-tree-init="search"
                  @on-tree-select="treeSelect"
                  :viewData="viewData"
                  viewTitle="详情">
      <template #treeButtonItem>
        <qd-a-button v-auth="'saveRootBtn'"
                     slot="extra"
                     name="新建顶级"
                     iconType="md-add"
                     @on-click="saveRootVisible=true"></qd-a-button>
      </template>

      <template #viewButtonItem>
        <qd-a-button v-auth="'saveBtn'"
                     name="新建子级菜单"
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

      <template #viewTabItem>
        <page-element :menuId="selectId"></page-element>
      </template>

    </qd-tree-view>

    <save-root-menu :visible="saveRootVisible"
                    @on-close="saveRootVisible = false"
                    @on-success="saveSuccess"></save-root-menu>

    <save-menu :visible="saveVisible"
               :parentId="selectId"
               :parentName="this.form.menuName"
               @on-close="saveVisible = false"
               @on-success="saveSuccess"></save-menu>

    <update-menu :visible="updateVisible"
                 :id="selectId"
                 @on-close="updateVisible = false"
                 @on-success="updateSuccess"></update-menu>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import SaveRootMenu from './save-root-menu'
import SaveMenu from './save-menu'
import UpdateMenu from './update-menu'
import QdAButton from '@/components/customize/qd-button/qd-a-button'
import QdTreeView from '@/components/customize/qd-tree-view'
import PageElement from './components/page-element'
export default {
  name: 'Menu',
  components: { PageElement, QdTreeView, QdAButton, UpdateMenu, SaveMenu, SaveRootMenu },
  props: {

  },
  data () {
    return {
      treeData: [],
      viewData: [],
      form: {},
      selectId: '',
      saveRootVisible: false,
      saveVisible: false,
      updateVisible: false
    }
  },
  computed: {
    ...mapGetters([
      'menuTypeDesc'
    ])
  },
  methods: {
    ...mapActions([
      'getMenuTree',
      'getMenuById',
      'removeMenu'
    ]),
    search (selectId) {
      this.getMenuTree().then(res => {
        if (res) {
          this.treeData = res
          if (!selectId) {
            this.selectId = this.treeData[0].id
            this.$set(this.treeData[0], 'selected', true)
            this.$set(this.treeData[0], 'expand', true)
          } else {
            this.selectId = selectId
          }
        }
      })
    },
    treeSelect (node) {
      this.selectId = node.id
    },
    saveSuccess (selectId) {
      this.search(selectId)
    },
    updateSuccess () {
      this.search(this.selectId)
      this.viewInit()
    },
    viewInit () {
      this.getMenuById(this.selectId).then(res => {
        if (res) {
          this.form = res
          this.viewData = []
          this.viewData.push([{ label: '菜单名称', value: this.form.menuName }, { label: '上级', value: this.form.parentId }])
          this.viewData.push([{ label: '菜单类型', value: this.menuTypeDesc(this.form.type) }, { label: '首页URL', value: this.form.indexUrl }])
          this.viewData.push([{ label: '前端路由名称', value: this.form.pageRouterName }, { label: '前端页面位置', value: this.form.pagePath }])
          this.viewData.push([{ label: '创建人', value: this.form.createUserName }, { label: '创建时间', value: this.form.createTime }])
          this.viewData.push([{ label: '修改人', value: this.form.updateUserName }, { label: '修改时间', value: this.form.updateTime }])
        }
      })
    },
    remove () {
      if (this.form.parentId === '0') {
        this.$Modal.warning({
          title: '提示',
          content: `顶级菜单不可删除`
        })
        return
      }

      this.$Modal.confirm({
        title: '提示',
        content: `确定删除这条数据么？`,
        onOk: () => {
          this.removeMenu(this.selectId).then(res => {
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
