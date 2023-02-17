<template>
  <div class="app-container">
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchFormConfig="searchFormConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <yi-pro-table ref="pageContentRef" :content-table-config="contentTableConfig" :request="tableRequest"
               @addBtnClick="showNewModal"
               @delBtnClick="handleDelClick"
    >
      <template #orderStage="scope">
        <dict-text dict-code="order_status"  :value="scope.row.orderStage" ></dict-text>
      </template>
      <template #customHandler="scope">
        <el-button size="mini" type="text"
                   @click="handleViewClick(scope.row)">查看
        </el-button>
        <el-button v-if="scope.row.orderStage===0" size="mini" type="text"
                   @click="showEditModal(scope.row)">编辑
        </el-button>
<!--        <el-button v-if="scope.row.orderStage===14" size="mini" type="text"-->
<!--                   @click="loanConfirm(scope.row)">确认放款-->
<!--        </el-button>-->
      </template>

    </yi-pro-table>
    <!--模态表单组件-->

    <order-detail-model  ref="orderDetailRef"></order-detail-model>


    <order-form-modal @beforeClose="handleResetClick" ref="orderFormRef"></order-form-modal>


  </div>
</template>

<script>

  //页面配置参数
  import {contentTableConfig} from "./config/content.table.config";
  import {searchFormConfig} from "./config/search.form.config";
   //页面相关请求方法
  import { getPage, addObj, putObj, delObj, loanConfirm } from '@/api/loan/order'

  import OrderDetailModel from '@/views/loan/order-detail/OrderDetailModel.vue'
  import OrderFormModal from '@/views/loan/order/OrderFormModal.vue'
  export default {
    name: "OrderPage",
    components:{
      OrderDetailModel,OrderFormModal
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

      //表格相关
      showNewModal() {
        this.$refs.orderFormRef.add({title: '新增订单'})
      },
      showEditModal(item) {
        this.$refs.orderFormRef.update({title: '编辑订单', item})
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
      handleViewClick(row){
        this.$refs.orderDetailRef.update({ title:'订单信息',item:row })
      },
      // loanConfirm(row){
      //   loanConfirm(row.orderId).then(res=>{
      //     this.$message.success("放款成功")
      //     this.$refs.pageContentRef.resetTable()
      //   })
      // }
    }

  }
</script>

<style lang="scss" scoped>

</style>
