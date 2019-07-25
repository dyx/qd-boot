<template>
  <FormItem :label="label" :prop="prop">
    <DatePicker transfer
                clearable
                class="form-item"
                :type="type"
                :format="format"
                :editable="false"
                v-model="currentValue"
                @on-change="change"></DatePicker>
  </FormItem>
</template>

<script>
export default {
  name: 'FormDateRange',
  props: {
    start: '',
    end: '',
    label: '',
    prop: '',
    type: {
      type: String,
      default: 'datetimerange'
    },
    format: {
      type: String,
      default: 'yyyy-MM-dd HH:mm'
    }
  },
  data () {
    return {
      currentValue: ['', '']
    }
  },
  methods: {
    change (dates) {
      this.currentValue = dates
      this.$emit('on-change', dates)
    }
  },
  watch: {
    start (val) {
      if (!val) {
        this.currentValue = ['', '']
      } else {
        this.currentValue.splice(0, 1, val)
      }
    },
    end (val) {
      if (!val) {
        this.currentValue = ['', '']
      } else {
        this.currentValue.splice(1, 1, val)
      }
    }
  }
}
</script>
