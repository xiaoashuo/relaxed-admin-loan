import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/partner/page',
    params: query
  })
}
export function getSelectData() {
  return yiRequest.get({
    url: '/loan/partner/select',
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/partner',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/partner/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/partner',
    data: obj
  })
}
