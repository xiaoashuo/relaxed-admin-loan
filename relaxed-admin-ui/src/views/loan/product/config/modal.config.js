export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[

      {
      type:'text',
      label: '主键',
      field:'productId',
      placeholder:'请输入主键',
      isHidden: true
      },


         {
      type:'text',
      label: '产品名称',
      field:'productName',
      placeholder:'请输入产品名称',
      rules:[
        { required: true, message: '请输入产品名称', trigger: 'blur' },
      ]
      },
    {
      type:'text',
      label: 'code',
      field:'productCode',
      placeholder:'请输入产品code',
      rules:[
        { required: true, message: '请输入产品code', trigger: 'blur' },
      ]
    },

         {
      type:'text',
      label: '产品描述',
      field:'productDesc',
      placeholder:'请输入产品描述',
      rules:[
        { required: true, message: '请输入产品描述', trigger: 'blur' },
      ]
      },







  ],
}
