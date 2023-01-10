export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '借款编号',
      field:'loanId',
      placeholder:'请输入借款编号',
      rules:[
        { required: true, message: '请输入借款编号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '订单编号,关联t_order表',
      field:'orderId',
      placeholder:'请输入订单编号,关联t_order表',
      rules:[
        { required: true, message: '请输入订单编号,关联t_order表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '借款编号(云融提供)',
      field:'loanBizNo',
      placeholder:'请输入借款编号(云融提供)',
      rules:[
        { required: true, message: '请输入借款编号(云融提供)', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '合作方业务号',
      field:'partnerBizNo',
      placeholder:'请输入合作方业务号',
      rules:[
        { required: true, message: '请输入合作方业务号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '合作方借款编号',
      field:'partnerLoanNo',
      placeholder:'请输入合作方借款编号',
      rules:[
        { required: true, message: '请输入合作方借款编号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '合同号',
      field:'contractNo',
      placeholder:'请输入合同号',
      rules:[
        { required: true, message: '请输入合同号', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
