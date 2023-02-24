export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[

    {
      type:'text',
      label: '主键',
      field:'id',
      isHidden: true
    },


    {
      type:'text',
      label: '名称',
      field:'activationName',
      placeholder:'请输入名称',
      rules:[
        { required: true, message: '请输入名称', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: '',
      field:'label',
      placeholder:'请输入',
      rules:[
        { required: true, message: '请输入', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: 'model id',
      field:'modelId',
      placeholder:'请输入model id',
      rules:[
        { required: true, message: '请输入model id', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: '注释',
      field:'comment',
      placeholder:'请输入注释',
      rules:[
        { required: true, message: '请输入注释', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: '底部阀值',
      field:'bottom',
      placeholder:'请输入底部阀值',
      rules:[
        { required: true, message: '请输入底部阀值', trigger: 'blur' },
      ]
    },


  ],
}
