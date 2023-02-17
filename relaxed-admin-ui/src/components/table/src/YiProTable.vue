<template>
  <yi-table ref="proTableRef"
            v-if="!destroyTable"
            :class="{'table-fullscreen': localFullScreen}"
            :loading="loading" :list-data="listData"
            :list-count="listCount"
            v-bind="contentTableConfig" :page="pageInfo" @update:page="changePage"
            @selection-change="handleSelectionChange"
            @row-click="handleRowClick"
            @row-dblclick="handleRowDbClick"
            @sort-change="handleSortChange"
            @expand-change="handleExpandChange"


  >
    <template #header>

      <slot   name="header">
        <div class="headerLeft" v-if="showTitle">
          <slot name="headerLeft"  >
            <span class="title">{{contentTableConfig.title}}</span>
          </slot>
        </div>
        <div class="headerRight" v-if="showTools">

          <slot name="userTools" v-if="showUserTools">
            <el-button type="primary" v-if="hasPermission('create')" plain icon="el-icon-plus"
                       size="small" @click="handleNewClick" style="margin-right: 12px">
              新建
            </el-button>
            <slot name="extUserTools"></slot>
          </slot>
          <el-row class="rightTools">
            <el-tooltip class="item" effect="dark" content="全屏" placement="top">
              <el-button size="mini" circle :icon="localFullScreen?'el-icon-aim':'el-icon-full-screen'" @click="handleFullScreen" />
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="刷新" placement="top">
              <el-button size="mini" circle icon="el-icon-refresh" @click="refresh()" />
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="显隐列" placement="top" v-if="transferPropList">
              <el-button size="mini" circle icon="el-icon-menu" @click="showColumn()" />
            </el-tooltip>
          </el-row>
          <el-dialog
            :title="title"
            :visible.sync="open"
            append-to-body
          >

            <el-transfer v-model="value" :props="{key:'prop',lable:'label'}" :titles="['显示','隐藏']" :data="transferPropList" @change="handleDataChange"></el-transfer>


          </el-dialog>
        </div>

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
import YiTable from "./YiTable.vue"
import screenfullMixin from "@/mixins/screenfullMixin";
import {ProTableMixin} from "@/mixins";

export default {
  name: "YiProTable",
  components:{
    YiTable,

  },

  mixins:[ProTableMixin,screenfullMixin],
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
    transferPropList(){
      let filter = this.contentTableConfig?.propList.filter(item=>item.prop)
      return filter
    }
  },
  data(){
    return{
      // 显隐数据
      value: [],
      // 弹出层标题
      title: "显示/隐藏",
      // 是否显示弹出层
      open: false,
    }
  },

  methods:{
    // 右侧列表元素变化
    handleDataChange(data) {
      this.transferPropList.forEach(column=>{
        let result = data.some(ele => ele === column.prop)
        this.$set(column,'hidden',result)
      })
    },
    // 刷新
    refresh() {
      this.refreshTable(false)
    },
    // 打开显隐列dialog
    showColumn() {
      this.open = true;
    },
    getScreenfullDomRef(){
      return this.$refs.proTableRef.$el
    },

    /**
     * 处理新建按钮
     */
    handleNewClick() {
      this.$emit('addBtnClick')
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
.header{
  .title {
    font-size: 20px;
    font-weight: 700;
  }
  .headerRight{
    display: flex;
    justify-content: flex-end;
    align-items: center
  }
}
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
