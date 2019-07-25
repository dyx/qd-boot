<template>
  <Row>
    <qd-loading></qd-loading>
    <Col span="10">
      <Card>
        <p slot="title">公司树</p>
        <slot slot="extra">
          <qd-a-button v-auth="'saveCompanyRootBtn'"
                       name="新建顶级"
                       iconType="md-add"
                       @on-click="saveRootVisible=true"></qd-a-button>
          <qd-a-button v-auth="'saveCompanyBtn'"
                       name="新建"
                       iconType="md-add"
                       @on-click="saveVisible=true"></qd-a-button>
          <qd-a-button v-auth="'updateCompanyBtn'"
                       name="编辑"
                       iconType="md-create"
                       @on-click="updateVisible=true"></qd-a-button>
          <qd-a-button v-auth="'removeCompanyBtn'"
                       name="删除"
                       iconType="md-trash"
                       @on-click="remove"></qd-a-button>
        </slot>

        <Tree ref="companyTree" :data="treeData" @on-select-change="selectCompany"></Tree>

        <save-root-company :visible="saveRootVisible"
                           @on-close="saveRootVisible = false"
                           @on-success="saveSuccess"></save-root-company>

        <save-company :visible="saveVisible"
                      :parentId="selectId"
                      :parentName="selectName"
                      @on-close="saveVisible = false"
                      @on-success="saveSuccess"></save-company>

        <update-company :visible="updateVisible"
                        :id="selectId"
                        @on-close="updateVisible = false"
                        @on-success="updateSuccess"></update-company>
      </Card>
    </Col>
    <Col span="14">
      <dept :companyId="selectId" :companyName="selectName"></dept>
    </Col>
  </Row>
</template>

<script>
import { mapActions } from 'vuex'
import QdTreeView from '@/components/customize/qd-tree-view/qd-tree-view'
import QdAButton from '@/components/customize/qd-button/qd-a-button'
import Dept from './components/dept'
import SaveCompany from './save-company'
import UpdateCompany from './update-company'
import SaveRootCompany from './save-root-company'
import QdLoading from '@/components/customize/qd-loading/qd-loading'

export default {
  name: 'Org',
  components: { QdLoading, SaveRootCompany, UpdateCompany, SaveCompany, Dept, QdAButton, QdTreeView },
  data () {
    return {
      treeData: [],
      selectId: 0,
      selectName: '',
      selectParentId: 0,
      saveRootVisible: false,
      saveVisible: false,
      updateVisible: false
    }
  },
  computed: {

  },
  methods: {
    ...mapActions([
      'getCompanyTree',
      'removeCompany'
    ]),
    search (selectId) {
      this.getCompanyTree().then(res => {
        if (res) {
          this.treeData = res
          if (!selectId) {
            this.selectId = this.treeData[0].id
            this.selectName = this.treeData[0].title
            this.selectParentId = this.treeData[0].parentId
            // 设置树选中状态
            this.$set(this.treeData[0], 'selected', true)
            this.$set(this.treeData[0], 'expand', true)
          }
        }
      })
    },
    selectCompany (selectedNodes, node) {
      // 禁止取消选择
      if (!node.selected) {
        this.$refs.companyTree.handleSelect(node.nodeKey)
      }

      this.selectId = node.id
      this.selectName = node.title
      this.selectParentId = node.parentId
    },
    saveSuccess () {
      this.search()
    },
    updateSuccess () {
      this.search(this.selectId)
    },
    remove () {
      if (this.selectParentId === '0') {
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
          this.removeCompany(this.selectId).then(res => {
            this.search()
          })
        }
      })
    }
  },
  mounted () {
    this.search()
  }
}
</script>
