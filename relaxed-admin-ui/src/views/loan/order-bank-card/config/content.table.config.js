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
    create: 'loan:order-bank-card:add',
    update: 'loan:order-bank-card:edit',
    delete: 'loan:order-bank-card:delete'
  },
  propList:[
           {
      prop: 'bankCardId',
      label:'银行卡主键',
      minWidth:180,
      slotName: 'bankCardId'
    },
              {
      prop: 'orderId',
      label:'订单号,关联t_order表',
      minWidth:180,
      slotName: 'orderId'
    },
              {
      prop: 'accountType',
      label:'账户类型（0-对公 1-对私 2-第三方支付）',
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
      label:'银行卡用途1:收款 2:还款 3:认证',
      minWidth:180,
      slotName: 'cardPurpose'
    },
              {
      prop: 'valid',
      label:'逻辑删除标识，未删除为 0，已删除为删除时间',
      minWidth:180,
      slotName: 'valid'
    },
              {
      prop: 'authStatus',
      label:'认证状态0:未认证1:已认证',
      minWidth:180,
      slotName: 'authStatus'
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
