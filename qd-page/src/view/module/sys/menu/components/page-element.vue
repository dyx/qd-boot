<template>
  <TabPane v-auth="'pageElementTab'" label="页面元素">
    <Row type="flex" justify="end">
      <Col>
        <Button v-auth="'savePageElementBtn'" size="small" type="primary" @click="saveVisible = true">新建</Button>
      </Col>
    </Row>
    <page-table :columns="columns"
                :data="data.records"
                :total="data.total"
                :page="params.page"
                :size="params.size"
                @on-change-page="changePage"></page-table>
    <save-page-element :visible="saveVisible"
                       :menuId="currentMenuId"
                       @on-close="saveVisible = false"
                       @on-success="saveSuccess"></save-page-element>
    <update-page-element :visible="updateVisible"
                         :id="selectId"
                         @on-close="updateVisible = false"
                         @on-success="search"></update-page-element>
  </TabPane>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import PageTable from '@/components/customize/qd-page/components/page-table'
import SavePageElement from './save-page-element'
import UpdatePageElement from './update-page-element'
export default {
  name: 'PageElement',
  components: { UpdatePageElement, SavePageElement, PageTable },
  props: {
    menuId: [Number, String]
  },
  data () {
    return {
      currentMenuId: '',
      columns: [
        { title: '元素名称', key: 'elementName', width: 150 },
        { title: '元素编码', key: 'elementCode', width: 150 },
        { title: '元素类型', key: 'elementType', width: 100, render: (h, { row }) => h('span', this.pageElementTypeDesc(row.elementType)) },
        { title: '请求url', key: 'url', minWidth: 200 },
        { title: '请求方法', key: 'method', width: 100, render: (h, { row }) => h('span', this.pageElementMethodDesc(row.method)) },
        {
          title: '操作',
          width: 150,
          align: 'center',
          render: (h, { row }) => {
            return h('div', [
              h('Button', {
                props: { type: 'primary', size: 'small' },
                directives: [{ name: 'auth', value: 'updatePageElementBtn' }],
                style: { marginLeft: '8px' },
                on: {
                  click: () => {
                    this.updateVisible = true
                    this.selectId = row.id
                  }
                }
              }, '编辑'),
              h('Poptip', {
                props: {
                  confirm: true,
                  title: '确定要删除么',
                  transfer: true
                },
                on: {
                  'on-ok': () => {
                    this.remove(row.id)
                  }
                }
              }, [h('Button', {
                props: { type: 'error', size: 'small' },
                directives: [{ name: 'auth', value: 'removePageElementBtn' }],
                style: { marginLeft: '8px' }
              }, '删除')])
            ])
          }
        }
      ],
      data: [],
      params: { ...this.$config.initPageObj },
      saveVisible: false,
      updateVisible: false,
      selectId: ''
    }
  },
  computed: {
    ...mapGetters([
      'pageElementTypeDesc',
      'pageElementMethodDesc'
    ])
  },
  methods: {
    ...mapActions([
      'getPageElementByMenuId',
      'removePageElement'
    ]),
    search () {
      this.getPageElementByMenuId({ menuId: this.currentMenuId, params: this.params }).then(res => {
        if (res) {
          this.data = res
        }
      })
    },
    changePage (page, size) {
      this.params.page = page
      this.params.size = size
      this.search()
    },
    saveSuccess () {
      // 返回首页
      this.params.page = 1
      this.search()
      this.saveVisible = false
    },
    remove (id) {
      this.removePageElement(id).then(res => {
        this.search()
      })
    }
  },
  watch: {
    menuId (newVal, oldVal) {
      if (newVal && newVal !== oldVal) {
        this.currentMenuId = newVal
        this.search()
      }
    }
  }
}
</script>
