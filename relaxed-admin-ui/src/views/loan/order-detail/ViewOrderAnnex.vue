<template>
  <div class="app-container">
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
               lazy-load
               @newBtnClick="showNewModal" @editBtnClick="showEditModal"
               @delBtnClick="handleDelClick"
    >
      <template #fileTypeSlot="scope">

         <dict-text dict-code="file_type" :value="scope.row.fileType"></dict-text>
         <span>-文件标识:{{scope.row.fileType}}</span>
      </template>
      <template #customHandler="scope">

        <el-button size="mini" type="text"  icon="el-icon-download"
                   @click="handleDownloadClick(scope.row)">下载
        </el-button>
      </template>
    </pro-table>
    <!--模态表单组件-->
    <form-modal ref="formModal" :modal-config="modalConfig"
                :append-to-body="true"
                :req-function="reqFunction"
                :before-request="beforeRequestDataHandle"
                :before-submit="beforeSubmitDataHandle"
                @submitSuccess="handleSubmit"
    ></form-modal>


  </div>
</template>

<script>

  //页面配置参数
  import {contentTableConfig} from "./config/annex/content.table.config";
  import {searchFormConfig} from "./config/annex/search.form.config";
  import {modalFormConfig} from "./config/annex/modal.form.config";
  //页面相关请求方法
  import {getPage, addObj, putObj, delObj,download} from "@/api/loan/order-annex";
  import { remoteFileDownload } from '@/utils/fileUtil'

  export default {
    name: "ViewOrderAnnexPage",
    props:{
      orderId:{
        type: Number,
        required:true
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
    created() {

      this.$nextTick(()=>{
        this.$refs.pageContentRef.searchTable({orderId:this.orderId})
      })
    },
    watch:{
      orderId(val){
        this.$refs.pageContentRef.searchTable({orderId:this.orderId})
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
      beforeRequestDataHandle(formData){
        const uploadData= formData.uploadData
        let  reqInfo={orderId:this.orderId,fileType:formData.fileType}
        if (uploadData){
          const firstData= uploadData[0]
          reqInfo.fileNo=firstData.fileId
          reqInfo.fileName=firstData.name
          reqInfo.fileUrl=firstData.path
        }
        return reqInfo
      },
      beforeSubmitDataHandle(formData){

        console.log(formData)
        if (!formData.fileUrl){
          this.$message.error("上传文件不能为空")
          return false
        }
        return  true
      },
      //模态框相关 提交成功后刷新表格
      handleSubmit(res) {
        this.$refs.pageContentRef.refreshTable(false)
      },
      //搜索框相关 搜索数据
      handleSearchClick(formData) {
        formData.orderId=this.orderId
        this.$refs.pageContentRef.searchTable(formData)
      },
      //重置搜索
      handleResetClick() {
        this.$refs.pageContentRef.searchTable({orderId:this.orderId})
      },
      handleDownloadClick(row){
        download(row.fileId).then(res=>{
          remoteFileDownload(res)
        })
      }

    }

  }
</script>

<style lang="scss" scoped>

</style>
