import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/template-area/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/template-area',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/template-area/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/template-area',
    data: obj
  })
}