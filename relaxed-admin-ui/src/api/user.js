import yiRequest from "@/utils/request";
export function updateUserPwd(oldPassword,newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return yiRequest.patch({
    url: '/system/user/change/password',
    params:data,
  })
}

/**
 * 获取用户分页信息
 * @param queryInfo
 * @returns {Promise | Promise<unknown>}
 */
export function getPageListData(queryInfo) {
  return yiRequest.post({
    url: '/system/user/page',
    params: queryInfo,
  })
}
/**
 * 获取个人相关信息
 * @returns {Promise | Promise<unknown>}
 */
export function profile() {
  return yiRequest.get({
    url: '/system/user/profile',
  })
}
export function uploadAvatar(payload) {
  return yiRequest.post({
    url: '/system/user/avatar',
    data:payload,
  })
}
export function addObj(payload) {
  return yiRequest.post({
    url: '/system/user',
    data:payload,
  })
}

export function putObj(payload) {
  return yiRequest.put({
    url: '/system/user',
    data:payload,
  })
}
export function pageByRole(payload) {
  return yiRequest.get({
    url:'/system/user/page/related/role',
    params: payload
  })
}

export function delObj(id) {
  return   yiRequest.delete({
    url: '/system/user/'+id,
    method:'delete'
  })
}

export function exportUser(payload) {
  return yiRequest.post({
    url: '/system/user/export',
    data:payload,
    showLoading: true,
    responseType: 'blob',
  })
}
