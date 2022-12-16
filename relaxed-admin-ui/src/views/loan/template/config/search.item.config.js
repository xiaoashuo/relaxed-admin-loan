export const searchItemFormConfig={
  showHeader:true,
  formItems:[

    {
      type:'text',
      label: '文本域',
      field:'textArea',
      placeholder:'请输入文本域',

      itemProps:{
        "label-width":'70px'
      },
      config:{
        clearable: true,
        style:'width:150px;color:red',
        size:"small"
      }
    },
    {
      type:'text',
      label: '节点名称',
      field:'tagName',
      placeholder:'请输入节点名称',

      itemProps:{
        "label-width":'100px'
      },
      config:{
        clearable: true,
        style:'width:150px;color:red',
        size:"small"
      }
    },
  ],
}
