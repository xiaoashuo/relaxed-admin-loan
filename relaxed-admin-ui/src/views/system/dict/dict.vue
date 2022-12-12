<template>
    <div class="app-container">
      <page-search  ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                    @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
      <!--表格组件-->
      <pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
                 @newBtnClick="showNewModal" @editBtnClick="showEditModal"
                 @delBtnClick="handleDelClick"

      >
        <template #valueType="scope">
          <dict-text dict-code="dict_value_type" :value="scope.row.valueType" ></dict-text>
        </template>
        <template #extHandler="scope">
          <el-button size="small" type="text" @click="handleDictItemClick(scope.row)">字典项</el-button>
        </template>
      </pro-table>

      <!--模态表单组件-->
      <form-modal ref="formModal" :modal-config="modalConfig"
                  :req-function="reqFunction"
                  @submitSuccess="handleSubmit"
      >
      </form-modal>
      <dict-item  ref="dictItemPage" ></dict-item>


    </div>
</template>

<script>

  import DictItem from './dict-item'

  import {contentTableConfig} from "./config/content.config";

  import {modalConfig} from "./config/modal.config";
  import {searchFormConfig} from "./config/search.config";
  import {getPageListData,addDictObj,putDictObj,delDictObj} from "@/api/dict";


    export default {
        name: "dict",
      components:{

        DictItem
      },
      data(){
        return{

          //表格和查询表单使用
          contentTableConfig:contentTableConfig,
          searchFormConfig:searchFormConfig,
          modalConfig:modalConfig,
          tableRequest: getPageListData,
          //pageModal使用
          //表单请求
          reqFunction:{
            create: addDictObj,
            update: putDictObj
          },
        }
      },

      methods:{
        handleDictItemClick(item){
          console.log("字典项被点击",this.$refs.dictItemPage,item)
          this.$refs.dictItemPage.show(item)
        },
        showNewModal(){
          this.$refs.formModal.add({title:'新增字典'})
        },
        showEditModal(item){
          this.$refs.formModal.update({title:'编辑字典',item})
        },
        handleDelClick(item){
          delDictObj(item.id).then(res=>{
            this.$refs.pageContentRef.refreshTable(false)
          })
        },
        //模态框相关
        handleSubmit(res){
          this.$refs.pageContentRef.refreshTable(false)
        },
        handleSearchClick(formData) {
          this.$refs.pageContentRef.searchTable(formData)
        },
        handleResetClick(){
          this.$refs.pageContentRef.resetTable()
        },

      }
    }
</script>

<style lang="scss" scoped>


</style>
