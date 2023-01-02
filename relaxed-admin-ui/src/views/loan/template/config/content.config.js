export const contentTableConfig={
  title: '模板文件',
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
  //   create: 'loan:template:add',
  //   update: 'loan:template:edit',
  //   delete: 'loan:template:delete'
  // },
  propList:[
           {
      prop: 'templateId',
      label:'主键id',
      minWidth:80,
      slotName: 'templateId'
    },
              {
      prop: 'templateCode',
      label:'模板代号',
      minWidth:180,
      slotName: 'templateCode'
    },
              {
      prop: 'templateName',
      label:'模板名称',
      minWidth:180,
      slotName: 'templateName'
    },


     {
      prop: 'fileType',
      label:'文件类型',
      minWidth:180,
      slotName: 'fileType'
    },
     {
      prop: 'remark',
      label:'模板描述',
      minWidth:180,
      slotName: 'remark'
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
