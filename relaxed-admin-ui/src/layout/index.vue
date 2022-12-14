<template>
  <div :class="classObj" class="app-wrapper">

    <div v-if="device==='mobile'&&sidebar.opened" class="drawer-bg" @click="handleClickOutside" />
    <sidebar class="sidebar-container" :class="sidebarContainerClass" />

    <div :class="mainContainerClass" class="main-container " >
      <div :class="{'fixed-header':fixedHeader}">
        <navbar />
        <tags-view v-if="needTagsView" />
      </div>
      <app-main id="main-content" />
      <right-panel >
        <settings />
      </right-panel>
    </div>

  </div>
</template>

<script>
  import RightPanel from '@/components/RightPanel'

  import { Navbar, Sidebar, Settings,AppMain ,TagsView} from './components'
import ResizeMixin from './mixin/ResizeHandler'
import WaterMark from '@/utils/watermark'
  import MenuEnum from "@/constants/enums/MenuEnum";
import { mapState } from 'vuex'
  import {openTimeOut,closeTimeout} from '@/utils/astrict'

  export default {
  name: 'Layout',
  components: {
    RightPanel,
    Navbar,
    Settings,
    Sidebar,
    AppMain,
    TagsView
  },
  mixins: [ResizeMixin],
  data(){
    return{
      menuEnum:MenuEnum
    }
  },
  computed: {
    ...mapState({
      sidebar: state => state.app.sidebar,
      device: state => state.app.device,
      showSettings: state => state.settings.showSettings,
      needTagsView: state => state.settings.tagsView,
      fixedHeader: state => state.settings.fixedHeader
    }),
    classObj() {
      return {
        hideSidebar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation,
        mobile: this.device === 'mobile'
      }
    },
    menuLayout:{
      get() {
        return this.$store.state.settings.menuLayout
      }
    },
    mainContainerClass(){
      return {'plus-main-container': this.menuLayout==this.menuEnum.MENU_LAYOUT.LEFT_PLUS_NAV.value,
        'top-main-container':this.menuLayout==this.menuEnum.MENU_LAYOUT.TOP_NAV.value,
        hasTagsView: this.needTagsView,
      }
    },
    sidebarContainerClass(){
      return {'plus-sidebar-container': this.menuLayout==this.menuEnum.MENU_LAYOUT.LEFT_PLUS_NAV.value,'top-sidebar-container':this.menuLayout==this.menuEnum.MENU_LAYOUT.TOP_NAV.value}
    }

  },
  mounted() {
    if (this.$store.getters.settings.watermark){
      WaterMark.set('main-content',this.$store.getters.name)
    }
   // openTimeOut()

  },
    beforeMount() {
    //  closeTimeout()
    },

    methods: {
    handleClickOutside() {
      this.$store.dispatch('app/closeSideBar', { withoutAnimation: false })
    }
  }
}
</script>

<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";
  @import "~@/styles/variables.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
    &.mobile.openSidebar{
      position: fixed;
      top: 0;
    }
  }
  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$sideBarWidth});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px)
  }

  .mobile .fixed-header {
    width: 100%;
  }


  /*顶部导航样式*/
  #app .top-sidebar-container{
    width: 0px !important;
  }
  #app .top-main-container{
    margin-left: 0px ;
    .fixed-header {
      position: fixed;
      top: 0;
      right: 0;
      z-index: 9;
      width: calc(100% - 0px);
      transition: width 0.28s;
    }
  }


  /*左侧增强菜单样式*/
  #app .plus-sidebar-container{
    width: 300px !important;
  }
  #app .plus-main-container{
    margin-left: 300px ;
    .fixed-header {
      position: fixed;
      top: 0;
      right: 0;
      z-index: 9;
      width: calc(100% - 300px);
      transition: width 0.28s;
    }
  }

  #app .hideSidebar .plus-sidebar-container{
    width: 54px!important;
    ::v-deep.left-menu-container{
       width: 100%;

    }
    ::v-deep.right-menu-container{
      display: none !important;
    }
  }

  #app .hideSidebar .plus-main-container{
    margin-left: 54px!important;
     .fixed-header {
      width: calc(100% - 54px)
    }

  }
</style>
