<template>
  <Modal
    title="分配数据权限"
    width="400"
    :mask-closable="false"
    :closable="false"
    v-model="currentVisible"
    @on-visible-change="close">
    <qd-loading></qd-loading>
    <Tree ref="tree"
          check-strictly
          check-directly
          show-checkbox
          :data="data"
          :render="renderContent"
          @on-check="check"
          @on-check-change="checkChange"></Tree>
    <template #footer>
      <Button @click="currentVisible = false">取消</Button>
      <Button type="primary" @click="submit">保存</Button>
    </template>

    <ref-org-multiple-tree disableClose
                           :visible="refOrgMultipleTreeVisible"
                           :checkedDeptIds="currentCheckedDeptIds"
                           @on-select="selectOrg"
                           :checkedMode="3"
                           @on-close="closeOrg"></ref-org-multiple-tree>
  </Modal>
</template>

<script>
import { mapActions } from 'vuex'
import QdForm from '@/components/customize/qd-form/qd-form'
import QdLoading from '@/components/customize/qd-loading/qd-loading'
import RefOrgMultipleTree from '@/components/ref/ref-org-multiple-tree'
import {CUSTOM_PERMISSION} from './consts'
export default {
  name: 'AssignData',
  components: {RefOrgMultipleTree, QdLoading, QdForm},
  props: {
    visible: false,
    roleId: ''
  },
  data () {
    return {
      currentVisible: false,
      data: [],
      form: [],
      refOrgMultipleTreeVisible: false,
      currentCustomDeptNodeKey: 0,
      currentCustomDeptDataObjId: 0,
      currentCheckedDeptIds: []
    }
  },
  methods: {
    ...mapActions([
      'getRoleDataCheckedByRoleId',
      'assignRoleData'
    ]),
    handleTree (data) {
      data.forEach(item => {
        if (item.children && item.children.length > 0) {
          this.$set(item, 'disabled', true)
          this.$set(item, 'expand', true)
          this.handleTree(item.children)
        }
      })
    },
    init () {
      this.getRoleDataCheckedByRoleId(this.roleId).then(res => {
        if (res) {
          this.data = res
          this.handleTree(this.data)
        }
      })
    },
    close (val) {
      if (!val) {
        this.form = []
        this.$emit('on-close')
      }
    },
    submit () {
      this.assignRoleData({ id: this.roleId, data: this.form }).then(res => {
        this.currentVisible = false
        this.$emit('on-success')
      })
    },
    check ({checked, nodeKey}) {
      let treeComponent = this.$refs.tree
      let currentNode = treeComponent.flatState[nodeKey].node
      let parentNodeKey = treeComponent.flatState[nodeKey].parent
      let parentNode = treeComponent.flatState[parentNodeKey].node
      if (currentNode.id !== CUSTOM_PERMISSION) {
        if (checked) {
          // 设置基础权限为单选
          parentNode.children.forEach(item => {
            item.checked = false
          })
        }
      } else {
        // 选中自定义部门时，显示组织参照
        if (checked) {
          this.showOrg(currentNode.nodeKey, currentNode.dataObjId, currentNode.customDeptIds)
        } else {
          this.setCustomDeptIds(this.data, currentNode.dataObjId, [])
        }
      }
    },
    checkChange (checkedNodes, node) {
      this.form = []
      checkedNodes.forEach(item => {
        this.form.push({dataObjId: item.dataObjId, permissionType: item.id, customDeptIds: item.customDeptIds})
      })
    },
    renderContent (h, {root, node, data}) {
      return h('span',
        {
          style: {
            display: 'inline-block',
            width: '100%',
            cursor: 'pointer'
          }
        },
        [
          h('span', [
            h('span', data.title)
          ]),
          h('span', {
            style: {
              display: 'inline-block',
              marginLeft: '40px'
            }
          }, [
            h('Button', {
              style: {display: data.level === 3 && data.id === CUSTOM_PERMISSION ? 'block' : 'none'},
              props: {type: 'primary', size: 'small'},
              on: {
                click: () => {
                  this.showOrg(node.nodeKey, data.dataObjId, data.customDeptIds)
                }
              }
            }, '选择部门')
          ])
        ]
      )
    },
    showOrg (nodeKey, dataObjId, customDeptIds) {
      this.refOrgMultipleTreeVisible = true
      this.currentCustomDeptNodeKey = nodeKey
      this.currentCustomDeptDataObjId = dataObjId
      this.currentCheckedDeptIds = customDeptIds
    },
    closeOrg () {
      this.refOrgMultipleTreeVisible = false
    },
    selectOrg (checkedItems) {
      let checkedIds = []
      if (checkedItems && checkedItems.length > 0) {
        checkedItems.forEach(item => {
          checkedIds.push(item.id)
        })
        for (let i = 0; i < this.form.length; i++) {
          let item = this.form[i]
          if (item.key === this.currentCustomDeptNodeKey) {
            item.customDeptIds = checkedIds
            break
          }
        }
      }

      this.setCustomDeptIds(this.data, this.currentCustomDeptDataObjId, checkedIds)
      this.$refs.tree.handleCheck({ checked: checkedIds && checkedIds.length > 0, nodeKey: this.currentCustomDeptNodeKey })
    },
    setCustomDeptIds (data, dataObjId, customDeptIds) {
      data.forEach(item => {
        if (item.children && item.children.length > 0) {
          this.setCustomDeptIds(item.children, dataObjId, customDeptIds)
        } else {
          if (item.dataObjId === dataObjId && item.id === 3) {
            item.customDeptIds = customDeptIds
          }
        }
      })
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
