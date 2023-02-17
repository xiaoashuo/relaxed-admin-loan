<template>
  <div class="app-container">
    <page-search
      ref="pageSearchRef"
      :searchFormConfig="searchFormConfig"
      @resetBtnClick="handleResetClick"
      @queryBtnClick="handleSearchClick"
    ></page-search>
    <!--表格组件-->
    <yi-pro-table
      ref="pageContentRef"
      :content-table-config="contentTableConfig"
      :request="tableRequest"
      @addBtnClick="showNewModal"
      @editBtnClick="showEditModal"
      @delBtnClick="handleDelClick"
    >
      <template #extHandler="scope">
        <el-dropdown
          size="mini"
          @command="(command) => handleCommand(command, scope.row)"
        >
          <span class="el-dropdown-link">
            <i class="el-icon-d-arrow-right el-icon--right"></i>更多
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="handleAuth">授权 </el-dropdown-item>
            <el-dropdown-item command="handleDetail">详情 </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </template>
    </yi-pro-table>
    <!--模态表单组件-->
    <form-modal
      ref="formModal"
      :modal-config="modalConfig"
      :req-function="reqFunction"
      @submitSuccess="handleSubmit"
    >
    </form-modal>
    <drawer-tree
      ref="drawerTree"
      title="角色授权"
      :data="allMenus"
      @confirm="confirmAuth"
    ></drawer-tree>
  </div>
</template>

<script>
import { DrawerTree } from '@/components/tree'
import { contentTableConfig } from './config/content.config'
import { searchFormConfig } from './config/search.config'
import { modalConfig } from './config/modal.config'

import {
  getMenuByRole,
  postMenusByRole,
  getMenuGrantList
} from '@/api/permission'

import { getPageList, addObj, putObj, delObj } from '@/api/role'
export default {
  name: 'RoleList',
  components: {
    DrawerTree
  },
  created() {
    //获取抽屉数据
    this.getAllMenus()
  },
  data() {
    return {
      //所有菜单
      allMenus: [],
      //表格和查询表单使用
      contentTableConfig: contentTableConfig,
      searchFormConfig: searchFormConfig,
      modalConfig: modalConfig,
      tableRequest: getPageList,
      //表单请求
      reqFunction: {
        create: addObj,
        update: putObj
      }
    }
  },
  methods: {
    handleCommand(common, row) {
      switch (common) {
        case 'handleAuth':
          this.handleAuthClick(row)
          break
        case 'handleDetail':
          this.handleDetailClick(row)
          break
        default:
          break
      }
    },
    async getAllMenus() {
      //1.获取所有菜单树
      const { data } = await getMenuGrantList()
      this.allMenus = data
    },

    handleDetailClick(row) {
      this.$router.push({
        path: '/system/role/detail',
        query: {
          id: row.id,
          roleCode: row.code
        }
      })
    },
    //展示抽屉
    async handleAuthClick(row) {
      //2.获取当前用户的所有权限
      const { data } = await getMenuByRole(row.code)
      let payload = { select: data, others: row }
      this.$refs.drawerTree.show(payload)
    },
    //确认状态
    confirmAuth(currentRow, checkedMenus) {
      postMenusByRole(currentRow.code, checkedMenus).then((res) => {
        this.$message({
          message: '授权成功',
          type: 'success'
        })
        this.$refs.drawerTree.cancel()
      })
    },
    //表格相关
    showNewModal(item) {
      this.$refs.formModal.add({ title: '新增角色', item })
    },
    showEditModal(item) {
      this.$refs.formModal.update({ title: '编辑角色', item })
    },
    handleDelClick(item) {
      delObj(item.id).then((res) => {
        this.$refs.pageContentRef.refreshTable(false)
      })
    },
    //模态框相关
    handleSubmit(res) {
      this.$refs.pageContentRef.refreshTable(false)
    },
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
::v-deep {
  /** 表格更多操作下拉样式 */
  .el-table .el-dropdown-link {
    cursor: pointer;
    color: #409eff;
  }
  .el-dropdown-menu__item {
    font-size: 13px;
    margin: 2px 0;
  }
  .el-table .el-dropdown,
  .el-icon-arrow-down {
    font-size: 13px;
  }
  .handleBtns {
    .el-dropdown {
      padding-top: 4px;
      font-size: 12px;
    }
  }
}

.custom-drawer {
  /*.el-drawer__header{*/
  /*  margin-bottom: 10px !important;*/
  /*}*/

  .handler {
    display: flex;
    justify-content: flex-end;
    margin-right: 10px;
  }
}
</style>
