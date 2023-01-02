export const contentTableConfig={
  title: '项目模板配置表',
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
    // create: 'loan:project-template:add',
    // update: 'loan:project-template:edit',
    // delete: 'loan:project-template:delete'
  },
  propList:[
           {
      prop: 'projectTemplateId',
      label:'主键',
      minWidth:180,
      slotName: 'projectTemplateId'
    },
    {
      prop: 'projectName',
      label:'项目名称',
      minWidth:180,
      slotName: 'projectName'
    },
              {
      prop: 'fileType',
      label:'文件类型',
      minWidth:180,
      slotName: 'fileType'
    },
              {
      prop: 'templateName',
      label:'模板名称',
      minWidth:180,
      slotName: 'templateName'
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
