<template>
  <FormItem :label="label" id="searchRef">
    <Input clearable
           readonly
           class="search-item"
           :placeholder="placeholder"
           v-model="currentValue"
           @click.native="click"
           @on-clear="clear"
           @on-change="change">
    <Icon type="md-search" slot="prefix"/>
    </Input>
  </FormItem>
</template>

<script>
export default {
  name: 'SearchRef',
  props: {
    value: '',
    label: '',
    placeholder: ''
  },
  data () {
    return {
      currentValue: '',
      isClearClick: false
    }
  },
  methods: {
    change (event) {
      let value = event.target.value
      this.$emit('input', value)
      this.setCurrentValue(value)
      this.$emit('on-change', event)
    },
    setCurrentValue (value) {
      if (value === this.currentValue) return
      this.currentValue = value
    },
    click () {
      if (!this.isClearClick) {
        this.$emit('on-click')
      }
      this.isClearClick = false
    },
    clear () {
      this.$emit('on-clear')
      this.isClearClick = true
    }
  },
  watch: {
    value (val) {
      this.setCurrentValue(val)
    }
  }
}
</script>

<style>
  #searchRef .ivu-input-wrapper:hover .ivu-input-icon-clear {
    display: inline-block;
  }
  #searchRef .ivu-icon-ios-close-circle:before {
    content: "\F177";
  }
</style>
