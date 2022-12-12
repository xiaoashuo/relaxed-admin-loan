<template>
  <el-row class="menu-container">
    <el-col :span="14" class="left-menu-container"
    :style="leftNavStyle"
    >
      <div >
        <el-scrollbar :class="settings.sideTheme" wrap-class="scrollbar-wrapper">

        <logo v-if="showLogo" :collapse="isCollapse"/>


                  <el-menu :default-active="activePath"
                 :text-color="settings.sideTheme === 'theme-dark' ? variables.menuColor : variables.menuLightColor"
                 :background-color="settings.sideTheme === 'theme-dark' ? variables.menuBackground : variables.menuLightBackground "
                 :collapse="isCollapse"
                 :active-text-color="settings.theme"
                id="leftMenu"
                 :collapse-transition="isCollapse"
                 mode="vertical"
                 @select="handleSelect"

        >
                    <el-menu-item  v-for="(item, index) in topMenus"

                                   :index="item.path" :key="item.path"
                    >
                      <item v-if="item.meta" :icon="item.meta && item.meta.icon" :title="item.meta.title" />


                      {{ item.title }}</el-menu-item
                    >

        </el-menu>
        </el-scrollbar>
      </div>

    </el-col>
    <el-col :span="10" class="right-menu-container"
            :style="rightNavStyle"

    >
      <div  :style="rightNavStyle">
        <el-scrollbar :class="settings.sideTheme" wrap-class="scrollbar-wrapper">
          <el-menu
            :default-active="activeChildrenMenu"
            :background-color="settings.sideTheme === 'theme-dark' ? variables.menuLightBackground : variables.menuBackground"
            id="leftPlusMenu"
            :collapse="isCollapse"
            :text-color="settings.sideTheme === 'theme-dark' ? variables.menuColor : variables.menuLightColor"
            :unique-opened="false"
            :active-text-color="settings.theme"
            :collapse-transition="false"
            mode="vertical"
          >
            <sidebar-item v-for="route in sidebarRouters" :key="route.path" :item="route" :base-path="route.path" />
          </el-menu>
        </el-scrollbar>
      </div>
    </el-col>
  </el-row>

</template>

<script>

  import SidebarItem from "@/layout/components/Sidebar/SidebarItem";
  import Logo from "@/layout/components/Sidebar/Logo";
  import Item from "@/layout/components/Sidebar/Item";
  import MenuMixin from "@/components/menu/src/mixins/MenuMixin";

  import {mapGetters} from "vuex";
  import variables from "@/styles/variables.scss";
    export default {
        name: "leftPlus",
        components:{
          SidebarItem,
          Logo,
          Item
        },
        mixins:[MenuMixin],
        data(){
          return{


          }

        },

      computed:{
        ...mapGetters([
          'sidebar','allRouters','sidebarRouters','settings'
        ]),
        theme() {
          return this.$store.state.settings.theme;
        },
        logoStyle(){

        },
        leftNavStyle(){

          return {'background-color': this.settings.sideTheme === 'theme-dark' ? variables.menuBackground
            : variables.menuLightBackground
          }
        },
        rightNavStyle(){
          return {'background-color': this.settings.sideTheme === 'theme-dark' ? variables.menuLightBackground
              : variables.menuBackground
          }
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
        activeChildrenMenu() {
          const route = this.$route
          const { meta, path } = route
          // if set path, the sidebar will highlight the path you set
          if (meta.activeMenu) {
            return meta.activeMenu
          }
          return path
        },
        showLogo() {
          return this.$store.state.settings.sidebarLogo
        },
        variables() {
          return variables
        },
        isCollapse() {
          return !this.sidebar.opened
        },
      },



    }
</script>

<style lang="scss" scoped>
.menu-container{
  height: 100%;

  .left-menu-container{
    height: 100%;

  }
  .right-menu-container{
    height: 100%;

  }
}

</style>
