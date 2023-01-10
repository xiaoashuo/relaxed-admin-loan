export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
              
         {
      type:'text',
      label: '主键',
      field:'collateralId',
      placeholder:'请输入主键',
      rules:[
        { required: true, message: '请输入主键', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '订单编号',
      field:'orderId',
      placeholder:'请输入订单编号',
      rules:[
        { required: true, message: '请输入订单编号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '权属人姓名',
      field:'propOwner',
      placeholder:'请输入权属人姓名',
      rules:[
        { required: true, message: '请输入权属人姓名', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '权属人身份证',
      field:'propIdNo',
      placeholder:'请输入权属人身份证',
      rules:[
        { required: true, message: '请输入权属人身份证', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '权证号',
      field:'propCertNo',
      placeholder:'请输入权证号',
      rules:[
        { required: true, message: '请输入权证号', trigger: 'blur' },
      ]
      },
    
              
         {
      type:'text',
      label: '省市区代号',
      field:'districtCode',
      placeholder:'请输入省市区代号',
      rules:[
        { required: true, message: '请输入省市区代号', trigger: 'blur' },
      ]
      },
    
                      
  ],
}
