<template>
  <el-dialog
    :title="title"
    :visible.sync="visible"
    fullscreen

    :before-close="dialogClose"
  >
    <el-tabs type="border-card" stretch v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="订单详情" name="detail">
        <view-order-detail :order="orderInfo.order"></view-order-detail>
      </el-tab-pane>
      <el-tab-pane label="个人信息" name="personInfo">
        <view-person-info :customerInfo="orderInfo.orderCustomer" :bankCards="orderInfo.orderBankCardList"></view-person-info>
      </el-tab-pane>
      <el-tab-pane label="房屋相关信息" name="houseInfo">房屋相关信息</el-tab-pane>
      <el-tab-pane label="共同借款人" name="borrowInfo">
        <view-order-borrow :orderId="item.orderId" ref="orderBorrowRef"></view-order-borrow>
      </el-tab-pane>
      <el-tab-pane label="影像资料" name="annexInfo">
        <view-order-annex :orderId="item.orderId" ref="orderAnnexRef"></view-order-annex>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>

<script>
import formModalMixin from "@/mixins/form/formModalMixin";

import ViewOrderDetail from '@/views/loan/order/ViewOrderDetail.vue'
import ViewOrderBorrow from '@/views/loan/order/ViewOrderBorrow.vue'
import ViewOrderAnnex from '@/views/loan/order/ViewOrderAnnex.vue'
import ViewPersonInfo from '@/views/loan/order/ViewPersonInfo.vue'
import {getDetail} from '@/api/loan/order'

export default {
  name: 'OrderDetailModel',
  components:{
    ViewOrderBorrow,ViewOrderAnnex,ViewOrderDetail,ViewPersonInfo
  },
  mixins:[formModalMixin],
  data(){
    return{
      activeName: 'detail',
      item:{},
      orderInfo:{}
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
      getDetail({orderId:this.item.orderId}).then(res=>{
        this.orderInfo=res.data
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
