<template>
    <div class="app-container">
      <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                   @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>

      <pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest">
        <template #extStatus="scope">
          <el-button plain size="small" :type="scope.row.status==1?'success':'danger'">{{scope.row.status==1? '成功': '失败'}}
          </el-button>
        </template>
      </pro-table>
    </div>
</template>

<script>


  import {getLoginLogPageList} from '@/api/log'
  import {searchFormConfig} from "./config/search.config";
  import {contentTableConfig} from "./config/content.config";
    export default {
        name: "login-page",

      data() {
        return {
          searchFormConfig:searchFormConfig,
          contentTableConfig:contentTableConfig,
          tableRequest: getLoginLogPageList
        };
      },

      methods:{
        handleSearchClick(formData) {
          this.$refs.pageContentRef.searchTable(formData)
        },
        handleResetClick(){
          this.$refs.pageContentRef.resetTable()
        }
      }
    }
</script>

<style lang="scss" scoped>
  .app-container{

  ::v-deep .el-table__expanded-cell{
    white-space: pre-wrap;
    padding-left: 50px;
  }
  }
</style>
