<template>

  <div class="user-modal-form">
    <el-dialog
      :title="title"
      :visible.sync="visible"
      width="40%"
      :before-close="dialogClose"
    >
      <el-form ref="pageForm"  :model="form"  :rules="rules" label-width="80px">
        <el-row :gutter="10" >
          <el-col :span="12">
            <el-form-item label="来源字段" prop="sourceField"  >
              <yi-select   v-model="form.sourceField"   :options="fieldProps.options" :placeholder="fieldProps.placeholder"></yi-select>

            </el-form-item>


          </el-col>
          <el-col :span="12">
             <span>  {{form.sourceLabel}}/{{form.sourceField}}</span>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="目标字段" prop="destField" >
              <el-input   v-model="form.destField" placeholder="请输入目标字段"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="目标标签" prop="destLabel" >
              <el-input   v-model="form.destLabel" placeholder="请输入目标字段标签"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12" >
            <el-form-item  label="插件种类" prop="plugin"  >
              <yi-select   v-model="form.plugin"   :options="pluginOptions" placeholder="请选择插件"></yi-select>

            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="参数" prop="args" >
              <el-input v-model="form.args" placeholder="请输入参数" ></el-input>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row  style="text-align: right" >
          <el-button @click="close">取 消</el-button>
          <el-button type="primary" @click="handleConfirmClick">确 定</el-button>
        </el-row>
      </el-form>
    </el-dialog>
  </div>

</template>

<script>
import {YiTreeSelect,YiSelect} from "@/components/select"

import formModalActionMixin from "@/mixins/form/formModalActionMixin";
import {getFieldList} from "@/api/risk/risk-field";
import { getPluginList } from '@/api/risk/risk-pre-item'
import {getUserRoleIds,getRoleSelectData} from "@/api/role";
import {listOrganization} from "@/api/organization";
export default {
  name: "RiskPreItemFormModel",
  mixins:[formModalActionMixin],
  components:{
    YiSelect,YiTreeSelect
  },
  data(){
    return{
      form: {},
      rules:{
        nickname:[
          { required: true, message: '请输入用户昵称', trigger: 'blur' },
        ],
        password:[
          { required: true, message: '请输入用户密码', trigger: 'blur' },
        ],
        organizationId:[
          { required: true, message: '请输入组织部门', trigger: 'blur' },
        ],
        username:[
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        phone:[
          { required: true, message: '请输入手机号', trigger: 'blur' },
        ],
        email:[
          { required: true, message: '请输入邮箱', trigger: 'blur' },
        ],
        status:[
          { required: true, message: '请选择状态', trigger: 'blur' },
        ],
      },
      fieldProps:{
        placeholder:'请选择角色',
        options: [],
      },
      pluginOptions:[],
      orgReq: listOrganization,



    }
  },
  created() {
     this.getAllPlugin()


  },
  watch:{
    "form.sourceField":{
      handler(newVal,oldVal){
        const item=this.fieldProps.options.find(e=>e.value==newVal)
        this.form.sourceLabel=item?.label
      }
    }
  },
  methods:{

    getAllPlugin(){
      getPluginList().then(res=>{
        this.pluginOptions=res.data
      })
    },
    getAllFields(modelId,callback){
      getFieldList(modelId).then(res=>{
         this.fieldProps.options = res.data
         callback()
      })
    },
    createdFormCallback(payload){
      this.form=this.defaultForm()
      this.form.modelId=payload.item.modelId
      this.getAllFields(this.form.modelId)
    },
    updatedFormCallback(payload){

      const item= payload.item

       console.log("当前",this.form)
       this.getAllFields(item.modelId,()=>{
         this.form={...item}
       })

    },
    defaultForm(){
      return {

      }
    },
    dialogClose(done){
      this.close()
      done()
    },
    beforeClose(){
      this.$refs.pageForm.clearValidate()
    },
    handleConfirmClick(){
      //处理表单提交
      this.$refs.pageForm.validate((valid,invalidFields)=>{
        if (!valid){
          return
        }else{
          //通过效验提交数据
          this.handleSubmit({...this.form})
        }
      })
    },


  }
}
</script>

<style lang="scss" scoped>

</style>
