<template>
  <div class="app-container">
    <!--表格组件-->
    <yi-pro-table ref="pageContentRef"
               lazy-load
               :content-table-config="contentTableConfig" :request="tableRequest"
               @addBtnClick="showNewModal"
    ></yi-pro-table>

    <!--模态表单组件-->
    <form-modal ref="formModal" :modal-config="modalFormConfig"
                :append-to-body="true"
                :req-function="reqFunction"
                :beforeRequest="beforeRequestDataHandle"
                @submitSuccess="handleSubmit"
    ></form-modal>

  </div>
</template>

<script>

//页面配置参数
import {contentTableConfig} from "./config/borrow/content.table.config";
import {searchFormConfig} from "./config/borrow/search.form.config";
import {modalFormConfig} from "./config/borrow/modal.form.config";

//页面相关请求方法
import { addObj, getPage, putObj } from '@/api/loan/order-borrow'

export default {
  name: "OrderBorrowForm",
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
      searchFormConfig: searchFormConfig,
      modalFormConfig:modalFormConfig,
      //表格请求
      tableRequest: getPage,
      reqFunction:{
        create: addObj,
        update: putObj
      }
    }
  },
  methods: {
    //表格相关
    showNewModal() {
      this.$refs.formModal.add({title: '新增'})
    },
    //模态框相关 提交成功后刷新表格
    handleSubmit(res) {
      this.$refs.pageContentRef.searchTable({orderId:this.orderId})
    },
    beforeRequestDataHandle(payload){
      payload.orderId=this.orderId
      return payload;
    }
  }

}
</script>

<style lang="scss" scoped>

</style>
