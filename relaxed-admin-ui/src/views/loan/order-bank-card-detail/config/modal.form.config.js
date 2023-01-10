export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '银行卡主键',
      field:'detailId',
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
      label: '银行卡编号,关联t_order_bank_card表',
      field:'bankCardId',
      placeholder:'请输入银行卡编号,关联t_order_bank_card表',
      rules:[
        { required: true, message: '请输入银行卡编号,关联t_order_bank_card表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '共同借款人编号，关联t_order_borrow',
      field:'userId',
      placeholder:'请输入共同借款人编号，关联t_order_borrow',
      rules:[
        { required: true, message: '请输入共同借款人编号，关联t_order_borrow', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '是否主借人0:非主借人 1:主借人',
      field:'loanType',
      placeholder:'请输入是否主借人0:非主借人 1:主借人',
      rules:[
        { required: true, message: '请输入是否主借人0:非主借人 1:主借人', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '姓名',
      field:'realName',
      placeholder:'请输入姓名',
      rules:[
        { required: true, message: '请输入姓名', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
