export default [
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/project',
     //指定组件存放路径
    component: () => import('@/views/loan/project/ProjectPage'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/project/config',
    //指定组件存放路径
    component: () => import('@/views/loan/project/ProjectTemplateConfig'),
  },
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/loan/project-template',
    //指定组件存放路径
    component: () => import('@/views/loan/project/ProjectTemplatePage'),
  }
]
