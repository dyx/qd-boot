<template>
  <FormItem :label="label" :prop="prop">
    <Input class="form-item" type="textarea" :readonly="readonly" v-model="currentValue"
           :autosize="{minRows: minRows,maxRows: maxRows}" @on-change="change"></Input>
  </FormItem>
</template>

<script>
export default {
  name: 'FormTextArea',
  props: {
    value: 0,
    label: '',
    prop: '',
    readonly: false,
    minRows: {
      type: Number,
      default: 3
    },
    maxRows: {
      type: Number,
      default: 5
    }
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
