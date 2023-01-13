export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[





         {
      type:'text',
      label: '账户类型',
      field:'accountType',
      placeholder:'请输入账户类型',
      rules:[
        { required: true, message: '请输入账户类型', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '户名',
      field:'accountName',
      placeholder:'请输入户名',
      rules:[
        { required: true, message: '请输入户名', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '户号',
      field:'accountNo',
      placeholder:'请输入户号',
      rules:[
        { required: true, message: '请输入户号', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '银行代号',
      field:'bankCode',
      placeholder:'请输入银行代号',
      rules:[
        { required: true, message: '请输入银行代号', trigger: 'blur' },
      ]
      },


  ],
}
