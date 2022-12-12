import Vue from 'vue'
import { getDictData, invalidDictHash } from '@/api/dict'
import cache from '@/utils/cache'
// 字典项hash列表的Key
const DICT_HASH_KEY = 'dict-hashes'
const DICT_DATA_KEY_PREFIX = 'dict-data:'
const state = {
  //字典数据缓存
  dictDataCache: {},
  // 控制请求，防止同页面渲染重复请求
  dictRequestFlag: {}
}

const getters = {
  dictDataCache(state) {
    return (dictKey) => {
      return state.dictDataCache[dictKey]
    }
  }
}
const mutations = {
  DICT_SET_CACHE: (state, item) => {
    state.dictDataCache[item.dictCode] = item
  },
  DICT_DEL_CACHE: (state, item) => {
    Vue.delete(state.dictDataCache, item.dictCode)
  },
  DICT_SET_REQUEST_FLAG(state, dictCode) {
    state.dictRequestFlag[dictCode] = true
  },
  DICT_DEL_REQUEST_FLAG(state, dictCode) {
    Vue.delete(state.dictRequestFlag, dictCode)
  }
}
function convertValueType(value, valueType) {
  let res = value
  // 如果没有type， 按number 处理
  valueType = valueType || 1
  if (valueType === 1) {
    res = Number(value) // 数字
  } else if (valueType === 2) {
    res = String(value) // 字符串
  } else if (valueType === 3) {
    // 布尔
    // 字符串 ”false“ 也会被转换为 true，所以要额外判断下
    // 参看 https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Boolean
    let b = Boolean(value)
    res = b && value.toLowerCase() === 'false' ? false : b
  }
  return res
}
const actions = {
  //获取字典数据 并填充进缓存
  async fillDictCache({ commit, state }, dictCodes = []) {
    //判断缓存是否存在
    try {
      //1.获取所有本地缓存 不在的数据
      const noDataList = dictCodes.filter((dictCode) => {
        if (!dictCode || state.dictDataCache[dictCode]) {
          return
        }

        let dictData = cache.getCache(DICT_DATA_KEY_PREFIX + dictCode)
        if (dictData) {
          //存在字典数据 放入VUEX
          commit('DICT_SET_CACHE', dictData)
        } else {
          return dictCode
        }
      })
      //获取上下文请求标记 若存在 则不在重复请求
      const requestFlagKey = noDataList.join(',')
      if (noDataList.length === 0 || state.dictRequestFlag[requestFlagKey]) {
        return
      }
      commit('DICT_SET_REQUEST_FLAG', requestFlagKey)
      //字典code
      const { data: dictResData = [] } = await getDictData(noDataList)
      dictResData.forEach((dictData) => {
        const dictCode = dictData.dictCode
        const hashCode = dictData.hashCode
        const dictItems = dictData.dictItems
        // 服务端返回的都是 String 类型，需转换为真实类型
        dictItems.forEach((item) => {
          item.value = convertValueType(item.value, dictData.valueType)
        })
        //存入本地缓存
        commit('DICT_SET_CACHE', dictData)
        cache.setCache(DICT_DATA_KEY_PREFIX + dictCode, dictData)
        let hashes = cache.getCache(DICT_HASH_KEY)
        let map = hashes ?? {}
        map[dictCode] = hashCode
        cache.setCache(DICT_HASH_KEY, map)
      })
      commit('DICT_DEL_REQUEST_FLAG', requestFlagKey)
    } catch (e) {
      console.log(e)
    }
  },
  async checkDictStatus({ commit, state }) {
    const map = cache.getCache(DICT_HASH_KEY)
    // 如果有属性，则去后台查询是否过期
    if (map && Object.getOwnPropertyNames(map).length > 0) {
      const { data = [] } = await invalidDictHash(map)
      for (let dictCode of data) {
        cache.deleteCache(DICT_DATA_KEY_PREFIX + dictCode)
        delete map[dictCode]
        commit('DICT_DEL_CACHE', dictCode)
      }
      // 更新删除数据后的Hash表
      cache.setCache(DICT_HASH_KEY, map)
    }
  }
}
export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions
}
