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
      <template #extHandler="scope">

        <el-button size="mini" type="text"  icon="el-icon-edit"
                   @click="handleModelConfig(scope.row)">进入模型
        </el-button>
      </template>
      <template #customStatus="scope">
        <el-switch
          v-model="scope.row.status"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="1"
          :inactive-value="0"
          @change="(val)=>handleStatusTagClick(scope.row,val)"
        >
        </el-switch>


      </template>

    </yi-pro-table>
    <!--模态表单组件-->
    <form-modal ref="formModal" :modal-config="modalConfig"
                :req-function="reqFunction"
                @submitSuccess="handleSubmit"
    ></form-modal>


  </div>
</template>

<script>

//页面配置参数
import {contentTableConfig} from "./config/content.table.config";
import {searchFormConfig} from "./config/search.form.config";
import {modalFormConfig} from "./config/modal.form.config";
//页面相关请求方法
import {getPage, addObj, putObj, delObj,switchModelStatus} from "@/api/risk/risk-model";

export default {
  name: "RiskModelPage",
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
    handleModelConfig(row){
      this.$router.push({
        path:'/risk/model/config?modelId='+row.id
      })
    },
    handleStatusTagClick(row,val){
      switchModelStatus(row.id,val).then(res=>{
        this.$message.success("切换状态成功")
        this.$refs.pageContentRef.refreshTable(false)
      })
    }

  }

}
</script>

<style lang="scss" scoped>

</style>
