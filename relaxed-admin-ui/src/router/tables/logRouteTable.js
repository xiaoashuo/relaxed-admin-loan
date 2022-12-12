export default [
  {
    path: '/log/list/access',
    component: () => import('@/views/system/log/access/access-page')
  },
  {
    path: '/log/list/operate',
    component: () => import('@/views/system/log/option/operate-page')
  },
  {
    path: '/log/list/login',
    component: () => import('@/views/system/log/login/login-page')
  }
]
