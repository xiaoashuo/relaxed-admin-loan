export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[



         {
      type:'text',
      label: '银行编码',
      field:'bankCode',
      placeholder:'请输入银行编码',
      rules:[
        { required: true, message: '请输入银行编码', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '银行名称',
      field:'bankName',
      placeholder:'请输入银行名称',
      rules:[
        { required: true, message: '请输入银行名称', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '描述',
      field:'remark',
      placeholder:'请输入描述',
      rules:[
        { required: true, message: '请输入描述', trigger: 'blur' },
      ]
      },





  ],
}
