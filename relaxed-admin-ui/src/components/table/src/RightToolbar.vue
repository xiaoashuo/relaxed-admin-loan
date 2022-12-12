<template>
  <div class="top-right-btn">
    <el-row>


      <el-tooltip class="item" effect="dark" content="刷新" placement="top">
        <el-button size="mini" circle icon="el-icon-refresh" @click="refresh()" />
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="显隐列" placement="top" v-if="columns">
        <el-button size="mini" circle icon="el-icon-menu" @click="showColumn()" />
      </el-tooltip>
    </el-row>
    <el-dialog
      :title="title"
      :visible.sync="open"
      append-to-body
    >

      <el-transfer v-model="value" :props="{key:'prop',lable:'label'}" :titles="['显示','隐藏']" :data="columns" @change="handleDataChange"></el-transfer>


    </el-dialog>
  </div>
</template>

<script>

    export default {
        name: "RightTools",
      components:{

      },
        props:{
          columns: {
            type: Array,
          },
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
          this.columns.forEach(column=>{
            let result = data.some(ele => ele === column.prop)
            this.$set(column,'hidden',result)
          })
        },
        // 刷新
        refresh() {
          this.$emit("searchTable");
        },
        // 打开显隐列dialog
        showColumn() {
          this.open = true;
        },
      }
    }
</script>

<style scoped>

</style>
