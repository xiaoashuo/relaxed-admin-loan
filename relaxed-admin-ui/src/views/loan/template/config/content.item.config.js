export const contentItemTableConfig={
  title: '参数',
  showSelectColumn:false,
  tableProps:{
    'row-style': {
      height: '10px'
    },
    'cell-style': {
      padding: '2px 0'
    },

  },
  permissions: {
    create: 'loan:template-area:add',
    update: 'loan:template-area:edit',
    delete: 'loan:template-area:delete'
  },
  propList:[

    {
      prop: 'textArea',
      label:'文本域',
      minWidth:180,
      slotName: 'textArea'
    },
    {
      prop: 'tagName',
      label:'节点名称',
      minWidth:180,
      slotName: 'tagName'
    },
    {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
    },

  ]
}
