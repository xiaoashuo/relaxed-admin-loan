export const contentTableConfig={
  title: '',
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
    // create: 'risk:risk-field:add',
    // update: 'risk:risk-field:edit',
    // delete: 'risk:risk-field:delete'
  },
  propList:[
    {
      prop: 'id',
      label:'主键',
      minWidth: 80,
    },

    {
      prop: 'fieldName',
      label:'字段',
      minWidth:180,
    },
    {
      prop: 'label',
      label:'名称',
      minWidth:150,
    },
    {
      prop: 'fieldType',
      label:'字段类型',
      minWidth:120,
    },


    {
      prop: 'indexed',
      label:'索引',
      minWidth:80,
    },
    {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
      slotName: 'createdTime'
    },

    {

      label:'操作',
      minWidth:250,
      slotName:'handler'
    }
  ]
}
