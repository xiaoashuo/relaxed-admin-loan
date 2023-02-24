export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[






    {
      type:'text',
      label: '字段名',
      field:'fieldName',
      placeholder:'请输入字段名',
      rules:[
        { required: true, message: '请输入字段名', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: '显示名称',
      field:'label',
      placeholder:'请输入显示名称',
      rules:[
        { required: true, message: '请输入显示名称', trigger: 'blur' },
      ]
    },


    {
      type:'dict-select',
      label: '字段类型',
      field:'fieldType',
      placeholder:'请输入字段类型',
      rules:[
        { required: true, message: '请输入字段类型', trigger: 'blur' },
      ],
      config:{
        dictCode: 'field_type',
        clearable: true
      }
    },


    {
      type:'dict-select',
      label: '校验类型',
      field:'validateType',
      placeholder:'请输入校验类型',
      rules:[
        { required: true, message: '请输入校验类型', trigger: 'blur' },
      ],
      config:{
        dictCode: 'validate_field_type',
        clearable: true
      }
    },
    {
      type:'text',
      label: '验证参数',
      field:'validateArgs',
      placeholder:'请输入验证参数',

    },

  ],
}
