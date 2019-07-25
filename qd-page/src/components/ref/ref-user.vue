<template>
  <qd-ref-page title="用户"
               :width="860"
               :visible="currentVisible"
               :data="pageData.records"
               :total="pageData.total"
               :columns="columns"
               @on-search="search"
               @on-select="select"
               @on-close="close">
    <template #searchItem>
      <search-text label="用户名" v-model="params.username"></search-text>
      <search-text label="姓名" v-model="params.fullName"></search-text>
    </template>
  </qd-ref-page>
</template>

<script>
  import {mapActions} from 'vuex';
  import SearchText from '@/components/customize/qd-page/components/search-text';
  import PageTable from '@/components/customize/qd-page/components/page-table';
  import QdRefPage from '@/components/customize/qd-ref/qd-ref-page';

  export default {
    name: 'RefUser',
    components: {QdRefPage, PageTable, SearchText},
    props: {
      visible: false
    },
    data() {
      return {
        currentVisible: false,
        params: {},
        pageData: {},
        columns: [
          {type: 'index', width: 60, align: 'center'},
          {title: '用户名', key: 'username', width: '200'},
          {title: '姓名', key: 'fullName', width: '150'},
          {title: '公司', key: 'companyName', width: '200'},
          {title: '部门', key: 'deptName', width: '200'}
        ]
      };
    },
    methods: {
      ...mapActions([
        'getUserRefPage'
      ]),
      search({page, size}) {
        this.params.page = page;
        this.params.size = size;
        this.getUserRefPage(this.params).then(res => {
          if (res) {
            this.pageData = res;
          }
        });
      },
      select (row, index) {
        this.currentVisible = false;
        this.$emit('on-select', row);
      },
      close () {
        this.currentVisible = false
        this.params = {}
        this.$emit('on-close');
      }
    },
    watch: {
      visible (val) {
        if (val) {
          this.currentVisible = true;
        }
      }
    }
  };
</script>
