<template>

      <yi-form v-bind="searchFormConfig"  v-model="formData">
        <template  #header >
          <slot name="header">
            <h1 class="header-title">高级检索</h1>
          </slot>
        </template>
        <template #footer>
          <div class="handle-search-btns">
            <el-button  icon="el-icon-refresh"  size="small" @click="handleResetClick">重置</el-button>
            <el-button  type="primary" size="small"  icon="el-icon-search" @click="handleQueryClick">搜索</el-button>
          </div>
        </template>
      </yi-form>

</template>

<script>
    import YiForm from '@/components/form'
    export default {
        name: "page-search",
      components:{
        YiForm
      },
        props:{
          searchFormConfig:{
            type:Object,
            required: true
          },

        },
      data(){
          return {
            formData:{}
          }

      },
      watch:{
        formData(newVal){
          this.$emit("input",newVal)
        }
      },
      created() {
          this.initFormData()
      },


      methods:{
          //初始化表单项
          initFormData(){
            const formItems=this.searchFormConfig?.formItems??[]
            for (const item of formItems){
              this.formData[item.field]=''
            }
          },
        handleResetClick(){
          this.initFormData()
          this.$emit('resetBtnClick')
        },
        handleQueryClick(){
          this.$emit('queryBtnClick',this.formData)
        }
      }
    }
</script>

<style lang="scss" scoped>
  .header-title{
    color: red;
    font-size: 20px;

  }
  .handle-search-btns{
    text-align: right;
    padding: 0 50px 20px 0;
  }
</style>
