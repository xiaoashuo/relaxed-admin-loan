export const contentTableConfig={
  title: '字典列表',
  showSelectColumn:false,
  childrenProps:{
    'row-style':  {
      height: '10px'
    }
  },
  propList:[

    {
      prop: 'dictCode',
      label:'标识',
      minWidth: 100,
    },
    {
      prop: 'name',
      label:'文本值',
      minWidth: 150,
    },
    {
      prop: 'value',
      label:'数据值',
      minWidth: 100,
    },
    {
      prop: 'sort',
      label:'排序',
      minWidth: 100,
    },
    {
      prop: 'status',
      label:'状态',
      minWidth: 100,
      slotName: 'extStatus'
    },
    {
      prop: 'remarks',
      label:'备注',
      minWidth: 100,
    },
    {
      prop: 'createdTime',
      label:'创建时间',
      minWidth:180,
      slotName: 'createdTime'
    },
    {

      label:'操作',
      minWidth:250,
      slotName:'handler'
    }
  ]
}
