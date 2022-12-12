import yiRequest from "@/utils/request";
export function getPageList(queryInfo) {
  return yiRequest.get({
    url: '/system/role/page',
    params: queryInfo
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/system/role',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/system/role/' + id,
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/system/role',
    data: obj
  })
}

/**
 * 解除身份绑定用户
 * @param roleId
 * @returns {Promise | Promise<unknown>}
 */
export function unbindUser(userId,roleCode) {
  return yiRequest.delete({
    url: '/system/role/user',
    params:{
      userId,
      roleCode
    }
  })
}
/**
 * 根据角色id 获取角色信息
 * @param roleId
 * @returns {Promise | Promise<unknown>}
 */
export function getRoleInfo(roleId) {
  return yiRequest.get({
    url: '/system/role',
    params:{
      roleId
    }
  })
}

/**
 * 获取所有角色 下拉数据
 * @returns {Promise<unknown>}
 */
export function getRoleSelectData() {
  return yiRequest.get({
    url: '/system/role/select',
  })
}
/**
 * 根据用户id 获取角色信息
 * @param userId
 * @returns {Promise | Promise<unknown>}
 */
export function getUserRoleIds(userId) {
   return yiRequest.get({
      url: '/system/user/scope/'+userId
   })
}
/**
 * 根据用户id 获取角色信息
 * @param userId
 * @returns {Promise | Promise<unknown>}
 */
export function putUserRoleIds(userId,payload) {
  return yiRequest.put({
    url: '/system/user/scope/'+userId,
    data: payload
  })
}
