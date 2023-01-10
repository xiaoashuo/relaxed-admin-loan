export const contentTableConfig={
  title: '审核表',
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
    create: 'loan:audit:add',
    update: 'loan:audit:edit',
    delete: 'loan:audit:delete'
  },
  propList:[
           {
      prop: 'auditId',
      label:'主键',
      minWidth:180,
      slotName: 'auditId'
    },
              {
      prop: 'creditId',
      label:'授信编号，关联t_credit表',
      minWidth:180,
      slotName: 'creditId'
    },
              {
      prop: 'orderId',
      label:'订单编号，关联t_order表',
      minWidth:180,
      slotName: 'orderId'
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
      prop: 'strategyCode',
      label:'风控策略码',
      minWidth:180,
      slotName: 'strategyCode'
    },
              {
      prop: 'name',
      label:'客户姓名',
      minWidth:180,
      slotName: 'name'
    },
              {
      prop: 'idNo',
      label:'身份证号码',
      minWidth:180,
      slotName: 'idNo'
    },
              {
      prop: 'bankCardNo',
      label:'银行卡号',
      minWidth:180,
      slotName: 'bankCardNo'
    },
              {
      prop: 'mobileNo',
      label:'手机号',
      minWidth:180,
      slotName: 'mobileNo'
    },
              {
      prop: 'extParamJsonStr',
      label:'其余的额外参数',
      minWidth:180,
      slotName: 'extParamJsonStr'
    },
              {
      prop: 'auditStatus',
      label:'系统审核',
      minWidth:180,
      slotName: 'auditStatus'
    },
              {
      prop: 'auditStatusPeople',
      label:'人工审核',
      minWidth:180,
      slotName: 'auditStatusPeople'
    },
              {
      prop: 'reasonCode',
      label:'拒绝原因码仅拒绝时返回，可多个以“,”分隔',
      minWidth:180,
      slotName: 'reasonCode'
    },
              {
      prop: 'reasonDesc',
      label:'拒绝原因描述 仅拒绝时返回',
      minWidth:180,
      slotName: 'reasonDesc'
    },
              {
      prop: 'resTime',
      label:'响应时间',
      minWidth:180,
      slotName: 'resTime'
    },
              {
      prop: 'resultTime',
      label:'风控审核出结果的时间',
      minWidth:180,
      slotName: 'resultTime'
    },
              {
      prop: 'peopleResultTime',
      label:'人工审核出结果时间',
      minWidth:180,
      slotName: 'peopleResultTime'
    },
              {
      prop: 'approvalMark',
      label:'黑名单标记 B类、C类、逗号分隔',
      minWidth:180,
      slotName: 'approvalMark'
    },
              {
      prop: 'bhCreditRptId',
      label:'百行征信报告id(如果风控规则有的话)',
      minWidth:180,
      slotName: 'bhCreditRptId'
    },
              {
      prop: 'brSpeclistRptId',
      label:'百融特殊名单报告id(如果风控规则有的话)',
      minWidth:180,
      slotName: 'brSpeclistRptId'
    },
              {
      prop: 'brIntentionRptId',
      label:'百融借贷意向报告id(如果风控规则有的话)',
      minWidth:180,
      slotName: 'brIntentionRptId'
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
