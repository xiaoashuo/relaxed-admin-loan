
import Layout from "@/layout/index";
import ParentView from "@/layout/components/ParentView/ParentView";
import {getMenuRouter} from "@/api/permission";
import {firstUpperCase} from "@/utils/strUtil";

import LocalRouteTable from './localTables'


//前端未找到页面路由
const NotFoundRouter=  { path: '*', redirect: '/404', hidden: true }


/**
 * 生成动态路由
 */
export const generateDynamicRouter=()=>{
  return new Promise((resolve, reject) => {
    getMenuRouter().then(res=>{
      //后端响应数据
      const {data}=res
      //转换成菜单树
      const routers=generator(data)
      routers.push(NotFoundRouter)
      resolve(routers)
    })
      .catch(err=>{
        reject(err)
      })
  })
}

export const generator = (routerMap, parent) => {
  return routerMap.map(item=>{
    const targetType = item.targetType
    const path = (`${parent && parent.path || ''}/${item.path}`).replace('//','/')

    // 路由名称，由路由地址生成，大驼峰形式
    const name = path.replace('-', '/')
      .split('/')
      .filter(x => x && x !== '')
      .map(x => firstUpperCase(x))
      .join('')
    //0 目录 1菜单 2按钮
    const routeType=  item.type;
    const currentRouter={
      path: path,
      name: name,
      hidden:item.hidden,
      alwaysShow: item.alwaysShow,
      meta: { title: item.title, icon: item.icon,activeMenu:item.activeMenu,targetType: targetType },
    }
    if (routeType===0){
      //目录
      currentRouter.component=parent? ParentView:Layout;
    }else if (routeType===1){
      //菜单
      if (targetType===1){
        let cppn = LocalRouteTable.find(source=>source.path===path);

        if (cppn){
          currentRouter.component = cppn.component
        }else{
          console.error("路由地址:",path,"组件注册失败,原因:本地组件未注册")
        }


      }else if (targetType===2){
          //iframe
        currentRouter.meta.url = item.uri
        currentRouter.component = () => import('@/views/iframe/index')
      }else if (targetType===3){
       // currentRouter.meta.targetType=item.targetType
        currentRouter.children=[{
          path: item.uri,
          meta: { title: item.title, icon: item.icon ,activeMenu:item.activeMenu},
        }
        ]
      }

    }
    if (item.children && item.children.length>0){

      let children=generator(item.children,currentRouter)
      const notFoundPath = `${currentRouter && currentRouter.path || ''}/*`
      children.push({
        path: notFoundPath,
        name: notFoundPath,
        hidden: true,
        redirect: '/404'
      })
      currentRouter.children = children
      if (item.uri&&item.uri.length>0){
        currentRouter.redirect=item.uri;
      }else{
        fillRedirect(currentRouter)
      }
    }
    return currentRouter

  })
}

/**
 * 设置当前路由的默认跳转地址为其子路由的path
 * @param currentRouter
 */
function fillRedirect (currentRouter) {
  const children = currentRouter.children
//  const redirectRouter = children.find(x => !x.hidden)
  let firstChild = children[0];
  if (firstChild.meta.targetType==3){
    //若默认跳转地址为外链,则拒绝跳转 不做任何操作.ps 此处暂时只作为面包屑跳转使用
    currentRouter.meta.redirectType=firstChild.meta.targetType
  }
  const redirectRouter = firstChild
  redirectRouter && (currentRouter.redirect = redirectRouter.path)
}


