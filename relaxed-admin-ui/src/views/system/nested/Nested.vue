<template>
    <div style="width: 100%;height: 100%">

      <yi-upload :upload-url="uploadUrl"
                 :render-value-handle="renderValueHandler"
                 :delete-request="deleteFileRequest"
                 v-model="uploadData"></yi-upload>
      <div>一级菜单</div>
      <router-view/>
    </div>
</template>

<script>
  import {YiUpload} from '@/components/upload'
  import {deleteFile} from '@/api/common'

  export default {
        name: "Nested",
        components:{
          YiUpload
        },
      data(){
          return{
            uploadUrl:  process.env.VUE_APP_BASE_API + "/common/file/upload",
            uploadData:[
              // {
              //   "name": "1602655167008256000.pdf",
              //   "url": "http://localhost:9401/profile/upload/20221214/4cf56d8f-6b37-4ff9-99dc-dc23f2f0ecf0.pdf",
              //   "fileId": "1603005497218760704"
              // }
            ]
              //"http://localhost:9401/profile/upload/20221214/4cf56d8f-6b37-4ff9-99dc-dc23f2f0ecf0.pdf"
            ,
            deleteFileRequest:deleteFile
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
