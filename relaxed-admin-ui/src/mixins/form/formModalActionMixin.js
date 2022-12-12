import formModalMixin from "@/mixins/form/formModalMixin";

export default {
  mixins:[formModalMixin],
  props:{
    beforeSubmit:{
      type: Function,
      default: (payload)=> true
    },
    //请求前 数据加工处理
    beforeRequest:{
      type: Function,
      default: (payload)=>payload
    },
    reqFunction:{
      type:Object,
      default: ()=>({
        create: (payload)=>Promise.resolve(),
        update: (payload)=>Promise.resolve()
      })
    },
  },
  data(){
    return{

    }
  },
  methods:{
    handleSubmit(payload){

      const reqData=this.beforeRequest(payload)
      console.log("接收到请求",reqData)
      //请求提交前 逻辑检查 不成功则 不提交
      if (this.beforeSubmit(reqData)===false){
        return
      }
      const reqFunction=this.reqFunction[this.formAction]
      reqFunction(reqData).then(res=>{
        console.log("请求创建响应",res)
        //表单提交成功 发射请求事件
        this.$emit('submitSuccess',res)
        this.close()
      }).catch(err=>{
        //表单请求失败 发送失败事件
        this.$emit('submitFail',err)
      })
    }
  }
}
