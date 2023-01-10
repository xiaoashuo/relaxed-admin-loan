import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/audit-detail/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/audit-detail',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/audit-detail/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/audit-detail',
    data: obj
  })
}