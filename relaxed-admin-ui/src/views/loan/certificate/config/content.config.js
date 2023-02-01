export const contentTableConfig={
  title: '签章证书表',
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
  //   create: 'loan:certificate:add',
    update: 'loan:certificate:edit',
  //   delete: 'loan:certificate:delete'
   },
  propList:[
           {
      prop: 'certificateId',
      label:'id',
      minWidth:100,
      slotName: 'certificateId'
    },
              {
      prop: 'certificateAlias',
      label:'证书别名',
      minWidth:180,
      slotName: 'certificateAlias'
    },
              {
      prop: 'subjectType',
      label:'主体类型',
      minWidth:180,
      slotName: 'subjectType'
    },
              {
      prop: 'certificateSubject',
      label:'主体名称',
      minWidth:180,
      slotName: 'certificateSubject'
    },
              {
      prop: 'certificateNo',
      label:'主体证件号',
      minWidth:180,
      slotName: 'certificateNo'
    },
              {
      prop: 'certificatePwd',
      label:'证书密码',
      minWidth:180,
      slotName: 'certificatePwd'
    },
              {
      prop: 'certificateRemark',
      label:'证书描述',
      minWidth:180,
      slotName: 'certificateRemark'
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
