<template>
  <div style="width: 80%; background-color: rgba(231, 250, 240, 1)">
    二级菜单

    <!--      <icon-selector style="height: 300px"></icon-selector>-->

    <div>
      枚举名称
      <el-input placeholder="请输入枚举名称" v-model="enumName"></el-input>
    </div>

    <div>
      枚举值
      <el-input placeholder="请输入枚举值" v-model="value"></el-input>
    </div>
    <div>
      值类型
      <el-input
        type="number"
        placeholder="1Number2String3Boolean"
        v-model="valueType"
      ></el-input>
    </div>

    <el-button @click="handleClick">点击获取枚举</el-button>
    <div>当前枚举值对应Label: {{ enumLabel }}</div>
    <div>当前枚举对应对象: {{ enumObj }}</div>
    <div>当前枚举对应列表: {{ enumList }}</div>
    <div>当前枚举常量对象{{ GenderEnum.GENDER.FEMALE.value }}</div>
  </div>
</template>

<script>
import { IconSelector } from '@/components/icons'
import GenderEnum from '@/constants/enums/system/GenderEnum'
export default {
  name: 'NestedChildren',
  components: {
    IconSelector
  },
  data() {
    return {
      enumName: undefined,
      value: undefined,
      enumLabel: null,
      enumObj: null,
      enumList: null,
      valueType: 0,
      GenderEnum: GenderEnum
    }
  },
  methods: {
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
