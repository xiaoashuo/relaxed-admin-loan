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
    create: 'loan:bill-item:add',
    update: 'loan:bill-item:edit',
    delete: 'loan:bill-item:delete'
  },
  propList:[
           {
      prop: 'billItemId',
      label:'账单科目表',
      minWidth:180,
      slotName: 'billItemId'
    },
              {
      prop: 'loanId',
      label:'借款编号,用于关联t_loan表',
      minWidth:180,
      slotName: 'loanId'
    },
              {
      prop: 'billId',
      label:'账单编号,用于关联t_bill表',
      minWidth:180,
      slotName: 'billId'
    },
              {
      prop: 'itemName',
      label:'科目名称',
      minWidth:180,
      slotName: 'itemName'
    },
              {
      prop: 'itemType',
      label:'科目类型（1: 本金 11: 利息 21: 罚息）',
      minWidth:180,
      slotName: 'itemType'
    },
              {
      prop: 'receivableAmt',
      label:'应收金额',
      minWidth:180,
      slotName: 'receivableAmt'
    },
              {
      prop: 'receiptsAmt',
      label:'实收金额',
      minWidth:180,
      slotName: 'receiptsAmt'
    },
              {
      prop: 'valid',
      label:'逻辑删除标识，未删除为 0，已删除为删除时间',
      minWidth:180,
      slotName: 'valid'
    },
              {
      prop: 'latestSyncTime',
      label:'最后同步时间',
      minWidth:180,
      slotName: 'latestSyncTime'
    },
              {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
    },
              {
      prop: 'updatedTime',
      label:'更新时间',
      minWidth: 180,
    },
          {

      label:'操作',
      minWidth:250,
      slotName:'handler'
    }
  ]
}
