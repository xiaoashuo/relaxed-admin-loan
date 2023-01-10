export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '填充项编号',
      field:'itemFillerId',
      placeholder:'请输入填充项编号',
      rules:[
        { required: true, message: '请输入填充项编号', trigger: 'blur' },
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
      label: '账单id,用于关联t_bill表',
      field:'billId',
      placeholder:'请输入账单id,用于关联t_bill表',
      rules:[
        { required: true, message: '请输入账单id,用于关联t_bill表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '账单科目id,用于关联t_bill_item表',
      field:'billItemId',
      placeholder:'请输入账单科目id,用于关联t_bill_item表',
      rules:[
        { required: true, message: '请输入账单科目id,用于关联t_bill_item表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '填充名称（可自定义）',
      field:'fillerName',
      placeholder:'请输入填充名称（可自定义）',
      rules:[
        { required: true, message: '请输入填充名称（可自定义）', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '填充类型：2- 减免 11- 实收',
      field:'fillerType',
      placeholder:'请输入填充类型：2- 减免 11- 实收',
      rules:[
        { required: true, message: '请输入填充类型：2- 减免 11- 实收', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
