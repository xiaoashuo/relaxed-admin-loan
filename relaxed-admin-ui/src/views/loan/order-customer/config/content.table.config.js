export const contentTableConfig={
  title: '',
  showSelectColumn:false,
  tableProps:{
    'row-style': {
      height: '10px'
    },
    'cell-style': {
      padding: '2px 0'
    },

  },
  permissions: {
    create: 'loan:order-customer:add',
    update: 'loan:order-customer:edit',
    delete: 'loan:order-customer:delete'
  },
  propList:[
           {
      prop: 'customerId',
      label:'客户编号，关联t_customer表',
      minWidth:180,
      slotName: 'customerId'
    },
              {
      prop: 'partnerCustomerNo',
      label:'合作方客户编号',
      minWidth:180,
      slotName: 'partnerCustomerNo'
    },
              {
      prop: 'creditId',
      label:'授信编号，关联t_credit表',
      minWidth:180,
      slotName: 'creditId'
    },
              {
      prop: 'orderId',
      label:'订单号，关联t_order表',
      minWidth:180,
      slotName: 'orderId'
    },
              {
      prop: 'orgUserNo',
      label:'合作方客户编号',
      minWidth:180,
      slotName: 'orgUserNo'
    },
              {
      prop: 'realName',
      label:'客户姓名',
      minWidth:180,
      slotName: 'realName'
    },
              {
      prop: 'certificateType',
      label:'证件类型',
      minWidth:180,
      slotName: 'certificateType'
    },
              {
      prop: 'certificateNo',
      label:'证件号码',
      minWidth:180,
      slotName: 'certificateNo'
    },
              {
      prop: 'customType',
      label:'客户类型',
      minWidth:180,
      slotName: 'customType'
    },
              {
      prop: 'gender',
      label:'性别',
      minWidth:180,
      slotName: 'gender'
    },
              {
      prop: 'age',
      label:'年龄',
      minWidth:180,
      slotName: 'age'
    },
              {
      prop: 'birthDate',
      label:'出生日期',
      minWidth:180,
      slotName: 'birthDate'
    },
              {
      prop: 'nationality',
      label:'国籍',
      minWidth:180,
      slotName: 'nationality'
    },
              {
      prop: 'mobileNo',
      label:'手机号码',
      minWidth:180,
      slotName: 'mobileNo'
    },
              {
      prop: 'educationCode',
      label:'最高学历',
      minWidth:180,
      slotName: 'educationCode'
    },
              {
      prop: 'degreeCode',
      label:'最高学位',
      minWidth:180,
      slotName: 'degreeCode'
    },
              {
      prop: 'residenceRegion',
      label:'现居住地行政区划',
      minWidth:180,
      slotName: 'residenceRegion'
    },
              {
      prop: 'residenceAddress',
      label:'现居住地址',
      minWidth:180,
      slotName: 'residenceAddress'
    },
              {
      prop: 'residenceCode',
      label:'现居住地址省市区编码',
      minWidth:180,
      slotName: 'residenceCode'
    },
              {
      prop: 'residencePostCode',
      label:'现居住地址邮政编码',
      minWidth:180,
      slotName: 'residencePostCode'
    },
              {
      prop: 'residenceStatus',
      label:'居住状况',
      minWidth:180,
      slotName: 'residenceStatus'
    },
              {
      prop: 'homePhone',
      label:'住宅电话',
      minWidth:180,
      slotName: 'homePhone'
    },
              {
      prop: 'permanentAddress',
      label:'户籍地址',
      minWidth:180,
      slotName: 'permanentAddress'
    },
              {
      prop: 'permanentRegion',
      label:'户籍所在地行政区划',
      minWidth:180,
      slotName: 'permanentRegion'
    },
              {
      prop: 'nativePlace',
      label:'籍贯',
      minWidth:180,
      slotName: 'nativePlace'
    },
              {
      prop: 'maritalStatus',
      label:'婚姻状况',
      minWidth:180,
      slotName: 'maritalStatus'
    },
              {
      prop: 'eMail',
      label:'用户电子邮箱',
      minWidth:180,
      slotName: 'eMail'
    },
              {
      prop: 'employmentStatus',
      label:'就业状态',
      minWidth:180,
      slotName: 'employmentStatus'
    },
              {
      prop: 'professionCode',
      label:'职业',
      minWidth:180,
      slotName: 'professionCode'
    },
              {
      prop: 'jobDuties',
      label:'职务',
      minWidth:180,
      slotName: 'jobDuties'
    },
              {
      prop: 'positionalTitles',
      label:'职称',
      minWidth:180,
      slotName: 'positionalTitles'
    },
              {
      prop: 'companyName',
      label:'现单位名称',
      minWidth:180,
      slotName: 'companyName'
    },
              {
      prop: 'companyKind',
      label:'现单位所属行业',
      minWidth:180,
      slotName: 'companyKind'
    },
              {
      prop: 'companyTrade',
      label:'现单位性质',
      minWidth:180,
      slotName: 'companyTrade'
    },
              {
      prop: 'companyAddress',
      label:'现单位地址',
      minWidth:180,
      slotName: 'companyAddress'
    },
              {
      prop: 'companyRegion',
      label:'现单位行政区划，用6位数字型代码表示，参照GB/T2260-2007',
      minWidth:180,
      slotName: 'companyRegion'
    },
              {
      prop: 'companyPostCode',
      label:'现单位地址邮政编码（无法填报时，统一填999999）',
      minWidth:180,
      slotName: 'companyPostCode'
    },
              {
      prop: 'companyPhone',
      label:'现单位固定电话',
      minWidth:180,
      slotName: 'companyPhone'
    },
              {
      prop: 'salaryAccount',
      label:'现单位工资账号',
      minWidth:180,
      slotName: 'salaryAccount'
    },
              {
      prop: 'accountOpenBank',
      label:'单位工资账号开户银行代码',
      minWidth:180,
      slotName: 'accountOpenBank'
    },
              {
      prop: 'mailingAddress',
      label:'通讯地址',
      minWidth:180,
      slotName: 'mailingAddress'
    },
              {
      prop: 'mailingCode',
      label:'通讯地址省市区编码',
      minWidth:180,
      slotName: 'mailingCode'
    },
              {
      prop: 'mailingPostCode',
      label:'通讯地址邮政编码',
      minWidth:180,
      slotName: 'mailingPostCode'
    },
              {
      prop: 'mailingRegion',
      label:'通讯地址行政区划，用6位数字型代码表示，参照GB/T2260-2007',
      minWidth:180,
      slotName: 'mailingRegion'
    },
              {
      prop: 'fullMailingAddress',
      label:'完整通讯地址',
      minWidth:180,
      slotName: 'fullMailingAddress'
    },
              {
      prop: 'jobYears',
      label:'现单位工作年限',
      minWidth:180,
      slotName: 'jobYears'
    },
              {
      prop: 'annualIncome',
      label:'年收入',
      minWidth:180,
      slotName: 'annualIncome'
    },
              {
      prop: 'certificateValidityDate',
      label:'证件有效期起始日期',
      minWidth:180,
      slotName: 'certificateValidityDate'
    },
              {
      prop: 'certificateExpiryDate',
      label:'证件有效期起始日期',
      minWidth:180,
      slotName: 'certificateExpiryDate'
    },
              {
      prop: 'certificateIssuer',
      label:'证件签发机关名称',
      minWidth:180,
      slotName: 'certificateIssuer'
    },
              {
      prop: 'certificateIssuerRegion',
      label:'证件签发机关所在地行政区划',
      minWidth:180,
      slotName: 'certificateIssuerRegion'
    },
              {
      prop: 'existsLink',
      label:'是否关联关系（1是 0否）',
      minWidth:180,
      slotName: 'existsLink'
    },
              {
      prop: 'linkType',
      label:'关联类型（exists_link为1时必填。关联类型：1信托投资公司的股东、2信托投资公司股东的关联企业，如股东的子公司、3信托投资公司自有资金投资的企业、4信托投资公司自有资金投资的企业的关联企业，如投资的企业的子公司、5信托投资公司以托管或信托等其他方式控制的企业、6信托投资公司的董事、监事、经理、7信托投资公司的董事、监事、经理投资持股5%以上或者担任高级管理人员的公司、企业或其他经济组织、8其他关联）',
      minWidth:180,
      slotName: 'linkType'
    },
              {
      prop: 'linkInvestMoney',
      label:'投资入股信托公司金额（LINK_TYPE为1，2，3时股东投资入股信托公司金额。默认传0）',
      minWidth:180,
      slotName: 'linkInvestMoney'
    },
              {
      prop: 'valid',
      label:'逻辑删除标识，未删除为 0，已删除为删除时间',
      minWidth:180,
      slotName: 'valid'
    },
              {
      prop: 'createdTime',
      label:'创建时间',
      minWidth: 180,
    },
              {
      prop: 'updatedTime',
      label:'更新时间',
      minWidth: 180,
    },
          {

      label:'操作',
      minWidth:250,
      slotName:'handler'
    }
  ]
}
