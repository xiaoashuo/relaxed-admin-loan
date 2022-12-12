import {listOrganization} from "@/api/organization";

export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[
    {
      type:'text',
      label: '组织id',
      field:'id',
      isHidden: true
    },
    {
      type:'tree-select',
      label: '父级组织',
      field:'parentId',
      placeholder:'请选择父级组织',
      config:{
        remoteLoad: true,
        request: listOrganization,
        clearable: false,
        multiple: false,
        normalizer: (node)=>{
          return {
            id: node.id,
            label: node.name+'-'+node.id,
            children: node.children && node.children.length ? node.children : 0
          }
        },
        placeholder:"请选择菜单"
      },
      rules:[
        { required: true, message: '父级组织不能为空', trigger: 'change' },
      ]
    },
    {
      type:'text',
      label: '组织名称',
      field:'name',
      placeholder:'请输入组织名称',
      rules:[
        { required: true, message: '请输入组织名称', trigger: 'blur' },

      ]

    },
    {
      type:'number',
      label: '排序',
      field:'sort',
      placeholder:'按数值由小到大升序',

    },
    {
      type:'text',
      label: '备注信息',
      field:'remarks',
      placeholder:'备注信息',

    },



  ],
}
