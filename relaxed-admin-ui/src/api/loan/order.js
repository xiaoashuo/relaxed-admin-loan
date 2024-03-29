import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/order/page',
    params: query
  })
}
export function getFormDetail(orderId) {
  return yiRequest.get({
    url: '/loan/order/form/detail',
    params: { orderId:orderId }
  })
}
export function getDetail(query) {
  return yiRequest.get({
    url: '/loan/order/detail',
    params: query
  })
}
export function submitOrder(orderId) {
  return yiRequest.post({
    url: '/loan/order/submit',
    params: { orderId }
  })
}
export function loanConfirm(orderId) {
  return yiRequest.post({
    url: '/loan/order/confirm',
    params: { orderId }
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/order',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/order/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/order',
    data: obj
  })
}
