<template>
  <FormItem :label="label" :prop="prop">
    <InputNumber class="form-item"
                 :active-change="false"
                 :precision="precision"
                 :step="step"
                 :min="min"
                 :max="max"
                 :readonly="readonly"
                 v-model="currentValue"
                 @on-change="change"></InputNumber>
  </FormItem>
</template>

<script>
export default {
  name: 'FormNumber',
  props: {
    value: {
      type: Number,
      default: 0
    },
    label: '',
    prop: '',
    readonly: false,
    precision: {
      type: Number,
      default: 0
    },
    step: {
      type: Number,
      default: 1
    },
    min: {
      type: Number,
      default: -Infinity
    },
    max: {
      type: Number,
      default: Infinity
    }
  },
  data () {
    return {
      currentValue: this.value
    }
  },
  methods: {
    change (value) {
      this.$emit('input', value)
    },
    setCurrentValue (value) {
      if (value === this.currentValue) return
      this.currentValue = value
    }
  },
  watch: {
    value (val) {
      this.setCurrentValue(val)
    }
  }
}
</script>
