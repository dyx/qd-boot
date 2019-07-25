<template>
  <Row>
    <qd-loading></qd-loading>
    <Col :span="treeSpan">
      <Card>
        <p v-if="treeTitle.length > 0" slot="title">{{treeTitle}}</p>

        <slot slot="extra" name="treeButtonItem"></slot>

        <slot name="treeSearchItem">
        </slot>
        <Button v-if="$scopedSlots.treeSearchItem" type="primary" size="small" @click="init" style="margin-left: 8px">查询</Button>

        <Row :style="{margin: $scopedSlots.treeSearchItem ? '8px 0' : 0 }">
          <qd-loading></qd-loading>
          <Col>
            <Tree ref="tree" :data="treeData" @on-select-change="selectChange"></Tree>
          </Col>
        </Row>

        <Row>
          <Col>
            <Page show-total
                  show-elevator
                  transfer
                  size="small"
                  v-if="treeTotal > 0"
                  :total="treeTotal"
                  :page-size="treeSize"
                  :current="treePage"
                  @on-change="changePage"></Page>
          </Col>
        </Row>
      </Card>
    </Col>
    <Col :span="24-treeSpan">
      <Card dis-hover>

        <p v-if="viewTitle" slot="title">{{viewTitle}}</p>

        <slot v-if="viewTitle" slot="extra" name="viewButtonItem"></slot>

        <view-panel v-if="viewTitle" :data="viewData"></view-panel>

        <Tabs>
          <slot name="viewTabItem"></slot>
        </Tabs>
      </Card>
    </Col>
  </Row>
</template>

<script>
import QdLoading from '../qd-loading/qd-loading'
import ViewPanel from '../qd-view/components/view-panel'
export default {
  name: 'QdTreeView',
  components: { ViewPanel, QdLoading },
  props: {
    treeTitle: '',
    treeData: {
      type: Array
    },
    treePage: Number,
    treeSize: Number,
    treeTotal: Number,
    treeSpan: {
      type: Number,
      default: 8
    },
    viewTitle: '',
    viewData: {
      type: Array
    }
  },
  data () {
    return {

    }
  },
  methods: {
    init () {
      this.$emit('on-tree-init')
    },
    selectChange (selectedNodes, node) {
      // 禁止取消选择
      if (!node.selected) {
        this.$refs.tree.handleSelect(node.nodeKey)
      }
      if (node) {
        this.$emit('on-tree-select', node)
      }
    },
    changePage (page) {
      this.$emit('on-tree-change-page', page)
    }
  },
  mounted () {
    this.init()
  }
}
</script>
