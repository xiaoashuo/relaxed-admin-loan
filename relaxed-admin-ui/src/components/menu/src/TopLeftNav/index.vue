<template>

  <el-menu :default-active="activePath"
           mode="horizontal"
           @select="handleSelect"
          >
    <el-menu-item  v-for="(item, index) in topMenus"
                   :style="{'--theme': theme}"
                   v-if="(index+1) < visibleNumber"
                   :index="item.path" :key="index"
    >
      <item v-if="item.meta" :icon="item.meta && item.meta.icon" :title="item.meta.title" />
      {{ item.title}}
    </el-menu-item>

    <!-- 顶部菜单超出数量折叠 -->
    <el-submenu :style="{'--theme': theme}" index="more" v-if="topMenus.length > visibleNumber">
      <template slot="title">更多菜单</template>
      <el-menu-item  v-for="(item, index) in topMenus"
                     v-if="(index+1) >= visibleNumber"
                     :index="item.path" :key="index"
      >
        <item v-if="item.meta" :icon="item.meta && item.meta.icon" :title="item.meta.title" />


        {{ item.title }}
      </el-menu-item>
<!--      <template v-for="(item, index) in topMenus">-->
<!--        <el-menu-item-->
<!--          :index="item.path"-->
<!--          :key="index"-->
<!--          v-if="index >= visibleNumber"-->
<!--        ><svg-icon :icon-class="item.icon" />-->
<!--          {{ item.title }}</el-menu-item-->
<!--        >-->
<!--      </template>-->
    </el-submenu>
  </el-menu>

</template>

<script>
  import Item from "@/layout/components/Sidebar/Item";
  import { isExternal } from '@/utils/validate'
  import MenuMixin from "@/components/menu/src/mixins/MenuMixin";
    export default {
        name: "topNav",
        components:{
          Item
        },
        mixins:[MenuMixin],
        data(){
          return{
            //顶栏初始数
            visibleNumber: 5,


          }
        },


      computed:{
        theme() {
          return this.$store.state.settings.theme;
        },
        //顶部显示菜单
        topMenus(){
          let topMenus=[]
          this.routers.map((menu) => {
            if (menu.hidden !== 1) {
              // 兼容顶部栏一级菜单内部跳转
              topMenus.push(menu);
            }
          });
          return topMenus

        },
        // 所有的路由信息
        routers() {
          return this.$store.state.router.topNavRoutes;
        },



      },

      beforeMount() {
        window.addEventListener('resize', this.setVisibleNumber)
      },
      beforeDestroy() {
        window.removeEventListener('resize', this.setVisibleNumber)
      },
      mounted() {
        this.setVisibleNumber();
      },

      methods: {

        // 根据宽度计算设置显示栏数
        setVisibleNumber() {
          const width = document.body.getBoundingClientRect().width / 3;
          this.visibleNumber = parseInt(width / 85);
        },



      }

    }
</script>

<style lang="scss" scoped>
  .topleftmenu-container.el-menu--horizontal > .el-menu-item {
    float: left;
    height: 50px !important;
    line-height: 50px !important;
    color: #999093 !important;
    padding: 0 5px !important;
    margin: 0 10px !important;
  }

  .topleftmenu-container.el-menu--horizontal > .el-menu-item.is-active, .el-menu--horizontal > .el-submenu.is-active .el-submenu__title {
    border-bottom: 2px solid #{'var(--theme)'} !important;
    color: #303133;
  }

  /* submenu item */

  .topleftmenu-container.el-menu--horizontal >  ::v-deep .el-submenu .el-submenu__title {
    float: left;
    height: 50px !important;
    line-height: 50px !important;
    color: #999093 !important;
    padding: 0 5px !important;
    margin: 0 10px !important;
  }

</style>
