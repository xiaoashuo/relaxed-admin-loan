import yiRequest from "@/utils/request";

export function deleteFile(payload) {
  return yiRequest.post({
    url: '/common/file/delete',
    data: payload
  })
}
