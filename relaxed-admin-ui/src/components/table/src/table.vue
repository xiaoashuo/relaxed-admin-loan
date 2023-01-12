<template>
  <div class="yi-table">
<!--    header-->

    <div class="header" v-if="headerProps.visible">
       <slot   name="header">
         <div class="headerLeft" v-if="headerProps.leftVisible">
           <slot name="headerLeft"  >
             <span class="title">{{title}}</span>
           </slot>
         </div>
         <div class="headerRight" v-if="headerProps.rightVisible">
           <slot name="headerRight"  >
           </slot>
         </div>

       </slot>

     </div>
<!--    content-->
    <el-table ref="elTableRef"
               :data="listData"
               v-loading="loading"

      border

      style="width: 100%" v-bind="tableProps"
      @selection-change="handleSelectionChange"
              @row-click="handleRowClick"
              @row-dblclick="handleRowDbClick"
      @sort-change="handleSortChange"
      @header-click="handleHeaderClick"
              @expand-change="handleExpandChange"
     >
      <el-table-column v-if="showSelectColumn" type="selection"  align="center" min-width="60"></el-table-column>

      <template  v-for="item in propList" >

        <el-table-column  v-if="!item.hidden"    v-bind="item" align="center" :key="item.prop" show-overflow-tooltip>
          <template #default="scope">
            <slot :name="item.slotName" :row="scope.row" >
                {{scope.row[item.prop]}}
            </slot>
          </template>
        </el-table-column>

      </template>

    </el-table>
<!--    footer-->
    <div class="footer" v-if="showFooter">
      <slot name="footer" >
        <el-pagination
          :current-page="page.current"
          :page-sizes="pageProps.pageSizes"
          :page-size="page.size"
          :layout="pageProps.layout"
          :total="listCount"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
      </slot>
    </div>
  </div>

</template>

<script>


  export default {
        name: "yi-table",
        model:{
          prop:'page',
          event:'update:page'
        },
        props:{
          title:{
            type: String,
            required: true
          },
          listData:{
            type: Array,
            required: true
          },
          listCount:{
            type: Number,
            default: 0
          },
          propList:{
            type: Array,
            required: true
          },
          tableProps:{
            type:Object,
            default: ()=>({})
          },
          pageProps:{
            type:Object,
            //不理解为啥加（）
            default: ()=>({
             pageSizes:[10, 20, 30, 40],
             layout: "total, sizes, prev, pager, next, jumper"
            })
          },
          page:{
            type:Object,
            default: ()=>({current:0,size:10})
          },

          showFooter:{
            type:Boolean,
            default: true
          },
          showSelectColumn:{
            type:Boolean,
            default: false
          },
          headerProps:{
            type: Object,
            default: ()=>({
              visible: true,
              leftVisible: true,
              rightVisible: true
            })
          },

          loading: {
            type: Boolean,
            default: true
          },

        },




      methods:{

        handleHeaderClick(column,event){
          this.$emit('header-click', column,event)
        },
        handleSortChange({column, prop, order}){
          this.$emit('sort-change', {column, prop, order})
        },
        handleExpandChange(row,expanded){
          this.$emit('expand-change', row,expanded)
        },
        handleRowDbClick(row, column, event){
         this.$emit('row-dblclick',row,column,event)
        },
        handleRowClick(row, column, event){
          this.$emit('row-click',row,column,event)
        },
        handleSizeChange(size){
          this.$emit('update:page',{...this.page,size})
        },
        handleCurrentChange(current){
          console.log(current)
          this.$emit('update:page',{...this.page,current})
        },
        handleSelectionChange(value) {
          this.$emit("selection-change",value)
        },
        /**
         * 清空所有选中行
         */
        clearAllSelectionRows(){
          this.$refs.elTableRef.clearSelection()
        }
      }
    }
</script>

<style lang="scss" scoped>
  .header {
    width: 100%;
    height: 45px;
    display: flex;
    justify-content: space-between;
    align-items: center;
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

  .footer {
    margin-top: 15px;

    .el-pagination {
      text-align: right;
    }
  }

</style>
