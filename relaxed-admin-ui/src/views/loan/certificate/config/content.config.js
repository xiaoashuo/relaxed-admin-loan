export const contentTableConfig={
  title: '签章证书表',
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
    create: 'loan:certificate:add',
    update: 'loan:certificate:edit',
    delete: 'loan:certificate:delete'
  },
  propList:[
           {
      prop: 'certificateId',
      label:'签章证书主键id',
      minWidth:180,
      slotName: 'certificateId'
    },
              {
      prop: 'certificateAlias',
      label:'证书别名',
      minWidth:180,
      slotName: 'certificateAlias'
    },
              {
      prop: 'subjectType',
      label:'主体类型 1个人 2公司',
      minWidth:180,
      slotName: 'subjectType'
    },
              {
      prop: 'certificateSubject',
      label:'个人及个人名称,公司及名称',
      minWidth:180,
      slotName: 'certificateSubject'
    },
              {
      prop: 'certificateNo',
      label:'主体证件号 个人 身份证号 公司 则营业执照编码',
      minWidth:180,
      slotName: 'certificateNo'
    },
              {
      prop: 'certificatePwd',
      label:'证书密码',
      minWidth:180,
      slotName: 'certificatePwd'
    },
              {
      prop: 'certificateRemark',
      label:'证书描述',
      minWidth:180,
      slotName: 'certificateRemark'
    },
              {
      prop: 'createBy',
      label:'创建人',
      minWidth:180,
      slotName: 'createBy'
    },
              {
      prop: 'updateBy',
      label:'修改人',
      minWidth:180,
      slotName: 'updateBy'
    },
              {
      prop: 'deleted',
      label:'逻辑删除标识，未删除为 0，已删除为删除时间',
      minWidth:180,
      slotName: 'deleted'
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
