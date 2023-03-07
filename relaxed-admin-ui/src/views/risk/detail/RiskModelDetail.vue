<template>
 <div class="app-container">
   <div  >
     <el-collapse  >
       <el-collapse-item title="模型信息" name="1">
         <el-descriptions>
         <el-descriptions-item label="名称">{{modelData.modelName}}</el-descriptions-item>
         <el-descriptions-item label="guid">{{modelData.guid}}</el-descriptions-item>
         <el-descriptions-item label="事件主键">{{modelData.entryName}}</el-descriptions-item>
         <el-descriptions-item label="实体主键">{{modelData.entityName}}</el-descriptions-item>
         <el-descriptions-item label="日期字段">{{modelData.referenceDate}}</el-descriptions-item>
         <el-descriptions-item label="创建日期">{{modelData.createdTime}}</el-descriptions-item>
         </el-descriptions>
       </el-collapse-item>

     </el-collapse>




   </div>
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
import { getByModelId } from '@/api/risk/risk-model'


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
      modelId:this.$route.query.modelId,
      modelData:{}
    }
  },
  created() {
    console.log(this.$route)
    getByModelId(this.modelId).then(res=>{
      console.log("当前模型信息",res)
      this.modelData=res.data
    })

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
