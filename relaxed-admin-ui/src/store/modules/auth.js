import { login, logout } from '@/api/auth'
import {TOKEN_KEY,USER_INFO_KEY,PERMISSION_KEY,ROLE_KEY} from "@/constants/UserConstant";
import { removeToken } from '@/utils/auth'
import CACHE from '@/utils/cache'
import { resetRouter } from '@/router'
import {putObj, uploadAvatar} from "@/api/user";
import {passEncrypt} from "@/utils/password"
import store from "@/store";

const getDefaultState = () => {
  return {
    token: CACHE.getCache(TOKEN_KEY)??'',
    userInfo: CACHE.getCache(USER_INFO_KEY)??{},
    permissions:CACHE.getCache(PERMISSION_KEY)??[],
    roles: CACHE.getCache(ROLE_KEY)??[]
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_USER_INFO: (state, userInfo) => {
    state.userInfo = userInfo
  },
  SET_PERMISSIONS: (state,permissions)=>{
    state.permissions=permissions
  },
  SET_ROLES: (state,roles)=>{
    state.roles=roles
  },

}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: passEncrypt(password) }).then(response => {
        const { access_token, expires_in,info: userInfo ,attributes:{permissions,roleCodes}} = response
        const ttl = expires_in * 1000
      //  console.log('-------',attributes, permissions,roleCodes)
        commit('SET_TOKEN', access_token)
        commit('SET_USER_INFO',userInfo)
        commit('SET_PERMISSIONS',permissions)
        commit('SET_ROLES',roleCodes)
        CACHE.setCache(TOKEN_KEY,access_token)
        CACHE.setCache(USER_INFO_KEY,userInfo)
        CACHE.setCache(PERMISSION_KEY,permissions)
        CACHE.setCache(ROLE_KEY,roleCodes)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },



  // user logout
  logout({dispatch, commit, state },reload=true) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        resolve()
      }).catch(error => {
        resolve()
      }).finally(()=>{
        // //延迟刷新一次 重置store中路由为空,以便下次路由获取更新
        dispatch('resetToken').then(()=>{
          console.log("是否",reload)
          if (reload){
            // //延迟刷新一次 重置store中路由为空,以便下次路由获取更新
            setTimeout(() => {
              window.location.reload()
            }, 10)
          }
        })
      })
    })
  },

  // remove token
  resetToken({ dispatch,commit }) {
    return new Promise(resolve => {
      // //重置store中路由为空,以便下次路由获取
      //清空授权vuex
      commit('RESET_STATE')
      // dispatch('router/resetRoutes',null,{root:true})
      // dispatch('tagsView/delAllViews', null, { root: true })
      //清空本地缓存
      CACHE.deleteCache(TOKEN_KEY)
      CACHE.deleteCache(USER_INFO_KEY)
      CACHE.deleteCache(PERMISSION_KEY)
      CACHE.deleteCache(ROLE_KEY)
      //重置路由
      resetRouter()
      resolve()
    })
  },


  updateUserInfo({commit},payload){
    return new Promise(resolve => {
      putObj(payload).then(res=>{
        commit('SET_USER_INFO',payload)
        CACHE.setCache(USER_INFO_KEY,payload)
        resolve(res)
      })
    })
  },

  updateUserAvatar({commit,state},payload){
    return new Promise(resolve=>{
      uploadAvatar(payload).then(response => {
        const avatar= response.data.url;
        state.userInfo.avatar=avatar
        CACHE.setCache(USER_INFO_KEY,state.userInfo)
        resolve(avatar)
      });
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

