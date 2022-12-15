<template>
  <div class="yi-form">
    <div v-if="showHeader" class="header">
      <slot name="header" />
    </div>
    <el-form
      ref="inner-form"
      class="content"
      :model="modelValue"
      v-bind="formProps"
    >
      <el-row>
        <template v-for="item in formItems">
          <el-col v-bind="getColLayout(item)">
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
              <template v-else-if="item.type === 'upload'">
                <yi-upload  v-bind="item.config"  :value="modelValue[`${item.field}`]"
                              @input="handleValueChange($event, item.field)"></yi-upload>

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
import {YiUpload} from '@/components/upload'

export default {
  name: 'YiForm',
  components: {
    YiRadioGroup,
    YiSelect,
    YiTreeSelect,
    YiUpload
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
    formItems: {
      type: Array,
      default: () => []
    },

    formProps: {
      type: Object,
      default: () => ({
        "label-width":'100px'
      })
    },
    //表单动作
    formAction:String,
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
    showHeader: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {}
  },

  methods: {
    getColLayout(item){
      return item.colLayout??this.colLayout
    },
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
.yi-form {
  .header {
    height: 50px;
  }
  .content {
  }
}
</style>
