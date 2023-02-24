export default [
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/risk/model',
    //指定组件存放路径
    component: () => import('@/views/risk/model/RiskModelPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/risk/model/config',
    //指定组件存放路径
    component: () => import('@/views/risk/detail/RiskModelDetail.vue'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/risk/risk-field',
    //指定组件存放路径
    component: () => import('@/views/risk/detail/RiskFieldPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/risk/risk-pre-item',
    //指定组件存放路径
    component: () => import('@/views/risk/detail/RiskPreItemPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/risk/risk-activation',
    //指定组件存放路径
    component: () => import('@/views/risk/detail/RiskActivationPage'),
  }
]
