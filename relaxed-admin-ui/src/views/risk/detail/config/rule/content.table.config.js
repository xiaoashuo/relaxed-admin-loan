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
    // create: 'risk:risk-rule:add',
    // update: 'risk:risk-rule:edit',
    // delete: 'risk:risk-rule:delete'
  },
  propList:[
    {
      prop: 'id',
      label:'主键',
      minWidth: 100,
      hidden: true
    },

    {
      prop: 'name',
      label:'规则编号',
      minWidth:180,
    },
    {
      prop: 'label',
      label:'规则名称',
      minWidth:180,
    },

    {
      prop: 'initScore',
      label:'初始分数',
      minWidth:180,
    },
    {
      prop: 'baseNum',
      label:'基数',
      minWidth:180,
    },
    {
      prop: 'operator',
      label:'运算符',
      minWidth:180,
    },
    {
      prop: 'rate',
      label:'比例',
      minWidth:180,
    },
    {
      prop: 'max',
      label:'最大得分值',
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
