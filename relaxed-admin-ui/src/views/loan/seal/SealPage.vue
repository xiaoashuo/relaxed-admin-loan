<template>
  <div class="app-container">
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
               @newBtnClick="showNewModal" @editBtnClick="showEditModal"
               @delBtnClick="handleDelClick"
    >
      <template #sealAddress="scope">
        <yi-image
          :src="scope.row.sealAddress"
          :image-style="{width: '40px' ,height:'40px'}"
          :preview-src-list="[scope.row.sealAddress]"
        />
      </template>

    </pro-table>
    <!--模态表单组件-->
    <form-modal ref="formModal" :modal-config="modalConfig"
                :req-function="reqFunction"
                @submitSuccess="handleSubmit"
                :before-request="handleBeforeRequest"
                :before-submit="handleBeforeSubmit"
    >
      <template #sealSource="scope">
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
                         :fileType="uploadConfig.fileType"
                         list-type="picture-card"

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
  import {contentTableConfig} from "./config/content.table.config";
  import {searchFormConfig} from "./config/search.form.config";
  import {modalFormConfig} from "./config/modal.form.config";
  //页面相关请求方法
  import {getPage, addObj, putObj, delObj} from "@/api/loan/seal";
  import { FILE_UPLOAD_URL } from '@/constants/SysConstants'
  import { deleteFile } from '@/api/common'

  import {YiRadioGroup} from '@/components/radio'
  import {YiUpload} from '@/components/upload'
  import YiImage from '@/components/image'
  export default {
    name: "seal",
    components:{
      YiRadioGroup,
      YiUpload,
      YiImage
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
        //上传配置
        uploadData:[],
        uploadConfig:{
          uploadUrl: FILE_UPLOAD_URL,
          deleteFileRequest:deleteFile,
          limit: 1,
          fileType:['png','jpg','jpeg']

        }
      }
    },
    methods: {
      //表格相关
      showNewModal() {
        this.uploadData=[]
        this.$refs.formModal.add({title: '新增',item:{sealSource:1}})
      },
      showEditModal(item) {

        console.log(item)
        if(item.sealSource==2){
          let uploadData = item.sealAddress
          const filename=item.sealFilename
          const uid= new Date().getTime()

          this.uploadData=[]
          this.uploadData.push({
            url: uploadData,
            name: filename,
            uid:uid,
            fileId:uid
          })
        }

        this.$refs.formModal.update({title: '编辑', item})
      },
      handleBeforeRequest(payload){
        const sealSource= payload.sealSource
        if (sealSource==2){
          let paramArray = this.uploadData[0]
          payload.sealAddress=paramArray.path
          payload.sealFilename=paramArray.name
        }
        return payload
      },
      handleBeforeSubmit(payload){
        const sealSource= payload.sealSource

        if (sealSource===2&&!payload.sealAddress){
          this.$message.error("文件不能为空");
          return false
        }
        return true
      },
      //删除数据
      handleDelClick(item) {
        delObj(item.sealId).then(res => {
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
