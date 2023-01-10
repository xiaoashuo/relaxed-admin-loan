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
    create: 'loan:bill:add',
    update: 'loan:bill:edit',
    delete: 'loan:bill:delete'
  },
  propList:[
           {
      prop: 'billId',
      label:'账单编号',
      minWidth:180,
      slotName: 'billId'
    },
              {
      prop: 'loanId',
      label:'借款编号,用于关联t_loan表',
      minWidth:180,
      slotName: 'loanId'
    },
              {
      prop: 'partnerBillNo',
      label:'合作方账单编号',
      minWidth:180,
      slotName: 'partnerBillNo'
    },
              {
      prop: 'termNo',
      label:'账单期号',
      minWidth:180,
      slotName: 'termNo'
    },
              {
      prop: 'startDate',
      label:'开始日期',
      minWidth:180,
      slotName: 'startDate'
    },
              {
      prop: 'endDate',
      label:'结束日期',
      minWidth:180,
      slotName: 'endDate'
    },
              {
      prop: 'dueDate',
      label:'最后还款日（含宽限期）',
      minWidth:180,
      slotName: 'dueDate'
    },
              {
      prop: 'clearDate',
      label:'结清日期',
      minWidth:180,
      slotName: 'clearDate'
    },
              {
      prop: 'billType',
      label:'账单类型1：正常账单 2：提前清贷账单 3：降贷账单  4：回购账单99：债转账单',
      minWidth:180,
      slotName: 'billType'
    },
              {
      prop: 'termStatus',
      label:'本期状态1:正常 2:逾期 5:已结清',
      minWidth:180,
      slotName: 'termStatus'
    },
              {
      prop: 'billReceivableAmt',
      label:'本期应收金额',
      minWidth:180,
      slotName: 'billReceivableAmt'
    },
              {
      prop: 'billReceiptsAmt',
      label:'本期实收金额（含减免金额）',
      minWidth:180,
      slotName: 'billReceiptsAmt'
    },
              {
      prop: 'billMitigateAmt',
      label:'本期减免金额',
      minWidth:180,
      slotName: 'billMitigateAmt'
    },
              {
      prop: 'latestSyncTime',
      label:'最后同步时间',
      minWidth:180,
      slotName: 'latestSyncTime'
    },
              {
      prop: 'valid',
      label:'逻辑删除标识，未删除为 0，已删除为删除时间',
      minWidth:180,
      slotName: 'valid'
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
