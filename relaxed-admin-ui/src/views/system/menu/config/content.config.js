export const contentTableConfig = {
  title: '菜单列表',
  showSelectColumn: false,
  showFooter: false,
  tableProps: {
    rowKey: 'id',
    treeProp: {
      children: 'children'
    },
    'row-style': {
      height: '10px'
    },
    'cell-style': {
      padding: 0
    }
  },
  propList: [
    {
      prop: 'id',
      label: 'ID',
      minWidth: 180,
      type: ''
    },
    {
      prop: 'title',
      label: '菜单名称',
      minWidth: 180
    },
    {
      prop: 'path',
      label: '路由地址',
      minWidth: 180
    },
    {
      prop: 'type',
      label: '菜单类型',
      minWidth: 180,
      slotName: 'menuType'
    },
    {
      prop: 'hidden',
      label: '隐藏',
      minWidth: 180,
      slotName: 'hidden'
    },
    {
      prop: 'createdTime',
      label: '创建时间',
      minWidth: '250',
      slotName: 'createdTime'
    },
    {
      prop: 'updatedTime',
      label: '更新时间',
      minWidth: '250',
      slotName: 'updatedTime'
    },
    {
      label: '操作',
      minWidth: 120,
      slotName: 'handler',
      fixed: 'right'
    }
  ]
}
