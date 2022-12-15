export const contentConfig={
  title: '信托产品表',
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
  //   create: 'loan:product:add',
  //   update: 'loan:product:edit',
  //   delete: 'loan:product:delete'
  // },
  propList:[
           {
      prop: 'productId',
      label:'主键',
      minWidth:180,
      slotName: 'productId'
    },
              {
      prop: 'productName',
      label:'信托产品名称',
      minWidth:180,
      slotName: 'productName'
    },
              {
      prop: 'productDesc',
      label:'描述',
      minWidth:180,
      slotName: 'productDesc'
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
