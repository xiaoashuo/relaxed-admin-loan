<template>
    <div style="width: 100%;height: 100%">

      <yi-upload :upload-url="uploadUrl"
                 :render-value-handle="renderValueHandler"
                 v-model="uploadData"></yi-upload>
      <div>一级菜单</div>
      <router-view/>
    </div>
</template>

<script>
  import {YiUpload} from '@/components/upload'
    export default {
        name: "Nested",
        components:{
          YiUpload
        },
      data(){
          return{
            uploadUrl:  process.env.VUE_APP_BASE_API + "/common/file/upload",
            uploadData:[]
          }
      },
      methods:{
          renderValueHandler(fileList){

            let map = fileList.map(item=>{
              return  {
                name: item.name,
                url:item.url,
                fileId: item.fileId
              }
            })
            console.log("当前map",map)
            return map
          }
      }
    }
</script>

<style lang="scss" scoped>

</style>
