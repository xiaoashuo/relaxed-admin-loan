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
               @addBtnClick="showNewModal" @editBtnClick="showEditModal"
               @delBtnClick="handleDelClick"
    ></yi-pro-table>
    <!--模态表单组件-->
      <risk-rule-form-modal ref="formModal" :req-function="reqFunction"></risk-rule-form-modal>
    </el-dialog>



  </div>
</template>

<script>

//页面配置参数
import {contentTableConfig} from "./config/rule/content.table.config";
import {searchFormConfig} from "./config/rule/search.form.config";
//页面相关请求方法
import {getPage, addObj, putObj, delObj} from "@/api/risk/risk-rule";
import formModalActionMixin from '@/mixins/form/formModalActionMixin'
import RiskRuleFormModal from '@/views/risk/detail/RiskRuleFormModal.vue'
import formModalMixin from '@/mixins/form/formModalMixin'

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
    }
  },
  methods: {
    dialogClose(done){
      this.close()
      done()
    },
    //表格相关
    showNewModal() {
      this.$refs.formModal.add({title: '新增规则'})
    },
    showEditModal(item) {
      this.$refs.formModal.update({title: '编辑规则', item})
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
