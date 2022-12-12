export default [
  {
    path: '/nested/one',
    component: () => import('@/views/system/nested/Nested')
  },
  {
    path: '/nested/one/two',
    component: () => import('@/views/system/nested/NestedChildren')
  },
  {
    path: '/nested/one/three',
    component: () => import('@/views/system/nested/NestedThree')
  },
  {
    path: '/nested/dir/index',
    component: () => import('@/views/system/nested/NestedDirList')
  }
]
