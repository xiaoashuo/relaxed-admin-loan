/**
 * 本地路由表
 * 映射路径与组件关系
 */
const routeFiles = require.context('./tables', true, /\.js$/)

const localRouteTable = []
routeFiles.keys().forEach((key) => {
  const routeTable = require('./tables' + key.split('.')[1])
  Array.prototype.push.apply(localRouteTable, routeTable.default)
})
export default localRouteTable
