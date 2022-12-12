import cache from "@/utils/cache";
const state = {

}

const mutations = {

}

const actions = {
  checkDictHash({commit,dispatch,rootGetters}){
    if (cache.getCache('userInfo')){
      dispatch('dict/checkDictStatus',{},{root: true})
    }

  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
