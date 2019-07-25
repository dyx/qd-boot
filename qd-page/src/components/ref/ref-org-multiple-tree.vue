<template>
  <div>
    <Modal id="refOrgMultipleTree"
           v-if="readOnly"
           footer-hide
           title="部门"
           v-model="currentVisible"
           @on-visible-change="visibleChange">
      <Tree show-checkbox check-strictly :data="data"></Tree>
    </Modal>
    <Modal v-else
           title="部门"
           :mask-closable="!disableClose"
           :closable="!disableClose"
           v-model="currentVisible"
           @on-visible-change="visibleChange">
      <Tree check-directly show-checkbox check-strictly :data="data" @on-check-change="checkChange"></Tree>
      <template #footer>
        <Button v-if="!disableClose" @click="currentVisible = false">取消</Button>
        <Button type="primary" @click="select">确定</Button>
      </template>
    </Modal>
  </div>
</template>

<script>
import {mapActions} from 'vuex'

export default {
  name: 'RefOrgMultipleTree',
  components: {},
  props: {
    visible: false,
    readOnly: false,
    checkedDeptIds: {
      type: [Array, String],
      default () {
        return []
      }
    },
    checkStrictly: {
      type: Boolean,
      default: false
    },
    disableClose: {
      type: Boolean,
      default: false
    },
    /**
     * 1 都可选择
     * 2 只选择公司
     * 3 只选择部门
     */
    checkedMode: {
      type: Number,
      default: 1
    }
  },
  data () {
    return {
      currentVisible: false,
      data: [],
      currentCheckedItems: []
    }
  },
  methods: {
    ...mapActions([
      'getOrgTree'
    ]),
    handleTree (data) {
      data.forEach(item => {
        if (this.checkedDeptIds && this.checkedDeptIds.length > 0) {
          if (item.type === 2 && this.checkedDeptIds.indexOf(item.id) > -1) {
            this.$set(item, 'checked', true)
            this.currentCheckedItems.push({id: item.id, type: item.type})
          }
        }

        if (this.readOnly) {
          this.$set(item, 'disabled', true)
          if (item.checked) {
            this.$set(item, 'selected', true)
          }
        } else {
          if (this.checkedMode === 2) {
            if (item.type === 2) {
              this.$set(item, 'disabled', true)
            }
          } else if (this.checkedMode === 3) {
            if (item.type === 1) {
              this.$set(item, 'disabled', true)
            }
          }
        }
        this.$set(item, 'expand', true)
        this.handleTree(item.children)
      })
    },
    search () {
      this.getOrgTree().then(res => {
        this.data = res
        this.handleTree(this.data)
      })
    },
    select () {
      this.currentVisible = false
      this.$emit('on-select', this.currentCheckedItems)
    },
    visibleChange (val) {
      if (!val) {
        this.currentCheckedItems = []
        this.currentVisible = false
        this.$emit('on-close')
      }
    },
    checkChange (checkedNodes, node) {
      this.currentCheckedItems = []
      if (this.checkedMode === 1) {
        checkedNodes.forEach(item => {
          this.currentCheckedItems.push({id: item.id, type: item.type})
        })
      } else if (this.checkedMode === 2) {
        checkedNodes.forEach(item => {
          if (item.type === 1) {
            this.currentCheckedItems.push({id: item.id, type: item.type})
          }
        })
      } else if (this.checkedMode === 3) {
        checkedNodes.forEach(item => {
          if (item.type === 2) {
            this.currentCheckedItems.push({id: item.id, type: item.type})
          }
        })
      }
    }
  },
  watch: {
    visible (val) {
      if (val) {
        this.search()
        this.currentVisible = true
      }
    }
  }
}
</script>

<style>
  #refOrgMultipleTree .ivu-tree-title-selected {
    color: #fff;
    background-color: #2d8cf0;
  }
  #refOrgMultipleTree .ivu-tree-title-selected:hover {
    color: #fff;
    background-color: #2d8cf0;
  }
</style>
