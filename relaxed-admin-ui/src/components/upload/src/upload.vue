<template>
  <div class="upload-file">
    <el-upload
      ref="fileUpload"
      style="width: 50%"
      class="upload-file-uploader"
      drag
      :limit="limit"
      :file-list="fileList"
      :action="uploadUrl"
      :multiple="multiple"
      :headers="headers"
      :name="fieldName"
      :before-upload="handleBeforeUpload"
      :on-exceed="handleExceed"
      :on-error="handleUploadError"
      :on-success="handleUploadSuccess"
    >
      <!-- 上传按钮 -->
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <!-- 上传提示 -->

<!--       <div class="el-upload__tip text-center">-->
<!--         -->
<!--       </div>-->
              <div class="el-upload__tip" slot="tip" >
                请上传
                <template v-if="fileSize"> 大小不超过{{ fileSize }}MB </template>
                <template v-if="fileType"> 格式为 {{ fileType.join("/") }} </template>
                的文件
              </div>


    </el-upload>

  </div>
</template>

<script>
  // 对象转成指定字符串分隔
  function listToString(list, separator) {
    let strs = ''
    separator = separator || ','
    for (let i in list) {
      strs += list[i].url + separator
    }
    return strs != '' ? strs.substr(0, strs.length - 1) : ''
  }

  export default {
    name: 'yi-upload',
    props: {
      // 值
      value: [String, Object, Array],
      //上传地址
      uploadUrl: {
        type: String,
        required: true
      },
      fieldName: {
        type: String,
        default: 'file'
      },
      multiple: {
        type: Boolean,
        default: false
      },
      drag: {
        type: Boolean,
        default: false
      },
      // 数量限制
      limit: {
        type: Number,
        default: 5
      },
      // 文件类型, 例如['png', 'jpg', 'jpeg']
      fileType: {
        type: Array,
        default: () => ['doc', 'xls', 'ppt', 'txt', 'pdf']
      },
      // 大小限制(MB)
      fileSize: {
        type: Number,
        default: 5
      },

      renderValueHandle: {
        type: Function,
        default: (fileList) => {
          return listToString(fileList)
        }
      }
    },
    data() {
      return {
        number: 0,
        uploadList: [],
        fileList: [],
        headers: {
          Authorization: 'Bearer ' + this.$store.getters.token
        }
      }
    },
    watch: {
      // value: {
      //   handler(val) {
      //     if (val) {
      //       let temp = 1;
      //       // 首先将值转为数组
      //       const list = Array.isArray(val) ? val : this.value.split(',');
      //       // 然后将数组转为对象数组
      //       this.fileList = list.map(item => {
      //         if (typeof item === "string") {
      //           item = { name: item, url: item };
      //         }
      //         item.uid = item.uid || new Date().getTime() + temp++;
      //         return item;
      //       });
      //     } else {
      //       this.fileList = [];
      //       return [];
      //     }
      //   },
      //   deep: true,
      //   immediate: true
      // }
    },
    methods: {

      // 上传前校检格式和大小
      handleBeforeUpload(file) {
        // 校检文件类型
        if (this.fileType) {
          const fileName = file.name.split('.')
          const fileExt = fileName[fileName.length - 1]
          const isTypeOk = this.fileType.indexOf(fileExt) >= 0
          if (!isTypeOk) {
            this.$message.error(`文件格式不正确, 请上传${this.fileType.join('/')}格式文件!`)
            return false
          }
        }
        // 校检文件大小
        if (this.fileSize) {
          const isLt = file.size / 1024 / 1024 < this.fileSize
          if (!isLt) {
            this.$message.error(`上传文件大小不能超过 ${this.fileSize} MB!`)
            return false
          }
        }
        //this.$message.loading("正在上传文件，请稍候...");
        this.number++
        return true
      },
      // 文件个数超出
      handleExceed() {
        this.$message.error(`上传文件数量不能超过 ${this.limit} 个!`)
      },
      // 上传失败
      handleUploadError(err) {
        this.$message.error('上传文件失败，请重试')
        // this.$modal.closeLoading()
      },
      // 上传成功回调
      handleUploadSuccess(res, file) {
        console.log(res)
        if (res.code === 200) {
          const data = res.data
          this.uploadList.push({ name: data.filename, url: data.url, fileId: data.fileId })
          this.uploadedSuccessfully()
        } else {
          this.number--
          // this.$modal.closeLoading();
          this.$message.error(res.msg)
          this.$refs.fileUpload.handleRemove(file)
          this.uploadedSuccessfully()
        }
      },
      // 上传结束处理
      uploadedSuccessfully() {
        if (this.number > 0 && this.uploadList.length === this.number) {
          this.fileList = this.fileList.concat(this.uploadList)
          this.uploadList = []
          this.number = 0
          this.$emit('input', this.renderValueHandle(this.fileList))
          // this.$modal.closeLoading();
        }
      },
      // 删除文件
      handleDelete(index) {
        this.fileList.splice(index, 1)
        this.$emit('input', this.renderValueHandle(this.fileList))
      },
      // 获取文件名称
      getFileName(name) {
        if (name.lastIndexOf('/') > -1) {
          return name.slice(name.lastIndexOf('/') + 1)
        } else {
          return ''
        }
      }
    }
  }
</script>


<style scoped lang="scss">

</style>

