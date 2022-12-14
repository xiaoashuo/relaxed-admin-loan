export const contentTableConfig={
  title: '产品配置表',
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
    create: 'loan:project:add',
    update: 'loan:project:edit',
    delete: 'loan:project:delete'
  },
  propList:[
           {
      prop: 'projectId',
      label:'主键',
      minWidth:180,
      slotName: 'projectId'
    },
              {
      prop: 'partnerId',
      label:'信托项目主键（t_config_partner）',
      minWidth:180,
      slotName: 'partnerId'
    },
              {
      prop: 'productId',
      label:'信托产品主键（t_config_product）',
      minWidth:180,
      slotName: 'productId'
    },
              {
      prop: 'trustPlanId',
      label:'信托计划主键（t_config_trust_plan）',
      minWidth:180,
      slotName: 'trustPlanId'
    },
              {
      prop: 'projectCode',
      label:'合作项目代号',
      minWidth:180,
      slotName: 'projectCode'
    },
              {
      prop: 'projectName',
      label:'合作项目名称',
      minWidth:180,
      slotName: 'projectName'
    },
              {
      prop: 'productCode',
      label:'云融产品编号',
      minWidth:180,
      slotName: 'productCode'
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
