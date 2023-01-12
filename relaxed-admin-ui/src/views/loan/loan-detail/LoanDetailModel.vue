<template>
  <el-dialog
    :title="title"
    :visible.sync="visible"
    fullscreen

    :before-close="dialogClose"
  >
    <el-tabs type="border-card" stretch v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="借据详情" name="detail">
        <view-loan-detail :loan-info="loanInfo" ></view-loan-detail>
      </el-tab-pane>
      <el-tab-pane label="账单信息" name="billInfo">
        <view-bill-info :loanId="item.loanId" :loan="loanInfo.loan"></view-bill-info>
      </el-tab-pane>

      <el-tab-pane label="还款明细" name="repaymentDetail">
        <view-trade-repayment :orderId="item.orderId"></view-trade-repayment>
      </el-tab-pane>
      <el-tab-pane label="影像资料" name="annexInfo">
        <view-order-annex :orderId="item.orderId"></view-order-annex>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>

<script>
import ViewLoanDetail from '@/views/loan/loan-detail/ViewLoanDetail.vue'
import ViewBillInfo from '@/views/loan/loan-detail/ViewBillInfo.vue'
import ViewOrderAnnex from '@/views/loan/order-detail/ViewOrderAnnex.vue'
import ViewTradeRepayment from '@/views/loan/loan-detail/ViewTradeRepayment.vue'
import formModalMixin from "@/mixins/form/formModalMixin";
import { getDetail } from '@/api/loan/loan'




export default {
  name: 'OrderDetailModel',
  components:{
    ViewLoanDetail,ViewOrderAnnex,ViewTradeRepayment,ViewBillInfo
  },
  mixins:[formModalMixin],
  data(){
    return{
      activeName: 'detail',
      item:{},
      loanInfo:{},

    }
  },

  methods:{
    dialogClose(done){
      this.close()
      done()
    },
    updatedFormCallback(payload){
      this.activeName='detail'
      this.item=payload.item
      console.log(this.item)
      getDetail({loanId:this.item.loanId}).then(res=>{
        this.loanInfo=res.data

      })
    },
    handleClick() {
      console.log(this.activeName);
      switch (this.activeName){
        case "borrowInfo":

          break
      }
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep {
  .el-dialog__header{
    border-bottom: 1px solid #e8e8e8;
  }
  .el-dialog__body{
    padding: 20px 20px;
  }
}
</style>
