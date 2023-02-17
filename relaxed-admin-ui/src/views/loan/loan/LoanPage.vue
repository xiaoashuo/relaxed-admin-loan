<template>
  <div class="app-container">
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <yi-pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
               @addBtnClick="showNewModal" @editBtnClick="showEditModal"
               @delBtnClick="handleDelClick"
    >
      <template #loanStatus="scope">
        <dict-text dict-code="loan_status" :value="scope.row.loanStatus"></dict-text>
      </template>
      <template #customHandler="scope">

        <el-button size="mini" type="text"
                   @click="handleViewClick(scope.row)">查看
        </el-button>
        <el-button v-if="scope.row.loanStatus==21" size="mini" type="text"
                   @click="handleClearClick(scope.row)">结清申请
        </el-button>
      </template>
    </yi-pro-table>
    <!--模态表单组件-->
    <form-modal ref="formModal" :modal-config="modalConfig"
                :req-function="reqFunction"
                @submitSuccess="handleSubmit"
    ></form-modal>

    <loan-detail-model ref="loanDetailRef"></loan-detail-model>

  </div>
</template>

<script>

  //页面配置参数
  import {contentTableConfig} from "./config/content.table.config";
  import {searchFormConfig} from "./config/search.form.config";
  import {modalFormConfig} from "./config/modal.form.config";
  //页面相关请求方法
  import {getPage, addObj, putObj, delObj,clearApply} from "@/api/loan/loan";

  import LoanDetailModel from '@/views/loan/loan-detail/LoanDetailModel.vue'
  export default {
    name: "LoanPage",
    components:{
      LoanDetailModel
    },
    data() {
      return {
        //页面相关配置
        contentTableConfig: contentTableConfig,
        searchFormConfig: searchFormConfig,
        modalConfig: modalFormConfig,
        //表格请求
        tableRequest: getPage,
        //表单请求
        reqFunction: {
          create: addObj,
          update: putObj
        },
      }
    },
    methods: {
      //表格相关
      showNewModal() {
        this.$refs.formModal.add({title: '新增'})
      },
      showEditModal(item) {
        this.$refs.formModal.update({title: '编辑', item})
      },
      //删除数据
      handleDelClick(item) {
        delObj(item.id).then(res => {
          this.$refs.pageContentRef.refreshTable(false)
        })
      },
      //模态框相关 提交成功后刷新表格
      handleSubmit(res) {
        this.$refs.pageContentRef.refreshTable(false)
      },
      //搜索框相关 搜索数据
      handleSearchClick(formData) {
        this.$refs.pageContentRef.searchTable(formData)
      },
      //重置搜索
      handleResetClick() {
        this.$refs.pageContentRef.resetTable()
      },
      handleViewClick(row){
        this.$refs.loanDetailRef.update({ title:'借据信息',item:row })
      },
      handleClearClick(row){
        clearApply(row.loanId).then(res=>{
          this.$message.success("结清申请成功")
          this.handleResetClick()
        })
      },


    }

  }
</script>

<style lang="scss" scoped>

</style>
