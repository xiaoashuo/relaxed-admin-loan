export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
    {
      type:'text',
      label: '用户id',
      field:'id',
      isHidden: true
    },


    {
      type:'text',
      label: '字典标识',
      field:'dictCode',
      config: {
        disabled: true
      }

    },
    {
      type:'text',
      label: '文本值',
      field:'name',
      rules:[
        { required: true, message: '请输入文本值', trigger: 'blur' },
      ]
    },
    {
      type:'text',
      label: '数据值',
      field:'value',
      rules:[
        { required: true, message: '请输入数据值', trigger: 'blur' },
      ]
    },
    {
      type:'dict-select',
      label: '状态',
      field:'status',
      placeholder:'请选择',
      config:{
        dictCode: 'status'
      },

      rules:[
        { required: true, message: '请选则状态', trigger: 'blur' },
      ],

    },
    {
      type:'number',
      label: '排序',
      field:'sort',
      config:{
        controlsPosition:"right",
      }
    },
    {
      type:'text',
      label: '描述',
      field:'remarks',
    },


    // {
    //   field: 'createdTime',
    //   type: 'datepicker',
    //   label:'创建时间',
    //   rules:[],
    //   config:{
    //     startPlaceHolder:'开始时间',
    //     endPlaceHolder:'结束时间',
    //     type: 'daterange'
    //   }
    //
    // }
  ],
}
