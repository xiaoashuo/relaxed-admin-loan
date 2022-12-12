import EnumPlugin from '@/global/plugins/EnumPlugin'
import EnumJson from '@/constants/enum-index'

export function registerPlugin(Vue) {
  Vue.use(EnumPlugin, EnumJson)
}
