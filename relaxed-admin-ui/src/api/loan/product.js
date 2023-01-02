import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/product/page',
    params: query
  })
}
export function getSelectData() {
  return yiRequest.get({
    url: '/loan/product/select',
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/product',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/product/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/product',
    data: obj
  })
}
