<template>
  <div class="rules-box">
    <el-card class="box-card">
      <el-row>
        <el-row class="loop-child flex-row">
          <!--关联条件-->
          <el-select
            class="relations-box"
            size="mini"
            v-model="rulesData.relation"
            @change="rulesChange"
          >
            <el-option
              v-for="item in relations"
              :label="item.label"
              :value="item.value"
              :key="item.value"
            ></el-option>
          </el-select>
          <!--功能区-->
          <div
            v-if="enableDeleteChild"
            class="delete-child"
            title="删除子条件"
            @click="deleteChild(autoIndex)"
          ><i class="el-icon-delete-solid"></i></div>
          <div
            class="add-child"
            title="添加子条件"
            @click="addChild"
          ><i class="el-icon-s-unfold"></i></div>
          <div
            class="add-sibling"
            title="添加同级条件"
            @click="addCondition"
          ><i class="el-icon-circle-plus"></i></div>
        </el-row>

        <template v-for="(item, index) in rulesData.conditions">
          <el-row :key="'condition'+index" class="flex-row loop-child">
            <!--原始字段数据-->
            <el-select
              class="condition-name"
              v-model="item.name"
              @change="rulesChange"
              size="mini"
              placeholder="请选择条件名"
            >
              <el-option
                v-for="form in formDatas"
                :label="form.label"
                :value="form.value"
                :key="form.value"
              ></el-option>
            </el-select>
            <!--操作符号-->
            <el-select
              class="condition-operate"
              v-model="item.operate"
              @change="rulesChange"
              size="mini"
              placeholder="请选择逻辑符号"
            >
              <el-option
                v-for="oper in operators"
                :label="oper.label"
                :value="oper.value"
                :key="oper.value"
              ></el-option>
            </el-select>
            <!--条件值-->
            <div class="condition-value">
              <template v-if="item.operate!=='NULL' && item.operate!=='NOT_NULL' ">
                <el-input
                  size="mini"
                  v-model="item.value"
                  @input="rulesChange"
                  placeholder="请输入条件值"
                />
              </template>

            </div>
            <!--删除当前条件-->
            <div
              class="delete-condition"
              @click="deleteCondition(index)"
              title="删除条件"
            ><i class="el-icon-delete-solid"></i></div>
          </el-row>
        </template>
        <el-row
          v-if="rulesData.children.length > 0"
          v-for="(group, index) in rulesData.children"
          class="loop-child"
          :key="'row-'+autoIndex+'-'+index"
        >
          <risk-condition
            :topDatas="rulesData"
            :form-datas="formDatas"
            :rulesData="group"
            :enableDeleteChild="true"
            :autoIndex="index"
            @rulesChange="childChange"
          />
        </el-row>
      </el-row>
    </el-card>
  </div>
</template>


<script>
import RiskCondition from '@/views/risk/detail/RiskCondition.vue'
export default {
  name:"RiskCondition",
  components:{
    RiskCondition
  },
  mixins:[],
  props:{
    //规则描述
    rulesData: {
      type: Object,
      default: ()=>{
        return {
          relation: 'and',
          conditions: [],
          children: []
        }
      }
    },
    //表单数据
    formDatas: {
      type: Array,
      default: ()=>{
        return []
      }
    },
    //父级数据
    topDatas: {
      type: Object,
      default: ()=>{
        return {
          relation: 'and',
          conditions: [],
          children: []
        }
      }
    },
    enableDeleteChild: {
      type: Boolean,
      default: false
    },
    autoIndex: {
      type: Number,
      default: 0
    }
  },
  data(){
    return {
      relations:[{
        label: "满足全部条件",
        value:"and"
      },{
        label: "满足任一条件",
        value:"or"
      }],
      operators: [
            {
              label:"等于",
              value:"=="
            },
            {
              label:"大于",
              value:">"
            },
        {
          label:"大于等于",
          value:">="
        },
        {
          label:"小于",
          value:"<"
        },
        {
          label:"小于等于",
          value:"<="
        },
        {
          label:"不等于",
          value:"!="
        },
        {
          label:"为空",
          value:"NULL"
        },
        {
          label:"不为空",
          value:"NOT_NULL"
        },
        ]

  }
  },
  methods:{
    addCondition(){
      let conditionName = this.formDatas && this.formDatas[0] && this.formDatas[0].value
      if(conditionName){
        this.rulesData.conditions.push({
          name: conditionName,
          operate:"==",
          value:""
        })
        this.rulesChange(this.rulesData)
      }else{
        this.$message.warning("请先获取条件数据！")
      }
    },
    addChild(){
      let conditionName = this.formDatas && this.formDatas[0] && this.formDatas[0].value
      if(conditionName){
        this.rulesData.children.push({
          relation: 'and',
          conditions:[],
          children:[]
        })
        this.changeObserver()//监测组件是否改变的方法,组件改变不等于条件改变的ruleChange方法
      }else{
        this.$message.warning("请先获取条件数据！")
      }
    },
    deleteChild(index){
      let conditionDelete = this.topDatas && this.topDatas.children && this.topDatas.children.length > 0
      if(conditionDelete){
        this.topDatas.children.splice(index, 1)
      }
      this.rulesChange(this.rulesData)
    },
    deleteCondition(index){
      this.rulesData.conditions.splice(index, 1)
      this.rulesChange()
    },
    childChange(childData){
      this.rulesChange()
    },
    valueRuleChange(value){

    },
    rulesChange(){
      this.changeObserver()
      this.$emit("rulesChange", this.rulesData)
    },
    changeObserver(){
      this.$emit("changeObserver", this.rulesData)
    }
  }
}
</script>

<style lang="scss" scoped>
.rules-box{
  width:100%;
  *{
    box-sizing:border-box;
  }
  ::v-deep {
    .el-card__body{
      padding:0 1em;
    }
  }
  .flex-row{
    display: flex;
    flex-direction: row;
    align-items:center;
  }
  .loop-child{
    width:100%;
    position:relative;
    border:1px solid #d9d9d9;
    border-style: none none none solid;
    padding:10px 0 10px 12px;
    line-height:1;

    ::v-deep {
      .el-input__icon{
        width:14px;
        line-height:28px;
      }
      .el-input__inner{
        padding-left:5px;
        padding-right:18px;
      }
    }
    &::before{
      display:block;
      content:'';
      position:absolute;
      background-color:white;
      width:1px;
      height:50%;
    }
    &:first-child::before{
      left:-1px;
      top:0;
    }
    &:last-child::before{
      left:-1px;
      bottom:0;
    }
    &::after{
      top:50%;
      left:0;
      position:absolute;
      content:'';
      display:block;
      width:1px;
      height:1px;
      border:1px solid #d9d9d9;
      border-style: solid none none none;
    }
    .relations-box{
      margin-right:0.5em;
    }
    .delete-child{
      cursor:pointer;
      padding:0.28em;
      margin-right:0.5em;
    }
    .delete-condition{
      cursor:pointer;
      padding:0.28em;
      margin:0 0 0 0.5em;
    }
    .add-child{
      cursor:pointer;
      padding:0.28em;
      margin-right:0.5em;

      i{
        font-size:18px;
      }
    }
    .add-sibling{
      cursor:pointer;
      padding:0.28em;
      margin-right:0.5em;

      i{
        font-size:18px;
      }
    }
    .condition-name{
      max-width:140px;
      ::v-deep{
        .el-input{
          display:inline-block;
        }
      }
    }
    .condition-operate{
      max-width:83px;
    }
    .condition-value{
      flex:1;
      margin-left:1rem;
    }
  }
}
</style>
