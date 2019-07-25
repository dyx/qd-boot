<template>
  <FormItem :label="label">
    <Select transfer class="search-item" :clearable="clearable" :placeholder="placeholder" v-model="currentValue" @on-change="change">
      <Option v-for="item in items" :value="item.value" :key="item.value">{{item.desc}}</Option>
    </Select>
  </FormItem>
</template>

<script>
export default {
  name: 'SearchSelect',
  props: {
    value: '',
    label: '',
    items: Array,
    placeholder: '',
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
