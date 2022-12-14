export const contentTableConfig={
  title: '模板文件配置表',
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
    create: 'loan:template:add',
    update: 'loan:template:edit',
    delete: 'loan:template:delete'
  },
  propList:[
           {
      prop: 'templateId',
      label:'模板主键id',
      minWidth:180,
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
      prop: 'templatePath',
      label:'模板文件(填充模板)存放路径',
      minWidth:180,
      slotName: 'templatePath'
    },
              {
      prop: 'datafilePath',
      label:'数据文件(导入模板)存放路径(如有)',
      minWidth:180,
      slotName: 'datafilePath'
    },
              {
      prop: 'fileType',
      label:'文件类型1.服务信托2.财富端确认书',
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
      prop: 'createBy',
      label:'创建人',
      minWidth:180,
      slotName: 'createBy'
    },
              {
      prop: 'updateBy',
      label:'修改人',
      minWidth:180,
      slotName: 'updateBy'
    },
              {
      prop: 'deleted',
      label:'逻辑删除标识，未删除为 0，已删除为删除时间',
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
