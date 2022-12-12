<template>
    <el-select :placeholder="placeholder"
            :clearable="clearable"
            :disabled="disabled"
               style="width: 100%"
               :value="selectedValue"
               @change="handleChange"
    >
      <el-option
        v-for="item in this.dictItems"
        :key="item.id"
        :label="item.name"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
</template>

<script>


import DictGroupMixin from "@/components/dict/src/mixin/DictGroupMixin";
  export default {
        name: "DictSelect",
        mixins:[DictGroupMixin],
        props:{
          placeholder: {
            type: String,
            default: '请选择'
          },
          clearable:{
            type: Boolean,
            default: false
          },
        },
    }
</script>

<style scoped>

</style>
