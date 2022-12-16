<template>
  <yi-table ref="proTableRef"
            v-if="!destroyTable"
            :class="{'table-fullscreen': localFullScreen}"
            :loading="loading" :list-data="listData"
            :list-count="listCount"
            v-bind="contentTableConfig" :page="pageInfo" @update:page="changePage"
            @selection-change="handleSelectionChange"
            @row-click="handleRowClick"
           @sort-change="handleSortChange"


  >
    <template #headerRight >
        <slot name="headerRight">
          <el-button type="primary" v-if="hasPermission('create')" plain icon="el-icon-plus"
                     size="small" @click="handleNewClick" style="margin-right: 12px">
            新建
          </el-button>
          <slot name="extOption"></slot>
          <div style="margin-right: 8px">
            <el-tooltip class="item" effect="dark" content="全屏" placement="top">
              <el-button size="mini" circle :icon="localFullScreen?'el-icon-aim':'el-icon-full-screen'" @click="handleFullScreen" />
            </el-tooltip>
          </div>

          <right-toolbar :columns="contentTableConfig.propList"  @searchTable="refreshTable(false)"></right-toolbar>
        </slot>
    </template>
    <template #status="scope">
      <dict-tag v-model="scope.row.status"   :type="scope.row.status?'success':'danger'" dict-code="status" ></dict-tag>
    </template>
    <template #createdTime="scope">
      <span>{{scope.row.createdTime | formatUtcString}}</span>
    </template>
    <template #updatedTime="scope">
      <span>{{scope.row.updatedTime | formatUtcString}}</span>
    </template>
    <template #handler="scope">
      <div  class="handleBtns">

        <el-button size="mini" type="text" v-if="hasPermission('update')" icon="el-icon-edit"
                   @click="handleEditClick(scope.row)">编辑
        </el-button>
        <el-popconfirm title="确定要删除吗?" v-if="hasPermission('delete')" @confirm="()=>handleDeleteClick(scope.row)">
          <el-button slot="reference" size="mini"  icon="el-icon-delete" type="text">删除
          </el-button>
        </el-popconfirm>

        <slot name="extHandler" :row="scope.row"></slot>
      </div>
    </template>
    <!--      插槽传递-->
    <template v-for="item in otherPropSlots" v-slot:[item.slotName]="scope">
      <template v-if="item.slotName">
        <slot :name="item.slotName" :row="scope.row"></slot>
      </template>
    </template>
  </yi-table>


</template>

<script>
  import YiTable from "./table"
  import RightToolbar from "@/components/table/src/RightToolbar";

  import {ProTableMixin} from "@/mixins";
  import {usePermission} from "@/utils/usePermission";

    export default {
        name: "ProTable",
        components:{
          YiTable,
          RightToolbar,
        },
        mixins:[ProTableMixin],
        computed:{
          otherPropSlots() {
            const otherPropSlots = this.contentTableConfig?.propList.filter((item) => {
              if (item.slotName === "status") return false
              if (item.slotName === "createdTime") return false
              if (item.slotName === "updatedTime") return false
              if (item.slotName === "handler") return false
              return true
            })
            return otherPropSlots;
          },

        },


      methods:{

        getScreenfullDomRef(){
          return this.$refs.proTableRef.$el
        },

        /**
         * 处理新建按钮
         */
        handleNewClick() {
          this.$emit('newBtnClick')
        },
        /**
         * 处理编辑按钮
         * @param item
         */
        handleEditClick(item) {
          this.$emit('editBtnClick', item)
        },
        /**
         * 处理删除按钮
         * @param item
         */
        handleDeleteClick(item) {
          this.$emit('delBtnClick', item)
        },


        handleSelectionChange(value) {
          this.selectedRows=value
          this.$emit("selectionChange",value)
        },
        /**
         * 清空所有选中行
         * 清空完成 会发送数据改变事件
         */
        clearAllSectionRows(){
          this.$refs.proTableRef.clearAllSelectionRows()
        }
      }

    }
</script>

<style lang="scss" scoped>
 .handleBtns{
   /*white-space: nowrap;*/
   /*display: inline-block;*/

   .el-button{
     margin-right: 5px;
   }
 }

 .table-fullscreen {
   position: fixed;
   top: 0;
   left: 0;
   width: 100vw;
   height: 100vh;
   overflow: auto;
   padding: 10px 24px;

   box-sizing: border-box;
   /*z-index: 9999;*/
   background: white;
   ::v-deep{
     .footer{
       height: 40px;
     }
     .el-table{
       width: 100vw;
       height: calc(100vh - 65px - 59px);
     }
   }
 }
</style>
