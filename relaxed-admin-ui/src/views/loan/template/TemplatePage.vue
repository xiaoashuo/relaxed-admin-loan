<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="14">
        <!--搜索组件-->
        <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                     @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
        <!--表格组件-->
        <pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
                   @newBtnClick="showNewModal" @editBtnClick="showEditModal"
                   @delBtnClick="handleDelClick"
                   @row-click="handleRowClick"
        ></pro-table>
        <!--模态表单组件-->
        <form-modal ref="formModal" :modal-config="modalConfig"
                    :req-function="reqFunction"
                    @submitSuccess="handleSubmit"
                    :before-request="beforeDataHandle"
                    :before-submit="beforeSubmitHandle"
        >
          <template #fileUpload="scope">
            <el-form-item label="模板文件">
              <yi-upload class="upload-file" :upload-url="uploadConfig.uploadUrl"

                         :limit="uploadConfig.limit"
                         :auto-upload="false"
                         :fileType="uploadConfig.fileType"

                         drag
                         v-model="uploadData"></yi-upload>
            </el-form-item>

          </template>
        </form-modal>
      </el-col>
      <el-col :span="10">
        <!--搜索组件-->
        <page-search ref="pageItemSearchRef" :searchFormConfig="searchItemFormConfig"
                     @resetBtnClick="handleItemResetClick" @queryBtnClick="handleItemSearchClick"></page-search>
        <!--表格组件-->
        <pro-table ref="pageItemContentRef" :content-table-config="contentItemTableConfig"
                   :lazy-load="true"
                   :request="itemRequest"
        ></pro-table>

      </el-col>
    </el-row>



  </div>
</template>

<script>

  //页面配置参数
  import {contentTableConfig} from "./config/content.config";
  import {searchFormConfig} from "./config/search.config";
  import {modalConfig} from "./config/modal.config";
  import {contentItemTableConfig} from './config/content.item.config'
  import {searchItemFormConfig} from './config/search.item.config'
  //页面相关请求方法
  import {getPage, addObj, putObj, delObj} from "@/api/loan/template";
  import {getPage as getItemPage} from '@/api/loan/template-area'

  import {YiUpload} from '@/components/upload'

  export default {
    name: "templatePage",
    components:{
      YiUpload
    },
    data() {
      return {
        //页面相关配置
        contentTableConfig: contentTableConfig,

        searchFormConfig: searchFormConfig,
        modalConfig: modalConfig,
        contentItemTableConfig: contentItemTableConfig,
        searchItemFormConfig: searchItemFormConfig,
        //表格请求
        tableRequest: getPage,
        itemRequest: getItemPage,
        //表单请求
        reqFunction: {
          create: addObj,
          update: putObj
        },
        currentTemplateId:undefined,
        uploadData:"",
        uploadConfig:{
          limit: 1,
          fileType:['docx']

        }
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
      handleRowClick(row,column,event){
        this.currentTemplateId=row.templateId
        const formData={templateId:this.currentTemplateId}
        this.$refs.pageItemContentRef.searchTable(formData)
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
      //搜索框相关 搜索数据
      handleItemSearchClick(formData) {
        if (!this.currentTemplateId){
           this.$message.error("请先点击模板数据,在进行筛选")
          return false;
        }
        formData.templateId=this.currentTemplateId
        this.$refs.pageItemContentRef.searchTable(formData)
      },
      //重置搜索
      handleItemResetClick() {
        if (!this.currentTemplateId){
          this.$message.error("请先点击模板数据")
          return
        }
        const formData={templateId:this.currentTemplateId}
        this.$refs.pageItemContentRef.searchTable(formData)
      },
      beforeDataHandle(payload){
        console.log("当前参数",payload)
        let formData=new FormData()
        for (const key in payload){
          formData.append(key,payload[key])
        }
        formData.append("file",this.uploadData[0]?.raw)
        return formData
      },
      beforeSubmitHandle(payload){
        const file=payload.get("file")
        if (!file){
          this.$message.error("模板文件不能为空")
          return false
        }
        return true
      }
    }

  }
</script>

<style lang="scss" scoped>

</style>
