export const contentTableConfig={
  title: '审核拒绝原因表',
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
    create: 'loan:audit-detail:add',
    update: 'loan:audit-detail:edit',
    delete: 'loan:audit-detail:delete'
  },
  propList:[
           {
      prop: 'auditDetailId',
      label:'主键',
      minWidth:180,
      slotName: 'auditDetailId'
    },
              {
      prop: 'orderId',
      label:'订单编号，关联t_order表',
      minWidth:180,
      slotName: 'orderId'
    },
              {
      prop: 'auditId',
      label:'审核编号，关联t_audit表',
      minWidth:180,
      slotName: 'auditId'
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
      prop: 'auditType',
      label:'0 ： 系统审核。1 人工审核',
      minWidth:180,
      slotName: 'auditType'
    },
              {
      prop: 'auditStatus',
      label:'审批标记',
      minWidth:180,
      slotName: 'auditStatus'
    },
              {
      prop: 'resTime',
      label:'响应时间',
      minWidth:180,
      slotName: 'resTime'
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
