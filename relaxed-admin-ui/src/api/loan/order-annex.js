import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/order-annex/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/order-annex',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/order-annex/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/order-annex',
    data: obj
  })
}