export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[


         {
      type:'text',
      label: '合作方别名',
      field:'partnerAlias',
      placeholder:'请输入合作方别名',
      rules:[
        { required: true, message: '请输入合作方别名', trigger: 'blur' },
      ]
      },







  ],
}
