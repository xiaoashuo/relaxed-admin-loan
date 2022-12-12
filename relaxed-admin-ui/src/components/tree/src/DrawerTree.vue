<template>
  <div class="drawer-tree">
    <el-drawer
      :title="title"
      :visible.sync="visible"
      :direction="direction"
      :wrapper-closable="false"
    >

      <el-tree
        ref="elTreeRef"
        :props="treeProps"
        :data="data"

        :node-key="nodeKey"
        default-expand-all
        show-checkbox
        @check="handleCheckChange"
      />

      <slot>
        <div class="handler">
          <el-button type="primary" @click="confirm">确定</el-button>
          <el-button @click="cancel">取消</el-button>
        </div>
      </slot>

    </el-drawer>
  </div>

</template>

<script>
export default {
  name: 'DrawerTree',
  props: {
    direction: {
      type: String,
      default: 'rtl'
    },
    title: {
      type: String,
      default: ''
    },
    treeProps: {
      type: Object,
      default: () => ({ children: 'children', label: 'title' })
    },
    nodeKey: {
      type: String,
      default: 'id'
    },
    data: {
      type: Array,
      default: () => ({})
    }

  },
  data() {
    return {
      // 是否显示
      visible: false,
      // 选中数据
      select: [],
      // 附件数据
      others: {}
    }
  },
  methods: {
    getTreeRef() {
      return this.$refs.elTreeRef
    },
    show(payload) {
      console.log('接收到', payload)
      this.select = payload.select
      this.others = payload.others
      this.visible = true
      this.$nextTick(() => {
        const arr = []
        this.select.forEach(id => {
          if (!this.hasChildren(id)) {
            arr.push(id)
          }
        })
        this.getTreeRef().setCheckedKeys(arr, false)
      })
    },
    hasChildren(id) {
      return this.getTreeRef().getNode(id).childNodes && this.getTreeRef().getNode(id).childNodes.length
    },
    cancel() {
      console.log('触发取消')
      this.others = {}
      this.select = []
      this.visible = false
    },
    handleCheckChange(data1, data2) {
      const checkedKeys = data2.checkedKeys
      const halfCheckedKeys = data2.halfCheckedKeys
      const menuList = [...checkedKeys, ...halfCheckedKeys]
      console.log('树控件列表', menuList)
      this.select = menuList
    },
    confirm() {
      this.$emit('confirm', this.others, this.select)
    }

  }

}
</script>

<style lang="scss" scoped>

  .handler{
    display: flex;
    justify-content: flex-end;
    margin-right: 10px;
  }
</style>
