import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/certificate/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/certificate',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/certificate/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/certificate',
    data: obj
  })
}