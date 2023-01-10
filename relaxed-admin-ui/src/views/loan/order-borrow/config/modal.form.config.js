export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[

         {
      type:'text',
      label: '关联人主键',
      field:'userId',
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
      label: '关联人姓名',
      field:'relatedName',
      placeholder:'请输入关联人姓名',
      rules:[
        { required: true, message: '请输入关联人姓名', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '联系人证件号码',
      field:'certificateNo',
      placeholder:'请输入联系人证件号码',
      rules:[
        { required: true, message: '请输入联系人证件号码', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '联系人电话',
      field:'mobileNo',
      placeholder:'请输入联系人电话',
      rules:[
        { required: true, message: '请输入联系人电话', trigger: 'blur' },
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
