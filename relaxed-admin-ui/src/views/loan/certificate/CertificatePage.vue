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
                :before-request="handleBeforeRequest"
                :before-submit="handleBeforeSubmit"
                @submitSuccess="handleSubmit"
    >

      <template #authorizeType="scope">
        <el-col>
          <el-form-item :label="scope.row.item.label">

            <yi-radio-group  v-bind="scope.row.item.config"  v-model="scope.row.data[`${scope.row.item.field}`]"  >
            </yi-radio-group>

          </el-form-item>

          <template v-if="scope.row.data[`${scope.row.item.field}`]===2">
            <el-form-item label="证书文件">
              <yi-upload class="upload-file" :upload-url="uploadConfig.uploadUrl"
                         :delete-request="uploadConfig.deleteFileRequest"
                         :limit="uploadConfig.limit"
                         drag
                         v-model="uploadData"></yi-upload>
            </el-form-item>
          </template>
        </el-col>



      </template>
    </form-modal>


  </div>
</template>

<script>

  //页面配置参数
  import {contentTableConfig} from "./config/content.config";
  import {searchFormConfig} from "./config/search.config";
  import {modalConfig} from "./config/modal.config";
  //页面相关请求方法
  import {getPage, addObj, putObj, delObj} from "@/api/loan/certificate";

  import {YiRadioGroup} from '@/components/radio'
  import {YiUpload} from '@/components/upload'

  import {FILE_UPLOAD_URL} from '@/constants/SysConstants'

  import {deleteFile} from '@/api/common'
  export default {
    name: "certificate",
    components:{
      YiRadioGroup,YiUpload
    },
    data() {
      return {
        //页面相关配置
        contentTableConfig: contentTableConfig,
        searchFormConfig: searchFormConfig,
        modalConfig: modalConfig,
        //表格请求
        tableRequest: getPage,
        //表单请求
        reqFunction: {
          create: addObj,
          update: putObj
        },

        //上传配置
        uploadData:"",
        uploadConfig:{
          uploadUrl: FILE_UPLOAD_URL,
          deleteFileRequest:deleteFile,
          limit: 1

        }
      }
    },
    methods: {
      //表格相关
      showNewModal() {
        this.$refs.formModal.add({title: '新增',item:{authorizeType:1}})
      },
      showEditModal(item) {
        console.log(item)
        this.$refs.formModal.update({title: '编辑', item})
      },
      //删除数据
      handleDelClick(item) {
        delObj(item.id).then(res => {
          this.$refs.pageContentRef.refreshTable(false)
        })
      },
      handleBeforeRequest(payload){
       const authorizeType= payload.authorizeType
       if (authorizeType==2){
          payload.keystoreUrl=this.uploadData
        }
        return payload
      },
      handleBeforeSubmit(payload){
        const authorizeType= payload.authorizeType

        if (authorizeType===2&&!payload.keystoreUrl){
          this.$message.error("文件不能为空");
          return false
        }
        return true
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
  .form-modal{
    ::v-deep {
      .upload-file{
        width: 360px;
        .upload-file-uploader{
          width: 360px;
        }
      }
    }
  }

</style>
