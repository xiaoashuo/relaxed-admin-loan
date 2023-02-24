export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[

    {
      type:'text',
      label: '模型名称',
      field:'modelName',
      placeholder:'请输入模型名称',
      rules:[
        { required: true, message: '请输入模型名称', trigger: 'blur' },
      ]
    },



    {
      type:'text',
      label: '描述',
      field:'label',
      placeholder:'请输入',
      rules:[
        { required: true, message: '请输入描述', trigger: 'blur' },
      ]
    },

    {
      type:'text',
      label: '实体主键',
      field:'entityName',
      placeholder:'请输入事件中标识实体的主键',
      rules:[
        { required: true, message: '请输入事件中标识实体的主键', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: '事件主键',
      field:'entryName',
      placeholder:'请输入事件主键',
      rules:[
        { required: true, message: '请输入事件主键', trigger: 'blur' },
      ]
    },
    {
      type:'text',
      label: '事件时间',
      field:'referenceDate',
      placeholder:'请输入',
      rules:[
        { required: true, message: '请输入事件时间', trigger: 'blur' },
      ]
    },

  ],
}
