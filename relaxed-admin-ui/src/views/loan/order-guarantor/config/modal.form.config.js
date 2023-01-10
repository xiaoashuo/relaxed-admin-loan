export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '关联人主键',
      field:'guarantorId',
      placeholder:'请输入关联人主键',
      rules:[
        { required: true, message: '请输入关联人主键', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '订单号',
      field:'orderId',
      placeholder:'请输入订单号',
      rules:[
        { required: true, message: '请输入订单号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '担保机构/担保人名称',
      field:'guarantorName',
      placeholder:'请输入担保机构/担保人名称',
      rules:[
        { required: true, message: '请输入担保机构/担保人名称', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '担保函编号',
      field:'guarantorNo',
      placeholder:'请输入担保函编号',
      rules:[
        { required: true, message: '请输入担保函编号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '备注',
      field:'remark',
      placeholder:'请输入备注',
      rules:[
        { required: true, message: '请输入备注', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '逻辑删除标识，未删除为 0，已删除为删除时间',
      field:'valid',
      placeholder:'请输入逻辑删除标识，未删除为 0，已删除为删除时间',
      rules:[
        { required: true, message: '请输入逻辑删除标识，未删除为 0，已删除为删除时间', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
