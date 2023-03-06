import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/risk/rule/page',
    params: query
  })
}
export function getRuleFieldList(modelId) {
  return yiRequest.get({
    url: '/risk/rule/fields/'+modelId,
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/risk/rule',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/risk/rule/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/risk/rule',
    data: obj
  })
}
