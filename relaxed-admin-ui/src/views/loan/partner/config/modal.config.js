export const modalConfig= {

  formItems:[
              
         {
      type:'text',
      label: '主键',
      field:'partnerId',
      placeholder:'请输入主键',
      rules:[
        { required: true, message: '请输入主键', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '合作方别名',
      field:'partnerAlias',
      placeholder:'请输入合作方别名',
      rules:[
        { required: true, message: '请输入合作方别名', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '是否有效(0:未删除 1 或时间戳为已删除)',
      field:'deleted',
      placeholder:'请输入是否有效(0:未删除 1 或时间戳为已删除)',
      rules:[
        { required: true, message: '请输入是否有效(0:未删除 1 或时间戳为已删除)', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '创建时间',
      field:'createdTime',
      placeholder:'请输入创建时间',
      rules:[
        { required: true, message: '请输入创建时间', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '更新时间',
      field:'updatedTime',
      placeholder:'请输入更新时间',
      rules:[
        { required: true, message: '请输入更新时间', trigger: 'blur' },
      ]
      },
    
       
  ],
}
