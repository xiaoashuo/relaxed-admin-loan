export const modalConfig= {

  formItems:[
              
         {
      type:'text',
      label: '主键',
      field:'trustPlanId',
      placeholder:'请输入主键',
      rules:[
        { required: true, message: '请输入主键', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '信托计划名称',
      field:'trustPlanName',
      placeholder:'请输入信托计划名称',
      rules:[
        { required: true, message: '请输入信托计划名称', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '信托计划编号',
      field:'trustPlanNo',
      placeholder:'请输入信托计划编号',
      rules:[
        { required: true, message: '请输入信托计划编号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '信托计划成立日期',
      field:'establishedTime',
      placeholder:'请输入信托计划成立日期',
      rules:[
        { required: true, message: '请输入信托计划成立日期', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '信托计划到期日期',
      field:'expireTime',
      placeholder:'请输入信托计划到期日期',
      rules:[
        { required: true, message: '请输入信托计划到期日期', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '信托计划摊还期开始日期',
      field:'repayTime',
      placeholder:'请输入信托计划摊还期开始日期',
      rules:[
        { required: true, message: '请输入信托计划摊还期开始日期', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
