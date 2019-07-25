<template>
  <Spin fix v-if="this.$store.state.app.showRequestLoading"></Spin>
</template>

<script>
export default {
  name: 'QdLoading'
}
</script>
