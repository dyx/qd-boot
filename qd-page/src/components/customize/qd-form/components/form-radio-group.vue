<template>
  <FormItem :label="label" :prop="prop">
    <RadioGroup v-model="currentValue" @on-change="change">
      <Radio v-for="item in items" :label="item.value" :key="item.value">{{item.desc}}</Radio>
    </RadioGroup>
  </FormItem>
</template>

<script>
export default {
  name: 'FormRadioGroup',
  props: {
    value: '',
    label: '',
    prop: '',
    items: Array
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
