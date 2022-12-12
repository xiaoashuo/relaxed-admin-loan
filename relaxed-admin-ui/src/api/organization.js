import yiRequest from "@/utils/request";
import {listToTree} from "@/utils/treeUtil";

export function getOrganizationTree(payload) {
  return yiRequest.get({
    url: '/system/organization/tree',
    params:payload,
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

export function listOrganization() {
  return yiRequest.get({
    url: '/system/organization/list',
    interceptors:{
      responseInterceptor:(res)=>{
        const root=[{
          id:0,
          name:'组织机构',
        }]
        root[0].children=listToTree(res.data,0)
        res.data=root
        return res
      }
    }
  })
}
export function addObj(obj) {
  return yiRequest.post({
    url: '/system/organization',
    data: obj
  })
}

export function delObj(id) {
  return yiRequest.delete({
    url: '/system/organization/' + id,
  })
}

export function putObj(obj) {
  return yiRequest.put({
    url: '/system/organization',
    data: obj
  })
}
