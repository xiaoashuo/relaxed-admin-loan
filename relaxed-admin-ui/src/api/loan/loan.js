import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/loan/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/loan',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/loan/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/loan',
    data: obj
  })
}