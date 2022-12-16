import {getSelectData} from '@/api/loan/partner'
import {getSelectData as getSealSelectData} from '@/api/loan/seal'
import {getSelectData as getCertificateSelectData} from '@/api/loan/certificate'

export const modalConfig= {
  colLayout:{
    span: 24
  },
  formItems:[




         {
      type:'select',
      label: '合作方',
      field:'partnerId',
      placeholder:'请输入合作方',
      rules:[
        { required: true, message: '请输入合作方', trigger: 'blur' },
      ],
           config:{
             remoteLoad: true,
             request: getSelectData
           }
      },


         {
      type:'text',
      label: '信托产品',
      field:'productId',
      placeholder:'请输入信托产品',
      rules:[
        { required: true, message: '请输入信托产品', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '信托计划',
      field:'trustPlanId',
      placeholder:'请输入信托计划',
      rules:[
        { required: true, message: '请输入信托计划', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '项目代号',
      field:'projectCode',
      placeholder:'请输入项目代号',
      rules:[
        { required: true, message: '请输入项目代号', trigger: 'blur' },
      ]
      },


         {
      type:'text',
      label: '项目名称',
      field:'projectName',
      placeholder:'请输入项目名称',
      rules:[
        { required: true, message: '请输入项目名称', trigger: 'blur' },
      ]
      },

    {
      type:'select',
      label: '签章图片',
      field:'sealId',
      placeholder:'请选择签章图片',
      rules:[
        { required: true, message: '请选择签章图片', trigger: 'blur' },
      ],
      config:{
        remoteLoad: true,
        request: getSealSelectData
      }
    },
    {
      type:'select',
      label: '证书',
      field:'sealId',
      placeholder:'请选择证书',
      rules:[
        { required: true, message: '请选择证书', trigger: 'blur' },
      ],
      config:{
        remoteLoad: true,
        request: getCertificateSelectData
      }
    },
  ],
}
