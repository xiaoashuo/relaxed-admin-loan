export const contentTableConfig={
  title: '字典列表',
  showSelectColumn:false,
  tableProps:{
    'cell-style':{
      padding: '5px 0'
    }
  },
  propList:[

    {
      prop: 'code',
      label:'标识',
      minWidth: 150,
    },
    {
      prop: 'title',
      label:'标题',
      minWidth: 150,
    },
    {
      prop: 'valueType',
      label:'数据类型',
      minWidth: 100,
      slotName: 'valueType'
    },
    {
      prop: 'remarks',
      label:'备注',
      minWidth: 180,
    },

    {
      prop: 'createdTime',
      label:'创建时间',
      minWidth:180,
      slotName: 'createdTime'
    },
    {

      label:'操作',
      minWidth:180,
      slotName:'handler'
    }
  ]
}
