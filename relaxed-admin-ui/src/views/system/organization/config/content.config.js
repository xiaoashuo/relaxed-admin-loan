export const contentTableConfig={
  title: '部门列表',
  showSelectColumn:false,
  showFooter: false,
  tableProps:{
    rowKey: 'id',
    'default-expand-all': true,
    treeProp:{
      children:'children'
    },

  },
  propList:[

    {
      prop: 'name',
      label:'组织名称',
      minWidth: 50,
    },
    {
      prop: 'sort',
      label:'排序',
      minWidth: 50,
    },
    {
      prop: 'remarks',
      label:'备注',
      minWidth: 150,
    },
    {
      prop: 'createdTime',
      label:'创建时间',
      minWidth:50,
      slotName: 'createdTime'
    },
    {

      label:'操作',
      width:160,
      slotName:'handler'
    }
  ]
}
