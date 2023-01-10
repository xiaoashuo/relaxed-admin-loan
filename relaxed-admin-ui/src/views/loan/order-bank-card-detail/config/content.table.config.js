export const contentTableConfig={
  title: '订单银行卡放款详情表',
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
    create: 'loan:order-bank-card-detail:add',
    update: 'loan:order-bank-card-detail:edit',
    delete: 'loan:order-bank-card-detail:delete'
  },
  propList:[
           {
      prop: 'detailId',
      label:'银行卡主键',
      minWidth:180,
      slotName: 'detailId'
    },
              {
      prop: 'orderId',
      label:'订单号,关联t_order表',
      minWidth:180,
      slotName: 'orderId'
    },
              {
      prop: 'bankCardId',
      label:'银行卡编号,关联t_order_bank_card表',
      minWidth:180,
      slotName: 'bankCardId'
    },
              {
      prop: 'userId',
      label:'共同借款人编号，关联t_order_borrow',
      minWidth:180,
      slotName: 'userId'
    },
              {
      prop: 'loanType',
      label:'是否主借人0:非主借人 1:主借人',
      minWidth:180,
      slotName: 'loanType'
    },
              {
      prop: 'realName',
      label:'姓名',
      minWidth:180,
      slotName: 'realName'
    },
              {
      prop: 'certificateNo',
      label:'证件号码',
      minWidth:180,
      slotName: 'certificateNo'
    },
              {
      prop: 'loanAmt',
      label:'放款金额',
      minWidth:180,
      slotName: 'loanAmt'
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
