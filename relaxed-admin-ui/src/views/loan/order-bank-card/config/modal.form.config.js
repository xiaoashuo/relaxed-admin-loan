export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '银行卡主键',
      field:'bankCardId',
      placeholder:'请输入银行卡主键',
      rules:[
        { required: true, message: '请输入银行卡主键', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '订单号,关联t_order表',
      field:'orderId',
      placeholder:'请输入订单号,关联t_order表',
      rules:[
        { required: true, message: '请输入订单号,关联t_order表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '账户类型（0-对公 1-对私 2-第三方支付）',
      field:'accountType',
      placeholder:'请输入账户类型（0-对公 1-对私 2-第三方支付）',
      rules:[
        { required: true, message: '请输入账户类型（0-对公 1-对私 2-第三方支付）', trigger: 'blur' },
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
