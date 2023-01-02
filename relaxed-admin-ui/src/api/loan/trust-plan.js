import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/trust-plan/page',
    params: query
  })
}
export function getSelectData() {
  return yiRequest.get({
    url: '/loan/trust-plan/select',
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/trust-plan',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/trust-plan/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/trust-plan',
    data: obj
  })
}
