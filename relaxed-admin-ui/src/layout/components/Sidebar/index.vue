<template>
  <div  class="sidebar-container">
<!--    放置左侧+左侧增强导航-->
    <template v-if="menuLayout==menuEnum.MENU_LAYOUT.LEFT_PLUS_NAV.value">
      <left-plus-nav></left-plus-nav>
    </template>
    <template v-if="menuLayout==menuEnum.MENU_LAYOUT.LEFT_NAV.value||menuLayout==menuEnum.MENU_LAYOUT.TOP_LEFT_NAV.value">
      <left-nav></left-nav>
    </template>
  </div>




</template>

<script>
  import LeftNav from "@/components/menu/src/Left/LeftNav";
  import LeftPlusNav from "@/components/menu/src/LeftPlus/LeftPlusNav";
  import MenuEnum from "@/constants/enums/MenuEnum";



export default {

  components:{
    LeftNav,LeftPlusNav
  },
  data(){
    return {
      menuEnum:MenuEnum
    }
  },
  computed: {

    menuLayout:{
      get() {
        return this.$store.state.settings.menuLayout
      }
    },
  },

}
</script>
<style lang="scss" scoped>



</style>
