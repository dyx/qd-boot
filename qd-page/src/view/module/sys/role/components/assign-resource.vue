<template>
  <Modal
    title="分配功能权限"
    width="1000"
    :mask-closable="false"
    v-model="currentVisible"
    @on-visible-change="close">
    <qd-loading></qd-loading>
    <Row>
      <Col span="8">
        <Card dis-hover title="菜单树">
          <Tree show-checkbox
                ref="menuTree"
                :data="menuData"
                @on-check-change="checkMenu"
                @on-select-change="selectMenu"></Tree>
        </Card>
      </Col>
      <Col span="16">
        <Card dis-hover title="元素列表">
          <Table border
                 highlight-row
                 stripe
                 size="small"
                 :columns="pageElementColumns"
                 :data="currentMenuPageElementData"
                 @on-selection-change="checkedPageElement">
          </Table>
        </Card>
      </Col>
    </Row>
    <template #footer>
      <Button @click="currentVisible = false">取消</Button>
      <Button type="primary" @click="submit">保存</Button>
    </template>
  </Modal>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form/qd-form'
import QdLoading from '@/components/customize/qd-loading/qd-loading'
export default {
  name: 'AssignResource',
  components: { QdLoading, QdForm },
  props: {
    visible: false,
    roleId: ''
  },
  data () {
    return {
      currentVisible: false,
      menuData: [],
      pageElementData: {},
      currentMenuPageElementData: [],
      pageElementColumns: [
        { type: 'selection', width: 60, align: 'center' },
        { title: '名称', key: 'elementName' },
        { title: '编码', key: 'elementCode' }
      ],
      form: {},
      firstLoad: true
    }
  },
  methods: {
    ...mapActions([
      'getResourceCheckedByRoleId',
      'assignRoleResource'
    ]),
    getFirstLeafNodeKey (data) {
      if (data.children && data.children.length > 0) {
        return this.getFirstLeafNodeKey(data.children[0])
      } else {
        return data.nodeKey
      }
    },
    handleMenuTree (data) {
      data.forEach(item => {
        if (item.children && item.children.length > 0) {
          // 非叶子节点不可点击
          this.$set(item, 'disabled', true)
          // 非叶子节点取消选中状态
          this.$set(item, 'checked', false)
          this.$set(item, 'expand', true)
        }
        this.handleMenuTree(item.children)
      })
    },
    handlePageElementList (data) {
      Object.values(data).forEach(value => {
        value.forEach(item => {
          // 替换属性
          item._checked = item.checked
          delete item.checked
        })
      })
    },
    checkMenuById (data, menuId) {
      for (let i = 0; i < data.length; i++) {
        let item = data[i]
        if (item.children && item.children.length > 0) {
          this.checkMenuById(item.children, menuId)
        } else {
          if (item.id === menuId) {
            this.$set(item, 'checked', true)
            return
          }
        }
      }
    },
    init () {
      this.getResourceCheckedByRoleId(this.roleId).then(res => {
        if (res) {
          this.handleMenuTree(res.menuList)
          this.menuData = res.menuList

          this.handlePageElementList(res.pageElementMap)
          this.pageElementData = res.pageElementMap
        }
      })
    },
    close (val) {
      if (!val) {
        this.firstLoad = true
        this.menuData = []
        this.pageElementData = {}
        this.currentMenuPageElementData = []
        this.form = {}
        this.$emit('on-close')
      }
    },
    submit () {
      this.form.menuList = []
      this.$refs.menuTree.getCheckedAndIndeterminateNodes().forEach(item => {
        this.form.menuList.push(item.id)
      })

      if (!this.form.menuList || this.form.menuList.length === 0) {
        alert('至少分配一个菜单')
        return
      }

      this.form.pageElementList = []
      Object.values(this.pageElementData).forEach(value => {
        value.forEach(item => {
          if (item._checked) {
            this.form.pageElementList.push(item.id)
          }
        })
      })

      this.assignRoleResource({ id: this.roleId, data: this.form }).then(res => {
        this.currentVisible = false
        this.$emit('on-success')
      })
    },
    checkMenu (checkedNodes, node) {
      // 更新列表复选框选中状态
      let list = this.pageElementData[node.id]
      if (list) {
        list.forEach(item => {
          item._checked = node.checked
        })
      }
    },
    selectMenu (selectedNodes, node) {
      // 不允许消选择
      if (!node.selected) {
        this.$refs.menuTree.handleSelect(node.nodeKey)
      }
      // 替换列表数据
      this.currentMenuPageElementData = []
      let list = this.pageElementData[node.id]
      if (list) {
        list.forEach(item => {
          this.currentMenuPageElementData.push(item)
        })
      }
    },
    checkedPageElement (selection) {
      if (this.currentMenuPageElementData && this.currentMenuPageElementData.length > 0) {
        let menuId = this.currentMenuPageElementData[0].menuId
        this.pageElementData[menuId] = []
        this.currentMenuPageElementData.forEach(item => {
          item._checked = false
          if (selection && selection.length > 0) {
            for (let i = 0; i < selection.length; i++) {
              if (selection[i].id === item.id) {
                item._checked = true
                break
              }
            }
          }
          this.pageElementData[menuId].push(item)
        })
      }
    }
  },
  updated () {
    // 选择第一个叶子节点
    if (this.firstLoad) {
      let stateTree = this.$refs.menuTree.stateTree
      if (stateTree && stateTree.length > 0) {
        let nodeKey = this.getFirstLeafNodeKey(stateTree[0])
        if (nodeKey) {
          this.$refs.menuTree.handleSelect(nodeKey)
          this.firstLoad = false
        }
      }
    }
  },
  watch: {
    visible (val) {
      if (val) {
        this.init()
        this.currentVisible = val
      }
    }
  }
}
</script>
