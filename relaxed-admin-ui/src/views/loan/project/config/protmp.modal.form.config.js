import { getSelectData as getTemplateSelectData } from '@/api/loan/template'

export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[





    {
      type:'dict-select',
      label: '文件类型',
      field:'fileType',
      config:{
        dictCode: 'file_type',
        clearable: true
      },
      rules:[
        { required: true, message: '请选择文件类型', trigger: 'blur' },
      ]
    },
         {
      type:'slot',
      label: '模板id',
      field:'templateId',
      slotName:'templateId',
      placeholder:'请输入模板id',
      config:{
        remoteLoad: true,
        request: getTemplateSelectData
      } ,
      rules:[
        { required: true, message: '请输入模板id', trigger: 'blur' },
      ]
      },




  ],
}
