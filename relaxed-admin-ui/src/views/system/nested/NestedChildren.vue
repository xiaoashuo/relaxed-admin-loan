<template>
  <div style="width: 80%; background-color: rgba(231, 250, 240, 1)">
    二级菜单

    <!--      <icon-selector style="height: 300px"></icon-selector>-->
<!--<pro-form  :rows="rowDatas"-->
<!--           :show-header="true"-->
<!--           v-model="formData"></pro-form>-->

    <pro-form-modal :width="'60%'" ref="proForm" :modal-config="modalConfig" v-model="formData" ></pro-form-modal>
    <el-button @click="showForm">显示</el-button>
    <el-button @click="openTimeout1">打开定时</el-button>
    <el-button @click="closeTimeout1">关闭定时</el-button>
    <el-button @click="openLock">开启锁屏</el-button>
    <lock ref="lockRef"></lock>
<!--    <div>-->
<!--      枚举名称-->
<!--      <el-input placeholder="请输入枚举名称" v-model="enumName"></el-input>-->
<!--    </div>-->

<!--    <div>-->
<!--      枚举值-->
<!--      <el-input placeholder="请输入枚举值" v-model="value"></el-input>-->
<!--    </div>-->
<!--    <div>-->
<!--      值类型-->
<!--      <el-input-->
<!--        type="number"-->
<!--        placeholder="1Number2String3Boolean"-->
<!--        v-model="valueType"-->
<!--      ></el-input>-->
<!--    </div>-->

<!--    <el-button @click="handleClick">点击获取枚举</el-button>-->
<!--    <div>当前枚举值对应Label: {{ enumLabel }}</div>-->
<!--    <div>当前枚举对应对象: {{ enumObj }}</div>-->
<!--    <div>当前枚举对应列表: {{ enumList }}</div>-->
<!--    <div>当前枚举常量对象{{ GenderEnum.GENDER.FEMALE.value }}</div>-->
  </div>
</template>

<script>
import { IconSelector } from '@/components/icons'
import ProFormModal from '@/components/form/src/ProFormModal'
import {openTimeOut,closeTimeout} from '@/utils/astrict'
import Lock from '@/components/LockSetting'
import GenderEnum from '@/constants/enums/system/GenderEnum'
export default {
  name: 'NestedChildren',
  components: {
    IconSelector,ProFormModal,Lock
  },
  data() {
    return {
      enumName: undefined,
      value: undefined,
      enumLabel: null,
      enumObj: null,
      enumList: null,
      valueType: 0,
      GenderEnum: GenderEnum,
      formData:{},
      modalConfig:{

        rows:[
          //第一行
          {
            layout:{},
            cols:[
              {

                "formItems":[
                  {
                    type:'text',
                    label: '组织名称1',
                    field:'name',
                    placeholder:'请输入组织名称',
                    "colLayout":{
                      span:8
                    }
                  },
                  {
                    type:'text',
                    label: '性别1',
                    field:'gender',
                    placeholder:'请输入组织名称',
                    "colLayout":{
                      span:8
                    }
                  },
                  {
                    type:'text',
                    label: '年龄1',
                    field:'age',
                    placeholder:'请输入组织名称',
                    "colLayout":{
                      span:8
                    }
                  },
                ]
              }
            ]
          },
          //第二行
          {
            layout:{},
            cols:[
              {

                "formItems":[
                  {
                    type:'text',
                    label: '公司2',
                    field:'company',
                    placeholder:'请输入组织名称',
                    "colLayout":{
                      span:8
                    }
                  },
                  {
                    type:'text',
                    label: '测试2',
                    field:'test',
                    placeholder:'请输入组织名称',
                    "colLayout":{
                      span:8
                    }
                  },
                  {
                    type:'select',
                    label: '测试2',
                    field:'dept',
                    placeholder:'请输入组织名称',
                    "colLayout":{
                      span:8
                    },
                    config:{

                      options:[
                        {
                          label:'测试',
                          value:1
                        },
                        {
                          label:'开发',
                          value:2
                        }
                      ]
                    }

                  },
                ]
              }
            ]
          },
          //第三行
          {
            layout:{},
            cols:[
              {

                formItems:[
                  {
                    type:'text',
                    label: '公开3',
                    field:'pb',
                    placeholder:'请输入组织名称',
                  },
                  {
                    type:'text',
                    label: '隐私3',
                    field:'pri',
                    placeholder:'请输入组织名称',
                  },
                ]
              }
            ]
          }
        ]
      },

    }
  },
  methods: {
    openTimeout1(){
      console.log("开启了")
      openTimeOut()
    },
    openLock(){
      this.$refs.lockRef.handleLock()
    },
    closeTimeout1(){
      console.log("关闭了")
      closeTimeout()
    },
    showForm(){
      this.$refs.proForm.add({title:"显示"})
    },
    handleClick() {
      const valueType = parseInt(this.valueType)
      this.enumLabel = this.$enums.getDescByValue(
        this.enumName,
        this.value,
        valueType,
        'unknown'
      )
      this.enumList = this.$enums.getEnumList(this.enumName, valueType)
      this.enumObj = this.$enums.getEnumPair(this.enumName)
    }
  }
}
</script>

<style scoped></style>
