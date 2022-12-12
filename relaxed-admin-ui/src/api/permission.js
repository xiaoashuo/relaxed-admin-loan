
import yiRequest from "@/utils/request";
export function getMenuRouter() {
  return yiRequest.get({
    url: '/system/router'
  })
}
export function getMenuGrantList() {
  return yiRequest.get({
    url: '/system/menu/grant-list'
  })
}

export function getMenuByRole(roleCode) {
  return yiRequest.get({
    url: '/system/role/permission/code/'+roleCode,
    showLoading: true
  })
}

export function postMenusByRole(roleCode,menuIds) {
  return yiRequest.put({
    url: '/system/role/permission/code/'+roleCode,
    data: menuIds
  })
}
