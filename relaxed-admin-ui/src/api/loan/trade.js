import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/trade/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/trade',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/trade/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/trade',
    data: obj
  })
}