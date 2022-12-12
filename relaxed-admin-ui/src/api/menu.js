import yiRequest from "@/utils/request";
import {listToTree} from "@/utils/treeUtil";
/**
 * 获取配置分页信息
 * @param queryInfo
 * @returns {Promise | Promise<unknown>}
 */
export function getPageListData(queryInfo) {
  return yiRequest.get({
    url: '/system/menu/page',
    params: queryInfo
  })
}
/**
 * 获取授权菜单列表
 * @returns {Promise | Promise<unknown>}
 */
export function getGrantList() {
  return yiRequest.get({
    url: '/system/menu/grant-list',
    // interceptors:{
    //   responseInterceptor:(res)=>{
    //     const root=[{
    //       id:0,
    //       title:'根目录',
    //       children:[]
    //     }]
    //     root[0].children=res.data
    //     res.data=root
    //     return res
    //   }
    // }
  })
}

/**
 * 存储菜单
 * @param payload
 * @returns {Promise | Promise<unknown>}
 */
export function saveMenu(payload) {
  return yiRequest.post({
    url: '/system/menu',
    data: payload
  })
}

/**
 * 更新菜单
 * @param payload
 * @returns {Promise | Promise<unknown>}
 */
export function updateMenu(payload) {
  return yiRequest.put({
    url: '/system/menu',
    data: payload
  })
}

/**
 * 删除菜单
 * @param 菜单id
 * @returns {Promise | Promise<unknown>}
 */
export function delMenu(menuId) {
  return yiRequest.delete({
    url: '/system/menu/'+menuId,
  })
}
