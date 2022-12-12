<template>

  <el-menu :default-active="activePath"
           mode="horizontal"
           @select="handleSelect"

          >
    <template v-for="(item, index) in topMenus">
      <el-menu-item :style="{'--theme': theme}"
                    :index="item.path" :key="index"
                    v-if="index < visibleNumber"
      ><svg-icon :icon-class="item.icon" />
        {{ item.title }}</el-menu-item
      >
    </template>
    <!-- 顶部菜单超出数量折叠 -->
    <el-submenu :style="{'--theme': theme}" index="more" v-if="topMenus.length > visibleNumber">
      <template slot="title">更多菜单</template>
      <template v-for="(item, index) in topMenus">
        <el-menu-item
          :index="item.path"
          :key="index"
          v-if="index >= visibleNumber"
        ><svg-icon :icon-class="item.icon" />
          {{ item.title }}</el-menu-item
        >
      </template>
    </el-submenu>
  </el-menu>

</template>

<script>
  import { isExternal } from '@/utils/validate'
    export default {
        name: "topNav",
        data(){
          return{
            //顶栏初始数
            visibleNumber: 5,
            activePath:this.getDefaultActive(),
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
      // watch:{
      //   activePath(val){
      //     console.log("当前监听到值",val)
      //     this.activeRoutes(val)
      //   },
      // },
      beforeMount() {
        window.addEventListener('resize', this.setVisibleNumber)
      },
      beforeDestroy() {
        window.removeEventListener('resize', this.setVisibleNumber)
      },
      mounted() {
          console.log("被挂在了")
        this.setVisibleNumber();
        this.activePath=this.getDefaultActive()
        this.activeRoutes(this.activePath)
      },

      methods: {
        getDefaultActive(){

          const path = this.$route.path;
          console.log("当前激活路径",this.$route,path,path.lastIndexOf("/"))
          let activePath = path;
          if (path !== undefined && path.lastIndexOf("/") > 0) {
            //system/user
            const tmpPath = path.substring(1, path.length);
            //提取父级激活菜单 system
            activePath =  tmpPath.substring(0, tmpPath.indexOf("/"));
            //this.$store.dispatch('app/toggleSideBarHide', false);
          } else if(!this.$route.children) {
            activePath=this.$route.matched[0].path===""?"/":this.$route.matched[0].path
            // this.$store.dispatch('app/toggleSideBarHide', true);
          }
          return activePath;
        }  ,
        // 根据宽度计算设置显示栏数
        setVisibleNumber() {
          const width = document.body.getBoundingClientRect().width / 3;
          this.visibleNumber = parseInt(width / 85);
        },
        handleSelect(key, keyPath) {
         //1.根据当前点击的key 查询对应的item
          console.log("当前点击",key,keyPath)
          const currentRoute=this.$store.getters.allRouters.find(item=>{
            if (key==="/"){
              return  item.path===key
            }
            return item.path=="/"+key
          })
          console.log("--",key, keyPath,currentRoute);

          if (currentRoute.children){
            this.activePath=key
             //当前路由存在孩子
            const sonRoute=currentRoute.children[0]
            const targetType=sonRoute.targetType
            const sonPath=sonRoute.path
            if (targetType==3){
              //外部链接
              const externalUrl=  sonRoute.children[0].path
              const activeMenu=  sonRoute.path.substring(1,sonRoute.path)

              // this.$router.replace(sonPath)

              //激活当前路由
             // window.open(externalUrl, "_blank");

            }else if (targetType==2){
              this.$router.push({ path: sonPath });
            }else if (targetType==1){
              this.$router.push({ path: sonPath });
            }

          }
          this.activeRoutes(this.activePath)

        },
        // 当前激活的路由
        activeRoutes(key) {
          console.log("当前激活路由",key)
          //一级菜单key 计算二级菜单
          const currentRoutes=this.$store.getters.allRouters.find(item=>{
            if (key==="/"){
              return  item.path===key
            }
            return item.path=="/"+key
          })
          console.log("当前激活一级菜单",key,"二级菜单",currentRoutes)
         const routes = currentRoutes?.children??[]
          if(routes.length > 0) {
            this.$store.commit("router/SET_SIDEBAR_ROUTES", routes);
          }
        },

      }

    }
</script>

<style lang="scss" scoped>
  .topmenu-container.el-menu--horizontal > .el-menu-item {
    float: left;
    height: 50px !important;
    line-height: 50px !important;
    color: #999093 !important;
    padding: 0 5px !important;
    margin: 0 10px !important;
  }

  .topmenu-container.el-menu--horizontal > .el-menu-item.is-active, .el-menu--horizontal > .el-submenu.is-active .el-submenu__title {
    border-bottom: 2px solid #{'var(--theme)'} !important;
    color: #303133;
  }

  /* submenu item */

  .topmenu-container.el-menu--horizontal >  ::v-deep .el-submenu .el-submenu__title {
    float: left;
    height: 50px !important;
    line-height: 50px !important;
    color: #999093 !important;
    padding: 0 5px !important;
    margin: 0 10px !important;
  }

</style>
