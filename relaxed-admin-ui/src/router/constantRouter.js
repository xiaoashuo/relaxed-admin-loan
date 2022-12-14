/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect/:path*',
    component: () => import(/* webpackChunkName: "redirect" */ '@/views/redirect/index')
  },
  {
    path: '/user',
    component: () => import('@/layout/UserLayout'),
    hidden: 1,
    children:[
      {
        path: 'login',
        name: 'login',
        component: () => import('@/views/login/index'),
      },
    ]
  },

  // 锁屏
  {
    path: '/lock',
    hidden: true,
    name: '锁屏页',
    component: () => import('@/views/lock')
  },
  // {
  //   path: '/login',
  //   component: () => import('@/views/login/index'),
  //   hidden: 1
  // },
  {
    path: '/pdf',
    component: () => import('@/components/pdf/pdf'),
    hidden: 1
  },
  {
    path: '/404',
    component: () => import('@/views/error/404'),
    hidden: 1
  },
  // // 404 page must be placed at the end !!!
  // { path: '*', redirect: '/404', hidden: true }
]
