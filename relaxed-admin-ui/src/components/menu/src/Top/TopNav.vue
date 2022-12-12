<template>
  <div  class="topNav"  :style="{
    height: '100%',

    backgroundColor: settings.sideTheme === 'theme-dark' ? variables.menuBackground : variables.menuLightBackground }">
    <logo :collapse="false" v-if="showLogo"/>
    <el-scrollbar :class="settings.sideTheme" wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        id="el-menu"

        :background-color="settings.sideTheme === 'theme-dark' ? variables.menuBackground : variables.menuLightBackground"
        :text-color="settings.sideTheme === 'theme-dark' ? variables.menuColor : variables.menuLightColor"
        :unique-opened="true"
        :active-text-color="settings.theme"
        :collapse-transition="false"
        mode="horizontal"
      >
        <top-side-bar-item v-for="route in sidebarRouters" :key="route.path" :item="route" :base-path="route.path" />
      </el-menu>
    </el-scrollbar>
<!--    <el-row >-->
<!--      <el-col  v-if="showLogo" :span="6">-->
<!--        <logo :collapse="false" />-->
<!--      </el-col>-->
<!--      <el-col :span="showLogo?18:24">-->
<!--       -->
<!--      </el-col>-->
<!--    </el-row>-->


  </div>
</template>

<script>

  import TopSideBarItem from "./TopSideBarItem";
  import Logo from "@/layout/components/Sidebar/Logo";
  import {mapGetters} from "vuex";
  import variables from "@/styles/variables.scss";

  export default {
    name:'TopNav',
    components: { TopSideBarItem, Logo},
    computed: {
      ...mapGetters([
        'sidebar','allRouters','sidebarRouters','settings'
      ]),

      activeMenu() {
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
  .topNav{

    display: flex;
    left: 0px !important;
    .sidebar-logo-container{
      width: 180px;
    }
    ::v-deep{

      .el-scrollbar theme-dark{
        flex: 1;
      }
      .el-scrollbar__wrap{
        overflow: hidden;
      }
      .scrollbar-wrapper{
        width: 100%;
        white-space: nowrap;
        position: relative;
        overflow: hidden;
        .el-scrollbar__wrap {
          // height: 100%;
          overflow-y: hidden;
          overflow-x: auto;
          margin: 0 !important;
        }
        .is-vertical{
          display: none;
        }
      }
      .el-scrollbar{
        flex: 1;
        .is-vertical{
          display: none;
        }
      }
      .el-menu--horizontal {
        display: flex;
      }
      .el-menu-item ,.el-submenu__title{
        height: 53px;
        line-height: 53px;
      }


    }
  }
</style>
