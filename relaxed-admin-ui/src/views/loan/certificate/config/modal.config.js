import {FILE_UPLOAD_URL} from '@/constants/SysConstants'

export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[




         {
      type:'text',
      label: '证书别名',
      field:'certificateAlias',
      placeholder:'请输入证书别名',
      rules:[
        { required: true, message: '请输入证书别名', trigger: 'blur' },
      ]
      },



    {
      type:'dict-radio-group',
      label: '主体类型',
      field:'subjectType',
      config:{
        dictCode: 'subject_type',
        clearable: true
      },
      rules:[
        { required: true, message: '请选择主体类型', trigger: 'blur' },
      ]
    },

         {
      type:'text',
      label: '主体名称',
      field:'certificateSubject',
      placeholder:'请输入主体名称',
      rules:[
        { required: true, message: '请输入主体名称', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '主体证件号',
      field:'certificateNo',
      placeholder:'请输入主体证件号',
      rules:[
        { required: true, message: '请输入主体证件号', trigger: 'blur' },
      ]
      },
     {
      type:'text',
      label: '证书密码',
      field:'certificatePwd',
      placeholder:'请输入证书密码',
      rules:[
        { required: true, message: '请输入证书密码', trigger: 'blur' },
      ]
      },
    {
      type:'dict-radio-group',
      label: '密钥库类型',
      field:'keystoreType',
      rules:[
        { required: true, message: '请选择密钥库类型', trigger: 'blur' },
      ],
      config:{
        dictCode: 'keystore_type',
        clearable: true
      }
    },
    {
      type:'slot',
      label: '证书授权',
      field:'authorizeType',
      placeholder:'请输入证书密码',
      slotName:'authorizeType',
      config:{
        options:[
          {
            label:'系统生成',
            value: 1
          },
          {
            label:'自定义',
            value: 2
          }
        ]
      }
    },
    {
      type:'text',
      label: '有效期(时)',
      field:'validityTime',
      placeholder:'请输入有效期',
      rules:[
        { required: true, message: '请输入有效期', trigger: 'blur' },
      ]
    },



  ],
}
