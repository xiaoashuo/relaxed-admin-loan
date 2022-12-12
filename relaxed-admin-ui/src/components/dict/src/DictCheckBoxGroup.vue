<template>
  <el-checkbox-group  :value="selectedValue" :disabled="disabled" @input="handleChange">

    <el-checkbox  v-for="dictItem in dictItems"
                 :key="dictItem.id"
                 :label="dictItem.value"
                 :disabled="dictItem.disabled"
    >

      {{ dictItem.name }}
    </el-checkbox>
  </el-checkbox-group>
</template>

<script>
  import DictGroupMixin from "@/components/dict/src/mixin/DictGroupMixin";
    export default {
        name: "DictCheckBox",
      mixins:[DictGroupMixin],
    }
</script>

<style scoped>

</style>
