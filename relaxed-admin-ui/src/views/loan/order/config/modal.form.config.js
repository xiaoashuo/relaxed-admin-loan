export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[

         {
      type:'text',
      label: '主键id',
      field:'orderId',
      placeholder:'请输入主键id',
      rules:[
        { required: true, message: '请输入主键id', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '授信编号',
      field:'creditId',
      placeholder:'请输入授信编号',
      rules:[
        { required: true, message: '请输入授信编号', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '合作方业务号',
      field:'partnerBizNo',
      placeholder:'请输入合作方业务号',
      rules:[
        { required: true, message: '请输入合作方业务号', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '批次号',
      field:'applyBatchNo',
      placeholder:'请输入批次号',
      rules:[
        { required: true, message: '请输入批次号', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '预审编号',
      field:'preAuditNo',
      placeholder:'请输入预审编号',
      rules:[
        { required: true, message: '请输入预审编号', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '地区代码',
      field:'regionCode',
      placeholder:'请输入地区代码',
      rules:[
        { required: true, message: '请输入地区代码', trigger: 'blur' },
      ]
      },


  ],
}
