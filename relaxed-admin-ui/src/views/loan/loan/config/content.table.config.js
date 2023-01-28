export const contentTableConfig={
  title: '借据列表',
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
    create: 'loan:certificate:add',
    // update: 'loan:certificate:edit',
    // delete: 'loan:certificate:delete'
  },
  propList:[


     {
      prop: 'partnerBizNo',
      label:'订单编号',
      minWidth:180,
    },
              {
      prop: 'partnerLoanNo',
      label:'借款编号',
      minWidth:180,
    },
              {
      prop: 'contractNo',
      label:'合同号',
      minWidth:180,

    },

              {
      prop: 'productName',
      label:'产品名称',
      minWidth:180,

    },

              {
      prop: 'trustPlanName',
      label:'信托计划名称',
      minWidth:180,

    },
              {
      prop: 'realName',
      label:'客户姓名',
      minWidth:180,

    },

              {
      prop: 'certificateNo',
      label:'证件号码',
      minWidth:180,

    },
    {
      prop: 'loanStatus',
      label:'借款状态',
      minWidth:180,
      slotName:'loanStatus'

    },
    {
      prop: 'startDate',
      label:'贷款开始日期',
      minWidth:180,

    },
              {
      prop: 'endDate',
      label:'贷款到期日期',
      minWidth:180,

    },



              {
      prop: 'loanAmt',
      label:'放款金额',
      minWidth:180,
      slotName: 'loanAmt'
    },


              {
      prop: 'totalTerms',
      label:'总期数',
      minWidth:80,
      slotName: 'totalTerms'
    },


              {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
      slotName:'createdTime'
    },

      {

      label:'操作',
      minWidth:150,
      slotName:'customHandler'
    }
  ]
}
