export const contentTableConfig={
  title: '',
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
    // create: 'test:risk-model:add',
    // update: 'test:risk-model:edit',
    // delete: 'test:risk-model:delete'
  },
  propList:[
    {
      prop: 'id',
      label:'主键',
      minWidth: 100,
    },
    {
      prop: 'modelName',
      label:'模型名称',
      minWidth:180,
    },
    {
      prop: 'entityName',
      label:'实体主键',
      minWidth:180,
    },
    {
      prop: 'entryName',
      label:'事件主键',
      minWidth:180,
    },
    {
      prop: 'guid',
      label:'标识',
      minWidth:180,
    },
    {
      prop: 'referenceDate',
      label:'事件时间',
      minWidth:180,
    },
    {
      prop: 'status',
      label:'状态',
      minWidth:180,
      slotName:'customStatus'
    },
    {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
      slotName: 'createdTime'
    },

    {

      label:'操作',
      minWidth:250,
      slotName:'handler'
    }
  ]
}
