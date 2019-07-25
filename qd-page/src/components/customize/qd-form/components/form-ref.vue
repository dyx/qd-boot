<template>
  <FormItem id="formRef" :label="label" :prop="prop" :rules="rules">
    <Input clearable
           readonly
           ref="formRef"
           class="form-item"
           :placeholder="placeholder"
           v-model="currentValue"
           @click.native="click"
           @on-clear="clear"
           @on-change="change">
    <Icon type="md-search" slot="prefix"/>
    </Input>
    <slot name="extra"></slot>
  </FormItem>
</template>

<script>
export default {
  name: 'FormRef',
  props: {
    value: '',
    label: '',
    prop: '',
    placeholder: '',
    rules: {
      type: [Object, Array]
    }
  },
  data() {
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
      if (value) {
        this.$refs.formRef.focus()
      }
      this.currentValue = value
    },
    click () {
      if (!this.isClearClick) {
        this.$emit('on-click')
      }
      this.isClearClick = false
      this.$refs.formRef.focus()
    },
    clear () {
      this.$emit('on-clear')
      this.isClearClick = true
    }
  },
  watch: {
    value(val) {
      this.setCurrentValue(val)
    }
  }
}
</script>

<style>
  #formRef .ivu-input-wrapper:hover .ivu-input-icon-clear {
    display: inline-block;
  }

  #formRef .ivu-icon-ios-close-circle:before {
    content: "\F177";
  }
</style>
