export default [
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loanConfig/certificate',
    //指定组件存放路径
    component: () => import('@/views/loan/certificate/CertificatePage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loanConfig/partner',
    //指定组件存放路径
    component: () => import('@/views/loan/partner/PartnerPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loanConfig/product',
    //指定组件存放路径
    component: () => import('@/views/loan/product/ProductPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loanConfig/project',
    //指定组件存放路径
    component: () => import('@/views/loan/project/ProjectPage'),
  },

  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loanConfig/project-template',
    //指定组件存放路径
    component: () => import('@/views/loan/project/ProjectTemplatePage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loanConfig/seal',
    //指定组件存放路径
    component: () => import('@/views/loan/seal/SealPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loanConfig/template',
    //指定组件存放路径
    component: () => import('@/views/loan/template/TemplatePage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loanConfig/trust-plan',
    //指定组件存放路径
    component: () => import('@/views/loan/trust-plan/TrustPlanPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loanConfig/bank-code',
    //指定组件存放路径
    component: () => import('@/views/loan/bank-code/BankCodePage'),
  }
]
