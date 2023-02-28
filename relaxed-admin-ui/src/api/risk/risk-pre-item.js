import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/risk/pre/item/page',
    params: query
  })
}
export function getPluginList() {
  return yiRequest.get({
    url: '/risk/plugin/list/enabled',
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/risk/pre',
    data: obj
  })
}

export function delObj(modelId,id) {
  return yiRequest.delete({
    url: `/risk/pre/${modelId}/${id}`
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/risk/pre',
    data: obj
  })
}
