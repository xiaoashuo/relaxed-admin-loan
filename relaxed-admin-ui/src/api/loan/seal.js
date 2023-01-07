import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/seal/page',
    params: query
  })
}
export function getListData() {
  return yiRequest.get({
    url: '/loan/seal/list',
  })
}
export function previewTemplatePdf(templateId) {
  return yiRequest.post({
    url: '/loan/seal/preview/template',
    params: {templateId:templateId},
    'Content-Type':'application/json',
    responseType: 'blob'
  })
}
export function previewPdf(payload) {
  return yiRequest.post({
    url: '/loan/seal/preview',
    data: payload,
    'Content-Type':'application/json',
    responseType: 'blob'
  })
}
export function getSelectData() {
  return yiRequest.get({
    url: '/loan/seal/select',
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/seal',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/seal/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/seal',
    data: obj
  })
}
