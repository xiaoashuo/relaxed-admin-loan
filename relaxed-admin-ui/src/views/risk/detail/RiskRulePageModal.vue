<template>
  <div class="app-container">
    <el-dialog
      :title="title"
      :visible.sync="visible"

      fullscreen
      :before-close="dialogClose"
    >
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <yi-pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
                  :lazy-load="true"
               @addBtnClick="showNewModal" @editBtnClick="showEditModal"
               @delBtnClick="handleDelClick"
    >
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
      <risk-rule-form-modal ref="formModal" :req-function="reqFunction" @submitSuccess="handleSubmit"></risk-rule-form-modal>
    </el-dialog>



  </div>
</template>

<script>

//页面配置参数
import {contentTableConfig} from "./config/rule/content.table.config";
import {searchFormConfig} from "./config/rule/search.form.config";
//页面相关请求方法
import { getPage, addObj, putObj, delObj, getRuleFieldList, switchStatus } from '@/api/risk/risk-rule'
import formModalActionMixin from '@/mixins/form/formModalActionMixin'
import RiskRuleFormModal from '@/views/risk/detail/RiskRuleFormModal.vue'
import formModalMixin from '@/mixins/form/formModalMixin'
import { getFieldList } from '@/api/risk/risk-field'

export default {
  name: "RiskRulePageModal",
  components: { RiskRuleFormModal },
  mixins:[formModalMixin],
  data() {
    return {
      //页面相关配置
      contentTableConfig: contentTableConfig,
      searchFormConfig: searchFormConfig,
      //表格请求
      tableRequest: getPage,
      //表单请求
      reqFunction: {
        create: addObj,
        update: putObj
      },
      activationData:{

      },
      allFields:[]
    }
  },


  methods: {
    dialogClose(done){
      this.close()
      done()
    },
    show(payload){
      this.title=payload.title
      this.visible=true
      this.$nextTick(()=>{
        this.$refs.pageContentRef.searchTable({modelId:this.activationData.modelId,activationId:this.activationData.id })
      })
    },
    /**
     * 构建创建表单成功后的回调
     * @param payload
     */
    createdFormCallback(payload){
      const {title,item}=payload
      this.activationData=item

      getRuleFieldList(item.modelId).then(res=>{
        const {data}=res
        for (const item of data){
          const type=item.type
          const label=item.label
          const value=item.value
          const modifiedLabel=`${type=='1'?'基础字段':'预处理字段'}/${label}`
          const modifiedValue=`context.${type=='1'?'eventJson':'preItemMap'}.${value}`
          this.allFields.push({label:modifiedLabel,value:modifiedValue})
        }
      })


    },
    //表格相关
    showNewModal() {
      this.$refs.formModal.add({title: '新增规则',item:{activationData:this.activationData,allFields:this.allFields}})
    },
    showEditModal(item) {
      this.$refs.formModal.update({title: '编辑规则', item:{activationData:this.activationData,ruleData:item,allFields:this.allFields}})
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
      formData.modelId=this.activationData.modelId
      formData.activationId=this.activationData.id
      this.$refs.pageContentRef.searchTable(formData)
    },
    //重置搜索
    handleResetClick() {
      this.$refs.pageContentRef.searchTable({modelId:this.activationData.modelId,activationId:this.activationData.id })
    },
    handleStatusTagClick(row,val){
      switchStatus(row.id,val).then(res=>{
        this.$message.success("切换状态成功")
        this.$refs.pageContentRef.refreshTable(false)


      })
    }

  }

}
</script>

<style lang="scss" scoped>

</style>
