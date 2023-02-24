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
    create: 'risk:risk-activation:add',
    update: 'risk:risk-activation:edit',
    delete: 'risk:risk-activation:delete'
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
      minWidth:180,
    },
    {
      prop: 'label',
      label:'',
      minWidth:180,
    },
    {
      prop: 'modelId',
      label:'model id',
      minWidth:180,
    },
    {
      prop: 'comment',
      label:'注释',
      minWidth:180,
    },
    {
      prop: 'bottom',
      label:'底部阀值',
      minWidth:180,
    },
    {
      prop: 'median',
      label:'中间阀值',
      minWidth:180,
    },
    {
      prop: 'high',
      label:'顶部阀值',
      minWidth:180,
    },
    {
      prop: 'status',
      label:'状态',
      minWidth:180,
    },
    {
      prop: 'ruleOrder',
      label:'',
      minWidth:180,
    },
    {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
      slotName: 'createdTime'
    },
    {
      prop: 'updatedTime',
      label:'更新时间',
      minWidth: 180,
      slotName: 'updatedTime'
    },
    {

      label:'操作',
      minWidth:250,
      slotName:'handler'
    }
  ]
}
