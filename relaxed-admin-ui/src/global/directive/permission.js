import {usePermission} from "@/utils/usePermission";

function checkPermission(el, binding) {
  const { value } = binding
  const hasPermission=usePermission(value)
  if (!hasPermission) {
    el.parentNode && el.parentNode.removeChild(el)
  }
}


export default {
  inserted(el,binding){
    checkPermission(el, binding)
  },
  update(el,binding){
    checkPermission(el, binding)
  }
}
