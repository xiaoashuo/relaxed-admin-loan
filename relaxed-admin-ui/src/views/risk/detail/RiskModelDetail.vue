<template>
 <div class="modelDetail">
   <el-tabs v-model="activeName" @tab-click="handleClick">
     <el-tab-pane label="字段管理" name="first">

       <risk-field-page :model-id="modelId"></risk-field-page>

     </el-tab-pane>
     <el-tab-pane label="预处理管理" name="second">
       <risk-pre-item-page :model-id="modelId"></risk-pre-item-page>
     </el-tab-pane>
     <el-tab-pane label="策略管理" name="third">策略管理</el-tab-pane>
   </el-tabs>
 </div>
</template>

<script>
import RiskFieldPage from '@/views/risk/detail/RiskFieldPage.vue'
import RiskPreItemPage from '@/views/risk/detail/RiskPreItemPage.vue'
export default {
  name: 'RiskModelDetail',
  components:{
    RiskPreItemPage,
    RiskFieldPage
  },
  data(){
    return{
      activeName: 'second',
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
