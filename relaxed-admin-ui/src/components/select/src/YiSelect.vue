<template>
  <div class="yi-select">
    <el-select :placeholder="placeholder" v-bind="nativeProps"
               :value="this.selectValue"
    @change="handleChange">
      <el-option style="width: 100%;"
                 v-for="option in selectData" :key="option.value"
                 :value="option.value" :label="option.label">
      </el-option>
    </el-select>

  </div>
</template>

<script>
    export default {
        name: "YiSelect",
      props:{
        placeholder:{
          type: String,
          default: '请选择'
        },
        nativeProps:{
          type: Object,
          default: ()=>({})
        },
        value:{
          required: true
        },
        remoteLoad:{
          type: Boolean,
          default: false
        },
        request:{
          type: Function,
          default: ()=>null
        },
        options:{
          type:Array,
          default:()=>([])
        },
      },
      data(){
          return{
            selectValue:this.value,
            selectData: []
          }
      },
      created() {
          console.log("进去当前",this.remoteLoad,this.value,this.options)
          if (this.remoteLoad){
            this.getTreeData()
          }else{
            this.selectData=this.options
          }
      },
      watch:{
          value(){
            this.selectValue=this.value
          },
        options:{
            handler:function(val){
              if (!this.remoteLoad){
                this.selectData=val
              }
            }
        }
      },
      methods:{
        getTreeData(){
          this.request().then(res=>{
            this.selectData=res.data
          })
        },
        getSelectData(){
          return this.selectData
        },
        handleChange(value){
          console.log("当前选中值",value)
          this.selectValue=value
          // v-decorator 方式的表单值联动
          this.$emit('change', this.selectValue)
          this.$emit('input', this.selectValue)
        }
      }
    }
</script>

<style scoped>

</style>
