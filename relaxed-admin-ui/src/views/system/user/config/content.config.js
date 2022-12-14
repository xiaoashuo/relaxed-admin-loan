export const contentTableConfig = {
  title: '用户列表',
  showSelectColumn: true,


  tableProps: {
    'row-style': {
      height: '10px'
    },
    'cell-style': {
      padding: '2px 0'
    },

  },
  permissions: {
    create: 'system:user:add',
    update: 'system:user:edit',
    delete: 'system:user:delete'
  },
  propList: [
    {
      prop: 'username',
      label: '登录账号',
      minWidth: 100
    },
    {
      prop: 'nickname',
      label: '昵称',
      minWidth: 150
    },
    {
      prop: 'status',
      label: '状态',
      minWidth: 100,
      slotName: 'status'
    },
    {
      prop: 'avatar',
      label: '头像',
      minWidth: 100,
      slotName: 'image'
    },
    {
      prop: 'email',
      label: '邮箱',
      minWidth: 180
    },
    {
      prop: 'phone',
      label: '手机号',
      minWidth: 150,

    },
    {
      prop: 'createdTime',
      label: '创建时间',
      minWidth: 180,
      sortable: 'custom',
      slotName: 'createdTime'
    },
    {
      prop: 'updatedTime',
      label: '更新时间',
      minWidth: 180,
      slotName: 'updatedTime',
      sortable: 'custom'
    },
    {
      label: '操作',
      minWidth: 180,
      slotName: 'handler'
    }
  ]
}
