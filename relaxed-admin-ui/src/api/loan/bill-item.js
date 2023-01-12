import yiRequest from "@/utils/request";

export function getPage(query) {
  return yiRequest.get({
    url: '/loan/bill-item/page',
    params: query
  })
}
export function getList(query) {
  return yiRequest.get({
    url: '/loan/bill-item/list',
    params: query,
    interceptors:{
      responseInterceptor:(res)=>{
        const data=res.data
        delete res.data
        res.data={records: data}
        return res
      }
    }
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/loan/bill-item',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/loan/bill-item/' + id
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/loan/bill-item',
    data: obj
  })
}
