export default [
  {
    path:'/dashboard',
    component: () => import('@/views/dashboard/index'),
  },
  //用户路由表
  {
    path:'/system/user',
    component: () => import('@/views/system/user/list'),
  },
  {
    path:'/system/user/profile',
    component: () => import('@/views/system/profile/index'),
  },
  //角色路由表
  {
    path:'/system/role',
    component: () => import('@/views/system/role/index'),
  },
  {
    path:'/system/role/detail',
    component: () => import('@/views/system/role/detail'),
  },
  //组织路由表
  {
    path:'/system/organization',
    component: () => import('@/views/system/organization/index'),
  },
  //菜单路由表
  {
    path:'/system/menu',
    component: () => import('@/views/system/menu/index'),
  },
  //字典路由表
  {
    path:'/system/dict',
    component: () => import('@/views/system/dict/dict'),
  },
 //配置路由表
  {
    path:'/system/config',
    component: () => import('@/views/system/config/index'),
  },

]
