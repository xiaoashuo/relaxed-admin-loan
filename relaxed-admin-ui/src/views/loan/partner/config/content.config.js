export const contentTableConfig={
  title: '合作方',
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
    create: 'loan:partner:add',
    update: 'loan:partner:edit',
    delete: 'loan:partner:delete'
  },
  propList:[
           {
      prop: 'partnerId',
      label:'主键',
      minWidth:180,
      slotName: 'partnerId'
    },
              {
      prop: 'partnerAlias',
      label:'合作方别名',
      minWidth:180,
      slotName: 'partnerAlias'
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
