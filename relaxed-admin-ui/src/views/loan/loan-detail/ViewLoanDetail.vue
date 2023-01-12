<template>
  <div class="loanDetail">
    <div class="content-info" v-if="loanInfo" >
      <h2>基本信息</h2>
      <div class="person-info">

        <el-row :gutter="10" type="flex" justify="space-between">
          <el-col :span="8"><span>合同编号 </span><b style="font-size:12px">{{itemInfo.contractNo}}</b></el-col>
          <el-col :span="8"><span>订单编号 </span><b style="font-size:12px">{{itemInfo.partnerBizNo}}</b></el-col>
          <el-col :span="8"><span>借据编号</span><b style="font-size:12px" >{{itemInfo.partnerLoanNo}}</b></el-col>
        </el-row>

        <el-row :gutter="10" type="flex" justify="space-between">
          <el-col :span="8"><span>借据状态</span><b>{{itemInfo.loanStatus}}</b></el-col>
          <el-col :span="8"><span>姓名 </span><b>{{customerInfo.realName}}</b></el-col>
          <el-col :span="8"><span>手机号码</span><b>{{customerInfo.mobileNo}}</b></el-col>
        </el-row>

        <el-row :gutter="10" type="flex" justify="space-between">
          <el-col :span="8"><span>身份证号</span><b>{{customerInfo.certificateNo}}</b></el-col>
        </el-row>

      </div>
      <h2>借据信息</h2>
      <div class="loan-content">

        <el-row :gutter="10" type="flex" justify="left">
          <el-col :span="8"><span>信托计划编号 </span><b>{{itemInfo.trustPlanCode}}</b></el-col>
          <el-col :span="8"><span>信托产品编号</span><b>{{itemInfo.productCode}}</b></el-col>
          <el-col :span="8"><span>逾期标记位</span><b>{{itemInfo.overdueFlag}}</b></el-col>
        </el-row>

        <el-row :gutter="10" type="flex" justify="space-between">
          <el-col :span="8"><span>申请日期 </span><b>{{itemInfo.useDate}}</b></el-col>
          <el-col :span="8"><span>放款日期</span><b>{{itemInfo.loanDate}}</b></el-col>
          <el-col :span="8"><span>起息日</span><b>{{itemInfo.loanDate}}</b></el-col>
        </el-row>
        <el-row :gutter="10" type="flex" justify="left">
          <el-col :span="8"><span>到期日期 </span><b>{{itemInfo.dueDate}}</b></el-col>
          <el-col :span="8"><span>结清日期</span><b>{{itemInfo.clearDate}}</b></el-col>
        </el-row>

        <el-row :gutter="10" type="flex" justify="space-between">
          <el-col :span="8"><span>贷款金额</span><b>{{itemInfo.loanAmt}}</b></el-col>
          <el-col :span="8"><span>期数</span><b>{{itemInfo.totalTerms}}</b></el-col>
          <el-col :span="8"><span>还款方式</span><b>{{itemInfo.repaymentWay}}</b></el-col>
        </el-row>


        <el-row :gutter="10" type="flex" justify="space-between">
          <el-col :span="8"><span>还款日</span><b>{{itemInfo.repayDay}}</b></el-col>
          <el-col :span="8"><span>当前期数</span><b>{{itemInfo.curPeriod}}</b></el-col>
          <el-col :span="8"><span>年利率</span><b>{{itemInfo.interestRate}}</b></el-col>
        </el-row>

        <el-row :gutter="10" type="flex" justify="left">
          <el-col :span="8"><span>罚息率(日)</span><b>{{itemInfo.penaltyRate}}</b></el-col>
          <el-col el-col :span="8"><span>宽限期</span><b>{{itemInfo.graceDays}}</b></el-col>
        </el-row>


        <el-row :gutter="10" type="flex" justify="space-between">
          <el-col :span="8"><span>应还总金额(元)</span><b>{{itemInfo.receivableAmt}}</b></el-col>
          <el-col :span="8"><span>应还本金(元)</span><b>{{itemInfo.principalReceivable}}</b></el-col>
          <el-col :span="8"><span>应还利息(元)</span><b>{{itemInfo.interestReceivable}}</b></el-col>
        </el-row>


        <el-row :gutter="10" type="flex" justify="space-between">
          <el-col :span="8"><span>已还总金额(元)</span><b>{{itemInfo.receiptsAmt}}</b></el-col>
          <el-col :span="8"><span>已还本金(元)</span><b>{{itemInfo.principalReceipts}}</b></el-col>
          <el-col :span="8"><span>已还利息(元)</span><b>{{itemInfo.interestReceipts}}</b></el-col>
        </el-row>

        <el-row :gutter="10" type="flex" justify="left">
          <el-col :span="8"><span>应还罚息（元）</span><b>{{itemInfo.penaltyReceivable}}</b></el-col>
          <el-col el-col :span="8"><span>应还费用（元）</span><b>{{itemInfo.costReceivable}}</b></el-col>
        </el-row>


        <el-row :gutter="10" type="flex" justify="left">
          <el-col :span="8"><span>已还罚息（元）</span><b>{{itemInfo.penaltyReceipts}}</b></el-col>
          <el-col el-col :span="8"><span>已还费用（元）</span><b>{{itemInfo.costReceipts}}</b></el-col>
        </el-row>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ViewLoanDetail',
  props:{
    loanInfo:{
      type: Object,
      default:()=>({})
    },

  },
  computed:{
    itemInfo(){
      return this.loanInfo.loan??{}
    },
    customerInfo(){
      return this.loanInfo.orderCustomer??{}
    }
  },
}
</script>

<style lang="scss" scoped>
.loanDetail{
  margin: 0 auto;
  height: 100%;
  .person-info {
    padding: 12px;
    box-sizing: border-box;
    width: 100%;
  }
  span {
    color: #333333;
    display: inline-block;
    float: left;
    text-align: left;
    width: 30%;
    padding: 10px 0 ;
    box-sizing: border-box;
    padding-right: 15px;
    font-size: 14px;
    font-family:PingFangSC-Regular,PingFang SC;
    font-weight:400;
  }

  b {
    float: left;
    padding: 10px 0 ;
    text-align: left;
    display: inline-block;
    width: 70%;
    color: #666666;
    font-size: 14px;
    font-family:PingFangSC-Regular,PingFang SC;
    font-weight:400;
  }

}
</style>
