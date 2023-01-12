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
    visible: false
  },
  showFooter:false,
  permissions: {
    create: 'loan:bill-item:add',
    update: 'loan:bill-item:edit',
    delete: 'loan:bill-item:delete'
  },
  propList:[



              {
      prop: 'itemName',
      label:'科目名称',
      minWidth:180,

    },

              {
      prop: 'receivableAmt',
      label:'应收金额',
      minWidth:180,

    },
              {
      prop: 'receiptsAmt',
      label:'实收金额',
      minWidth:180,
      slotName:'receiptsAmt'
    },

  ]
}
