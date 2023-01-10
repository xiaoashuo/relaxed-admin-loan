export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '客户编号，关联t_customer表',
      field:'customerId',
      placeholder:'请输入客户编号，关联t_customer表',
      rules:[
        { required: true, message: '请输入客户编号，关联t_customer表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '合作方客户编号',
      field:'partnerCustomerNo',
      placeholder:'请输入合作方客户编号',
      rules:[
        { required: true, message: '请输入合作方客户编号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '授信编号，关联t_credit表',
      field:'creditId',
      placeholder:'请输入授信编号，关联t_credit表',
      rules:[
        { required: true, message: '请输入授信编号，关联t_credit表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '订单号，关联t_order表',
      field:'orderId',
      placeholder:'请输入订单号，关联t_order表',
      rules:[
        { required: true, message: '请输入订单号，关联t_order表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '合作方客户编号',
      field:'orgUserNo',
      placeholder:'请输入合作方客户编号',
      rules:[
        { required: true, message: '请输入合作方客户编号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '客户姓名',
      field:'realName',
      placeholder:'请输入客户姓名',
      rules:[
        { required: true, message: '请输入客户姓名', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
