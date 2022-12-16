<template>
    <div style="width: 100%;height: 100%">

      <yi-upload class="upload-file" :upload-url="uploadUrl"

                 :delete-request="deleteFileRequest"
                 drag
                 v-model="uploadData"></yi-upload>
      <div>一级菜单</div>
      <yi-upload  class="image-file" :upload-url="uploadUrl"
                 :onRender="renderValueHandler"
                 :delete-request="deleteFileRequest"

                 list-type="picture-card"
                 :on-preview="handlePictureCardPreview"
                 :fileType="fileType"
                  :limit="1"
                 v-model="imageData"></yi-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>


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
            dialogImageUrl: '',
            dialogVisible: false,
            fileType:['png', 'jpg', 'jpeg'],
            imageData:[],
            uploadUrl:  process.env.VUE_APP_BASE_API + "/common/file/upload",
            // uploadData:"",
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
        handlePictureCardPreview(file) {
          this.dialogImageUrl = file.url
          this.dialogVisible = true;
        },
          renderValueHandler(fileList){

            let map = fileList.map(item=>{
              return  {
                name: item.name,
                url:item.url,
                fileId: item.fileId,
                uid:item.uid
              }
            })
            console.log("当前map",map)
            return map
          }
      }
    }
</script>

<style lang="scss" scoped>
  .upload-file{
    ::v-deep  .upload-file-uploader{
      width: 360px;
    }
  }

  .image-file{
    ::v-deep .upload-file-uploader{
      width: 100%;
    }
  }
</style>
