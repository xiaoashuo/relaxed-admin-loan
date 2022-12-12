export const contentTableConfig={
  title: '角色列表',
  showSelectColumn:false,
  tableProps:{

    'cell-style':{
      padding: '5px 0'
    }


  },
  propList:[

    {
      prop: 'name',
      label:'角色名称',
      minWidth: 180,
    },
    {
      prop: 'code',
      label:'标识',
      minWidth: 200,
    },
    {
      prop: 'remarks',
      label:'描述',
      minWidth: 180
    },
    {
      prop: 'createdTime',
      label:'创建时间',
      minWidth:'250',
      slotName: 'createdTime'
    },
    {
      prop: 'updatedTime',
      label:'更新时间',
      minWidth:'250',
      slotName: 'updatedTime'
    },
    {

      label:'操作',
      minWidth:'200',
      slotName:'handler'
    }
  ]
}
