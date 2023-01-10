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
    create: 'loan:bill-item-filler:add',
    update: 'loan:bill-item-filler:edit',
    delete: 'loan:bill-item-filler:delete'
  },
  propList:[
           {
      prop: 'itemFillerId',
      label:'填充项编号',
      minWidth:180,
      slotName: 'itemFillerId'
    },
              {
      prop: 'loanId',
      label:'借款编号,用于关联t_loan表',
      minWidth:180,
      slotName: 'loanId'
    },
              {
      prop: 'billId',
      label:'账单id,用于关联t_bill表',
      minWidth:180,
      slotName: 'billId'
    },
              {
      prop: 'billItemId',
      label:'账单科目id,用于关联t_bill_item表',
      minWidth:180,
      slotName: 'billItemId'
    },
              {
      prop: 'fillerName',
      label:'填充名称（可自定义）',
      minWidth:180,
      slotName: 'fillerName'
    },
              {
      prop: 'fillerType',
      label:'填充类型：2- 减免 11- 实收',
      minWidth:180,
      slotName: 'fillerType'
    },
              {
      prop: 'fillerAmount',
      label:'填充金额（应收增加为正、应收减少为负、实收增加为正、实收减少为负）',
      minWidth:180,
      slotName: 'fillerAmount'
    },
              {
      prop: 'fillerTarget',
      label:'填充目标：1- 应收2- 实收',
      minWidth:180,
      slotName: 'fillerTarget'
    },
              {
      prop: 'repayEvent',
      label:'交易事件：11- 逾期还款12- 正常还款13- 提前还款14- 提前结清',
      minWidth:180,
      slotName: 'repayEvent'
    },
              {
      prop: 'tradeId',
      label:'交易编号，关联t_trade表',
      minWidth:180,
      slotName: 'tradeId'
    },
              {
      prop: 'operatorId',
      label:'操作者编号：0- 系统',
      minWidth:180,
      slotName: 'operatorId'
    },
              {
      prop: 'remark',
      label:'说明',
      minWidth:180,
      slotName: 'remark'
    },
              {
      prop: 'partnerFillerTime',
      label:'账单填充时间',
      minWidth:180,
      slotName: 'partnerFillerTime'
    },
              {
      prop: 'reportNo',
      label:'保险报案号',
      minWidth:180,
      slotName: 'reportNo'
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
