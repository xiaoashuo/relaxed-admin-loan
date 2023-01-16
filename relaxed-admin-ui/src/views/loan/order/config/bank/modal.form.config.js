import {getSelectData} from '@/api/loan/bank-code'


export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[





         {
      type:'dict-select',
      label: '银行卡用途',
      field:'cardPurpose',
      placeholder:'请输入银行卡用途',
      rules:[
        { required: true, message: '请输入银行卡用途', trigger: 'blur' },
      ],
           config:{
        dictCode:'bank_card_purpose',
             clearable: true
           }
      },


    {
      type:'dict-select',
      label: '账户类型',
      field:'accountType',
      placeholder:'请输入账户类型',
      rules:[
        { required: true, message: '请输入账户类型', trigger: 'blur' },
      ],
      config:{
        dictCode:'bank_account_type',
        clearable: true
      }
    },
         {
      type:'text',
      label: '户名',
      field:'accountName',
      placeholder:'请输入户名',
      rules:[
        { required: true, message: '请输入户名', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '户号',
      field:'accountNo',
      placeholder:'请输入户号',
      rules:[
        { required: true, message: '请输入户号', trigger: 'blur' },
      ]
      },


         {
      type:'select',
      label: '银行代号',
      field:'bankCode',
      placeholder:'请输入银行代号',
      rules:[
        { required: true, message: '请输入银行代号', trigger: 'blur' },
      ],
           config:{

             remoteLoad: true,
             request: getSelectData,

           }
      },

    {
      type:'text',
      label: '预留手机号',
      field:'reserveMobile',
      placeholder:'请输入预留手机号',
      rules:[
        { required: true, message: '请输入预留手机号', trigger: 'blur' },
      ]
    },
  ],
}
