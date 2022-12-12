import yiRequest from "@/utils/request";
/**
 * 获取配置分页信息
 * @param queryInfo
 * @returns {Promise | Promise<unknown>}
 */
export function getPageListData(queryInfo) {
  return yiRequest.get({
    url: '/system/config/page',
    params: queryInfo
  })
}

/**
 * 添加配置信息
 * @param payload
 * @returns {Promise | Promise<unknown>}
 */
export function addObj(payload) {
  return yiRequest.post({
    url: '/system/config',
    data:payload,
  })
}

/**
 * 编辑配置信息
 * @param payload
 * @returns {Promise | Promise<unknown>}
 */
export function putObj(payload) {
  return yiRequest.put({
    url: '/system/config',
    data:payload,
  })
}

/**
 * 删除配置信息
 * @param id
 * @returns {Promise | Promise<unknown>}
 */
export function delObj(id) {
  return yiRequest.delete({
    url: '/system/config/' + id,
  })
}

