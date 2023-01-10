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
  permissions: {
    create: 'loan:order-borrow:add',
    update: 'loan:order-borrow:edit',
    delete: 'loan:order-borrow:delete'
  },
  propList:[
           {
      prop: 'userId',
      label:'关联人主键',
      minWidth:180,
      slotName: 'userId'
    },
              {
      prop: 'orderId',
      label:'订单号',
      minWidth:180,
      slotName: 'orderId'
    },
              {
      prop: 'relatedName',
      label:'关联人姓名',
      minWidth:180,
      slotName: 'relatedName'
    },
              {
      prop: 'certificateNo',
      label:'联系人证件号码',
      minWidth:180,
      slotName: 'certificateNo'
    },
              {
      prop: 'mobileNo',
      label:'联系人电话',
      minWidth:180,
      slotName: 'mobileNo'
    },
              {
      prop: 'address',
      label:'地址',
      minWidth:180,
      slotName: 'address'
    },
              {
      prop: 'eMail',
      label:'用户电子邮箱',
      minWidth:180,
      slotName: 'eMail'
    },
              {
      prop: 'guarantorFlag',
      label:'是否担保人1是2否',
      minWidth:180,
      slotName: 'guarantorFlag'
    },
              {
      prop: 'valid',
      label:'逻辑删除标识，未删除为 0，已删除为删除时间',
      minWidth:180,
      slotName: 'valid'
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
