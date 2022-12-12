export default {
  data(){
    return{
      activePath: this.activeMenu()
    }
  },
  mounted() {
    this.activeMenu()
  },
  watch:{
    '$route.path':function(newVal){
      this.activeMenu()
    }
  },
  methods:{
    handleSelect(key, keyPath) {
      //1.根据当前点击的key 查询对应的item
      const currentRoute=this.$store.getters.allRouters.find(item=>{
        if (key==="/"){
          return  item.path===key
        }
        return item.path==key
      })
      if (currentRoute.children){

        //当前路由存在孩子
        const sonRoute=currentRoute.children[0]
        const targetType=sonRoute.meta.targetType
        const sonPath=sonRoute.path
        if (targetType==3){
          //外部链接
          const externalUrl=  sonRoute.children[0].path
          const activeMenu=  sonRoute.path.substring(1,sonRoute.path)

          // this.$router.replace(sonPath)

          //激活当前路由
          // window.open(externalUrl, "_blank");
          this.activeRoutes(key)
        }else if (targetType==2){
          //菜单
          this.$router.push({ path: sonPath });
          this.activePath=key
        }else if (targetType==1){
          if (this.$route.path===sonPath){
            this.activeRoutes(key)
          }
          this.$router.push({ path: sonPath });
          this.activePath=key
        }
      }



    },
    activeMenu(){
      // /system/user
      const path = this.$route.path;
      let activePath = path;
      if (path !== undefined && path.lastIndexOf("/") > 0) {
        //system/user
        const tmpPath = path.substring(1, path.length);
        //提取父级激活菜单 system
        activePath =  '/'+tmpPath.substring(0, tmpPath.indexOf("/"));
        //this.$store.dispatch('app/toggleSideBarHide', false);
      } else if(!this.$route.children) {
        activePath=this.$route.matched[0].path===""?"/":this.$route.matched[0].path
        // this.$store.dispatch('app/toggleSideBarHide', true);
      }
      this.activePath=activePath
      //一级菜单变化 联动二级菜单变化
      this.activeRoutes(activePath);
    },
    // 当前激活的路由
    activeRoutes(key) {
      //一级菜单key 计算二级菜单
      const currentRoutes=this.$store.getters.allRouters.find(item=>{
        if (key==="/"){
          return  item.path===key
        }
        return item.path==key
      })
      const routes = currentRoutes?.children??[]
      if(routes.length > 0) {
        this.$store.commit("router/SET_SIDEBAR_ROUTES", routes);
      }
      this.activeRoutesCallback(currentRoutes)
    },
    activeRoutesCallback(parentRoute){
      this.$emit('activeMenu',parentRoute)
    }
  }
}
