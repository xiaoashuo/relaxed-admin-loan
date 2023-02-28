import { getPluginList } from '@/api/risk/risk-pre-item'

export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[

    {
      type:'slot',
      label: '来源字段',
      slotName:'sourceFieldSlot'
    },


    {
      type:'text',
      label: '来源标签',
      field:'sourceLabel',
      placeholder:'请输入来源标签',
      rules:[
        { required: true, message: '请输入来源标签', trigger: 'blur' },
      ]
    },


    {
      type:'text',
      label: '目标字段',
      field:'destField',
      placeholder:'请输入目标字段',
      rules:[
        { required: true, message: '请输入目标字段', trigger: 'blur' },
      ]
    },
    {
      type:'text',
      label: '目标标签',
      field:'destLabel',
      placeholder:'请输入目标字段',
      rules:[
        { required: true, message: '请输入目标字段', trigger: 'blur' },
      ]
    },
    {
      type:'select',
      label: '插件种类',
      field:'plugin',
      placeholder:'请输入插件种类',
      rules:[
        { required: true, message: '请输入插件种类', trigger: 'blur' },
      ],
      config:{
        remoteLoad: true,
        request: getPluginList,

      }
    },
    {
      type:'text',
      label: '参数',
      field:'args',
      placeholder:'请输入参数',
      rules:[
        { required: true, message: '请输入参数', trigger: 'blur' },
      ]
    },


  ],
}
