export const contentTableConfig={
  title: '借款合同表',
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
    create: 'loan:loan:add',
    update: 'loan:loan:edit',
    delete: 'loan:loan:delete'
  },
  propList:[
           {
      prop: 'loanId',
      label:'借款编号',
      minWidth:180,
      slotName: 'loanId'
    },
              {
      prop: 'orderId',
      label:'订单编号,关联t_order表',
      minWidth:180,
      slotName: 'orderId'
    },
              {
      prop: 'loanBizNo',
      label:'借款编号(云融提供)',
      minWidth:180,
      slotName: 'loanBizNo'
    },
              {
      prop: 'partnerBizNo',
      label:'合作方业务号',
      minWidth:180,
      slotName: 'partnerBizNo'
    },
              {
      prop: 'partnerLoanNo',
      label:'合作方借款编号',
      minWidth:180,
      slotName: 'partnerLoanNo'
    },
              {
      prop: 'contractNo',
      label:'合同号',
      minWidth:180,
      slotName: 'contractNo'
    },
              {
      prop: 'productCode',
      label:'产品代号',
      minWidth:180,
      slotName: 'productCode'
    },
              {
      prop: 'productName',
      label:'产品名称',
      minWidth:180,
      slotName: 'productName'
    },
              {
      prop: 'trustPlanCode',
      label:'信托计划代号',
      minWidth:180,
      slotName: 'trustPlanCode'
    },
              {
      prop: 'trustPlanName',
      label:'信托计划名称',
      minWidth:180,
      slotName: 'trustPlanName'
    },
              {
      prop: 'realName',
      label:'客户姓名',
      minWidth:180,
      slotName: 'realName'
    },
              {
      prop: 'certificateType',
      label:'证件类型，枚举值：1-户口簿2-护照5-港澳居民来往内地通行证6-台湾同胞来往内地通行证8-外国人居留证9-警官证A-香港身份证B-澳门身份证C-台湾身份证X-其他证件10-居民身份证及其他以公民身份证号码为标识的证件20-军人身份证件',
      minWidth:180,
      slotName: 'certificateType'
    },
              {
      prop: 'certificateNo',
      label:'证件号码',
      minWidth:180,
      slotName: 'certificateNo'
    },
              {
      prop: 'applyDate',
      label:'贷款申请日期',
      minWidth:180,
      slotName: 'applyDate'
    },
              {
      prop: 'loanDate',
      label:'放款时间',
      minWidth:180,
      slotName: 'loanDate'
    },
              {
      prop: 'startDate',
      label:'贷款确认日期（起息日）',
      minWidth:180,
      slotName: 'startDate'
    },
              {
      prop: 'endDate',
      label:'贷款到期日期',
      minWidth:180,
      slotName: 'endDate'
    },
              {
      prop: 'clearDate',
      label:'结清日期',
      minWidth:180,
      slotName: 'clearDate'
    },
              {
      prop: 'repayDay',
      label:'还款日',
      minWidth:180,
      slotName: 'repayDay'
    },
              {
      prop: 'tradeId',
      label:'放款交易编号',
      minWidth:180,
      slotName: 'tradeId'
    },
              {
      prop: 'loanAmt',
      label:'放款金额',
      minWidth:180,
      slotName: 'loanAmt'
    },
              {
      prop: 'currency',
      label:'币种（0:RMB）',
      minWidth:180,
      slotName: 'currency'
    },
              {
      prop: 'repaymentWay',
      label:'还款方式（0:等额本息 1:等额本金 2:先息后本 3:月息季本 4:X+Y,X期先息后本，Y期等额本金 5:一次性还本付息）',
      minWidth:180,
      slotName: 'repaymentWay'
    },
              {
      prop: 'periodUnit',
      label:'期数单位，0:天，1:月',
      minWidth:180,
      slotName: 'periodUnit'
    },
              {
      prop: 'totalTerms',
      label:'总期数',
      minWidth:180,
      slotName: 'totalTerms'
    },
              {
      prop: 'irrRate',
      label:'irr年利率',
      minWidth:180,
      slotName: 'irrRate'
    },
              {
      prop: 'currentTerm',
      label:'当前期数',
      minWidth:180,
      slotName: 'currentTerm'
    },
              {
      prop: 'merchantDisAmt',
      label:'商户帖息金额',
      minWidth:180,
      slotName: 'merchantDisAmt'
    },
              {
      prop: 'graceDays',
      label:'宽限期天数',
      minWidth:180,
      slotName: 'graceDays'
    },
              {
      prop: 'interestRateUnit',
      label:'利率单位（1:日利率 30:月利率 365:年利率）',
      minWidth:180,
      slotName: 'interestRateUnit'
    },
              {
      prop: 'interestRate',
      label:'利率',
      minWidth:180,
      slotName: 'interestRate'
    },
              {
      prop: 'penaltyRateUnit',
      label:'利率单位（1:日利率 30:月利率 365:年利率）',
      minWidth:180,
      slotName: 'penaltyRateUnit'
    },
              {
      prop: 'penaltyRate',
      label:'罚息利率',
      minWidth:180,
      slotName: 'penaltyRate'
    },
              {
      prop: 'loanStatus',
      label:'借款状态(20:待放款21:放款成功/还款中22:放款失败30:已结清40:回购中 99:转出)',
      minWidth:180,
      slotName: 'loanStatus'
    },
              {
      prop: 'overdueFlag',
      label:'逾期标记（0:未逾期 1:已逾期）',
      minWidth:180,
      slotName: 'overdueFlag'
    },
              {
      prop: 'principalReceivable',
      label:'应还本金',
      minWidth:180,
      slotName: 'principalReceivable'
    },
              {
      prop: 'principalReceipts',
      label:'已还本金',
      minWidth:180,
      slotName: 'principalReceipts'
    },
              {
      prop: 'interestReceivable',
      label:'应还利息',
      minWidth:180,
      slotName: 'interestReceivable'
    },
              {
      prop: 'interestReceipts',
      label:'已还利息',
      minWidth:180,
      slotName: 'interestReceipts'
    },
              {
      prop: 'penaltyReceivable',
      label:'应还罚息',
      minWidth:180,
      slotName: 'penaltyReceivable'
    },
              {
      prop: 'penaltyReceipts',
      label:'已还罚息',
      minWidth:180,
      slotName: 'penaltyReceipts'
    },
              {
      prop: 'receivableAmt',
      label:'应还总金',
      minWidth:180,
      slotName: 'receivableAmt'
    },
              {
      prop: 'receiptsAmt',
      label:'已还总金',
      minWidth:180,
      slotName: 'receiptsAmt'
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
      prop: 'nbContractId',
      label:'宁波银行放款入参借款合同编号',
      minWidth:180,
      slotName: 'nbContractId'
    },
          {

      label:'操作',
      minWidth:250,
      slotName:'handler'
    }
  ]
}
