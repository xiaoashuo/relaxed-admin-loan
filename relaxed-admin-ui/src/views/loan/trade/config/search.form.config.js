export const searchFormConfig={
  showHeader:true,
  formItems:[

    {
      type:'text',
      label: '合作方编号',
      field:'partnerBizNo',
      placeholder:'请输入合作方编号',
      config:{
        clearable: true
      }

    },
    {
      type:'text',
      label: '交易流水号',
      field:'tradeNo',
      placeholder:'请输入交易流水号',
      config:{
        clearable: true
      }

    },
  ],
}
