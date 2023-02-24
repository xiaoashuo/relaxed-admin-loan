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
    url: '/risk/risk-pre-item',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/risk/risk-pre-item/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/risk/risk-pre-item',
    data: obj
  })
}
