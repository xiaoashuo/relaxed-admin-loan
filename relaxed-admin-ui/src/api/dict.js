
import yiRequest from "@/utils/request";
export function getDictData (dictCodes) {
  return yiRequest.get({
    url: '/system/dict/data?dictCodes=' + dictCodes.join(',')
  })
}
export function invalidDictHash(map) {
  return yiRequest.post({
    url: '/system/dict/invalid-hash',
    data: map
  })
}
export function addDictObj(obj) {
  return yiRequest.post({
    url: '/system/dict',
    data: obj
  })
}
export function putDictObj(obj) {
  return yiRequest.put({
    url: '/system/dict',
    data: obj
  })
}
/**
 * 获取配置分页信息
 * @param queryInfo
 * @returns {Promise | Promise<unknown>}
 */
export function getPageListData(queryInfo) {
  return yiRequest.get({
    url: '/system/dict/page',
    params: queryInfo
  })
}

/**
 *
 * 删除字典
 * @param id
 * @returns {Promise | Promise<unknown>}
 */
export function delDictObj(id) {
  return yiRequest.delete({
    url: '/system/dict/' + id,
  })
}
/**
 * 获取字典项分页信息
 * @param queryInfo
 * @returns {Promise | Promise<unknown>}
 */
export function getDictItemPageListData(queryInfo) {
  return yiRequest.get({
    url: '/system/dict/item/page',
    params: queryInfo
  })
}
export function addDictItemObj(obj) {
  return yiRequest.post({
    url: '/system/dict/item',
    data: obj
  })
}

export function putDictItemObj(obj) {
  return yiRequest.put({
    url: '/system/dict/item',
    data: obj
  })
}
export function delDictItemObj(id) {
  return yiRequest.delete({
    url: '/system/dict/item/' + id,
  })
}
/**
 * 修改字典状态
 * @param id
 * @param status
 * @returns {Promise | Promise<unknown>}
 */
export function modifyDictStatus (id,status) {
  return yiRequest.patch({
    url: `/system/dict/item/${id}?status=${status}`
  })
}
