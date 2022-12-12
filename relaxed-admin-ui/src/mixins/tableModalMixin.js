import tableMixin from "@/mixins/tableMixin";

export default {
  mixins:[tableMixin],
  methods:{
    /**
     * 处理新建按钮
     */
    handleCreateClick() {
      this.$emit('newBtnClick')
    },
    /**
     * 处理编辑按钮
     * @param item
     */
    handleUpdateClick(item) {
      this.$emit('editBtnClick', item)
    },
    /**
     * 处理删除按钮
     * @param item
     */
    handleDeleteClick(item) {
      this.$emit('delBtnClick', item)
    },
    /**
     * 处理单元行被双击事件
     * @param item
     */
    handleRowDblClick(item){
      this.$emit('row-dblclick', item)
    }
  }
}
