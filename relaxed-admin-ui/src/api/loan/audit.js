import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/audit/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/audit',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/audit/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/audit',
    data: obj
  })
}