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
      minWidth:80,

    },
    {
      prop: 'partnerLoanNo',
      label:'借据编号',
      minWidth:180,

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
      prop: 'tradeAccountName',
      label:'账户姓名 ',
      minWidth:180,
    },


   {
      prop: 'payPlatformId',
      label:'放款通道',
      minWidth:180,
      slotName: 'payPlatformId'
    },
    {
      prop: 'trustPlanName',
      label: '信托计划名称',
      minWidth:180,

    },
     {
      prop: 'failReason',
      label:'交易备注',
      minWidth:180,
    },


    {
      prop: 'tradeTime',
      label:'交易时间',
      minWidth:180,
    },

  ]
}
