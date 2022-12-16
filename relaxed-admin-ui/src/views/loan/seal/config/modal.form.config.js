export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[







         {
      type:'text',
      label: '主体名称',
      field:'sealSubject',
      placeholder:'请输入主体名称',
      rules:[
        { required: true, message: '请输入主体名称', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '图片描述',
      field:'sealRemark',
      placeholder:'请输入图片描述',
      rules:[
        { required: true, message: '请输入图片描述', trigger: 'blur' },
      ]
      },



    {
      type:'dict-radio-group',
      label: '图片类型',
      field:'sealPictureType',
      rules:[
        { required: true, message: '请选择图片类型', trigger: 'blur' },
      ],
      config:{
        dictCode: 'seal_picture_type',
        clearable: true
      }
    },
    {
      type:'slot',
      label: '生成方式',
      field:'sealSource',
      slotName:'sealSource',
      config:{
        options:[
          {
            label:'系统生成',
            value: 1
          },
          {
            label:'自定义',
            value: 2
          }
        ]
      },
      rules:[
        { required: true, message: '请选择生成方式', trigger: 'blur' },
      ]
    },



  ],
}
