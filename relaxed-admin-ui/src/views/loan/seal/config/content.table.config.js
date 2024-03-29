export const contentTableConfig={
  title: '签章图片表',
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
    // create: 'loan:seal:add',
    update: 'loan:seal:edit',
    // delete: 'loan:seal:delete'
  },
  propList:[
           {
      prop: 'sealId',
      label:'主键id',
      minWidth:80,
      slotName: 'sealId'
    },
              {
      prop: 'sealCode',
      label:'图片编号',
      minWidth:180,
      slotName: 'sealCode'
    },
              {
      prop: 'sealSubject',
      label:'主体名称',
      minWidth:180,
      slotName: 'sealSubject'
    },
              {
      prop: 'sealRemark',
      label:'描述',
      minWidth:180,
      slotName: 'sealRemark'
    },
              {
      prop: 'sealAddress',
      label:'地址',
      minWidth:180,
      slotName: 'sealAddress'
    },


              {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
    },

          {

      label:'操作',
      minWidth:100,
      slotName:'handler'
    }
  ]
}
