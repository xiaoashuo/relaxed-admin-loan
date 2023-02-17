<template>
  <div class="personContent" v-if="customerInfo">
    <div class="person-info">
      <h2>个人基本信息</h2>
      <el-row :gutter="10" type="flex" justify="left">
        <el-col :span="8"><span>姓名 </span><b>{{customerInfo.realName}}</b></el-col>
        <el-col :span="8"><span>身份证号码</span><b>{{customerInfo.idNo}}</b></el-col>
      </el-row>

      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>身份证有效期 </span><b>{{customerInfo.certificateValidityDate}}-{{customerInfo.certificateExpiryDate}}</b></el-col>
        <el-col :span="8"><span>性别</span><b>{{customerInfo.gender}}</b></el-col>
        <el-col :span="8"><span>出生年月</span><b>{{customerInfo.birthDate}}</b></el-col>
      </el-row>

      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>手机号码 </span><b>{{customerInfo.mobileNo}}</b></el-col>
        <el-col :span="8"><span>最高学历</span><b>{{customerInfo.educationDesc}}</b></el-col>
        <el-col :span="8"><span>最高学位</span><b>{{customerInfo.degreeDesc}}</b></el-col>
      </el-row>

      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>籍贯 </span><b>{{customerInfo.nativePlace}}</b></el-col>
        <el-col :span="8"><span>婚姻状况</span><b>{{customerInfo.maritalStatus}}</b></el-col>
        <el-col :span="8"><span>住宅电话</span><b>{{customerInfo.homePhone}}</b></el-col>
      </el-row>

      <el-row :gutter="10" type="flex" justify="left">
        <el-col :span="8"><span>户籍地区域 </span><b>{{customerInfo.permanentRegion}}</b></el-col>
        <el-col :span="8"><span>户籍地址</span><b>{{customerInfo.permanentAddress}}</b></el-col>
      </el-row>

    </div>
    <h2>居住信息</h2>
    <div class="loan-content">
      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>居住状态 </span><b>{{customerInfo.residenceStatus}}</b></el-col>
      </el-row>
      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>居住地区域 </span><b>{{customerInfo.residenceCode}}</b></el-col>
        <el-col :span="8"><span>居住地地址</span><b>{{customerInfo.residenceAddress}}</b></el-col>
        <el-col :span="8"><span>现居住地邮编</span><b>{{customerInfo.residencePostCode}}</b></el-col>
      </el-row>

      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>通讯地区域 </span><b>{{customerInfo.mailingCode}}</b></el-col>
        <el-col :span="8"><span>通讯地址</span><b>{{customerInfo.mailingAddress}}</b></el-col>
        <el-col :span="8"><span>通讯地址邮编</span><b>{{customerInfo.mailingPostCode}}</b></el-col>
      </el-row>
    </div>

    <h2>职业信息</h2>
    <div class="loan-content">
      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>就业状态 </span><b>{{customerInfo.employmentStatus}}</b></el-col>
      </el-row>


      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>职业 </span><b>{{customerInfo.professionCode}}</b></el-col>
        <el-col :span="8"><span>现单位职务</span><b>{{customerInfo.jobDuties}}</b></el-col>
        <el-col :span="8"><span>现单位职称</span><b>{{customerInfo.positionalTitles}}</b></el-col>
      </el-row>

      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>现单位名称 </span><b>{{customerInfo.companyName}}</b></el-col>
        <el-col :span="8"><span>现单位性质</span><b>{{customerInfo.companyTrade}}</b></el-col>
        <el-col :span="8"><span>现单位所属行业</span><b>{{customerInfo.companyKind}}</b></el-col>
      </el-row>


      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>现单位地区 </span><b>{{customerInfo.companyRegion}}</b></el-col>
        <el-col :span="8"><span>现单位地址</span><b>{{customerInfo.companyAddress}}</b></el-col>
        <el-col :span="8"><span>现单位地址邮编</span><b>{{customerInfo.companyPostCode}}</b></el-col>
      </el-row>

      <el-row :gutter="10" type="flex" justify="space-between">
        <el-col :span="8"><span>现单位电话 </span><b>{{customerInfo.companyPhone}}</b></el-col>
        <el-col :span="8"><span>现单位工龄</span><b>{{customerInfo.jobYears}}</b></el-col>
        <el-col :span="8"><span>年收入(元)</span><b>{{customerInfo.annualIncome}}</b></el-col>
      </el-row>
    </div>
    <h2>银行账户信息</h2>
    <div class="bankInfo-list">
      <yi-table ref="proTableRef"
                 v-if="bankCards"
                :loading="false"
                :list-data="bankCards"
                :list-count="bankCards.length"
                v-bind="contentTableConfig"

      >
      </yi-table>
    </div>

  </div>
</template>

<script>

export default {
  name: 'ViewPersonInfo',
  props:{
    customerInfo:{
      type:Object,
      require: true
    },
    bankCards:{
      type:Array,
      default:()=>([])
    }
  },

  data(){
    return{
      contentTableConfig:{
        title: '',
        showSelectColumn:false,
        tableProps:{
          'row-style': {
            height: '10px'
          },
          'cell-style': {
            padding: '2px 0'
          },

        },
        headerProps:{
          visible: false
        },
        showFooter:false,
        permissions: {
          create: 'loan:order-bank-card:add',
          update: 'loan:order-bank-card:edit',
          delete: 'loan:order-bank-card:delete'
        },
        propList:[


          {
            prop: 'accountType',
            label:'账户类型',
            minWidth:180,
            slotName: 'accountType'
          },
          {
            prop: 'accountName',
            label:'户名',
            minWidth:180,

          },
          {
            prop: 'accountNo',
            label:'户号',
            minWidth:180,

          },
          {
            prop: 'bankCode',
            label:'银行代号',
            minWidth:180,

          },
          {
            prop: 'bankName',
            label:'银行名称',
            minWidth:180,

          },
          {
            prop: 'openBankName',
            label:'开户行',
            minWidth:180,

          },
          {
            prop: 'openBankUnionBank',
            label:'开户行（分支行）联行号',
            minWidth:180,

          },
          {
            prop: 'reserveMobile',
            label:'预留手机号',
            minWidth:180,

          },
          {
            prop: 'cardPurpose',
            label:'银行卡用途',
            minWidth:180,

          },

          {
            prop: 'authStatus',
            label:'认证状态',
            minWidth:180,

          },
          {
            prop: 'createdTime',
            label:'创建时间',
            minWidth: 180,
          },


        ]
      }
    }
  }
}
</script>


<style lang="scss" scoped>
.personContent{
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

