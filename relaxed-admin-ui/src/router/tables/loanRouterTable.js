export default [
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/order',
    //指定组件存放路径
    component: () => import('@/views/loan/order/OrderPage.vue'),
  },
]
