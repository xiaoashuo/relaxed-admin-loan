import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/bank-code/page',
    params: query
  })
}
export function getSelectData() {
  return yiRequest.get({
    url: '/loan/bank-code/select',
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/bank-code',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/bank-code/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/bank-code',
    data: obj
  })
}
