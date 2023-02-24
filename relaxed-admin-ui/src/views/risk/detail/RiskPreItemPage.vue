<template>
  <div class="app-container">
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <yi-pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
                  :lazy-load="true"
               @addBtnClick="showNewModal" @editBtnClick="showEditModal"
               @delBtnClick="handleDelClick"
    ></yi-pro-table>
    <!--模态表单组件-->
    <form-modal ref="formModal" :modal-config="modalConfig"
                :req-function="reqFunction"
                @submitSuccess="handleSubmit"
    ></form-modal>


  </div>
</template>

<script>

//页面配置参数
import {contentTableConfig} from "./config/preItem/content.table.config";
import {searchFormConfig} from "./config/preItem/search.form.config";
import {modalFormConfig} from "./config/preItem/modal.form.config";
//页面相关请求方法
import {getPage, addObj, putObj, delObj} from "@/api/risk/risk-pre-item";

export default {
  name: "RiskPreItemPage",
  props:{
    modelId:{
      type: String,
      required: true
    }
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
  created(){
    this.$nextTick(()=>{
      this.$refs.pageContentRef.searchTable({modelId:this.modelId},false)
    })
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
      formData.modelId=this.modelId
      this.$refs.pageContentRef.searchTable(formData)
    },
    //重置搜索
    handleResetClick() {
      this.$refs.pageContentRef.searchTable({modelId:this.modelId})
    },

  }

}
</script>

<style lang="scss" scoped>

</style>
