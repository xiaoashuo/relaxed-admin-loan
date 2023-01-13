export const contentTableConfig={
  title: '',
  showSelectColumn:false,
  tableProps:{
    'row-style': {
      height: '10px'
    },
    'cell-style': {
      padding: '2px 0'
    },

  },

  propList:[

     {
      prop: 'fileNo',
      label:'文件流水号',
      minWidth:180,

    },
              {
      prop: 'fileName',
      label:' 文件名',
      minWidth:180,

    },
              {
      prop: 'fileType',
      label:'文件类型',
      minWidth:180,
      slotName: 'fileTypeSlot'
    },

              {
      prop: 'remark',
      label:'备注',
      minWidth:80,

    },





  ]
}
