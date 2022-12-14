import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/template/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/template',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/template/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/template',
    data: obj
  })
}