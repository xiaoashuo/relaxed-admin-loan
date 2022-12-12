import * as filters from './filters' // global filters
export function registerFilter(Vue) {
  // register global utility filters
  Object.keys(filters).forEach(key => {
    Vue.filter(key, filters[key])
  })
}
