export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '主键',
      field:'auditDetailId',
      placeholder:'请输入主键',
      rules:[
        { required: true, message: '请输入主键', trigger: 'blur' },
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
      label: '审核编号，关联t_audit表',
      field:'auditId',
      placeholder:'请输入审核编号，关联t_audit表',
      rules:[
        { required: true, message: '请输入审核编号，关联t_audit表', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '拒绝原因码仅拒绝时返回，可多个以“,”分隔',
      field:'reasonCode',
      placeholder:'请输入拒绝原因码仅拒绝时返回，可多个以“,”分隔',
      rules:[
        { required: true, message: '请输入拒绝原因码仅拒绝时返回，可多个以“,”分隔', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '拒绝原因描述 仅拒绝时返回',
      field:'reasonDesc',
      placeholder:'请输入拒绝原因描述 仅拒绝时返回',
      rules:[
        { required: true, message: '请输入拒绝原因描述 仅拒绝时返回', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '0 ： 系统审核。1 人工审核',
      field:'auditType',
      placeholder:'请输入0 ： 系统审核。1 人工审核',
      rules:[
        { required: true, message: '请输入0 ： 系统审核。1 人工审核', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
