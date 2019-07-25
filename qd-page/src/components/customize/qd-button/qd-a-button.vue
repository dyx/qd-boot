<template>
  <a href="#" @click.prevent="click">
    <Icon v-if="iconType !== ''" :type="iconType" />
    {{name}}
  </a>
</template>

<script>
export default {
  name: 'QdAButton',
  props: {
    name: '',
    iconType: ''
  },
  data () {
    return {

    }
  },
  methods: {
    click (event) {
      this.$emit('on-click', event)
    }
  }
}
</script>
