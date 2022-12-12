/**
 * 记录表格基础属性 不涉及数据存储
 */
export default {
  props:{
    contentTableConfig: {
      type: Object,
      required: true
    },
    // 延迟加载，created时不主动加载数据
    lazyLoad:{
      type: Boolean,
      default: false
    },
    //执行真正查询前,对数据做预处理
    beforeSearchTable:{
      type:Function,
      default: (formData={},isResetPage=true)=>{
        //默认什么也不做
      }
    }

  },
  data(){
    return{
      //分页参数
      pageInfo: {
        current: 1,
        size: 10
      },
      //查询参数
      searchForm:{},


    }
  },
  created(){
    if (!this.lazyLoad){
      this.searchTable()
    }
  },
  methods:{

    /**
     * 改变分页信息
     */
    changePage(pageInfo){
      this.pageInfo={...pageInfo}
      this.getPageData(this.searchForm,this.pageInfo)

    },

    /**
     * 查询数据
     * @param formData 搜索条件
     * @param isResetPage 是否重置分页器 不包括 pageSize
     */
    searchTable(formData={},isResetPage=true){
      this.beforeSearchTable(formData,isResetPage)
      this.searchForm={...formData}
      if (isResetPage){
        this.pageInfo={current: 1, size: this.pageInfo.size}
      }
      this.getPageData(this.searchForm,this.pageInfo)
    },
    /**
     * 获取当前查询参数
     * @returns {{}}
     */
    getSearchForm(){
      return this.searchForm
    },
    /**
     * 刷新表格
     */
    refreshTable(isResetPage=true){
      this.searchTable(this.searchForm,isResetPage)
    },
    /**
     * 重置表格 分页器 pageSize 不变
     */
    resetTable() {
      //pageInfo 回归初始态
      this.searchForm={}
      this.pageInfo = {current: 1, size: this.pageInfo.size}
      this.getPageData(this.searchForm,this.pageInfo)
    },
    /**
     * 加载分页数据
     * @param queryInfo
     * @param pageInfo
     */
    getPageData(queryInfo = {},pageInfo={}) {

    }

  }

}
