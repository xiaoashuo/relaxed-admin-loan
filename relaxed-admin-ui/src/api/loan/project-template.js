import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/project-template/page',
    params: query
  })
}
export function getDetail(projectTemplateId) {
  return yiRequest.get({
    url: '/loan/project-template/detail',
    params: {
      projectTemplateId
    }
  })
}
export function modifySealConfig(payload) {
  return yiRequest.post({
    url: '/loan/project-template/signConfig',
    data: payload
  })
}

export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/project-template',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/project-template/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/project-template',
    data: obj
  })
}
