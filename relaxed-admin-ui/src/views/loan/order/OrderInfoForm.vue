<template>
 <div class="orderInfoForm">
       <pro-form :width="'60%'" ref="proForm" v-bind="orderInfoFormConfig" v-model="formData" >
         <template #residenCeCode="scope">
           <el-form-item
             class="yi-form-item"
             :label="scope.row.item.label"
             :prop="scope.row.item.field"
             :rules="scope.row.item.rules"
             v-bind="scope.row.item.itemProps"
           >
             <el-cascader v-model="scope.row.data[scope.row.item.field]" :props="props"></el-cascader>

           </el-form-item>
         </template>
         <template #mailingCodeArr="scope">
           <el-form-item
             class="yi-form-item"
             :label="scope.row.item.label"
             :prop="scope.row.item.field"
             :rules="scope.row.item.rules"
             v-bind="scope.row.item.itemProps"
           >
           <el-cascader v-model="scope.row.data[scope.row.item.field]" :props="props"></el-cascader>
           </el-form-item>
         </template>
       </pro-form>
 </div>
</template>

<script>


let id = 0;
import {orderInfoFormConfig} from './config/form/orderInfo.form.config'
import { getFormDetail, addObj } from '@/api/loan/order'
import { listByParentCode } from '@/api/loan/province'

export default {
  name: 'OrderInfoForm',
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
  data(){
    return{
      formData:this.getDefaultForm(),
      orderInfoFormConfig:orderInfoFormConfig,
      props: {
        lazy: true,
        lazyLoad (node, resolve) {
          const { level,root } = node;
          const parentCode=root?0:node.value
          listByParentCode(parentCode).then(res=>{
            res.data.forEach(e=>{
              e.leaf=level>=2
            })
            resolve(res.data);
          })
          // setTimeout(() => {
          //   const nodes = Array.from({ length: level + 1 })
          //     .map(item => ({
          //       value: ++id,
          //       label: `选项${id}`,
          //       leaf: level >= 2
          //     }));
          //   // 通过调用resolve将子节点数据返回，通知组件数据加载完成
          //   resolve(nodes);
          // }, 1000);
        }
      }
    }
  },
  created() {

    console.log("从新渲染",this.orderId)
    if (this.orderId){
      this.loadDetail()
    }
  },
  methods:{
    getDefaultForm(){
      return  {
        "age": 18,
        "contractNo": "999987915244",
        "applyAmount": "10000.00",
        "applyTerms": "1",
        "applyInterest": "0.001200",
        "penaltyRate": "0.0001000",
        "graceDays": "1",
        "productCode": "40202020",
        "trustPlanCode": "402001",
        "realName": "赵菡家",
        "mobileNo": "17760775947",
        "certificateNo": "110101199003075832",
        "loanPurpose": 1,
        "certificateStartDate": "2023-01-05",
        "certificateEndDate": "2023-01-03",
        "birthDate": "2023-01-03",
        "residenceCodeArr": ["110000", "110100", "110101"],
        "residenceAddress": "11",
        "mailingAddress": "11",
        "mailingDetail": "11",
        "mailingCodeArr": ["110000", "110100", "110101"],
        "employmentStatus": 24,
        "professionCode": "6",
        "jobYears": "1",
        "gender": 1,
        "repaymentSource": 116105,
        "guaranteeWay": "B99",
        "businessDetail": 31,
        "investType": 31,
        "companyName": "1",
        "loanType": 4,
        "customType": "3",
        "annualEarnings": 4,
        "investIndustry": 112205,
        "certificateType": "8",
        "residenceCode": "110000,110100,110101",
        "mailingCode": "110000,110100,110101",
        "loanPeriod": "1",
        "interestRate": "0.0002",
        "repaymentWay": 116104,
        "annualIncome": 3,
        "orderId": null,
        "projectId": 2
      }
    },
    loadDetail(){
      getFormDetail(this.orderId).then(res=>{
        this.formData=res.data
        this.formData.residenceCodeArr=this.formData.residenceCode?.split(',')
        this.formData.mailingCodeArr=this.formData.mailingCode?.split(',')
      })
    },
    saveOrder(){
      return new Promise((resolve,reject)=>{
        console.log("当前数据",this.formData)
        let reqInfo={...this.formData}
        reqInfo.orderId=this.orderId,
        reqInfo.residenceCode=this.formData.residenceCodeArr?.join(',')
        reqInfo.mailingCode=this.formData.mailingCodeArr?.join(",")
        console.log("请求信息",reqInfo)
        addObj(reqInfo).then(res=>{
          resolve(res)
        })
      })


    },
    resetForm(){
      this.formData={}
    }
  }

}
</script>

<style scoped>

</style>
