<template>
 <div class="app-container">
   <el-tabs v-model="activeName" @tab-click="handleClick">
     <el-tab-pane label="字段管理" name="first">

       <risk-field-page :model-id="modelId"></risk-field-page>

     </el-tab-pane>
     <el-tab-pane label="预处理管理" name="second">
       <risk-pre-item-page :model-id="modelId"></risk-pre-item-page>
     </el-tab-pane>
     <el-tab-pane label="策略管理" name="third">
       <risk-activation-page :model-id="modelId"></risk-activation-page>
     </el-tab-pane>
   </el-tabs>
 </div>
</template>

<script>
import RiskFieldPage from '@/views/risk/detail/RiskFieldPage.vue'
import RiskPreItemPage from '@/views/risk/detail/RiskPreItemPage.vue'
import RiskActivationPage from '@/views/risk/detail/RiskActivationPage.vue'
export default {
  name: 'RiskModelDetail',
  components:{
    RiskActivationPage,
    RiskPreItemPage,
    RiskFieldPage
  },
  data(){
    return{
      activeName: 'first',
      modelId:this.$route.query.modelId
    }
  },
  created() {
    console.log(this.$route)
  },
  methods:{
    handleClick(tab, event) {
      console.log(tab, event);
    }
  }
}
</script>

<style scoped>

</style>
