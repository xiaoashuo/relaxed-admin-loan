export const modalConfig= {

  formItems:[
              
         {
      type:'text',
      label: '主键',
      field:'projectId',
      placeholder:'请输入主键',
      rules:[
        { required: true, message: '请输入主键', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '信托项目主键（t_config_partner）',
      field:'partnerId',
      placeholder:'请输入信托项目主键（t_config_partner）',
      rules:[
        { required: true, message: '请输入信托项目主键（t_config_partner）', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '信托产品主键（t_config_product）',
      field:'productId',
      placeholder:'请输入信托产品主键（t_config_product）',
      rules:[
        { required: true, message: '请输入信托产品主键（t_config_product）', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '信托计划主键（t_config_trust_plan）',
      field:'trustPlanId',
      placeholder:'请输入信托计划主键（t_config_trust_plan）',
      rules:[
        { required: true, message: '请输入信托计划主键（t_config_trust_plan）', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '合作项目代号',
      field:'projectCode',
      placeholder:'请输入合作项目代号',
      rules:[
        { required: true, message: '请输入合作项目代号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '合作项目名称',
      field:'projectName',
      placeholder:'请输入合作项目名称',
      rules:[
        { required: true, message: '请输入合作项目名称', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
