<template>
  <div class="app-container">
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
               @newBtnClick="showNewModal" @editBtnClick="showEditModal"
               @delBtnClick="handleDelClick"
    ></pro-table>
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
  import {getPage, addObj, putObj, delObj} from "@/api/loan/seal";

  export default {
    name: "seal",
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

    }

  }
</script>

<style lang="scss" scoped>

</style>
