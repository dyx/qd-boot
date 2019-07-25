<template>
  <div>
    <Row type="flex" justify="end">
      <Col>
        <Button size="small" type="primary" v-auth="'assignDataBtn'" @click="assignDataVisible = true">分配</Button>
      </Col>
    </Row>
    <Tree check-strictly :render="renderContent" :data="data"></Tree>
    <assign-data :visible="assignDataVisible"
                 :roleId="roleId"
                 @on-success="search"
                 @on-close="assignDataVisible=false"></assign-data>
    <ref-org-multiple-tree :readOnly="true"
                           :visible="refOrgMultipleTreeVisible"
                           :checkedDeptIds="currentCheckedDeptIds"
                           @on-close="refOrgMultipleTreeVisible=false"></ref-org-multiple-tree>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import AssignData from './assign-data'
import {CUSTOM_PERMISSION} from './consts'
import RefOrgMultipleTree from '@/components/ref/ref-org-multiple-tree'
export default {
  name: 'RoleData',
  components: {RefOrgMultipleTree, AssignData},
  props: {
    roleId: ''
  },
  data () {
    return {
      data: [],
      assignDataVisible: false,
      refOrgMultipleTreeVisible: false,
      currentCheckedDeptIds: []
    }
  },
  methods: {
    ...mapActions([
      'getRoleDataCheckedByRoleId'
    ]),
    handleTree (data) {
      data.forEach(item => {
        this.$set(item, 'expand', true)
        if (item.checked) {
          this.$set(item, 'selected', true)
        }
        this.$set(item, 'disabled', true)
        if (item.children && item.children.length > 0) {
          this.handleTree(item.children)
        }
      })
    },
    search () {
      this.getRoleDataCheckedByRoleId(this.roleId).then(res => {
        if (res) {
          this.data = res
          this.handleTree(this.data)
        }
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
            h('span', {
              style: {
                color: data.checked ? '#2d8cf0' : '#666'
              }
            }, data.title)
          ]),
          h('span', {
            style: {
              display: 'inline-block',
              marginLeft: '40px'
            }
          }, [
            h('Button', {
              style: {display: data.level === 3 && data.id === CUSTOM_PERMISSION && data.checked ? 'block' : 'none'},
              props: {type: 'primary', size: 'small'},
              on: {
                click: () => {
                  this.refOrgMultipleTreeVisible = true
                  this.currentCheckedDeptIds = data.customDeptIds
                }
              }
            }, '查看部门')
          ])
        ]
      )
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
