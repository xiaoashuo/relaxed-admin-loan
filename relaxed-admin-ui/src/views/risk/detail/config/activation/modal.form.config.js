export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[

    {
      type:'text',
      label: '策略名称',
      field:'activationName',
      placeholder:'请输入策略名称',
      rules:[
        { required: true, message: '请输入策略名称', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: '标签',
      field:'label',
      placeholder:'请输入标签',
      rules:[
        { required: true, message: '请输入标签', trigger: 'blur' },
      ]
    },




    {
      type:'text',
      label: '底部阈值',
      field:'bottom',
      placeholder:'请输入底部阈值',
      rules:[
        { required: true, message: '请输入底部阈值', trigger: 'blur' },
      ]
    },
    {
      type:'text',
      label: '中部阀值',
      field:'median',
      placeholder:'请输入中部阀值',
      rules:[
        { required: true, message: '请输入中部阀值', trigger: 'blur' },
      ]
    },
    {
      type:'text',
      label: '顶部阀值',
      field:'high',
      placeholder:'请输入顶部阀值',
      rules:[
        { required: true, message: '请输入顶部阀值', trigger: 'blur' },
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



  ],
}
