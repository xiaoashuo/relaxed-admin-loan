<template>
  <div>
    <el-dialog
      :title="title"
      :visible.sync="visible"
      width="60%"
      :show-close="false"

    >
      <icon-selector-tab @change="handleIconChange"></icon-selector-tab>
      <div slot="footer" class="dialog-footer">
        <el-button  @click="close">取消</el-button>
        <el-button type="primary" @click="handleOk">确定</el-button>
      </div>

    </el-dialog>
  </div>

</template>

<script>
  import IconSelectorTab from '@/components/icons/src/IconSelectorTab'
    export default {
        name: "IconSelectorModal",
      components:{
        IconSelectorTab
      },
      props:{
          title:{
            type:String,
            default:''
          }
      },
      data(){
          return{
            visible: false,
            icon:''
          }
      },
      methods:{
          show(){
            this.visible=true
          },
          close(){
            this.visible=false
          },

        handleIconChange(value){
          this.icon=value
        },
        handleOk(){
          this.$emit('choose', this.icon)
          this.close()
        }
      }
    }
</script>

<style scoped>

</style>
