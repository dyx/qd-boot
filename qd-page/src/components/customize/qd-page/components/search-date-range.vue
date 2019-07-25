<template>
  <FormItem :label="label">
    <DatePicker transfer
                clearable
                style="width: 180px"
                type="daterange"
                :editable="false"
                v-model="currentValue"
                @on-change="change"></DatePicker>
  </FormItem>
</template>

<script>
const startSuffix = '00:00:00'
const endSuffix = '23:59:59'
export default {
  name: 'SearchDateRange',
  props: {
    start: '',
    end: '',
    label: ''
  },
  data () {
    return {
      currentValue: ['', '']
    }
  },
  methods: {
    change (dates) {
      this.currentValue = [this.format(dates[0], startSuffix), this.format(dates[1], endSuffix)]
      this.$emit('on-change', this.currentValue)
    },
    format (dateStr, formatStr) {
      if (dateStr) {
        return dateStr + ' ' + formatStr
      }
      return ''
    }
  },
  watch: {
    start (val) {
      if (!val) {
        this.currentValue = ['', '']
      }
    },
    end (val) {
      if (!val) {
        this.currentValue = ['', '']
      }
    }
  }
}
</script>
