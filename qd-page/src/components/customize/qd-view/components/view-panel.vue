<template>
  <div>
    <Row v-for="(items, index) in data" :key="index">
      <template v-for="(item, itemIndex) in items">
        <Col :span="24/items.length" class="view-panel-col" :key="itemIndex">
          <view-label v-if="items.length > 1"
                      :key="itemIndex"
                      :label="item.label"
                      :type="item.type"
                      :render="item.render"
                      :value="item.value"></view-label>
          <view-label v-else
                      :labelSpan="3"
                      :valueSpan="21"
                      :key="itemIndex"
                      :type="item.type"
                      :label="item.label"
                      :render="item.render"
                      :value="item.value"></view-label>
        </Col>
      </template>
    </Row>
  </div>
</template>

<script>
import './index.less'
import ViewLabel from './view-label'

export default {
  name: 'ViewPanel',
  components: { ViewLabel },
  props: {
    data: Array
  }
}
</script>
