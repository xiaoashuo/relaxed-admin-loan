<template>
  <div class="app-container">

    <!--表格组件-->
    <yi-pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
               :lazy-load="true"
               @addBtnClick="showNewModal" @editBtnClick="showEditModal"
               @delBtnClick="handleDelClick"
    >
      <template #accountType="scope">
        <dict-text dict-code="bank_account_type" :value="scope.row.accountType"></dict-text>
      </template>
      <template #cardPurpose="scope">
        <dict-text dict-code="bank_card_purpose" :value="scope.row.cardPurpose"></dict-text>
      </template>
    </yi-pro-table>
    <!--模态表单组件-->
    <form-modal ref="formModal" :modal-config="modalConfig"
                :append-to-body="true"
                :req-function="reqFunction"
                :before-request="beforeRequestDataHandle"
                @submitSuccess="handleSubmit"
    ></form-modal>


  </div>
</template>

<script>

//页面配置参数
import {contentTableConfig} from "./config/bank/content.table.config";
import {modalFormConfig} from "./config/bank/modal.form.config";
//页面相关请求方法
import {getPage, addObj, putObj, delObj} from "@/api/loan/order-bank-card";

export default {
  name: "OrderBankForm",
  props:{
    orderId:{
      type: Number|null,
      required:true
    },
    isCreamForm:{
      type: Boolean,
      required:true
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
  data() {
    return {
      //页面相关配置
      contentTableConfig: contentTableConfig,
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
    beforeRequestDataHandle(formData){
      formData.orderId=this.orderId
      return formData
    },
    //模态框相关 提交成功后刷新表格
    handleSubmit(res) {
      this.$refs.pageContentRef.refreshTable(false)
    },


  }

}
</script>

<style lang="scss" scoped>

</style>
