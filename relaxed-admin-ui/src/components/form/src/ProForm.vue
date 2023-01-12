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
            <el-form-item
              v-if="!item.isHidden && item.type !== 'slot'"
              :label="item.label"
              :prop="item.field"
              :rules="item.rules"
              v-bind="item.itemProps"
            >
              <template v-if="item.type === 'text' || item.type === 'password'">
                <el-input
                  :value="modelValue[`${item.field}`]"
                  v-bind="item.config"
                  style="height:30px"
                  :placeholder="item.placeholder"
                  :show-password="item.type === 'password'"
                  @input="handleValueChange($event, item.field)"
                />
              </template>
              <template v-if="item.type === 'number'">
                <el-input-number
                  :value="modelValue[`${item.field}`]"
                  v-bind="item.config"
                  @input="handleValueChange($event, item.field)"
                />
              </template>
              <template v-else-if="item.type === 'select'">
                <yi-select
                  :placeholder="item.placeholder"
                  v-bind="item.config"
                  :value="modelValue[`${item.field}`]"
                  @input="handleValueChange($event, item.field)"
                />
              </template>
              <template v-else-if="item.type === 'dict-select'">
                <dict-select
                  :placeholder="item.placeholder"
                  :value="modelValue[`${item.field}`]"
                  v-bind="item.config"
                  @input="handleValueChange($event, item.field)"
                />
              </template>

              <template v-else-if="item.type === 'tree-select'">
                <yi-tree-select
                  :value="modelValue[`${item.field}`]"
                  v-bind="item.config"
                  @input="handleValueChange($event, item.field)"
                />
              </template>
              <template v-else-if="item.type === 'cascader-select'">
                <cascader-select
                  :placeholder="item.placeholder"
                  v-bind="item.config"
                  :value="modelValue[`${item.field}`]"
                  @input="handleValueChange($event, item.field)"
                >
                </cascader-select>
              </template>
              <template v-else-if="item.type === 'dict-radio-group'">
                <dict-radio-group
                  :placeholder="item.placeholder"
                  :value="modelValue[`${item.field}`]"
                  v-bind="item.config"
                  @input="handleValueChange($event, item.field)"
                />
              </template>
              <template v-else-if="item.type === 'radio-group'">
                <yi-radio-group  v-bind="item.config"  :value="modelValue[`${item.field}`]" @input="handleValueChange(value, item.field)" >

                </yi-radio-group>
              </template>
              <template v-else-if="item.type === 'datepicker'">
                <el-date-picker
                  style="width: 100%"
                  v-bind="item.config"
                  :value="modelValue[`${item.field}`]"
                  @input="handleValueChange($event, item.field)"
                />
              </template>
            </el-form-item>
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
  import {YiRadioGroup} from '@/components/radio'
  import { YiSelect, YiTreeSelect } from '@/components/select'

  export default {
    name: 'ProForm',
    components: {
      YiRadioGroup,
      YiSelect,
      YiTreeSelect
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
      handleValueChange(value, field) {
        this.$emit('update:input', { ...this.modelValue, [field]: value })
      },
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
