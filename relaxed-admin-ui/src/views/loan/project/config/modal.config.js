import { getSelectData } from '@/api/loan/partner'
import { getSelectData as getSealSelectData } from '@/api/loan/seal'
import { getSelectData as getProductSelectData } from '@/api/loan/product'
import { getSelectData as getTrustPlanSelectData } from '@/api/loan/trust-plan'
import { getSelectData as getCertificateSelectData } from '@/api/loan/certificate'

export const modalConfig = {
  colLayout: {
    span: 24
  },
  formItems: [


    {
      type: 'select',
      label: '合作方',
      field: 'partnerId',
      placeholder: '请输入合作方',
      rules: [
        { required: true, message: '请输入合作方', trigger: 'blur' }
      ],
      config: {
        remoteLoad: true,
        request: getSelectData
      }
    },
    {
      type: 'select',
      label: '信托产品',
      field: 'productId',
      placeholder: '请输入信托产品',
      rules: [
        { required: true, message: '请输入信托产品', trigger: 'blur' }
      ],
      config: {
        remoteLoad: true,
        request: getProductSelectData
      }
    },

    {
      type: 'select',
      label: '信托计划',
      field: 'trustPlanId',
      placeholder: '请输入信托计划',
      rules: [
        { required: true, message: '请输入信托计划', trigger: 'blur' }
      ],
      config: {
        remoteLoad: true,
        request: getTrustPlanSelectData
      }
    },


    {
      type: 'text',
      label: '项目代号',
      field: 'projectCode',
      placeholder: '请输入项目代号',
      rules: [
        { required: true, message: '请输入项目代号', trigger: 'blur' }
      ],
      colLayout:{
        span: 13
      }
    },


    {
      type: 'text',
      label: '项目名称',
      field: 'projectName',
      placeholder: '请输入项目名称',
      rules: [
        { required: true, message: '请输入项目名称', trigger: 'blur' }
      ],
      colLayout:{
        span: 13
      }
    }


  ]
}
