const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  size: state => state.app.size,
  token: state => state.auth.token,
  settings: state=>state.settings,
  avatar: state => state.auth.userInfo.avatar,
  name: state => state.auth.userInfo.username,
  userInfo: state=>state.auth.userInfo,
  permissions: state=>state.auth.permissions,
  roleCodes: state=>state.auth.roles,
  asyncRouters: state=>state.router.addRoutes,
  allRouters: state=>state.router.routes,
  sidebarRouters: state=>state.router.sideBarRoutes,
  topNavRouters: state=>state.router.topNavRoutes,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  errorLogs: state => state.errorLog.logs,


}
export default getters
