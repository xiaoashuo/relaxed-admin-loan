<template>
  <div class="orderDetail" v-if="order" >

      <div class="person-info">
        <el-row>
          <el-col :span="8"><span>订单编号 </span><b>{{order.partnerBizNo}}</b></el-col>
          <el-col :span="8"><span>订单状态</span><b>{{order.orderStage}}</b></el-col>
          <el-col :span="8"><span>申请时间</span><b>{{order.applyTime}}</b></el-col>
        </el-row>
        <el-row :gutter="10" type="flex" justify="space-between">
          <el-col :span="8"><span>姓名 </span><b>{{order.realName}}</b></el-col>
          <el-col :span="8"><span>身份证号</span><b>{{order.idNo}}</b></el-col>
          <el-col :span="8"><span>手机号码</span><b>{{order.mobileNo}}</b></el-col>
        </el-row>
      </div>
    <h2>订单申请信息</h2>
    <div class="loan-content">

      <el-row :gutter="10" type="flex" justify="left">
        <el-col :span="8"><span>信托计划编号 </span><b>{{order.trustPlanCode}}</b></el-col>
        <el-col :span="8"><span>信托产品编号</span><b>{{(order.productCode)}}</b></el-col>
        <el-col :span="8"><span>合作方业务号 </span><b>{{(order.partnerBizNo)}}</b></el-col>
      </el-row>


      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>申请金额 </span><b>{{order.applyAmount}}</b></el-col>
        <el-col :span="8"><span>申请期限(日)</span><b>{{order.loanPeriod}}</b></el-col>
        <el-col :span="8"><span>申请利率</span><b>{{order.annualYieldRate}}</b></el-col>
      </el-row>


      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>业务发生地 </span><b>{{order.regionName}}</b></el-col>
        <el-col :span="8"><span>还款方式</span><b>{{order.repaymentWay}}</b></el-col>
        <el-col :span="8"><span>贷款分类</span><b>{{order.loanType}}</b></el-col>
      </el-row>


      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>征信贷款种类 </span><b>{{order.creditLoanType}}</b></el-col>
        <el-col :span="8"><span>投向行业</span><b>{{order.investIndustry}}</b></el-col>
        <el-col :span="8"><span>贷款用途</span><b>{{order.loanPurpose}}</b></el-col>
      </el-row>

      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>预计还款来源 </span><b>{{order.repaymentSource}}</b></el-col>
        <el-col :span="8"><span>业务种类细分</span><b>{{order.industryDetail}}</b></el-col>
        <el-col :span="8"><span>担保方式</span><b>{{order.guaranteeWay}}</b></el-col>
      </el-row>
    </div>
    <div class="auditInfo">
      <h2>系统审核</h2>

      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>审核时间</span><b>{{auditInfo.resultTime}}</b></el-col>
        <el-col :span="8"><span>审核结果</span><b>{{auditInfo.auditStatus}}</b></el-col>
      </el-row>
      <div class="auditDetail-list">
        <yi-table ref="proTableRef"
                  v-if="auditInfo.auditDetails"
                  :loading="false"
                  :list-data="auditInfo.auditDetails"
                  :list-count="auditInfo.auditDetails.length"
                  v-bind="contentTableConfig"

        >
        </yi-table>
      </div>
    </div>

  </div>
</template>

<script>
import YiTable from '@/components/table'
export default {
  name: 'ViewOrderDetail',
  components:{
    YiTable
  },
  props:{
    order:{
      type: Object,
      require:true
    },
    auditInfo:{
      type: Object,
      default:()=>({})
    }

  },

  data(){
    return{
      contentTableConfig:{
        title:'',
        headerProps:{
          visible:false
        },
        showFooter:false,
        showSelectColumn:false,
        tableProps:{
          'row-style': {
            height: '10px'
          },
          'cell-style': {
            padding: '2px 0'
          },

        },

        propList:[

          {
            prop: 'reasonCode',
            label:'拒绝原因码仅',
            minWidth:180,

          },
          {
            prop: 'reasonDesc',
            label:'拒绝原因描述',
            minWidth:180,

          },


        ]
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.orderDetail{
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
