import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/-province/page',
    params: query
  })
}
export function listByParentCode(parentCode) {
  return yiRequest.get({
    url: '/loan/province/parentCode',
    params: { parentCode }
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/-province',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/-province/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/-province',
    data: obj
  })
}
