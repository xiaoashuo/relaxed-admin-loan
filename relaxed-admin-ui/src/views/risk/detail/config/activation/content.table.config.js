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
    // create: 'risk:risk-activation:add',
    // update: 'risk:risk-activation:edit',
    // delete: 'risk:risk-activation:delete'
  },
  propList:[
    {
      prop: 'id',
      label:'主键',
      minWidth: 100,
      hidden: true
    },
    {
      prop: 'activationName',
      label:'名称',
      minWidth:120,
    },
    {
      prop: 'label',
      label:'策略标签',
      minWidth:180,
    },


    {
      prop: 'bottom',
      label:'底部阀值',
      minWidth:80,
    },
    {
      prop: 'median',
      label:'中间阀值',
      minWidth:80,
    },
    {
      prop: 'high',
      label:'顶部阀值',
      minWidth:80,
    },
    {
      prop: 'status',
      label:'状态',
      minWidth:80,
      slotName:'customStatus'
    },
    {
      prop: 'comment',
      label:'注释',
      minWidth:180,
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
