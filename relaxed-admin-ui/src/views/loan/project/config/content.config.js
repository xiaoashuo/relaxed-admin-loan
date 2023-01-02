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
  // permissions: {
  //   create: 'loan:project:add',
  //   update: 'loan:project:edit',
  //   delete: 'loan:project:delete'
  // },
  propList:[
           {
      prop: 'projectId',
      label:'主键',
      minWidth:180,
      slotName: 'projectId'
    },

    {
      prop: 'partnerAlias',
      label:'合作方',
      minWidth:180,
    },
              {
      prop: 'productName',
      label:'信托产品',
      minWidth:180,
    },
              {
      prop: 'trustPlanName',
      label:'信托计划',
      minWidth:180,
    },
              {
      prop: 'projectCode',
      label:'项目代号',
      minWidth:180,
      slotName: 'projectCode'
    },
              {
      prop: 'projectName',
      label:'项目名称',
      minWidth:180,
      slotName: 'projectName'
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
