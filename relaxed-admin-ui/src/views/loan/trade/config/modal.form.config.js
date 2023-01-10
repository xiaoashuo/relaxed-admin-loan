export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[

         {
      type:'text',
      label: '交易编号',
      field:'tradeId',
      placeholder:'请输入交易编号',
      rules:[
        { required: true, message: '请输入交易编号', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '订单主键 t_order',
      field:'orderId',
      placeholder:'请输入订单主键 t_order',
      rules:[
        { required: true, message: '请输入订单主键 t_order', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '合作方编号',
      field:'partnerBizNo',
      placeholder:'请输入合作方编号',
      rules:[
        { required: true, message: '请输入合作方编号', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '交易流水号',
      field:'tradeNo',
      placeholder:'请输入交易流水号',
      rules:[
        { required: true, message: '请输入交易流水号', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '还款方式',
      field:'repayType',
      placeholder:'请输入还款方式',
      rules:[
        { required: true, message: '请输入还款方式', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '交易状态',
      field:'tradeStatus',
      placeholder:'请输入交易状态',
      rules:[
        { required: true, message: '请输入交易状态', trigger: 'blur' },
      ]
      },


  ],
}
