export const contentTableConfig={
  title: '银行编码表',
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
    // create: 'loan:bank-code:add',
    // update: 'loan:bank-code:edit',
    // delete: 'loan:bank-code:delete'
  },
  propList:[
           {
      prop: 'bankCodeId',
      label:'主键id',
      minWidth:180,
      slotName: 'bankCodeId'
    },
              {
      prop: 'bankCode',
      label:'银行编码',
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
      prop: 'remark',
      label:'描述',
      minWidth:180,
      slotName: 'remark'
    },
              {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
    },

          {

      label:'操作',
      minWidth:150,
      slotName:'handler'
    }
  ]
}
