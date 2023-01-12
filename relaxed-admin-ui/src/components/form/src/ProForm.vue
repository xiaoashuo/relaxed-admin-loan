<template>
  <div class="pro-form">
    <div v-if="showHeader" class="header">
      <slot name="header" />
    </div>
    <el-form
      ref="inner-form"
      class="content"
      :model="modelValue"
      v-bind="formProps"
    >

      <el-row v-for="row in rows" v-bind="row.layout" >

        <template v-for="col in row.cols">

          <el-col v-for="item in col.formItems"  v-bind="item.colLayout?item.colLayout:colLayout">
            <template v-if="!item.isHidden && item.type === 'slot'">
              <slot
                :name="item.slotName"
                :row="{ item, data: modelValue }"
              />
            </template>
            <yi-form-item      v-if="!item.isHidden && item.type !== 'slot'"
                               :item="item" :model-value="modelValue"></yi-form-item>
          </el-col>
        </template>
      </el-row>
    </el-form>
    <div class="footer">
      <slot name="footer" />
    </div>
  </div>
</template>

<script>


  import YiFormItem from '@/components/form/src/YiFormItem.vue'
  export default {
    name: 'ProForm',
    components: {
      YiFormItem,
    },
    model: {
      prop: 'modelValue',
      event: 'update:input'
    },
    props: {
      modelValue: {
        type: Object,
        required: true
      },
      rows: {
        type: Array,
        default: () => []
      },
      //全局列布局
      colLayout: {
        type: Object,
        default: () => ({
          xl: 6, // >1920 显示4个
          lg: 8,
          md: 12,
          sm: 24,
          xs: 24
        })
      },
      formProps: {
        type: Object,
        default: () => ({
          "label-width":'100px'
        })
      },
      showHeader: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {}
    },

    methods: {

      getCurrentForm() {
        return this.$refs['inner-form']
      }
    }
  }
</script>

<style lang="scss" scoped>
  .pro-form {
    .header {
      height: 50px;
    }
    .content {
    }
  }
</style>
