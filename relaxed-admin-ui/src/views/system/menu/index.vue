<template>
  <div class="app-container">
    <!--      搜索区域-->
    <page-search
      ref="pageSearchRef"
      :searchFormConfig="searchFormConfig"
      @resetBtnClick="handleResetClick"
      @queryBtnClick="handleSearchClick"
    ></page-search>
    <!--      内容区域-->
    <!--表格组件-->
    <yi-pro-table
      ref="pageContentRef"
      :content-table-config="contentTableConfig"
      :request="tableRequest"
      @addBtnClick="showNewModal({ sort: 0 })"
      @editBtnClick="handleEditModal"
      @delBtnClick="handleDelData"
    >
      <template #extUserTools>
        <div>
          <el-button type="info"  plain icon="el-icon-sort"
                     size="small" @click="toggleExpandAll" style="margin-right: 12px">
            展开/折叠
          </el-button>
        </div>
      </template>
      <template #hidden="scope">
        <dict-text dict-code="yes_or_no" :value="scope.row.hidden"></dict-text>
      </template>
      <template #menuType="scope">
        <dict-text dict-code="menu_type" :value="scope.row.type"></dict-text>
      </template>
      <template #extHandler="scope">
        <div>
          <el-button
            size="small"
            type="text"
            icon="el-icon-plus"
            @click="showNewModal({ parentId: scope.row.id })"
            >新增
          </el-button>

        </div>
      </template>
    </yi-pro-table>

    <menu-modal-form
      ref="formModal"
      :tree-select-data="treeSelectData"
      @reload-page-table="handleResetClick"
    ></menu-modal-form>
    <el-tooltip
      placement="top"
      content="返回顶部"
      style="z-index: 600; border-radius: 50%; background-color: white"
    >
      <back-to-top
        :custom-style="myBackToTopStyle"
        :visibility-height="500"
        :back-position="0"
        transition-name="fade"
      />
    </el-tooltip>
  </div>
</template>

<script>
import MenuModalForm from '@/views/system/menu/MenuModalForm'

import { BackToTop } from '@/components/BackToTop'
import { contentTableConfig } from './config/content.config'
import { searchFormConfig } from './config/search.config'
import { modalConfig } from './config/modal.config'

import { getGrantList, saveMenu, getPageListData, delMenu } from '@/api/menu'

export default {
  name: 'index',
  components: { MenuModalForm, BackToTop },
  created() {
    this.contentTableConfig = contentTableConfig
    this.searchFormConfig = searchFormConfig
    this.modalConfig = modalConfig
    this.loadTreeSelectData()
  },
  data() {
    return {
      contentTableConfig: {},
      searchFormConfig: {},
      modalConfig: {},
      tableRequest: getPageListData,
      treeSelectData: [],
      myBackToTopStyle: {
        right: '50px',
        bottom: '50px',
        width: '40px',
        height: '40px',
        'border-radius': '4px',
        'line-height': '45px', // 请保持与高度一致以垂直居中 Please keep consistent with height to center vertically
        background: '#e7eaf1' // 按钮的背景颜色 The background color of the button
      }
    }
  },

  methods: {
    loadTreeSelectData() {
      getGrantList().then((res) => {
        this.treeSelectData = res.data
      })
    },
    toggleExpandAll(){
      this.$refs.pageContentRef.toggleExpandAll()
    },
    //表格相关
    showNewModal(item) {
      this.loadTreeSelectData()
      this.$refs.formModal.add({ title: '新增菜单', item })
    },
    handleDelData(item) {
      delMenu(item.id).then((res) => {
        this.$refs.pageContentRef.refreshTable(false)
      })
    },

    handleEditModal(item) {
      this.loadTreeSelectData()
      this.$refs.formModal.update({ title: '编辑菜单', item })
    },
    //处理搜索按钮
    handleSearchClick(formData) {
      this.$refs.pageContentRef.searchTable(formData)
    },
    handleResetClick() {
      this.$refs.pageContentRef.resetTable()
    }
  }
}
</script>

<style lang="scss" scoped>
/*修改展开按钮的样式 start*/
::v-deep .el-table__expand-icon {
  -webkit-transform: rotate(0deg);
  transform: rotate(0deg);
}
/*2.展开按钮未点击的样式是加号带边框*/
::v-deep .el-table__expand-icon .el-icon-arrow-right:before {
  content: '\e6d9';
  border: 1px solid #ccc;
  padding: 2px;
}
/*3.展开按钮点击后的样式是减号带边框*/
::v-deep .el-table__expand-icon--expanded .el-icon-arrow-right:before {
  content: '\e6d8';
}
</style>
