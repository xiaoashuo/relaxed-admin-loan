export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[



    {
      type:'text',
      label: '姓名',
      field:'relatedName',
      placeholder:'请输入姓名',
      rules:[
        { required: true, message: '请输入姓名', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: '证件号码',
      field:'certificateNo',
      placeholder:'请输入证件号码',
      rules:[
        { required: true, message: '请输入证件号码', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: '手机号',
      field:'mobileNo',
      placeholder:'请输入手机号',
      rules:[
        { required: true, message: '请输入手机号', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: '地址',
      field:'address',
      placeholder:'请输入地址',
      rules:[
        { required: true, message: '请输入地址', trigger: 'blur' },
      ]
    },


  ],
}
