import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/risk/model/page',
    params: query
  })
}
export function getByModelId(modelId) {
  return yiRequest.get({
    url: '/risk/model/'+modelId,
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/risk/model',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/risk/model/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/risk/model',
    data: obj
  })
}
