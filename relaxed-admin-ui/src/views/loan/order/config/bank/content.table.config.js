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

  propList:[

              {
      prop: 'accountType',
      label:'账户类型',
      minWidth:180,
      slotName: 'accountType'
    },
              {
      prop: 'accountName',
      label:'户名',
      minWidth:180,
      slotName: 'accountName'
    },
              {
      prop: 'accountNo',
      label:'户号',
      minWidth:180,
      slotName: 'accountNo'
    },
              {
      prop: 'bankCode',
      label:'银行代号',
      minWidth:180,
      slotName: 'bankCode'
    },
              {
      prop: 'bankName',
      label:'银行名称',
      minWidth:180,
      slotName: 'bankName'
    },
              {
      prop: 'openBankName',
      label:'开户行',
      minWidth:180,
      slotName: 'openBankName'
    },
              {
      prop: 'openBankUnionBank',
      label:'开户行（分支行）联行号',
      minWidth:180,
      slotName: 'openBankUnionBank'
    },
              {
      prop: 'reserveMobile',
      label:'预留手机号',
      minWidth:180,
      slotName: 'reserveMobile'
    },
        {
      prop: 'cardPurpose',
      label:'银行卡用途',
      minWidth:180,
      slotName: 'cardPurpose'
    },


  ]
}
