export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '账单编号',
      field:'billId',
      placeholder:'请输入账单编号',
      rules:[
        { required: true, message: '请输入账单编号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '借款编号,用于关联t_loan表',
      field:'loanId',
      placeholder:'请输入借款编号,用于关联t_loan表',
      rules:[
        { required: true, message: '请输入借款编号,用于关联t_loan表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '合作方账单编号',
      field:'partnerBillNo',
      placeholder:'请输入合作方账单编号',
      rules:[
        { required: true, message: '请输入合作方账单编号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '账单期号',
      field:'termNo',
      placeholder:'请输入账单期号',
      rules:[
        { required: true, message: '请输入账单期号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '开始日期',
      field:'startDate',
      placeholder:'请输入开始日期',
      rules:[
        { required: true, message: '请输入开始日期', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '结束日期',
      field:'endDate',
      placeholder:'请输入结束日期',
      rules:[
        { required: true, message: '请输入结束日期', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
