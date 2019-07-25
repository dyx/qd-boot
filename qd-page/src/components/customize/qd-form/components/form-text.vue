<template>
  <FormItem :label="label" :prop="prop" :rules="rules">
    <Input class="form-item" :clearable="clearable" :readonly="readonly" :disabled="disabled"
           v-model="currentValue"
           @on-change="change"></Input>
  </FormItem>
</template>

<script>
export default {
  name: 'FormText',
  props: {
    value: '',
    label: '',
    prop: '',
    rules: {
      type: [Object, Array]
    },
    readonly: false,
    disabled: false,
    clearable: true
  },
  data () {
    return {
      currentValue: this.value
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
    }
  },
  watch: {
    value (val) {
      this.setCurrentValue(val)
    }
  }
}
</script>
