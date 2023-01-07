<template>
  <div class="app-container">
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <pro-table ref="pageContentRef" :content-table-config="contentTableConfig"   :lazy-load="true" :request="tableRequest"
               @newBtnClick="showNewModal" @editBtnClick="showEditModal"
               @delBtnClick="handleDelClick"
    >
      <template #fileType="scope">
        <dict-text v-model="scope.row.fileType"     dict-code="file_type" ></dict-text>
      </template>
      <template #extHandler="scope">

        <el-button
          size="small"
          type="text"
          @click="showSealConfigModal(scope.row)"
        >
          签章配置
        </el-button>
      </template>
    </pro-table>
    <!--模态表单组件-->
    <form-modal ref="formModal" :modal-config="modalConfig"
                :req-function="reqFunction"
                @submitSuccess="handleSubmit"
    >
      <template #templateId="scope">

        <el-form-item label="模板">
          <yi-select  v-bind="scope.row.item.config"  v-model="scope.row.data[`${scope.row.item.field}`]"  >
          </yi-select>
        </el-form-item>
      </template>
    </form-modal>

    <seal-config-modal ref="sealConfigModalRef"></seal-config-modal>

  </div>
</template>

<script>

  //页面配置参数
  import {contentTableConfig} from "./config/protmp.content.table.config";
  import {searchFormConfig} from "./config/protmp.search.form.config";
  import {modalFormConfig} from "./config/protmp.modal.form.config";
  //页面相关请求方法
  import {getPage, addObj, putObj, delObj} from "@/api/loan/project-template";

  import { YiSelect } from '@/components/select'
  import SealConfigModal from '@/views/loan/project/SealConfigModal'
  export default {
    name: "ProjectTemplatePage",
    components:{
      YiSelect,SealConfigModal
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
        projectId:this.$route.query.projectId
      }
    },
   created() {
      this.$nextTick(()=>{
        this.$refs.pageContentRef.searchTable({ projectId:this.projectId })
      })
   },
    methods: {
      //表格相关
      showNewModal() {
        this.$refs.formModal.add({title: '新增',item:{projectId:this.projectId}})
      },
      showEditModal(item) {
        item.projectId=this.projectId
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
        formData.projectId=this.projectId
        this.$refs.pageContentRef.searchTable(formData)
      },
      //重置搜索
      handleResetClick() {
        this.$refs.pageContentRef.searchTable({ projectId:this.projectId })
      },
      showSealConfigModal(row){
        this.$refs.sealConfigModalRef.add({title:'配置签章',item:row})
      },


    }

  }
</script>

<style lang="scss" scoped>

</style>
