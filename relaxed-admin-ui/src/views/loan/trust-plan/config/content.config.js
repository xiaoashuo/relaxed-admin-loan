export const contentTableConfig={
  title: '信托计划配置表',
  showSelectColumn:false,
  tableProps:{
    'row-style': {
      height: '10px'
    },
    'cell-style': {
      padding: '2px 0'
    },

  },
  // permissions: {
  //   create: 'loan:trust-plan:add',
  //   update: 'loan:trust-plan:edit',
  //   delete: 'loan:trust-plan:delete'
  // },
  propList:[
           {
      prop: 'trustPlanId',
      label:'主键',
      minWidth:180,
      slotName: 'trustPlanId'
    },
              {
      prop: 'trustPlanName',
      label:'信托计划名称',
      minWidth:180,
      slotName: 'trustPlanName'
    },
              {
      prop: 'trustPlanNo',
      label:'信托计划编号',
      minWidth:180,
      slotName: 'trustPlanNo'
    },
              {
      prop: 'establishedTime',
      label:'成立日期',
      minWidth:180,
      slotName: 'establishedTime'
    },
              {
      prop: 'expireTime',
      label:'到期日期',
      minWidth:180,
      slotName: 'expireTime'
    },
              {
      prop: 'repayTime',
      label:'摊还期开始日期',
      minWidth:180,
      slotName: 'repayTime'
    },
              {
      prop: 'earningRate',
      label:'预期收益率',
      minWidth:180,
      slotName: 'earningRate'
    },
              {
      prop: 'fundPool',
      label:'初始规模',
      minWidth:180,
      slotName: 'fundPool'
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
