<template>
  <div class="menu-modal">
    <el-dialog :title="title" :visible.sync="visible" width="45%">
      <el-form
        ref="pageForm"
        :model="form"
        :rules="formRules"
        label-width="100px"
      >
        <el-form-item
          prop="originalId"
          v-model="form.originalId"
          style="display: none"
        ></el-form-item>
        <el-form-item label="上级菜单" prop="parentId" class="yi-form-item">
          <yi-tree-select
            ref="treeSelectRef"
            v-model="form.parentId"
            v-bind="treeSelect.config"
          ></yi-tree-select>
        </el-form-item>
        <el-form-item label="菜单类型" prop="type">
          <dict-radio-group
            dict-code="menu_type"
            v-model="form.type"
            @change="handleMenuTypeChange"
          ></dict-radio-group>
        </el-form-item>

        <el-row :gutter="16">
          <el-col :xs="24" :sm="24" :md="12">
            <el-form-item class="yi-form-item" prop="id">
              <span style="width: 100px" slot="label">
                菜单ID
                <el-tooltip placement="top-start">
                  <div slot="content">
                    菜单ID的长度固定为 6，由三部分构成。<br />
                    前两位是目录序号，中间两位是菜单序号，最后两位是按钮序号。<br />
                    例如目录的ID结构应为：XX0000，<br />
                    菜单结构为 XXXX00，<br />
                    按钮ID结构为 XXXXXX
                  </div>
                  <i class="el-icon-share"></i>
                </el-tooltip>
              </span>
              <el-input v-model="form.id"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12">
            <el-form-item label="显示排序" prop="sort">
              <el-input-number v-model="form.sort"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
        <template v-if="form.type == 0">
          <el-form-item label="菜单名称" prop="title" class="yi-form-item">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="菜单图标" prop="icon" class="yi-form-item">
            <el-popover
              placement="bottom-start"
              width="550"
              trigger="click"
              @hide="$refs['iconSelect'].reset()"
            >
              <icon-selector
                ref="iconSelect"
                @change="handleIconChange"
              ></icon-selector>
              <el-input
                slot="reference"
                v-model="form.icon"
                placeholder="点击选择图标"
                readonly
              >
                <svg-icon
                  v-if="form.iconType == 'svg'"
                  slot="prefix"
                  :icon-class="form.icon"
                  class="el-input__icon"
                />
                <i
                  v-else-if="form.iconType == 'normal'"
                  style="height: 32px; width: 16px"
                  slot="prefix"
                  :class="form.icon"
                />
                <i v-else slot="prefix" class="el-icon-search el-input__icon" />
              </el-input>
            </el-popover>
          </el-form-item>
          <el-row>
            <el-col :sm="24" :xs="24" :md="12">
              <el-form-item label="路由地址" prop="path">
                <el-input v-model="form.path"></el-input>
              </el-form-item>
            </el-col>
            <el-col :sm="24" :xs="24" :md="12">
              <el-form-item label="是否隐藏" prop="hidden">
                <dict-radio-group
                  dict-code="yes_or_no"
                  v-model="form.hidden"
                ></dict-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
        </template>
        <template v-if="form.type == 1">
          <el-row>
            <el-form-item label="菜单名称" prop="title" class="yi-form-item">
              <el-input v-model="form.title"></el-input>
            </el-form-item>
            <el-form-item label="菜单图标" prop="icon" class="yi-form-item">
              <el-popover
                placement="bottom-start"
                width="500"
                trigger="click"
                @hide="$refs['iconSelect'].reset()"
              >
                <icon-selector
                  ref="iconSelect"
                  @change="handleIconChange"
                ></icon-selector>
                <el-input
                  slot="reference"
                  v-model="form.icon"
                  placeholder="点击选择图标"
                  readonly
                >
                  <svg-icon
                    v-if="form.iconType == 'svg'"
                    slot="prefix"
                    :icon-class="form.icon"
                    class="el-input__icon"
                  />
                  <i
                    v-else-if="form.iconType == 'normal'"
                    style="height: 32px; width: 16px"
                    slot="prefix"
                    :class="form.icon"
                  />
                  <i
                    v-else
                    slot="prefix"
                    class="el-icon-search el-input__icon"
                  />
                </el-input>
              </el-popover>
            </el-form-item>
            <el-row>
              <el-col :sm="24" :xs="24" :md="12">
                <el-form-item label="路由地址" prop="path">
                  <el-input v-model="form.path"></el-input>
                </el-form-item>
              </el-col>
              <el-col :sm="24" :xs="24" :md="12">
                <el-form-item label="是否隐藏" prop="hidden">
                  <dict-radio-group
                    dict-code="yes_or_no"
                    v-model="form.hidden"
                  ></dict-radio-group>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :sm="24" :xs="24" :md="12">
                <el-form-item
                  label="打开方式"
                  prop="targetType"
                  class="yi-form-item"
                >
                  <dict-select
                    dict-code="menu_target_type"
                    v-model="form.targetType"
                  ></dict-select>
                </el-form-item>
              </el-col>
              <el-col :sm="24" :xs="24" :md="12">
                <el-form-item
                  label="组件缓存"
                  prop="keepAlive"
                  label-width="80px"
                >
                  <el-input v-model="form.keepAlive"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="资源路径" prop="uri" class="yi-form-item">
              <el-input v-model="form.uri"></el-input>
            </el-form-item>
          </el-row>
        </template>
        <template v-if="form.type == 2">
          <el-row>
            <el-form-item label="菜单名称" prop="title" class="yi-form-item">
              <el-input v-model="form.title"></el-input>
            </el-form-item>
            <el-form-item
              label="授权标识"
              prop="permission"
              class="yi-form-item"
            >
              <el-input v-model="form.permission"></el-input>
            </el-form-item>
          </el-row>
        </template>
        <el-form-item label="高亮菜单" prop="activeMenu" class="yi-form-item">
          <el-input v-model="form.activeMenu"></el-input>
        </el-form-item>
        <el-form-item label="备注信息" prop="remarks" class="yi-form-item">
          <el-input type="textarea" v-model="form.remarks"></el-input>
        </el-form-item>
        <el-form-item>
          <div style="text-align: right">
            <el-button type="primary" @click="handleSubmitClick"
              >保存</el-button
            >
            <el-button @click="this.close">取消</el-button>
          </div>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { YiSelect, YiTreeSelect } from '@/components/select'

