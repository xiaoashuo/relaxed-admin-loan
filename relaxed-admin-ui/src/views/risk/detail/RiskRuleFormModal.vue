<template>

  <div class="user-modal-form">
    <el-dialog
      :title="title"
      :visible.sync="visible"
      width="40%"
      append-to-body
      fullscreen
      :before-close="dialogClose"
    >
      <el-form ref="pageForm"  :model="form"  :rules="rules" label-width="80px">
        <el-form-item label="显示名称" prop="label"  >
          <el-input   v-model="form.label" placeholder="请输入显示名称"></el-input>
        </el-form-item>
        <el-form-item label="初始得分" prop="initScore"  >
          <el-input-number   v-model="form.initScore" placeholder="请输入初始得分"></el-input-number>
        </el-form-item>
        <el-form-item label="命中基数" prop="baseNum"  >
          <el-input-number   v-model="form.baseNum" placeholder="请输入命中基数"></el-input-number>
        </el-form-item>
        <el-form-item label="操作符" prop="operator"  >
          <dict-select dict-code="operator_symbol" placeholder="请选择操作符" v-model="form.operator"></dict-select>
        </el-form-item>
<!--        <el-form-item label="指标字段" prop="indicator "  >-->
<!--          <el-input   v-model="form.indicator" placeholder="请输入指标字段"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="比率" prop="rate"  >
          <el-input   v-model="form.rate" placeholder="请输入比率"></el-input>
        </el-form-item>
        <el-form-item label="最大值" prop="max"  >
          <el-input   v-model="form.max" placeholder="请输入最大值"></el-input>
        </el-form-item>
        <el-row style="padding-left: 15px">

          <el-form-item label="规则定义"  >
            <risk-condition :formDatas="formDatas" :rulesData="rulesData" @rulesChange="rulesChange" />
          </el-form-item>

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
import RiskCondition from '@/views/risk/detail/RiskCondition.vue'
import ca from 'element-ui/src/locale/lang/ca'

