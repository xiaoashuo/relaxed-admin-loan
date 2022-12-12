import permission from './directive/permission'
export function registerDirective(Vue) {
  Vue.directive("permission",permission)
}
