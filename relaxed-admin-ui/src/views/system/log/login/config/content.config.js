export const contentTableConfig = {
  title: '登录日志',
  showSelectColumn: false,

  headerProps: {
    visible: true,
    leftVisible: true,
    rightVisible: false
  },

  propList: [
    {
      prop: 'traceId',
      label: '追踪id',
      minWidth: 180
    },
    {
      prop: 'username',
      label: '用户名',
      minWidth: 180
    },
    {
      prop: 'ip',
      label: 'ip',
      minWidth: 180
    },
    {
      prop: 'os',
      label: '操作系统',
      minWidth: 180
    },
    {
      prop: 'status',
      label: '状态码',
      minWidth: 180,
      slotName: 'extStatus'
    },
    {
      prop: 'eventType',
      label: '事件类型',
      minWidth: 180
    },
    {
      prop: 'msg',
      label: '操作信息',
      minWidth: 180
    },
    {
      prop: 'location',
      label: '登陆地点',
      minWidth: 180
    },
    {
      prop: 'browser',
      label: '浏览器',
      minWidth: 180
    },
    {
      prop: 'createdTime',
      label: '创建时间',
      minWidth: '250',
      slotName: 'createdTime'
    }
  ]
}
