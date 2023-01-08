import tableMixin from "@/mixins/tableMixin";
import screenfullMixin from "@/mixins/screenfullMixin";
import { usePermission } from '@/utils/usePermission'

export default {
  mixins: [tableMixin, screenfullMixin],
  props: {
    //  多列排序
    multiSort: {
      type: Boolean,
      default: false
    },
    request: {
      type: Function,
      default: (queryInfo={},pageInfo={})=>{

      }
    },
    responseHandler: {
      type: Function,
      default: (data) => {
        return data
      }
    } ,
  },
  data(){
    return{
      loading: false,
      listData:[],
      listCount:0,
      selectedRows:[],
      sortFields: this.getDefaultSort(),
      destroyTable:false

    }
  },
  created() {
    if (this.multiSort){
      this.contentTableConfig.tableProps['header-cell-class-name']=this.handleCellHeaderStyle
    }
  },
  methods:{
    getDefaultSort(){
      const defaultSortField = this.contentTableConfig?.tableProps?.["default-sort"];
      return defaultSortField?{[defaultSortField.prop]:defaultSortField.order}:{}
    },
    hasPermission(btnType){
      //未指定权限 则不进行权限判断
      if (!this.contentTableConfig?.permissions){
        return true
      }
      const permission= this.contentTableConfig?.permissions[btnType]
      //未指定单位 按钮类型权限 则不进行权限判断
      if (!permission){
        return  true
      }
      const hasPermission= usePermission(permission)
      return hasPermission
    },
     /** 展开/折叠操作 */
    toggleExpandAll(){
      this.destroyTable=true
       this.contentTableConfig.tableProps['default-expand-all']=! this.contentTableConfig?.tableProps['default-expand-all']
      this.$nextTick(() => {
        this.destroyTable = false;
      });

     },

    /**
     * 头单元格样式重写
     * @param row
     * @param column
     * @param rowIndex
     * @param columnIndex
     */
    handleCellHeaderStyle({row, column, rowIndex, columnIndex}){
      if (!column.sortable){
        return
      }
      const headerProp= column.property
      const currentValue=this.sortFields[headerProp]
      return currentValue
    },
    handleRowClick(row,column,event){
      this.$emit('row-click',row,column,event)
    },
    handleRowDbClick(row,column,event){
      this.$emit('row-dblclick',row,column,event)
    },

      /**
       * 处理排序字段改变
       * @param column
       * @param prop
       * @param order
       */
      handleSortChange({column, prop, order}){
         if (!this.multiSort){
            this.sortFields={}
         }
         if (!order){
           delete this.sortFields[prop]
         }else{
           if (this.multiSort){
             const localOrder= this.sortFields[prop]
             if (order===localOrder){
               order=order==="ascending"?"descending":"ascending"
               column.order=order
             }
           }
           this.sortFields[prop]=order
         }


          this.refreshTable(false)
      },
      /**
       * 转换sort值
       * @param order ascending,descending null
       * @returns {string}
       */
      convertToSortValue(order){
          return "ascending"=== order?"asc":'desc'
      },
    /**
     * 获取选中行数组
     * @returns {[]}
     */
    getSelectionRows(){
      return this.selectedRows
    },
    /**
     * 加载分页数据
     * @param queryInfo
     * @param pageInfo
     */
    getPageData(queryInfo = {},pageInfo={}) {
        this.loading = true
        const targetSortField={}
        Object.keys(this.sortFields).forEach(key=>{
            targetSortField[key]=this.convertToSortValue(this.sortFields[key])
        })
         const reqInfo={...queryInfo,...pageInfo,sort: targetSortField}
         this.request(reqInfo).then(res=>{
          const data=res.data
          const {records,total}=this.responseHandler(data)
          this.listData=records
          this.listCount=total
        }).finally(()=>{
          this.loading = false
        })
    }

  }
}
