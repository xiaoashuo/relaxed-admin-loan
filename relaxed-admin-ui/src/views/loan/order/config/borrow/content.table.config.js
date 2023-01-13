export const contentTableConfig={
  title: '共同借款人',
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
  //   create: 'loan:order-borrow:add',
  //   update: 'loan:order-borrow:edit',
  //   delete: 'loan:order-borrow:delete'
  // },
  propList:[

              {
      prop: 'relatedName',
      label:'姓名',
      minWidth:180,

    },
              {
      prop: 'certificateNo',
      label:'证件号码',
      minWidth:180,

    },
              {
      prop: 'mobileNo',
      label:'电话',
      minWidth:180,

    },
              {
      prop: 'address',
      label:'地址',
      minWidth:180,

    },

              {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
    },


  ]
}
