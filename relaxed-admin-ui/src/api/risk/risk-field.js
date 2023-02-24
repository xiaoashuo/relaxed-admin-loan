import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/risk/field/list',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/risk/field',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/risk/field/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/risk/field',
    data: obj
  })
}
