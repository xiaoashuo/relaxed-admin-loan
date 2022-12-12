<template>
  <div class="app-container">
    <page-search
      ref="pageSearchRef"
      :searchFormConfig="searchFormConfig"
      @resetBtnClick="handleResetClick"
      @queryBtnClick="handleSearchClick"
    ></page-search>
    <!--表格组件-->
    <pro-table
      ref="pageContentRef"
      :content-table-config="contentTableConfig"
      :request="tableRequest"
    >
      <template #expandHandle="scope">
        <div class="reqParam">
          <h2>请求参数</h2>
          <span>{{ scope.row.reqParams }}</span>
        </div>
        <div class="reqBody">
          <h2>请求体</h2>
          <span>{{ scope.row.reqBody }}</span>
        </div>
        <div class="resBody">
          <h2>结果</h2>

          <span>{{ scope.row.result }}</span>
        </div>
      </template>
    </pro-table>
  </div>
</template>

<script>
import YiCard from '@/components/card'

import { searchFormConfig } from './config/search.config'
import { contentTableConfig } from './config/content.config'
import { getAccessLogPageList } from '@/api/log'
export default {
  name: 'access-page',
  components: {
    YiCard
  },
  data() {
    return {
      searchFormConfig: searchFormConfig,
      contentTableConfig: contentTableConfig,
      tableRequest: getAccessLogPageList
    }
  },

  methods: {
    handleSearchClick(formData) {
      this.$refs.pageContentRef.searchTable(formData)
    },
    handleResetClick() {
      this.$refs.pageContentRef.resetTable()
    }
  }
}
</script>

<style lang="scss" scoped>
.app-container {
  ::v-deep .el-table__expanded-cell {
    white-space: pre-wrap;
    padding-left: 50px;
  }
}
</style>
