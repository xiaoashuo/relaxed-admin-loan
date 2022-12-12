export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
    {
      type:'text',
      label: '配置id',
      field:'id',
      isHidden: true
    },
    {
      type:'text',
      label: '配置名称',
      field:'name',
      placeholder:'请输入配置名称',
      rules:[
        { required: true, message: '请输入配置名称', trigger: 'blur' },
      ]

    },
    {
      type:'text',
      label: '配置键值',
      field:'confKey',
      placeholder:'请输入配置键值',
      rules:[
        { required: true, message: '请输入配置键值', trigger: 'blur' },

      ]
    },
    {
      type:'text',
      label: '配置值',
      field:'confValue',
      placeholder:'请输入配置值',
      rules:[
        { required: true, message: '请输入配置值', trigger: 'blur' },

      ]
    },
    {
      type:'text',
      label: '分类',
      field:'category',
      placeholder:'请输入分类',
      rules:[
        { required: true, message: '请输入分类', trigger: 'blur' },

      ]
    },
    {
      type:'text',
      label: '备注信息',
      field:'remarks',
      placeholder:'请输入备注',

       config: {
         type:"textarea"
       }
    },

  ],
}
