import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/risk/field/page',
    params: query
  })
}

/**
 * 字段列表
 * @param modelId
 * @returns {Promise | Promise<unknown>}
 */
export function getFieldList(modelId) {
  return yiRequest.get({
    url: '/risk/field/list/'+modelId,
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/risk/field',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/risk/field/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/risk/field',
    data: obj
  })
}
