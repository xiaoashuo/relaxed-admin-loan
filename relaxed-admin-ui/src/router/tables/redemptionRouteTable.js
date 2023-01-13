export default [
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/list',
    //指定组件存放路径
    component: () => import('@/views/loan/loan/LoanPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/order-annex',
    //指定组件存放路径
    component: () => import('@/views/loan/order-annex/OrderAnnexPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/bill-item-filler',
    //指定组件存放路径
    component: () => import('@/views/loan/bill-item-filler/BillItemFillerPage'),
  },

  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/order-bank-card',
    //指定组件存放路径
    component: () => import('@/views/loan/order-bank-card/OrderBankCardPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/order-borrow',
    //指定组件存放路径
    component: () => import('@/views/loan/order-borrow/OrderBorrowPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/order-customer',
    //指定组件存放路径
    component: () => import('@/views/loan/order-customer/OrderCustomerPage'),
  },



  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/bill-item',
    //指定组件存放路径
    component: () => import('@/views/loan/bill-item/BillItemPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/bill',
    //指定组件存放路径
    component: () => import('@/views/loan/bill/BillPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/order-collateral',
    //指定组件存放路径
    component: () => import('@/views/loan/order-collateral/OrderCollateralPage'),
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
