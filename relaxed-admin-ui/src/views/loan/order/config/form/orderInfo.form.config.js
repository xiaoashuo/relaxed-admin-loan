import CascaderTest from './test'
import { getSelectData as getProductSelectData } from '@/api/loan/product'
import { getSelectData as getTrustPlanSelectData } from '@/api/loan/trust-plan'
export const  orderInfoFormConfig={

  rows:[
    //第一行
    {
      layout:{},
      cols:[
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '合同编号',
              field:'contractNo',
              placeholder:'请输入合同编号',
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '申请金额',
              field:'applyAmount',
              placeholder:'请输入申请金额',
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '申请期限(日)',
              field:'loanPeriod',
              placeholder:'请输入申请期限',
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '申请利率(日)',
              field:'interestRate',
              placeholder:'请输入申请利率',
            },
          ]
        },
      ]
    },
    //第二行
    {
      layout:{},
      cols:[
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'select',
              label: '信托产品',
              field:'productCode',
              placeholder:'请输入信托产品',
              config:{
                nativeProps:{
                  clearable:true,
                },

                remoteLoad: true,
                request: getProductSelectData
              }
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'select',
              label: '信托计划',
              field:'trustPlanCode',
              placeholder:'请输入信托计划',
              config:{
                nativeProps:{
                  clearable:true,
                },
                remoteLoad: true,
                request: getTrustPlanSelectData
              }
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '宽限期',
              field:'graceDays',
              placeholder:'请输入宽限期',
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '罚息率(日)',
              field:'penaltyRate',
              placeholder:'请输入罚息率',
            },
          ]
        },


      ]
    },
    //第三行
    {
      layout:{},
      cols:[
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '姓名',
              field:'realName',
              placeholder:'请输入姓名',
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '身份证',
              field:'certificateNo',
              placeholder:'请输入身份证',
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '手机号码',
              field:'mobileNo',
              placeholder:'请输入手机号码',
            },
          ]
        },

        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '贷款用途',
              field:'loanPurpose',
              placeholder:'请输入贷款用途',
              config:{
                dictCode: 'loan_purpose',
                clearable: true
              }
            },
          ]
        },

      ]
    },
    //第四行
    {
      layout:{},
      cols:[
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '邮箱',
              field:'email',
              placeholder:'请输入邮箱',
              config:{
                style:{width:'150px'}
              }
            },
          ]
        },
        {
          colLayout:{
            span:9
          },
          formItems:[
            {
              type:'datepicker',
              label: '证件有效期开始时间',
              field:'certificateStartDate',
              placeholder:'请输入证件有效期开始时间',
              itemProps:{
                labelWidth:'150px',
              },
              config:{
                style:{width:'250px'},
                type:'date',
                'value-format':'yyyy-MM-dd',
              }
            },
          ]
        },
        {
          colLayout:{
            span:9
          },
          formItems:[
            {
              type:'datepicker',
              label: '证件有效期结束时间',
              field:'certificateEndDate',
              placeholder:'请输入证件有效期结束时间',
              itemProps:{
                labelWidth:'150px',
              },
              config:{
                style:{width:'250px'},
                'value-format':'yyyy-MM-dd',
                type:'date',
              }
            },
          ]
        },

      ]
    },
    //第五行
    {
      layout:{},
      cols:[
        {
          colLayout:{
            span:8
          },
          formItems:[
            {
              type:'cascader-select',
              label: '现住地址',
              field:'residenceCodeArr',
              placeholder:'请输入现住地址',
              config:{
                style:{width:'200px'},
                options:CascaderTest
              }
            },

          ]
        },
        {
          colLayout:{
            span:8
          },
          formItems:[

            {
              type:'text',
              label: '具体地址',
              field:'residenceAddress',
              placeholder:'请输入具体地址',
              config:{

              }
            },
          ]
        },
      ]
    },
    //第六行
    {
      layout:{},
      cols:[
        {
          colLayout:{
            span:8
          },
          formItems:[
            {
              type:'cascader-select',
              label: '通讯地址',
              field:'mailingCodeArr',
              placeholder:'请输入通讯地址',
              config:{
                style:{width:'200px'},
                options:CascaderTest
              }
            },

          ]
        },
        {
          colLayout:{
            span:8
          },
          formItems:[

            {
              type:'text',
              label: '具体地址',
              field:'mailingAddress',
              placeholder:'请输入具体地址',
              config:{

              }
            },
          ]
        },
      ]
    },
    //第七行
    {
      layout:{},
      cols:[
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '就业状态',
              field:'employmentStatus',
              placeholder:'请输入就业状态',
              config:{
                style:{width:'150px'},
                dictCode: 'employment_status',
                clearable: true
              }
            },

          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '职业',
              field:'professionCode',
              placeholder:'请输入职业',
              config:{
                style:{width:'200px'},
                dictCode: 'profession_code',
                clearable: true
              }
            },

          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '现单位工作年限',
              field:'jobYears',
              placeholder:'请输入现单位工作年限',
              itemProps:{
                labelWidth:'150px',
              },
              config:{
                style:{width:'180px'}
              }
            },

          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '性别',
              field:'gender',
              placeholder:'请选择性别',
              config:{
                style:{width:'150px'},
                dictCode: 'gender',
                clearable: true
              }
            },

          ]
        },
      ]
    },
    //第八行
    {
      layout:{},
      cols:[
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '年收入(元)',
              field:'annualIncome',
              placeholder:'请选择年收入',
              config:{
                style:{width:'150px'},
                dictCode: 'annual_earnings',
                clearable: true
              }
            },

          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'text',
              label: '现单位名称',
              field:'companyName',
              placeholder:'请输入现单位名称',
              config:{

              }
            },

          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '业务种类',
              field:'businessDetail',
              placeholder:'请输入业务种类',
              config:{
                dictCode: 'business_detail',
                clearable: true
              }
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '还款来源',
              field:'repaymentWay',
              placeholder:'请输入还款来源',
              config:{
                dictCode: 'repayment_source',
                clearable: true
              }
            },
          ]
        },

      ]
    },
    //第九行
    {
      layout:{},
      cols:[
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '投向行业',
              field:'investIndustry',
              placeholder:'请选择投向行业',
              config:{
                style:{width:'150px'},
                dictCode: 'invest_industry',
                clearable: true
              }
            },

          ]
        },

        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '贷款类型',
              field:'loanType',
              placeholder:'请选择贷款类型',
              config:{
                dictCode: 'loan_type',
                clearable: true
              }
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '投入分类',
              field:'investType',
              placeholder:'请选择投入分类',
              config:{
                dictCode: 'business_detail',
                clearable: true
              }
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '担保方式',
              field:'guaranteeWay',
              placeholder:'请选择担保方式',
              config:{
                dictCode: 'guarantee_way',
                clearable: true
              }
            },
          ]
        },
      ]
    },
    //第10行
    {
      layout:{},
      cols:[
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '证件类型',
              field:'certificateType',
              placeholder:'请选择证件类型',
              config:{
                style:{width:'150px'},
                dictCode: 'certificate_type',
                clearable: true
              }
            },

          ]
        },

        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'dict-select',
              label: '客户类型',
              field:'customType',
              placeholder:'请选择客户类型',
              config:{
                dictCode: 'custom_type',
                clearable: true
              }
            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'number',
              label: '年龄',
              field:'age',
              placeholder:'请选择年龄',

            },
          ]
        },
        {
          colLayout:{
            span:6
          },
          formItems:[
            {
              type:'datepicker',
              label: '出生日期',
              field:'birthDate',
              placeholder:'请选择出生日期',
              config:{
                'value-format':'yyyy-MM-dd',
                type:'date',
              }
            },
          ]
        },
      ]
    },
  ]
}
