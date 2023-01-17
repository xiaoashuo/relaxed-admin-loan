<template>
  <el-dialog
    :title="title"
    :visible.sync="visible"
    fullscreen

    :before-close="dialogClose"
  >
    <el-steps :active="active" finish-status="success">
      <el-step title="订单信息"></el-step>
      <el-step title="共同借款人"></el-step>
      <el-step title="银行账户"></el-step>
      <el-step title="影像资料"></el-step>
    </el-steps>
    <order-info-form ref="orderInfoFormRef" :orderId="orderId"  :is-cream-form="isCreateForm"  v-if="active==0"></order-info-form>
    <order-borrow-form :orderId="orderId"  :is-cream-form="isCreateForm"  v-if="active==1"></order-borrow-form>
    <order-bank-form :orderId="orderId"  :is-cream-form="isCreateForm"  v-if="active==2"></order-bank-form>
    <order-annex-form :orderId="orderId"  :is-cream-form="isCreateForm"  v-if="active==3"></order-annex-form>

    <div style="text-align: center">
      <el-button  v-if="active==3" @click="handleSubmit">保存</el-button>

      <el-button  @click="close">取消</el-button>
      <el-button v-if="active<3" type="primary" @click="next">下一步</el-button>
      <el-button v-if="active>0" type="primary" @click="prev">上一步</el-button>

    </div>

  </el-dialog>
</template>

<script>
import formModalMixin from "@/mixins/form/formModalMixin";
import OrderInfoForm from '@/views/loan/order/OrderInfoForm.vue'
import OrderBorrowForm from '@/views/loan/order/OrderBorrowForm.vue'
import OrderBankForm from '@/views/loan/order/OrderBankForm.vue'
import OrderAnnexForm from '@/views/loan/order/OrderAnnexForm.vue'

import {submitOrder} from '@/api/loan/order'

export default {
  name: 'OrderCreateModal',
  mixins:[formModalMixin],
  components:{
    OrderInfoForm,OrderBorrowForm,OrderBankForm,OrderAnnexForm
  },
  data(){
    return{
      active: 0,
      orderId:null
    }
  },
  methods:{
    createdFormCallback(payload){
      this.active=0
      this.orderId=null
    },
    updatedFormCallback(payload){
      this.active=0
      this.orderId=payload.item.orderId
    },
    next() {
      if (this.active==0){
        //准备进入下一页时
        this.saveOrder()

      }else
      if (this.active==1||this.active==2){
       //准备进入下一页
        this.incrementActiveIndex()
      }


      // if (this.active++ > 3) this.active = 0;
    },
    prev(){
      if (this.active-- < 0) this.active = 0;
    },
    saveOrder(){
       this.$refs.orderInfoFormRef.saveOrder().then(res=>{
         console.log("接收到响应",res)
         this.orderId=res.data.orderId
         this.incrementActiveIndex()
       })
    },
    incrementActiveIndex(){
      if(this.active++>3) this.active=3
    },
    dialogClose(done){
      this.close()
      done()
    },
    handleSubmit(){
      submitOrder(this.orderId).then(res=>{
        this.$message.success("保存订单成功")
        this.close()
      })
    }

  }
}
</script>

<style scoped>

</style>
