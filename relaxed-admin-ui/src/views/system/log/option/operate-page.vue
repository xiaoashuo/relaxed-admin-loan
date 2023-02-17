<template>
    <div class="app-container">
      <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                   @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
      <!--表格组件-->
      <yi-pro-table ref="pageContentRef" :content-table-config="contentTableConfig"  :show-user-tools="false"
                    :request="tableRequest"


      >
        <template #expandHandle="scope">
          <div class="reqParam">
            <h2>请求</h2>
            <span>{{scope.row.params}}</span>
          </div>
          <div class="resBody">
            <h2>结果</h2>
            <span>{{scope.row.result}}</span>
          </div>
        </template>
        <template #extStatus="scope">
          <el-button plain size="small" :type="scope.row.httpStatus==200?'success':'danger'">{{scope.row.httpStatus==200? '成功': '失败'}}
          </el-button>
        </template>
      </yi-pro-table>



    </div>
</template>

<script>
  import {getOperatorLogPageList} from '@/api/log'
  import {searchFormConfig} from "./config/search.config";
  import {contentTableConfig} from "./config/content.config";
    export default {
        name: "operate-page",

      data() {
        return {
          searchFormConfig:searchFormConfig,
          contentTableConfig:contentTableConfig,
          tableRequest: getOperatorLogPageList
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
