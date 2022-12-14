import storage from '@/utils/storage'

export function registerProps(Vue) {
  Vue.prototype.$storage=storage
}
