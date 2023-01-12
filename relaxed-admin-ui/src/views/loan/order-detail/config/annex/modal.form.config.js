export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '文件主键',
      field:'fileId',
      placeholder:'请输入文件主键',
      rules:[
        { required: true, message: '请输入文件主键', trigger: 'blur' },
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
      label: '文件流水号',
      field:'fileNo',
      placeholder:'请输入文件流水号',
      rules:[
        { required: true, message: '请输入文件流水号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: ' 文件名',
      field:'fileName',
      placeholder:'请输入 文件名',
      rules:[
        { required: true, message: '请输入 文件名', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '文件类型（https://www.yuque.com/ntgnx4/hoyh19/ughdvq#g8rHZ）',
      field:'fileType',
      placeholder:'请输入文件类型（https://www.yuque.com/ntgnx4/hoyh19/ughdvq#g8rHZ）',
      rules:[
        { required: true, message: '请输入文件类型（https://www.yuque.com/ntgnx4/hoyh19/ughdvq#g8rHZ）', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '文件网址',
      field:'fileUrl',
      placeholder:'请输入文件网址',
      rules:[
        { required: true, message: '请输入文件网址', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
