export const contentTableConfig = {
  title: '配置列表',
  showSelectColumn: false,
  tableProps: {
    'row-style': {
      height: '10px'
    }
  },
  propList: [
    {
      prop: 'id',
      label: '主键',
      minWidth: 100,
      hidden: true
    },
    {
      prop: 'name',
      label: '配置名称',
      minWidth: 180
    },
    {
      prop: 'confKey',
      label: '配置键',
      minWidth: 150
    },
    {
      prop: 'confValue',
      label: '配置值',
      minWidth: 150
    },
    {
      prop: 'category',
      label: '分类',
      minWidth: 150
    },
    {
      prop: 'remarks',
      label: '备注',
      minWidth: 150
    },
    {
      prop: 'createdTime',
      label: '创建时间',
      minWidth: 180,
      slotName: 'createdTime'
    },
    {
      prop: 'updatedTime',
      label: '更新时间',
      minWidth: 180,
      slotName: 'updatedTime'
    },
    {
      label: '操作',
      minWidth: 250,
      slotName: 'handler'
    }
  ]
}
