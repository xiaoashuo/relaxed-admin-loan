import yiRequest from "@/utils/request";
export function getLoginLogPageList(queryInfo) {
  return yiRequest.get({
    url: '/log/login/page',
    params: queryInfo
  })
}

export function getAccessLogPageList(queryInfo) {
  return yiRequest.get({
    url: '/log/access/page',
    params: queryInfo
  })
}

export function getOperatorLogPageList(queryInfo) {
  return yiRequest.get({
    url: '/log/access/page',
    params: queryInfo
  })
}
