import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/project/page',
    params: query
  })
}
export function getSelectData() {
  return yiRequest.get({
    url: '/loan/project/select',
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/project',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/project/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/project',
    data: obj
  })
}
