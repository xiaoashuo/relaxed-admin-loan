
export const userContentTableConfig = {
  title: '用户列表',
  showSelectColumn: false,
  showHeader: false,
  headerProps: {
    visible: false
  },
  tableProps: {
    'row-style': {
      height: '10px'
    },
    'cell-style': {
      padding: '2px 0'
    }
  },
  propList: [
    {
      prop: 'userId',
      label: '用户id',
      minWidth: 100
    },
    {
      prop: 'username',
      label: '姓名',
      minWidth: 100
    },
    {
      prop: 'nickname',
      label: '昵称',
      minWidth: 150
    },
    {
      label: '操作',
      minWidth: '120',
      slotName: 'customHandler'
    }
  ]
}
