import {getMenuRouter} from "@/api/permission";

export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[


    {
      type:'tree-select',
      label: '上级菜单',
      field:'parentId',
      options: [],
      config:{
          clearable: false,
          multiple: false,
          normalizer: (node)=>{
            return {
            id: node.id,
            label: node.title+'-'+node.id,
            children: node.children && node.children.length ? node.children : 0
            }
           },
          placeholder:"请选择菜单"
      }
    },


    {
      type:'radio-group',
      label: '菜单类型',
      field:'type',
      options:[
        {
          value:  0,
          label: '目录'
        },
        {
          value:  1,
          label: '菜单'
        },
        {
          value:  2,
          label: '按钮'
        },
      ]
    },
    {
      type:'text',
      label: '菜单id',
      field:'id',
    },
    {
      type:'number',
      label: '显示排序',
      field:'sort',
    },
    {
      type:'text',
      label: '菜单名称',
      field:'title',
    },
    {
      type:'dict-radio-group',
      label: '是否隐藏',
      field:'hidden',
      config:{
        dictCode: 'yes_or_no',
      },

    },
    {
      type:'select',
      label: '打开方式',
      field:'targetType',
      options:[
        {
          value:  1,
          label: '组件'
        },
        {
          value:  2,
          label: '内链'
        },
        {
          value:  3,
          label: '外链'
        },
      ]
    },
    {
      type:'text',
      label: '路由地址',
      field:'path',
    },
    {
      type:'text',
      label: '资源路径',
      field:'uri',
    },
    {
      type:'radio-group',
      label: '组件缓存',
      field:'keepAlive',
      options:[
        {
          value:  0,
          label: '开启'
        },
        {
          value:  1,
          label: '关闭'
        },
      ]
    },
    {
      type:'radio-group',
      label: '恒显示',
      field:'alwaysShow',
      options:[
        {
          value:  0,
          label: '不合并'
        },
        {
          value:  1,
          label: '合并'
        },
      ]
    },
    {
      type:'text',
      label: '备注信息',
      field:'remark',
    },

  ],
}
