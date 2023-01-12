import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/page',
    params: query
  })
}
export function getDetail(query) {
  return yiRequest.get({
    url: '/loan/detail',
    params: query
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/loan',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan',
    data: obj
  })
}
