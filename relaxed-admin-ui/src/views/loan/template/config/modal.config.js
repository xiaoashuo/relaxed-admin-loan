export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[






         {
      type:'text',
      label: '模板名称',
      field:'templateName',
      placeholder:'请输入模板名称',
      rules:[
        { required: true, message: '请输入模板名称', trigger: 'blur' },
      ]
      },



    {
      type:'dict-select',
      label: '文件类型',
      field:'fileType',
      config:{
        dictCode: 'file_type',
        clearable: true
      },
      rules:[
        { required: true, message: '请选择文件类型', trigger: 'blur' },
      ]
    },



  ],
}
