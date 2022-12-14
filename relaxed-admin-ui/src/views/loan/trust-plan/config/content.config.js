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
  permissions: {
    create: 'loan:trust-plan:add',
    update: 'loan:trust-plan:edit',
    delete: 'loan:trust-plan:delete'
  },
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
      label:'信托计划成立日期',
      minWidth:180,
      slotName: 'establishedTime'
    },
              {
      prop: 'expireTime',
      label:'信托计划到期日期',
      minWidth:180,
      slotName: 'expireTime'
    },
              {
      prop: 'repayTime',
      label:'信托计划摊还期开始日期',
      minWidth:180,
      slotName: 'repayTime'
    },
              {
      prop: 'earningRate',
      label:'信托计划预期收益率',
      minWidth:180,
      slotName: 'earningRate'
    },
              {
      prop: 'fundPool',
      label:'信托计划初始规模',
      minWidth:180,
      slotName: 'fundPool'
    },
              {
      prop: 'deleted',
      label:'是否有效(0:未删除 1 或时间戳为已删除)',
      minWidth:180,
      slotName: 'deleted'
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
