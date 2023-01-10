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
    create: 'loan:order-collateral:add',
    update: 'loan:order-collateral:edit',
    delete: 'loan:order-collateral:delete'
  },
  propList:[
           {
      prop: 'collateralId',
      label:'主键',
      minWidth:180,
      slotName: 'collateralId'
    },
              {
      prop: 'orderId',
      label:'订单编号',
      minWidth:180,
      slotName: 'orderId'
    },
              {
      prop: 'propOwner',
      label:'权属人姓名',
      minWidth:180,
      slotName: 'propOwner'
    },
              {
      prop: 'propIdNo',
      label:'权属人身份证',
      minWidth:180,
      slotName: 'propIdNo'
    },
              {
      prop: 'propCertNo',
      label:'权证号',
      minWidth:180,
      slotName: 'propCertNo'
    },
              {
      prop: 'districtCode',
      label:'省市区代号',
      minWidth:180,
      slotName: 'districtCode'
    },
              {
      prop: 'addressPrex',
      label:'省市区中文(坐落地址)',
      minWidth:180,
      slotName: 'addressPrex'
    },
              {
      prop: 'communityNo',
      label:'小区',
      minWidth:180,
      slotName: 'communityNo'
    },
              {
      prop: 'buildingNo',
      label:'楼栋',
      minWidth:180,
      slotName: 'buildingNo'
    },
              {
      prop: 'roomNo',
      label:'门牌号',
      minWidth:180,
      slotName: 'roomNo'
    },
              {
      prop: 'addressDetail',
      label:'地址明细(坐落地址)',
      minWidth:180,
      slotName: 'addressDetail'
    },
              {
      prop: 'floor',
      label:'所在楼层',
      minWidth:180,
      slotName: 'floor'
    },
              {
      prop: 'totalFloor',
      label:'楼层总数',
      minWidth:180,
      slotName: 'totalFloor'
    },
              {
      prop: 'floorage',
      label:'建筑面积',
      minWidth:180,
      slotName: 'floorage'
    },
              {
      prop: 'houseNature',
      label:'房屋性质',
      minWidth:180,
      slotName: 'houseNature'
    },
              {
      prop: 'housePurpose',
      label:'房层用途',
      minWidth:180,
      slotName: 'housePurpose'
    },
              {
      prop: 'ownershipQty',
      label:'共有权人数',
      minWidth:180,
      slotName: 'ownershipQty'
    },
              {
      prop: 'ownershipName',
      label:'其他共有权人姓名',
      minWidth:180,
      slotName: 'ownershipName'
    },
              {
      prop: 'houseAge',
      label:'房龄',
      minWidth:180,
      slotName: 'houseAge'
    },
              {
      prop: 'collateralSituation',
      label:'房层抵押情况',
      minWidth:180,
      slotName: 'collateralSituation'
    },
              {
      prop: 'leaseSituation',
      label:'房屋租赁情况',
      minWidth:180,
      slotName: 'leaseSituation'
    },
              {
      prop: 'evalValuationWay',
      label:'评估方式',
      minWidth:180,
      slotName: 'evalValuationWay'
    },
              {
      prop: 'evalValuation',
      label:'评估价值',
      minWidth:180,
      slotName: 'evalValuation'
    },
              {
      prop: 'evalAgencyCode',
      label:'评估机构代码',
      minWidth:180,
      slotName: 'evalAgencyCode'
    },
              {
      prop: 'evalAgency',
      label:'评估机构名称',
      minWidth:180,
      slotName: 'evalAgency'
    },
              {
      prop: 'ownTwoYear',
      label:'是否满两年',
      minWidth:180,
      slotName: 'ownTwoYear'
    },
              {
      prop: 'onlyHousing',
      label:'是否唯一住房 1.唯一住房 2.非唯一住房',
      minWidth:180,
      slotName: 'onlyHousing'
    },
              {
      prop: 'collateralRanking',
      label:'资产抵押顺位',
      minWidth:180,
      slotName: 'collateralRanking'
    },
              {
      prop: 'identifyValuation',
      label:'抵押物认定价值',
      minWidth:180,
      slotName: 'identifyValuation'
    },
              {
      prop: 'collateralRate',
      label:'抵押率',
      minWidth:180,
      slotName: 'collateralRate'
    },
              {
      prop: 'collateralBalance',
      label:'前手抵押余额',
      minWidth:180,
      slotName: 'collateralBalance'
    },
              {
      prop: 'remark',
      label:'备注',
      minWidth:180,
      slotName: 'remark'
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
