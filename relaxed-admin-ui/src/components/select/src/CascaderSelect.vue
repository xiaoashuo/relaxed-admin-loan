<template>
 <div class="cascaderSelect">
   <el-cascader
     v-model="selectValue"
     :options="selectData"
     v-bind="nativeProps"
     @change="handleChange"></el-cascader>
 </div>
</template>

<script>
export default {
  name: 'CascaderSelect',
  props:{
    value:{
      required: true
    },
    remotedLoad:{
      type: Boolean,
      default: false
    },
    request:Function,
    options:Array,
    nativeProps:{
      type:Object,
      default:()=>({})
    }
  },
  data(){
    return{
      selectValue:[],
      selectData:[]

    }
  },
  created() {
     this.reloadData()
  },
  watch:{
    value(){
      this.selectValue=this.value
    },
  },
  methods:{
    reloadData(){
      if (this.remotedLoad){
        this.request().then(res=>{
          this.selectData=res.data
        })
      }else{
        this.selectData=this.options
      }
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
