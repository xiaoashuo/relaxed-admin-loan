<template>
  <div class="yi-radio-group">
    <el-radio-group :value="selectValue" v-bind="otherProps" @input="handleChange">
      <el-radio :label="option.value" v-for="option in selectData" :key="option.value">{{option.label}}</el-radio>
    </el-radio-group>
  </div>

</template>

<script>
  export default {
    name: 'YiRadioGroup',
    props:{
      otherProps:{
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
      console.log("进去当前",this.remoteLoad,this.value)
      if (this.remoteLoad){
        this.getRequestData()
      }else{
        this.selectData=this.options
      }
    },
    methods:{
      getRequestData(){
        this.request().then(res=>{
          this.selectData=res.data
        })
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
