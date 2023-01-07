<template>
  <el-dialog
    :title="title"
    :visible.sync="visible"
    fullscreen
    :before-close="dialogClose"
  >
    <project-template-config :seal-image-list="sealImageList" :project-data="projectData"></project-template-config>
  </el-dialog>
</template>

<script>
  import ProjectTemplateConfig from '@/views/loan/project/ProjectTemplateConfig'
  import formModalMixin from "@/mixins/form/formModalMixin";
  import { getDetail } from '@/api/loan/project-template'
  import { getListData as getSealListData } from '@/api/loan/seal'
  export default {
    name: 'SealConfigModal',
    components:{
      ProjectTemplateConfig
    },
    mixins:[formModalMixin],
    data(){
      return{
        item: {},
        projectData:{},
        sealImageList:[],
      }
    },
    created() {
      getSealListData().then(res=>{
        this.sealImageList=res.data
      })
    },
    methods:{
      dialogClose(done){
        this.close()
        done()
      },
      /**
       * 构建创建表单成功后的回调
       * @param payload
       */
      createdFormCallback(payload){
     console.log("当前签章",payload)
        this.item=payload.item
        getDetail(this.item.projectTemplateId).then(res=>{
          console.log("当前结果",res)
          this.projectData=res.data
        })

      },
    }
  }
</script>

<style scoped>

</style>
