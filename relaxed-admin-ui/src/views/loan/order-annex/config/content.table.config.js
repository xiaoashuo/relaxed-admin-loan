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
    create: 'loan:order-annex:add',
    update: 'loan:order-annex:edit',
    delete: 'loan:order-annex:delete'
  },
  propList:[
           {
      prop: 'fileId',
      label:'文件主键',
      minWidth:180,
      slotName: 'fileId'
    },
              {
      prop: 'orderId',
      label:'订单编号,关联t_order表',
      minWidth:180,
      slotName: 'orderId'
    },
              {
      prop: 'fileNo',
      label:'文件流水号',
      minWidth:180,
      slotName: 'fileNo'
    },
              {
      prop: 'fileName',
      label:' 文件名',
      minWidth:180,
      slotName: 'fileName'
    },
              {
      prop: 'fileType',
      label:'文件类型（https://www.yuque.com/ntgnx4/hoyh19/ughdvq#g8rHZ）',
      minWidth:180,
      slotName: 'fileType'
    },
              {
      prop: 'fileUrl',
      label:'文件网址',
      minWidth:180,
      slotName: 'fileUrl'
    },
              {
      prop: 'remark',
      label:'备注',
      minWidth:180,
      slotName: 'remark'
    },
              {
      prop: 'uploadTime',
      label:'上传时间',
      minWidth:180,
      slotName: 'uploadTime'
    },
              {
      prop: 'valid',
      label:'逻辑删除标识，未删除为 0，已删除为删除时间',
      minWidth:180,
      slotName: 'valid'
    },
              {
      prop: 'updStatus',
      label:'当前影像资料是否可以修改 0可修改,1不可修改',
      minWidth:180,
      slotName: 'updStatus'
    },
              {
      prop: 'isPush',
      label:'是否推送给云融0未推送,1已推送',
      minWidth:180,
      slotName: 'isPush'
    },
              {
      prop: 'noticeBatchNo',
      label:'通知批次号',
      minWidth:180,
      slotName: 'noticeBatchNo'
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
      prop: 'auditRecord',
      label:'审核记录',
      minWidth:180,
      slotName: 'auditRecord'
    },
              {
      prop: 'auditFlag',
      label:'审核标记1.红色2.橙色3.黄色4.绿色5.蓝色6:√,7:×',
      minWidth:180,
      slotName: 'auditFlag'
    },
          {

      label:'操作',
      minWidth:250,
      slotName:'handler'
    }
  ]
}
