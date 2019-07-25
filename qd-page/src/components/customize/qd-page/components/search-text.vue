<template>
  <FormItem :label="label">
    <Input clearable class="search-item" v-model="currentValue" @on-change="change"></Input>
  </FormItem>
</template>

<script>
export default {
  name: 'SearchText',
  props: {
    value: '',
    label: ''
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
