export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '主键',
      field:'auditId',
      placeholder:'请输入主键',
      rules:[
        { required: true, message: '请输入主键', trigger: 'blur' },
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
      label: '订单编号，关联t_order表',
      field:'orderId',
      placeholder:'请输入订单编号，关联t_order表',
      rules:[
        { required: true, message: '请输入订单编号，关联t_order表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '产品代号',
      field:'productCode',
      placeholder:'请输入产品代号',
      rules:[
        { required: true, message: '请输入产品代号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '产品名称',
      field:'productName',
      placeholder:'请输入产品名称',
      rules:[
        { required: true, message: '请输入产品名称', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '风控策略码',
      field:'strategyCode',
      placeholder:'请输入风控策略码',
      rules:[
        { required: true, message: '请输入风控策略码', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
