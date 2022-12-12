
import yiRequest from "@/utils/request";
export function getSelectData(url,queryInfo) {
  return yiRequest.get({
    url: url,
    params: queryInfo
  })
}
