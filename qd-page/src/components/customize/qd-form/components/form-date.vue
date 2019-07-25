<template>
    <FormItem :label="label" :prop="prop">
        <DatePicker transfer
                    clearable
                    class="qd-form-item"
                    :type="type"
                    :format="format"
                    :editable="false"
                    v-model="currentValue"
                    @on-change="change"></DatePicker>
    </FormItem>
</template>

<script>
export default {
  name: 'FormDate',
  props: {
    value: '',
    label: '',
    prop: '',
    type: {
      type: String,
      default: 'datetime'
    },
    format: {
      type: String,
      default: 'yyyy-MM-dd HH:mm:ss'
    }
  },
  data () {
    return {
      currentValue: ''
    }
  },
  methods: {
    change (val) {
      this.$emit('input', val)
      this.setCurrentValue(val)
      this.$emit('on-change', val)
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
