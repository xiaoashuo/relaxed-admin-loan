export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[




         {
      type:'text',
      label: '图片编号',
      field:'sealCode',
      placeholder:'请输入图片编号',
      rules:[
        { required: true, message: '请输入图片编号', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '主体名称',
      field:'sealSubject',
      placeholder:'请输入主体名称',
      rules:[
        { required: true, message: '请输入主体名称', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '图片描述',
      field:'sealRemark',
      placeholder:'请输入图片描述',
      rules:[
        { required: true, message: '请输入图片描述', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '图片地址',
      field:'sealAddress',
      placeholder:'请输入图片地址',
      rules:[
        { required: true, message: '请输入图片地址', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '创建人',
      field:'createBy',
      placeholder:'请输入创建人',
      rules:[
        { required: true, message: '请输入创建人', trigger: 'blur' },
      ]
      },


  ],
}
