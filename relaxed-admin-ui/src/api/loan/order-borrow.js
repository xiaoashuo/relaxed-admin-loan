import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/order-borrow/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/order-borrow',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/order-borrow/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/order-borrow',
    data: obj
  })
}