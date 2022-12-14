export const modalConfig= {

  formItems:[
              
         {
      type:'text',
      label: '模板主键id',
      field:'templateId',
      placeholder:'请输入模板主键id',
      rules:[
        { required: true, message: '请输入模板主键id', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '模板代号',
      field:'templateCode',
      placeholder:'请输入模板代号',
      rules:[
        { required: true, message: '请输入模板代号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '模板名称',
      field:'templateName',
      placeholder:'请输入模板名称',
      rules:[
        { required: true, message: '请输入模板名称', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '模板文件(填充模板)存放路径',
      field:'templatePath',
      placeholder:'请输入模板文件(填充模板)存放路径',
      rules:[
        { required: true, message: '请输入模板文件(填充模板)存放路径', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '数据文件(导入模板)存放路径(如有)',
      field:'datafilePath',
      placeholder:'请输入数据文件(导入模板)存放路径(如有)',
      rules:[
        { required: true, message: '请输入数据文件(导入模板)存放路径(如有)', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '文件类型1.服务信托2.财富端确认书',
      field:'fileType',
      placeholder:'请输入文件类型1.服务信托2.财富端确认书',
      rules:[
        { required: true, message: '请输入文件类型1.服务信托2.财富端确认书', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
