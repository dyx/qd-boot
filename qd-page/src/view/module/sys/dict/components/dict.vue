<template>
  <TabPane v-auth="'pageElementTab'" label="字典">
    <Row type="flex" justify="end">
      <Col>
        <Button v-auth="'saveDict'" size="small" type="primary" @click="saveVisible = true">新建</Button>
      </Col>
    </Row>
    <page-table :columns="columns"
                :data="data.records"
                :total="data.total"
                :page="params.page"
                :size="params.size"
                @on-change-page="changePage"
                @on-sort-change="sortChange"></page-table>
    <save-dict :typeCode="typeCode"
               :typeName="typeDesc"
               :visible="saveVisible"
               @on-close="saveVisible = false"
               @on-save-success="saveSuccess"
               @on-update-success="updateSuccess"></save-dict>
    <update-dict :id="selectId"
                 :visible="updateVisible"
                 @on-close="updateVisible = false"
                 @on-update-success="updateSuccess"></update-dict>
  </TabPane>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import PageTable from '@/components/customize/qd-page/components/page-table'
import SaveDict from './save-dict'
import UpdateDict from './update-dict'
export default {
  name: 'Dict',
  components: { UpdateDict, SaveDict, PageTable },
  props: {
    typeCode: String,
    typeDesc: String
  },
  data () {
    return {
      currentTypeCode: '',
      columns: [
        { title: '值', key: 'dictValue' },
        { title: '描述', key: 'dictDesc' },
        { title: '顺序', key: 'sortNum', sortable: 'custom' },
        {
          title: '操作',
          width: 150,
          align: 'center',
          render: (h, { row }) => {
            return h('div', [
              h('Button', {
                props: { type: 'primary', size: 'small' },
                directives: [{ name: 'auth', value: 'updateDictBtn' }],
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
                directives: [{ name: 'auth', value: 'removeDictBtn' }],
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

  },
  methods: {
    ...mapActions([
      'getDictPageByTypeCode',
      'removeDict'
    ]),
    search () {
      this.getDictPageByTypeCode({ typeCode: this.currentTypeCode, params: this.params }).then(res => {
        if (res) {
          this.data = res
        } else {
          this.data.records = []
        }
      })
    },
    changePage (page, size) {
      this.params.page = page
      this.params.size = size
      this.search()
    },
    sortChange (column, key, order) {
      this.params.sortField = key
      this.params.sortOrder = order
      this.search()
    },
    saveSuccess () {
      // 返回首页
      this.params.page = 1
      this.search()
      this.saveVisible = false
      // 通知父级刷新缓存页面
      this.$emit('on-success')
    },
    updateSuccess () {
      this.search()
      this.updateVisible = false
      this.$emit('on-success')
    },
    remove (id) {
      this.removeDict(id).then(res => {
        this.search()
        this.$emit('on-success')
      })
    }
  },
  watch: {
    typeCode (newVal, oldVal) {
      if (newVal && newVal !== oldVal) {
        this.currentTypeCode = newVal
        this.search()
      }
    }
  }
}
</script>
