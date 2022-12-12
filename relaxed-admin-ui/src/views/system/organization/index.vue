<template>
  <div class="app-container">

    <!--搜索组件-->
    <page-search
      ref="pageSearchRef"
      :search-form-config="searchFormConfig"
      @resetBtnClick="handleResetClick"
      @queryBtnClick="handleSearchClick"
    />
    <!--表格组件-->
    <pro-table
      ref="pageContentRef"
      :content-table-config="contentTableConfig"
      :request="tableRequest"
      @newBtnClick="showNewModal({sort:0})"
      @editBtnClick="showEditModal"
      @delBtnClick="handleDelClick"
    >
      <template #extOption>
        <div>
          <el-button type="info"  plain icon="el-icon-sort"
                     size="small" @click="toggleExpandAll" style="margin-right: 12px">
            展开/折叠
          </el-button>
        </div>

      </template>
      <template #extHandler="scope">
        <el-button
          size="small"
          type="text"
          icon="el-icon-plus"
          @click="showNewModal({sort:0,parentId: scope.row.id})"
        >新增
        </el-button>
      </template>
    </pro-table>
    <!--模态表单组件-->
    <form-modal
      ref="formModal"
      :modal-config="modalConfig"
      :req-function="reqFunction"
      @submitSuccess="handleSubmit"
    />
  </div>
</template>

<script>

import { contentTableConfig } from './config/content.config'
import { searchFormConfig } from './config/search.config'
import { modalConfig } from './config/modal.config'

import { getOrganizationTree, addObj, putObj, delObj } from '@/api/organization'

export default {
  name: 'Organization',

  data() {
    return {
      contentTableConfig: contentTableConfig,
      searchFormConfig: searchFormConfig,
      modalConfig: modalConfig,
      // 表格请求
      tableRequest: getOrganizationTree,
      // 表单请求
      reqFunction: {
        create: addObj,
        update: putObj
      }

    }
  },
  methods: {
    toggleExpandAll(){
      this.$refs.pageContentRef.toggleExpandAll()
    },
    // 表格相关
    showNewModal(item) {
      this.$refs.formModal.add({ title: '新建组织', item })
    },
    showEditModal(item) {
      this.$refs.formModal.update({ title: '编辑组织', item })
    },
    handleDelClick(item) {
      delObj(item.id).then(res => {
        this.$refs.pageContentRef.refreshTable(false)
      })
    },
    // 模态框相关
    handleSubmit(res) {
      this.$refs.pageContentRef.refreshTable(false)
    },
    // 搜索框相关
    handleSearchClick(formData) {
      this.$refs.pageContentRef.searchTable(formData)
    },
    handleResetClick() {
      this.$refs.pageContentRef.resetTable()
    }

  }
}
</script>

<style  lang="scss" scoped>
  /*修改展开按钮的样式 start*/
  ::v-deep  .el-table__expand-icon{
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  /*2.展开按钮未点击的样式是加号带边框*/
  ::v-deep   .el-table__expand-icon .el-icon-arrow-right:before{
    content: "\e6d9";
    border: 1px solid #ccc;
    padding: 2px;
  }
  /*3.展开按钮点击后的样式是减号带边框*/
  ::v-deep  .el-table__expand-icon--expanded .el-icon-arrow-right:before{
    content: "\e6d8";
  }

</style>
