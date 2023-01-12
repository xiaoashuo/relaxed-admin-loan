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
  headerProps:{
    visible:false
  },
  permissions: {
    create: 'loan:bill:add',
    update: 'loan:bill:edit',
    delete: 'loan:bill:delete'
  },
  propList:[
    {
      label: '',
      minWidth: 180,
      type: 'expand',
      slotName: 'expandHandle'
    },
    {
      prop: 'termNo',
      label:'账单期号',
      minWidth:180,
    },
    {
      prop: 'startDate',
      label:'开始日期',
      minWidth:180,

    },
    {
      prop: 'endDate',
      label:'结束日期',
      minWidth:180,
    },

    {
      prop: 'clearDate',
      label:'结清日期',
      minWidth:180,
    },

    {
      prop: 'termStatus',
      label:'本期状态',
      minWidth:180,
      slotName: 'termStatus'
    },
    {
      prop: 'billReceivableAmt',
      label:'本期应收金额',
      minWidth:180,
    },
    {
      prop: 'billReceiptsAmt',
      label:'本期实收金额',
      minWidth:180,
      slotName: 'billReceiptsAmt'
    },

  ]
}
