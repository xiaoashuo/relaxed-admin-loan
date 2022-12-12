<template>
  <el-radio-group :disabled="disabled" :value="selectedValue" @input="handleChange">

    <template v-if="type.toLowerCase() === 'radio'">
      <el-radio v-for="dictItem in dictItems"
                :label="dictItem.value"
                :disabled="dictItem.disabled"
                :key="dictItem.id">{{dictItem.name}}
      </el-radio>

    </template>

    <template v-if="type.toLowerCase() === 'button'">

      <el-radio-button
        v-for="dictItem in dictItems"
        :key="dictItem.id"
        :label="dictItem.value"
        :disabled="dictItem.disabled"
      >
        {{ dictItem.name }}
      </el-radio-button>
    </template>
  </el-radio-group>
</template>

<script>

  import DictGroupMixin from "@/components/dict/src/mixin/DictGroupMixin";
  export default {
        name: "DictRadioGroup",
        mixins:[DictGroupMixin],
       props: {
         type: {
            type: String,
            default: 'radio'
         }
       },

    }
</script>

<style scoped>

</style>
