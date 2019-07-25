<template>
  <FormItem :label="label" :prop="prop">
    <Select :clearable="clearable" transfer class="form-item" v-model="currentValue" @on-change="change">
      <Option v-for="item in items" :value="item.value" :key="item.value">{{item.desc}}</Option>
    </Select>
  </FormItem>
</template>

<script>
export default {
  name: 'FormSelect',
  props: {
    value: [Number, String],
    label: '',
    prop: '',
    items: Array,
    clearable: {
      type: Boolean,
      default: true
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
      this.$emit('on-change', value)
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
