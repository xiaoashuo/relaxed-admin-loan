<template>
    <div class="templateConfig">

       <div class="header">
         <div class="sealWay">
           <div>签章方式</div>
           <dict-select v-model="sealWay" v-bind="sealWayConfig"></dict-select>
         </div>
         <div class="content">
           Relaxed电子用印系统配置平台页
         </div>
         <div class="right">
           当前时间{{new Date()}}
         </div>
       </div>


        <template v-if="sealWay===1">
          <template-seal-config></template-seal-config>
        </template>
      <template v-if="sealWay===2">
        <keyword-seal-config></keyword-seal-config>
      </template>








    </div>
</template>

<script>
  import { YiSelect } from '@/components/select'

  import TemplateSealConfig from '@/views/loan/project/TemplateSealConfig'
  import KeywordSealConfig from '@/views/loan/project/KeywordSealConfig'
  import {getListData as getSealListData,getSelectData as getSealSelectData} from '@/api/loan/seal'
  import { getSelectData as getCertificateSelectData } from '@/api/loan/certificate'

  const SIGN_CACHE_KEY="signs";
  export default {
    name: 'ProjectTemplateConfig',
    components:{
      YiSelect,TemplateSealConfig,KeywordSealConfig
    },
    data(){
      return {
        sealWay:1,
        sealWayConfig:{
          dictCode: 'seal_way',

          otherProps:{
            clearable: true
          }
        },
      }
    }
  }
</script>

<style lang="scss" scoped>
.templateConfig{
  position: relative;
  padding-top: 10px;


  .header{
    padding-left: 10px;
    display: flex;
    justify-content: space-between;
    .sealWay{
      width: 200px;
    }
    .content{
      flex: 1;
      color: red;
      font-weight: 500;
      font-size: 30px;
      /*text-align: center;*/
      align-content: center;
      margin-left: 20px;
    }
    .right{
      width: 200px;
    }
  }
}

</style>
