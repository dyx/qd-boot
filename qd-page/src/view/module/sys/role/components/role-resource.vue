<template>
  <div>
    <Row type="flex" justify="end">
      <Col>
        <Button size="small" type="primary" v-auth="'assignResourceBtn'" @click="assignResourceVisible = true">分配</Button>
      </Col>
    </Row>
    <Row style="padding-top: 8px;">
      <Col span="12">
        <Card dis-hover title="菜单树">
          <Tree :data="menuData"></Tree>
        </Card>
      </Col>
      <Col span="12">
        <Card dis-hover title="元素列表">
          <Tree :data="pageElementData"></Tree>
        </Card>
      </Col>
    </Row>
    <assign-resource :visible="assignResourceVisible"
                     :roleId="roleId"
                     @on-close="assignResourceVisible = false"
                     @on-success="search"></assign-resource>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import AssignResource from './assign-resource'
export default {
  name: 'RoleResource',
  components: { AssignResource },
  props: {
    roleId: ''
  },
  data () {
    return {
      menuData: [],
      pageElementData: [],
      assignResourceVisible: false
    }
  },
  methods: {
    ...mapActions([
      'getResourceByRoleId'
    ]),
    handleTree (data) {
      data.forEach(item => {
        if (item.children && item.children.length > 0) {
          this.$set(item, 'expand', true)
          this.handleTree(item.children)
        }
      })
    },
    search () {
      this.getResourceByRoleId(this.roleId).then(res => {
        if (res) {
          this.menuData = res.menuList
          this.handleTree(this.menuData)
          this.pageElementData = res.pageElementList
          this.handleTree(this.pageElementData)
        }
      })
    }
  },
  watch: {
    roleId (newVal, oldVal) {
      if (newVal && newVal !== oldVal) {
        this.search()
      }
    }
  }
}
</script>
