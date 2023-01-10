import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/bill/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/bill',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/bill/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/bill',
    data: obj
  })
}