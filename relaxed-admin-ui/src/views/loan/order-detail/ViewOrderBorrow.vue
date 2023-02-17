<template>
  <div class="app-container">
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <yi-pro-table ref="pageContentRef"
               lazy-load
               :content-table-config="contentTableConfig" :request="tableRequest"

    ></yi-pro-table>



  </div>
</template>

<script>

//页面配置参数
import {contentTableConfig} from "./config/borrow/content.table.config";
import {searchFormConfig} from "./config/borrow/search.form.config";

//页面相关请求方法
import {getPage, } from "@/api/loan/order-borrow";

export default {
  name: "ViewOrderBorrowPage",
  props:{
    orderId:{
      type: Number,
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

      //表格请求
      tableRequest: getPage,

    }
  },
  methods: {



    //搜索框相关 搜索数据
    handleSearchClick(formData) {
      formData.orderId=this.orderId;
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
