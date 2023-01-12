<template>
    <div style="width: 100%;height: 100%">


      <cascader-select v-model="cascaderSelectValue"  :remoted-load="false"
                       :options="cascaderOption">

      </cascader-select>

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
  import {CascaderSelect} from '@/components/select'

  export default {
        name: "Nested",
        components:{
          YiUpload,
          CascaderSelect

        },
      data(){
          return{
            cascaderSelectValue:[],
            cascaderOption:[{
              value: 'zhinan',
              label: '指南',
              children: [{
                value: 'shejiyuanze',
                label: '设计原则',
                children: [{
                  value: 'yizhi',
                  label: '一致'
                }, {
                  value: 'fankui',
                  label: '反馈'
                }, {
                  value: 'xiaolv',
                  label: '效率'
                }, {
                  value: 'kekong',
                  label: '可控'
                }]
              }, {
                value: 'daohang',
                label: '导航',
                children: [{
                  value: 'cexiangdaohang',
                  label: '侧向导航'
                }, {
                  value: 'dingbudaohang',
                  label: '顶部导航'
                }]
              }]
            }, {
              value: 'zujian',
              label: '组件',
              children: [{
                value: 'basic',
                label: 'Basic',
                children: [{
                  value: 'layout',
                  label: 'Layout 布局'
                }, {
                  value: 'color',
                  label: 'Color 色彩'
                }, {
                  value: 'typography',
                  label: 'Typography 字体'
                }, {
                  value: 'icon',
                  label: 'Icon 图标'
                }, {
                  value: 'button',
                  label: 'Button 按钮'
                }]
              }, {
                value: 'form',
                label: 'Form',
                children: [{
                  value: 'radio',
                  label: 'Radio 单选框'
                }, {
                  value: 'checkbox',
                  label: 'Checkbox 多选框'
                }, {
                  value: 'input',
                  label: 'Input 输入框'
                }, {
                  value: 'input-number',
                  label: 'InputNumber 计数器'
                }, {
                  value: 'select',
                  label: 'Select 选择器'
                }, {
                  value: 'cascader',
                  label: 'Cascader 级联选择器'
                }, {
                  value: 'switch',
                  label: 'Switch 开关'
                }, {
                  value: 'slider',
                  label: 'Slider 滑块'
                }, {
                  value: 'time-picker',
                  label: 'TimePicker 时间选择器'
                }, {
                  value: 'date-picker',
                  label: 'DatePicker 日期选择器'
                }, {
                  value: 'datetime-picker',
                  label: 'DateTimePicker 日期时间选择器'
                }, {
                  value: 'upload',
                  label: 'Upload 上传'
                }, {
                  value: 'rate',
                  label: 'Rate 评分'
                }, {
                  value: 'form',
                  label: 'Form 表单'
                }]
              }, {
                value: 'data',
                label: 'Data',
                children: [{
                  value: 'table',
                  label: 'Table 表格'
                }, {
                  value: 'tag',
                  label: 'Tag 标签'
                }, {
                  value: 'progress',
                  label: 'Progress 进度条'
                }, {
                  value: 'tree',
                  label: 'Tree 树形控件'
                }, {
                  value: 'pagination',
                  label: 'Pagination 分页'
                }, {
                  value: 'badge',
                  label: 'Badge 标记'
                }]
              }, {
                value: 'notice',
                label: 'Notice',
                children: [{
                  value: 'alert',
                  label: 'Alert 警告'
                }, {
                  value: 'loading',
                  label: 'Loading 加载'
                }, {
                  value: 'message',
                  label: 'Message 消息提示'
                }, {
                  value: 'message-box',
                  label: 'MessageBox 弹框'
                }, {
                  value: 'notification',
                  label: 'Notification 通知'
                }]
              }, {
                value: 'navigation',
                label: 'Navigation',
                children: [{
                  value: 'menu',
                  label: 'NavMenu 导航菜单'
                }, {
                  value: 'tabs',
                  label: 'Tabs 标签页'
                }, {
                  value: 'breadcrumb',
                  label: 'Breadcrumb 面包屑'
                }, {
                  value: 'dropdown',
                  label: 'Dropdown 下拉菜单'
                }, {
                  value: 'steps',
                  label: 'Steps 步骤条'
                }]
              }, {
                value: 'others',
                label: 'Others',
                children: [{
                  value: 'dialog',
                  label: 'Dialog 对话框'
                }, {
                  value: 'tooltip',
                  label: 'Tooltip 文字提示'
                }, {
                  value: 'popover',
                  label: 'Popover 弹出框'
                }, {
                  value: 'card',
                  label: 'Card 卡片'
                }, {
                  value: 'carousel',
                  label: 'Carousel 走马灯'
                }, {
                  value: 'collapse',
                  label: 'Collapse 折叠面板'
                }]
              }]
            }, {
              value: 'ziyuan',
              label: '资源',
              children: [{
                value: 'axure',
                label: 'Axure Components'
              }, {
                value: 'sketch',
                label: 'Sketch Templates'
              }, {
                value: 'jiaohu',
                label: '组件交互文档'
              }]
            }],
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
      handleShow (e) {
        console.log(e)
        e.stopPropagation();
        const targetDimensions = this.$refs.box.getBoundingClientRect()

        const postition = {
          top: Math.random() * targetDimensions.height + targetDimensions.top,
          left: Math.random() * targetDimensions.width + targetDimensions.left,
        }
        console.log("当前",this.$refs.contextmenu.$data,targetDimensions,postition)

        this.$refs['contextmenu'].show({ top:100,left:100 })


      },
      handleHide() {
        this.$refs.contextmenu.hide()
      },
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
<style lang="scss">
  $main-color:#46a0fc;
  $main-color-bright:#ef5350;
  .buttons{
    padding: 5px 10px;
    border-left: 1px solid $main-color;
    border-right: 1px solid $main-color;
    background-color: #fff;
    & .bright{

      border-color: $main-color-bright
    }

  }

  .box{
    position: relative;
    width: 100%;
    height: 120px;
    line-height: 120px
  }

</style>
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
