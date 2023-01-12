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

    },
    {
      prop: 'trustPlanName',
      label: '信托计划名称',
      minWidth:180,

    },
              {
      prop: 'tradeNo',
      label:'交易流水号',
      minWidth:180,
      slotName: 'tradeNo'
    },

              {
      prop: 'tradeAmount',
      label:'交易金额',
      minWidth:180,
      slotName: 'tradeAmount'
    },
              {
      prop: 'repayType',
      label:'还款方式',
      minWidth:180,
      slotName: 'repayType'
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
