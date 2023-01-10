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
    create: 'loan:trade:add',
    update: 'loan:trade:edit',
    delete: 'loan:trade:delete'
  },
  propList:[
           {
      prop: 'tradeId',
      label:'交易编号',
      minWidth:180,
      slotName: 'tradeId'
    },
              {
      prop: 'orderId',
      label:'订单主键 t_order',
      minWidth:180,
      slotName: 'orderId'
    },
              {
      prop: 'partnerBizNo',
      label:'合作方编号',
      minWidth:180,
      slotName: 'partnerBizNo'
    },
              {
      prop: 'tradeNo',
      label:'交易流水号',
      minWidth:180,
      slotName: 'tradeNo'
    },
              {
      prop: 'repayType',
      label:'还款方式',
      minWidth:180,
      slotName: 'repayType'
    },
              {
      prop: 'tradeStatus',
      label:'交易状态',
      minWidth:180,
      slotName: 'tradeStatus'
    },
              {
      prop: 'tradeAmount',
      label:'交易金额',
      minWidth:180,
      slotName: 'tradeAmount'
    },
              {
      prop: 'tradeAccountBank',
      label:'交易银行名称',
      minWidth:180,
      slotName: 'tradeAccountBank'
    },
              {
      prop: 'tradeAccount',
      label:'交易账号',
      minWidth:180,
      slotName: 'tradeAccount'
    },
              {
      prop: 'tradeAccountType',
      label:'账户类型',
      minWidth:180,
      slotName: 'tradeAccountType'
    },
              {
      prop: 'tradeAccountName',
      label:'账户姓名 ',
      minWidth:180,
      slotName: 'tradeAccountName'
    },
              {
      prop: 'tradeAccountId',
      label:'帐户身份证号 ',
      minWidth:180,
      slotName: 'tradeAccountId'
    },
              {
      prop: 'payChannel',
      label:'支付渠道，例如：通联',
      minWidth:180,
      slotName: 'payChannel'
    },
              {
      prop: 'payPlatformId',
      label:'放款通道 （核心库t-config-platform配置id）',
      minWidth:180,
      slotName: 'payPlatformId'
    },
              {
      prop: 'payPlatformMerchantId',
      label:'商户id',
      minWidth:180,
      slotName: 'payPlatformMerchantId'
    },
              {
      prop: 'payPlatformUserName',
      label:'用户名',
      minWidth:180,
      slotName: 'payPlatformUserName'
    },
              {
      prop: 'payPlatformBusinessCode',
      label:'业务code',
      minWidth:180,
      slotName: 'payPlatformBusinessCode'
    },
              {
      prop: 'tradeTime',
      label:'交易时间',
      minWidth:180,
      slotName: 'tradeTime'
    },
              {
      prop: 'loanPurpose',
      label:'交易用途(1:放款  2:收款)',
      minWidth:180,
      slotName: 'loanPurpose'
    },
              {
      prop: 'failReason',
      label:'交易备注',
      minWidth:180,
      slotName: 'failReason'
    },
              {
      prop: 'tradeRemark',
      label:'',
      minWidth:180,
      slotName: 'tradeRemark'
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
