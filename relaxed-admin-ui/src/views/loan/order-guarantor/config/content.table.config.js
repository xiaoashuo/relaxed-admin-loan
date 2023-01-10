export const contentTableConfig={
  title: '担保机构',
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
    create: 'loan:order-guarantor:add',
    update: 'loan:order-guarantor:edit',
    delete: 'loan:order-guarantor:delete'
  },
  propList:[
           {
      prop: 'guarantorId',
      label:'关联人主键',
      minWidth:180,
      slotName: 'guarantorId'
    },
              {
      prop: 'orderId',
      label:'订单号',
      minWidth:180,
      slotName: 'orderId'
    },
              {
      prop: 'guarantorName',
      label:'担保机构/担保人名称',
      minWidth:180,
      slotName: 'guarantorName'
    },
              {
      prop: 'guarantorNo',
      label:'担保函编号',
      minWidth:180,
      slotName: 'guarantorNo'
    },
              {
      prop: 'remark',
      label:'备注',
      minWidth:180,
      slotName: 'remark'
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
