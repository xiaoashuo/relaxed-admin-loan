export default [
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/list',
    //指定组件存放路径
    component: () => import('@/views/loan/loan/LoanPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/order',
    //指定组件存放路径
    component: () => import('@/views/loan/order/OrderPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/trade/repayment',
    //指定组件存放路径
    component: () => import('@/views/loan/trade/TradeRepaymentPage.vue'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/trade/loan',
    //指定组件存放路径
    component: () => import('@/views/loan/trade/TradeLoanPage.vue'),
  },
]
