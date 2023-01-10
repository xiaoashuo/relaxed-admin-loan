export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '账单科目表',
      field:'billItemId',
      placeholder:'请输入账单科目表',
      rules:[
        { required: true, message: '请输入账单科目表', trigger: 'blur' },
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
      label: '账单编号,用于关联t_bill表',
      field:'billId',
      placeholder:'请输入账单编号,用于关联t_bill表',
      rules:[
        { required: true, message: '请输入账单编号,用于关联t_bill表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '科目名称',
      field:'itemName',
      placeholder:'请输入科目名称',
      rules:[
        { required: true, message: '请输入科目名称', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '科目类型（1: 本金 11: 利息 21: 罚息）',
      field:'itemType',
      placeholder:'请输入科目类型（1: 本金 11: 利息 21: 罚息）',
      rules:[
        { required: true, message: '请输入科目类型（1: 本金 11: 利息 21: 罚息）', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '应收金额',
      field:'receivableAmt',
      placeholder:'请输入应收金额',
      rules:[
        { required: true, message: '请输入应收金额', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
