<template>
  <Card dis-hover>
    <p slot="title">部门树</p>
    <slot slot="extra">
      <qd-a-button v-auth="'saveDeptRootBtn'"
                   name="新建顶级"
                   iconType="md-add"
                   @on-click="saveRootVisible=true"></qd-a-button>
      <qd-a-button v-auth="'saveDeptBtn'"
                   :disabled="!(selectId && selectId > 0)"
                   name="新建"
                   iconType="md-add"
                   @on-click="saveVisible=true"></qd-a-button>
      <qd-a-button v-auth="'updateDeptBtn'"
                   name="编辑"
                   iconType="md-create"
                   @on-click="updateVisible=true"></qd-a-button>
      <qd-a-button v-auth="'removeDeptBtn'"
                   name="删除"
                   iconType="md-trash"
                   @on-click="remove"></qd-a-button>
    </slot>

    <Tree ref="deptTree" :data="treeData" @on-select-change="selectDept"></Tree>

    <save-root-dept :visible="saveRootVisible"
                    :companyId="companyId"
                    :companyName="companyName"
                    @on-close="saveRootVisible = false"
                    @on-success="saveSuccess"></save-root-dept>

    <save-dept :visible="saveVisible"
               :companyId="companyId"
               :companyName="companyName"
               :parentId="selectId"
               :parentName="selectName"
               @on-close="saveVisible = false"
               @on-success="saveSuccess"></save-dept>

    <update-dept :visible="updateVisible"
                 :id="selectId"
                 @on-close="updateVisible = false"
                 @on-success="updateSuccess"></update-dept>
  </Card>
</template>

<script>
import { mapActions } from 'vuex'
import QdAButton from '@/components/customize/qd-button/qd-a-button'
import SaveRootDept from './save-root-dept'
import SaveDept from './save-dept'
import UpdateDept from './update-dept'
export default {
  name: 'Dept',
  components: { UpdateDept, SaveDept, SaveRootDept, QdAButton },
  props: {
    companyId: 0,
    companyName: ''
  },
  data () {
    return {
      treeData: [],
      selectId: 0,
      selectName: '',
      saveRootVisible: false,
      saveVisible: false,
      updateVisible: false
    }
  },
  methods: {
    ...mapActions([
      'getDeptTreeByCompanyId',
      'removeDept'
    ]),
    init () {
      this.selectId = 0
      this.selectName = ''
      this.search()
    },
    search (selectId) {
      this.getDeptTreeByCompanyId(this.companyId).then(res => {
        if (res && res.length > 0) {
          this.treeData = res
          if (!selectId) {
            this.selectId = this.treeData[0].id
            this.selectName = this.treeData[0].title
            // 设置树选中状态
            this.$set(this.treeData[0], 'selected', true)
            this.$set(this.treeData[0], 'expand', true)
          }
        } else {
          this.treeData = []
        }
      })
    },
    selectDept (selectedNodes, node) {
      // 禁止取消选择
      if (!node.selected) {
        this.$refs.deptTree.handleSelect(node.nodeKey)
      }
      this.selectId = node.id
      this.selectName = node.title
    },
    saveSuccess () {
      this.search()
    },
    updateSuccess () {
      this.search(this.selectId)
    },
    remove () {
      this.$Modal.confirm({
        title: '提示',
        content: `确定删除这条数据么？`,
        onOk: () => {
          this.removeDept(this.selectId).then(res => {
            this.search()
          })
        }
      })
    }
  },
  watch: {
    companyId (newVal, oldVal) {
      if (newVal && newVal !== oldVal) {
        this.init()
      }
    }
  }
}
</script>
