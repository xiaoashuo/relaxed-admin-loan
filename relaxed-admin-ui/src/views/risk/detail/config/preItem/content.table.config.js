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
    // create: 'risk:risk-pre-item:add',
    // update: 'risk:risk-pre-item:edit',
    // delete: 'risk:risk-pre-item:delete'
  },
  propList:[
    {
      prop: 'id',
      label:'ID',
      minWidth: 100,
      hidden: true
    },
    {
      prop: 'modelId',
      label:'模型ID',
      minWidth:180,
    },
    {
      prop: 'sourceField',
      label:'来源字段',
      minWidth:180,
    },
    {
      prop: 'sourceLabel',
      label:'来源标签',
      minWidth:180,
    },
    {
      prop: 'destField',
      label:'目标字段',
      minWidth:180,
    },

    {
      prop: 'destLabel',
      label:'目标标签',
      minWidth:180,
    },
    {
      prop: 'args',
      label:'参数',
      minWidth:180,
    },
    {
      prop: 'plugin',
      label:'转换插件',
      minWidth:180,
    },
    {
      prop: 'configJson',
      label:'响应字段配置信息',
      minWidth:180,
    },

    {
      prop: 'status',
      label:'状态',
      minWidth:180,
      slotName:'status'
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
