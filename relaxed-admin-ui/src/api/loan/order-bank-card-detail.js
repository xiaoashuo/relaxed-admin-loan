import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/order-bank-card-detail/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/order-bank-card-detail',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/order-bank-card-detail/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/order-bank-card-detail',
    data: obj
  })
}