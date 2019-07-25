<script>
export default {
  name: 'ViewLabelCustomValue',
  functional: true,
  props: {
    render: Function
  },
  render: (h, ctx) => {
    return ctx.props.render(h)
  }
}
</script>
