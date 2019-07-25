<template>
  <Modal title="选择公司"
         width="300"
         v-model="currentVisible"
         @on-visible-change="close">
    <Tree ref="tree" :data="treeData" @on-select-change="select"></Tree>
    <div slot="footer">
      <Button @click="currentVisible=false">取消</Button>
      <Button type="primary" @click="submit">确定</Button>
    </div>
  </Modal>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'RefCompanyTree',
  props: {
    visible: false
  },
  data () {
    return {
      currentVisible: false,
      treeData: [],
      selectNode: {}
    }
  },
  methods: {
    ...mapActions([
      'getCompanyRefTree'
    ]),
    search () {
      this.getCompanyRefTree().then(res => {
        this.treeData = res
        if (this.treeData && this.treeData.length > 0) {
          this.selectNode = this.treeData[0]
          this.$set(this.treeData[0], 'selected', true)
          this.$set(this.treeData[0], 'expand', true)
        }
      })
    },
    close (val) {
      if (!val) {
        this.$emit('on-close')
      }
    },
    select (selectedNodes, node) {
      // 禁止取消选择
      if (!node.selected) {
        this.$refs.tree.handleSelect(node.nodeKey)
      }
      this.selectNode = node
    },
    submit () {
      this.$emit('on-select', this.selectNode)
      this.currentVisible = false
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
