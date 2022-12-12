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
      label: '标识',
      field:'code',
      placeholder:'请输入标识',
      rules:[
        { required: true, message: '请输入标识', trigger: 'blur' },
      ]
    },
    {
      type:'dict-radio-group',
      label: '数据类型',
      field:'valueType',
      config:{
        dictCode: 'dict_value_type',
        clearable: true
      },
      rules:[
        { required: true, message: '请选择数据类型', trigger: 'blur' },
      ]
    },
    {
      type:'text',
      label: '名称',
      field:'title',
      placeholder:'请输入名称',
      rules:[
        { required: true, message: '请输入名称', trigger: 'blur' },
      ]
    },
    {
      type:'text',
      label: '描述',
      field:'remarks',
      placeholder:'请输入描述'
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
