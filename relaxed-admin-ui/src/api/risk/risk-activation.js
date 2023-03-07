import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/risk/activation/page',
    params: query
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/risk/activation',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/risk/activation/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/risk/activation',
    data: obj
  })
}


export function switchActivationStatus(obj) {
  return yiRequest.put({
    url: '/risk/activation/switch/status',
    params: obj
  })
}