const RelationMap={
  and:'&&',
  or:'||'
}
export default {
  name: "RiskRuleFormModal",
  mixins:[formModalActionMixin],
  components:{
    YiSelect,YiTreeSelect,RiskCondition
  },
  data(){
    return{
      formDatas:[],
      //初始化条件对象或者，已保存的条件对象
      rulesData:{},

      form: {},
      rules:{
        label:[
          { required: true, message: '显示昵称不能为空', trigger: 'blur' },
        ],
        initScore:[
          { required: true, message: '初始得分不能为空', trigger: 'blur' },
        ],
        baseNum:[
          { required: true, message: '命中基数不能为空', trigger: 'blur' },
        ],
        operator:[
          { required: true, message: '操作符不能为空', trigger: 'blur' },
        ],
        rate:[
          { required: true, message: '比率不能为空', trigger: 'blur' },
        ],
        max:[
          { required: true, message: '最大值不能为空', trigger: 'blur' },
        ],

      },





    }
  },
  created() {



  },
  watch:{

  },

  methods:{
    rulesChange(datas){
      console.log("数据改变",datas)//输出的条件对象
      this.rulesData=datas
    },
    /**
     * 检查规则是否存在错误  只要存在 则抛出
     */
    checkRuleData(datas){
      let errArr=[]
      const _selfCall=(errArr,datas)=>{
        const conditions=datas.conditions
        const childrens=datas.children
        if (conditions&&conditions.length>0){
          for (let i = 0; i < conditions.length; i++) {
            const condition=conditions[i]
            if (condition.operate=="NULL"||condition.operate=="NOT_NULL"){
              if (!(condition.name&&condition.operate)){
                errArr.push(condition.name+"必填性校验不通过")
              }
            }
            else if (!(condition.name&&condition.operate&&condition.value)){
              errArr.push(condition.name+"必填性校验不通过")
            }
            if (errArr.length>0){
              break
            }
          }
        }
          if (errArr.length<=0&&childrens&&childrens.length>0){
            for (const children of childrens){

              _selfCall(errArr,children)
              if (errArr.length>0){
                break
              }
            }
          }
      }
      _selfCall(errArr,datas)
      return errArr

    },
    /**
     * 定义转成script脚本
     */
    buildScriptFromDefinition(datas){
      let scripts=[];
      const _selfCall=(script,datas)=>{
        const relationSymbol= RelationMap[datas.relation]
        const conditions=datas.conditions
        const childrens=datas.children

        //同级别 关联
        let conditionLength = conditions.length
        if (conditions&&conditionLength>0){
          for (let i = 0; i < conditionLength; i++) {
            const condition=conditions[i]
            switch (condition.operate){
              case "NULL":
                script.push(`!${condition.name}`)
                break
              case "NOT_NULL":
                script.push(`${condition.name}`)
                break
              case "⊇":
                //包含
                script.push(`${condition.name}.split(",").toList().containsAll(${condition.value}.split(",").toList())`)
                break
              case "⊆":
                //包含于
                script.push(`'${condition.value}'.split(",").toList().containsAll(${condition.name}.split(",").toList())`)
                break
              default:
                script.push(`${condition.name} ${condition.operate} ${condition.value??''}`)
                break
            }
            if (i!==conditionLength-1){
              script.push(relationSymbol)
            }
          }
        }

        let childrenLength = childrens.length
        if (childrens&&childrenLength>0){
          for (let i = 0; i < childrenLength; i++) {
            const children= childrens[i]
            //取出当前孩子
            let childConcat=""
            if (conditions&&conditionLength>0){
              childConcat=` ${relationSymbol} ( `
            }else{
              childConcat=" ( "
            }
            script.push(childConcat)
            _selfCall(script,children)
            const lastElement=script[script.length-1]
            if (lastElement==childConcat){
              script.slice(script.length-1,1)
            }else{
              if (i==childrenLength-1){
                script.push(") ")
              }else{
                script.push(") "+relationSymbol)
              }
            }
          }



        }
      }
      _selfCall(scripts,datas)
       let scriptStr=scripts.join(" ")
      console.log("当前Data",scriptStr)
      return scriptStr;

    },



    createdFormCallback(payload){
      this.form=this.defaultForm()

      const {activationData,allFields}=payload.item
      this.form.modelId=activationData.modelId
      this.form.activationId=activationData.id
      console.log(payload)
      this.rulesData={
        relation:"and",
        conditions:[],
        children:[]
      }
      this.formDatas=allFields

    },
    updatedFormCallback(payload){
      const {activationData,ruleData,allFields}=payload.item
      this.rulesData=JSON.parse(ruleData.ruleDefinition)
      this.form={...ruleData}
      this.form.modelId=activationData.modelId
      this.form.activationId=activationData.id
      this.formDatas=allFields


    },
    defaultForm(){
      return {
         initScore:0,
         baseNum: 1,
         rate: 1
      }
    },
    dialogClose(done){
      this.close()
      done()
    },
    beforeClose(){
      this.$refs.pageForm.clearValidate()
      this.rulesData={}
      this.formDatas=[]
    },
    handleConfirmClick(){
      //处理表单提交
      this.$refs.pageForm.validate((valid,invalidFields)=>{
        if (!valid){
          return
        }else{
          //通过效验提交数据

          if (!this.rulesData){
            this.$message.error("规则定义不能为空")
            return
          }

          const errArr=this.checkRuleData(this.rulesData)
          console.log("错误",errArr)
          if (errArr.length>0){
            this.$message.error("请检查参数完整性")
            return
          }
          const script=this.buildScriptFromDefinition(this.rulesData)
          this.form.ruleDefinition=JSON.stringify(this.rulesData)
          this.form.scripts=`
      class ActivationCheckScript {
          public boolean check(def context, def report) {
             if (${script})
               return true;
             else
               return false;
          }
       }
      `
          console.log(this.form)
          this.handleSubmit({...this.form})
        }
      })
    },


  }
}
</script>

<style lang="scss" scoped>

</style>
