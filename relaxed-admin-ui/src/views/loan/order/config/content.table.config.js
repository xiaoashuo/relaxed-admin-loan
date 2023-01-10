export const contentTableConfig={
  title: '订单列表',
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
  //   create: 'loan:order:add',
  //   update: 'loan:order:edit',
  //   delete: 'loan:order:delete'
  // },
  propList:[


    {
      prop: 'partnerBizNo',
      label:'合作方业务号',
      minWidth:180,
    },


    {
      prop: 'realName',
      label:'姓名',
      minWidth:180,

    },

    {
      prop: 'certificateNo',
      label:'证件号码',
      minWidth:180,

    },
     {
      prop: 'applyAmount',
      label:'申请金额（单位:元）',
      minWidth:180,

    },

              {
      prop: 'orderStage',
      label:'订单阶段',
      minWidth:180,
      slotName: 'orderStage'
    },





              {
      prop: 'trustPlanName',
      label:'信托计划名称',
      minWidth:180,

    },

              {
      prop: 'productName',
      label:'信托产品名称',
      minWidth:180,

    },


              {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
     slotName: 'createdTime'
    },
              {
      prop: 'updatedTime',
      label:'更新时间',
      minWidth: 180,
      slotName: 'updatedTime'
    },

    {

      label:'操作',
      minWidth:100,
      slotName:'customHandler'
    }
  ]
}
