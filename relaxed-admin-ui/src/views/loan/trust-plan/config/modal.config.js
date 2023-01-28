export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[

         {
      type:'text',
      label: '主键',
      field:'trustPlanId',
      placeholder:'请输入主键',
      isHidden: true
      },


         {
      type:'text',
      label: '名称',
      field:'trustPlanName',
      placeholder:'请输入信托计划名称',
      rules:[
        { required: true, message: '请输入信托计划名称', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '编号',
      field:'trustPlanNo',
      placeholder:'请输入信托计划编号',
      rules:[
        { required: true, message: '请输入信托计划编号', trigger: 'blur' },
      ]
      },


         {
      type:'datepicker',
      label: '成立日期',
      field:'establishedTime',
      rules:[
        { required: true, message: '请选择信托计划成立日期', trigger: 'blur' },
      ],
           config:{
          type:'date',
             placeholder:'请选择信托计划成立日期',
             'value-format':'yyyy-MM-dd',
           }
      },


         {
      type:'datepicker',
      label: '到期日期',
      field:'expireTime',

      rules:[
        { required: true, message: '请选择信托计划到期日期', trigger: 'blur' },
      ],
           config:{
             type:'date',
             placeholder:'请选择信托计划到期日期',
             'value-format':'yyyy-MM-dd',
           }
      },


         {
      type:'datepicker',
      label: '摊还期开始日期',
      field:'repayTime',
      rules:[
        { required: true, message: '请选择信托计划摊还期开始日期', trigger: 'blur' },
      ],
           config:{
             type:'date',
             placeholder:'请选择信托计划摊还期开始日期',
             'value-format':'yyyy-MM-dd',
           }
      },


  ],
}
