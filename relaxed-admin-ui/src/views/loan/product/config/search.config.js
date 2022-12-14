export const searchConfig={
  showHeader:true,
  formItems:[
    {
      type:'text',
      label: '产品名称',
      field:'productName',
      placeholder:'请输入产品名称',
      rules:[
        { required: true, message: '请输入产品名称', trigger: 'blur' },
      ]
    },
  ],
}
