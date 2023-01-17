export const contentTableConfig={

  headerProps:{
    visible:false
  },
  showFooter:false,
  propList:[


    {
      prop: 'fillerType',
      label:'填充类型',
      minWidth:180,
      slotName: 'fillerType'
    },
    {
      prop: 'fillerAmount',
      label:'填充金额',
      minWidth:180,
      slotName: 'fillerAmount'
    },
    {
      prop: 'fillerTarget',
      label:'填充目标',
      minWidth:180,
      slotName: 'fillerTarget'
    },




    {
      prop: 'partnerFillerTime',
      label:'账单填充时间',
      minWidth:180,
      slotName: 'partnerFillerTime'
    },



  ]
}