import {YiRadioGroup} from '@/components/radio'
import { IconSelector } from '@/components/icons'

import { getGrantList, saveMenu, updateMenu } from '@/api/menu'

import formModalMixin from '@/mixins/form/formModalMixin'

export default {
  name: 'MenuModalForm',
  components: {
    YiTreeSelect,
    YiSelect,
    YiRadioGroup,
    IconSelector
  },

  mixins: [formModalMixin],
  props: {
    treeSelectData: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      form: {},
      formRules: {
        id: [{ required: true, message: '请输入菜单ID', trigger: 'blur' }],
        type: [{ required: true, message: '请选择菜单类型', trigger: 'blur' }],
        parentId: [
          { required: true, message: '请选择上级菜单', trigger: 'blur' }
        ],
        title: [{ required: true, message: '请输入菜单名称', trigger: 'blur' }]
      },
      treeSelect: {
        config: {
          clearable: false,
          multiple: false,
          normalizer: (node) => {
            return {
              id: node.id,
              label: node.title + '-' + node.id,
              children:
                node.children && node.children.length ? node.children : 0
            }
          },
          placeholder: '请选择菜单',
          options: [
            {
              id: 0,
              title: '根目录',
              children: []
            }
          ]
        }
      }
    }
  },
  watch: {
    treeSelectData(newTreeSelectData) {
      this.$set(
        this.treeSelect.config.options[0],
        'children',
        newTreeSelectData
      )
    }
  },
  methods: {
    getDefaultForm() {
      return {
        type: 0,
        sort: 0,
        targetType: 1,
        hidden: 0
      }
    },

    createdFormCallback(payload) {
      this.form = { ...this.getDefaultForm(), ...payload.item }
    },
    updatedFormCallback(payload) {
      this.form = { ...payload.item }
      this.form.originalId = this.form.id
    },
    beforeClose() {
      this.form = this.getDefaultForm()
      this.$refs.pageForm.clearValidate()
    },

    //处理图标点击
    handleIconChange(item) {
      this.$set(this.form, 'icon', item.value)
      this.form.iconType = item.type
      //取消popover 展示
      document.body.click()
    },

    handleSubmitClick() {
      //处理表单提交
      this.$refs.pageForm.validate((valid, invalidFields) => {
        if (!valid) {
          return
        } else {
          //通过效验提交数据
          this.handleSubmit()
        }
      })
    },
    handleSubmit() {
      if (this.isCreateForm) {
        //创建
        const saveData = { ...this.form }
        saveMenu(saveData).then((res) => {
          this.close()
          this.$emit('reload-page-table')
        })
      }
      if (this.isUpdateForm) {
        //更新
        const editData = { ...this.form }
        updateMenu(editData).then((res) => {
          this.close()
          this.$emit('reload-page-table')
        })
      }
    },
    handleMenuTypeChange(type) {
      //处理菜单类型 改变  主要用于 清空 现有数据
    }
  }
}
</script>

<style lang="scss" scoped>
/*@import "src/styles/element-ui";*/

::v-deep {
  .el-input__prefix {
    padding-top: 1.5px;
    font-size: 18px;
  }
}
</style>
