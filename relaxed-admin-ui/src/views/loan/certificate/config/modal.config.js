export const modalConfig= {

  formItems:[
              
         {
      type:'text',
      label: '签章证书主键id',
      field:'certificateId',
      placeholder:'请输入签章证书主键id',
      rules:[
        { required: true, message: '请输入签章证书主键id', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '证书别名',
      field:'certificateAlias',
      placeholder:'请输入证书别名',
      rules:[
        { required: true, message: '请输入证书别名', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '主体类型 1个人 2公司',
      field:'subjectType',
      placeholder:'请输入主体类型 1个人 2公司',
      rules:[
        { required: true, message: '请输入主体类型 1个人 2公司', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '个人及个人名称,公司及名称',
      field:'certificateSubject',
      placeholder:'请输入个人及个人名称,公司及名称',
      rules:[
        { required: true, message: '请输入个人及个人名称,公司及名称', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '主体证件号 个人 身份证号 公司 则营业执照编码',
      field:'certificateNo',
      placeholder:'请输入主体证件号 个人 身份证号 公司 则营业执照编码',
      rules:[
        { required: true, message: '请输入主体证件号 个人 身份证号 公司 则营业执照编码', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '证书密码',
      field:'certificatePwd',
      placeholder:'请输入证书密码',
      rules:[
        { required: true, message: '请输入证书密码', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
