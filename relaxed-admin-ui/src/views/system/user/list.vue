<template>
  <div class="app-container">
    <el-row style="margin-top: 20px">
      <el-col :span="4">
        <el-input
          v-model="filterText"
          placeholder="请输入部门名称"
          clearable
          size="small"
          prefix-icon="el-icon-search"
          style="margin-bottom: 20px"
        />

        <el-tree
          ref="tree"
          class="filter-tree"
          :data="treeData"
          :props="defaultProps"

          :expand-on-click-node="false"
          default-expand-all
          highlight-current
          :filter-node-method="filterNode"
          @node-click="handleNodeClick"
        />
      </el-col>
      <el-col :span="18" :offset="1">
        <!--搜索组件-->
        <page-search
          v-show="showSearch"
          ref="pageSearchRef"
          :search-form-config="searchFormConfig"
          @resetBtnClick="handleResetClick"
          @queryBtnClick="handleSearchClick"
        />
        <!--表格组件-->
        <yi-pro-table
          ref="pageContentRef"
          multi-sort
          :content-table-config="contentTableConfig"
          :request="tableRequest"
          :before-search-table="beforeSearchTable"
          @addBtnClick="showNewModal"
          @editBtnClick="showEditModal"
          @delBtnClick="handleDelClick"
        >
          <template #extOption>

            <el-button
              v-permission="'system:user:export'"
              type="warning"
              plain
              icon="el-icon-download"
              size="small"
              class="item"
              @click="handleExportClick"
            >
              导出
            </el-button>
            <el-tooltip class="icon-item" effect="dark" :content="showSearch ? '隐藏搜索' : '显示搜索'" placement="top">
              <el-button size="mini" circle icon="el-icon-search" @click="toggleSearch()" />
            </el-tooltip>
          </template>

          <template #image="scope">
            <yi-image
              :src="scope.row.avatar"
              :image-style="{width: '40px' ,height:'40px'}"
              :preview-src-list="[scope.row.avatar]"
            />

          </template>
          <template #extHandler="scope">
            <el-button
              size="small"
              type="text"
              @click="handleAuthRoleClick(scope.row)"
            >授权角色
            </el-button>

          </template>
        </yi-pro-table>
      </el-col>

    </el-row>
    <user-modal-form
      ref="userModalForm"
      :req-function="reqFunc"
      @submitSuccess="refreshTable"
    />
    <user-auth-modal-form ref="userAuthModalForm" @submitSuccess="refreshTable" />

  </div>
</template>

<script>
import { contentTableConfig } from './config/content.config'
import { searchFormConfig } from './config/search.config'

import { getUserRoleIds } from '@/api/role'
import UserAuthModalForm from '@/views/system/user/UserAuthModalForm'
import UserModalForm from '@/views/system/user/UserModalForm'
import yiImage from '@/components/image'

import { addObj, putObj, getPageListData, delObj, exportUser } from '@/api/user'
import { listOrganization } from '@/api/organization'
import { remoteFileDownload } from '@/utils/fileUtil'

import { addDateRange } from '@/utils'

export default {
  name: 'List',
  components: {

    UserAuthModalForm, UserModalForm, yiImage
  },
  data() {
    return {
      contentTableConfig: contentTableConfig,
      searchFormConfig: searchFormConfig,
      showSearch: true,
      organizationId: null,
      tableRequest: getPageListData,
      // 部门树相关
      filterText: '',
      treeData: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      // 编辑的请求函数
      reqFunc: {
        create: addObj,
        update: putObj
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  created() {
    listOrganization().then(res => {
      this.treeData = res.data
    })
  },
  methods: {
    toggleSearch() {
      this.showSearch = !this.showSearch
    },
    // 组织相关
    // 过滤节点
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    // 单击节点事件
    handleNodeClick(data) {
      console.log('点击', data)
      this.organizationId = data.id
      const localSearchForm = this.$refs.pageContentRef.getSearchForm()
      const queryData = { ...localSearchForm, organizationId: this.getOrganizationId() }
      this.$refs.pageContentRef.searchTable(queryData)
    },
    // 授权身份点击
    handleAuthRoleClick(item) {
      getUserRoleIds(item.userId).then(res => {
        const info = { ...item, roleCodes: res.data.roleCodes }
        this.$refs.userAuthModalForm.update({ title: '授权角色', item: info })
      })
    },
    // 表格相关
    beforeSearchTable(formData, isResetPage) {
      const dateRange = formData.createdTime
      addDateRange(formData, dateRange, 'CreatedTime')
      delete formData.createdTime
    },
    showNewModal(item) {
      this.$refs.userModalForm.add({ title: '新增用户' })
    },
    showEditModal(item) {
      this.$refs.userModalForm.update({ title: '编辑用户', item: { ...item }})
    },
    handleDelClick(item) {
      delObj(item.id).then(res => {
        this.$refs.pageContentRef.refreshTable(false)
      })
    },

    handleExportClick() {
      const selectionRows = this.$refs.pageContentRef.getSelectionRows()
      if (!selectionRows || selectionRows.length <= 0) {
        this.$message({
          message: '导出数据不能为空',
          type: 'error'
        })
        return
      }
      const selectRowKeys = selectionRows.map(e => e.userId)
      exportUser(selectRowKeys)
        .then(res => {
          remoteFileDownload(res)
        })
    },
    refreshTable() {
      this.$refs.pageContentRef.refreshTable()
    },
    // 搜索框相关
    handleSearchClick(formData) {
      const data = { ...formData, organizationId: this.getOrganizationId() }
      this.$refs.pageContentRef.searchTable(data)
    },
    handleResetClick() {
      const data = { organizationId: this.getOrganizationId() }
      this.$refs.pageContentRef.searchTable(data, false)
    },
    getOrganizationId() {
      return this.organizationId === 0 ? null : this.organizationId
    }

  }
}
</script>

<style lang="scss" scoped>
  .headerRight {
    .item {
      margin-right: 5px;
    }

    .icon-item {
      margin-right: 10px;
    }
  }

</style>
