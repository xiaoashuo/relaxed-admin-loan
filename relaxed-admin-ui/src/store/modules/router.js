import router, { resetRouter} from "@/router";
import {constantRoutes} from "@/router/constantRouter";
import {generateDynamicRouter} from "@/router/dynamicRouter";

const  state={
   routes:[],
   addRoutes:[],
   //顶部路由
   topNavRoutes:[],
   //侧边路由
   sideBarRoutes:[]
}

const mutations={
  SET_ROUTES:(state,routes)=>{
    //异步路由
    state.addRoutes=routes
    //所有路由
    state.routes=constantRoutes.concat(routes)

  },
  SET_TOPNAV_ROUTES:(state,routes)=>{
    state.topNavRoutes=routes
  },
  SET_SIDEBAR_ROUTES:(state,routes)=>{
    // console.log("当前路由",routes)
    state.sideBarRoutes=routes
  }
}


const actions={
   generateRoutes({commit}){
    return new Promise(async resolve => {
      const asyncRoutes=await  generateDynamicRouter()
      const topRoutes=[]
      for(let item of asyncRoutes){
        topRoutes.push(item)
      }
      commit('SET_ROUTES',asyncRoutes)
      commit('SET_TOPNAV_ROUTES',topRoutes)
      commit('SET_SIDEBAR_ROUTES',constantRoutes.concat(asyncRoutes))
      resolve(asyncRoutes)
    })
   },
  //重新生成路由
   refreshRoutes({dispatch,commit}){
     return new Promise(async resolve => {
      const asyncRoutes= await dispatch('generateRoutes')
       //重置路由
       resetRouter()
       //重新添加异步路由
       router.addRoutes(asyncRoutes)
       resolve(asyncRoutes)
     })


  },

  /**重置路由*/
   resetRoutes({commit}){
     commit('SET_ROUTES',[])
     commit('SET_TOPNAV_ROUTES',[])
     commit('SET_SIDEBAR_ROUTES',[])
   }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
