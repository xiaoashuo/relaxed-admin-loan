export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
    {
      type:'text',
      label: '用户id',
      field:'id',
      isHidden: true
    },
    {
      type:'text',
      label: '角色名称',
      field:'name',
      placeholder:'请输入角色名称',
      rules:[
        { required: true, message: '请输入角色名称', trigger: 'change' },
      ]
    },
    {
      type:'text',
      label: '标识',
      field:'code',
      placeholder:'请输入角色标识',
      rules:[
        { required: true, message: '请输入角色标识', trigger: 'change' },
      ]
    },
    {
      type:'text',
      label: '描述',
      field:'remarks',
      placeholder:'请输入描述'
    },
    // {
    //   field: 'createdTime',
    //   type: 'datepicker',
    //   label:'创建时间',
    //   rules:[],
    //   config:{
    //     startPlaceHolder:'开始时间',
    //     endPlaceHolder:'结束时间',
    //     type: 'daterange'
    //   }
    //
    // }
  ],
}
