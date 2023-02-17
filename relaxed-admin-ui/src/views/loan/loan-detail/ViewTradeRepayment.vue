<template>
  <div class="app-container">
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <yi-pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
               :lazy-load="true"
    >
      <template #repayType="scope">
        <dict-text dict-code="repay_type" :value="scope.row.repayType"></dict-text>
      </template>
    </yi-pro-table>



  </div>
</template>

<script>

//页面配置参数
import {contentTableConfig} from "./config/repayment.content.table.config";
import {repaymentSearchFormConfig} from "./config/repayment.search.form.config";
//页面相关请求方法
import {getTradeRepaymentPage} from "@/api/loan/trade";

export default {
  name: "ViewTradeRepaymentPage",
  props:{
    orderId:{
      type: Number,
      required: true
    }
  },
  data() {
    return {
      //页面相关配置
      contentTableConfig: contentTableConfig,
      searchFormConfig:repaymentSearchFormConfig,
      //表格请求
      tableRequest: getTradeRepaymentPage,

    }
  },
  created() {
    console.log("当前附件id",this.orderId)
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


    //搜索框相关 搜索数据
    handleSearchClick(formData) {
      formData.orderId=this.orderId
      this.$refs.pageContentRef.searchTable(formData)
    },
    //重置搜索
    handleResetClick() {
      this.$refs.pageContentRef.searchTable({orderId:this.orderId})
    },

  }

}
</script>

<style lang="scss" scoped>

</style>
