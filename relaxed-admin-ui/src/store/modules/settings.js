import variables from '@/styles/element-variables.scss'
import Cache from '@/utils/cache'
import defaultSettings from '@/settings'

const { sideTheme,showSettings,menuLayout,tagsView,watermark, fixedHeader, sidebarLogo } = defaultSettings
const storageSetting = Cache.getCache('layout-setting') || ''
const state = {
  theme: storageSetting.theme||variables.theme,
  sideTheme: storageSetting.sideTheme || sideTheme,
  showSettings: showSettings,
  fixedHeader: storageSetting.fixedHeader||fixedHeader,
  menuLayout: storageSetting.menuLayout||menuLayout,
  tagsView: storageSetting.tagsView||tagsView,
  watermark: storageSetting.watermark??watermark,
  sidebarLogo: storageSetting.sidebarLogo||sidebarLogo
}

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    // eslint-disable-next-line no-prototype-builtins
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  }
}

const actions = {
  changeSetting({ commit }, data) {
    commit('CHANGE_SETTING', data)
    let layoutSettings=Cache.getCache("layout-setting")??{}
    layoutSettings[data.key]=data.value
    Cache.setCache('layout-setting',layoutSettings)
  },
  removeLocalSettings(){
    Cache.deleteCache('layout-setting')
  }

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

