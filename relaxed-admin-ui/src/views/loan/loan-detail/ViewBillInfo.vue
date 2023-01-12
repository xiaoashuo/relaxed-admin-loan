<template>
  <div class="app-container">
    <div>
      <h2>订单信息</h2>
      <el-row>
        <el-col :span="12">
          订单编号: {{loan.partnerBizNo}}
        </el-col>
        <el-col :span="12">
          借据编号: {{loan.partnerLoanNo}}
        </el-col>
      </el-row>
    </div>
    <h2>账单信息</h2>

    <!--表格组件-->
    <pro-table ref="pageContentRef" :content-table-config="contentTableConfig"
               :lazy-load="true"
               :request="tableRequest"
               @expand-change="handleExpandChange"

    >
      <template #expandHandle="scope">
        <pro-table style="width: 90%;margin: 0 auto" ref="billItemPageContentRef"
                   :content-table-config="billItemContentTableConfig"
                   :request="tableBillItemRequest"

        >
          <template #receiptsAmt="scope">
            <div style="display: flex;justify-content: center">
              <span>{{scope.row.receiptsAmt}}</span>
              <el-popover
                placement="bottom"
                title="科目填充"
                width="600"
                trigger="click"
                @hide="handlePoppverHide"
                content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">
                <div slot="reference" >
                  <i class="el-icon-caret-right" @click="handleReceiptClick(scope.row)"></i>
                </div>

                <yi-table  :title="''" :header-props="billItemFillerConfig.headerProps"
                           :loading="billItemFillerLoading"
                           :show-footer="billItemFillerConfig.showFooter" :prop-list="billItemFillerConfig.propList" :list-data="billItemFillerList"></yi-table>
              </el-popover>
            </div>



          </template>
        </pro-table>

      </template>
    </pro-table>


  </div>
</template>

<script>

import YiTable from '@/components/table'
//页面配置参数
import {contentTableConfig} from "./config/bill.content.table.config";
import {contentTableConfig as billItemContentTableConfig} from "./config/billItem.content.table.config";

//页面相关请求方法
import {getPage} from "@/api/loan/bill";
import {getList as getBillItemList} from "@/api/loan/bill-item";
import {getList as getBillItemFillerList} from "@/api/loan/bill-item-filler";
export default {
  name: "ViewBillPage",
  components:{
    YiTable
  },
  props:{
    loanId:{
      type: Number,
      required: true
    },
    loan:{
      type: Object,
      default:()=>({})
    }
  },
  data() {
    return {
      //页面相关配置
      contentTableConfig: contentTableConfig,
      billItemContentTableConfig: billItemContentTableConfig,


      //表格请求
      tableRequest: getPage,
      tableBillItemRequest: getBillItemList,
      billItemFillerLoading: false,
      billItemFillerList:[],
      billItemFillerConfig:{
        headerProps:{
          visible:false
        },
         showFooter:false,
         propList:[

          {
            prop: 'fillerName',
            label:'填充名称',
            minWidth:180,
            slotName: 'fillerName'
          },
          {
            prop: 'fillerType',
            label:'填充类型',
            minWidth:180,
            slotName: 'fillerType'
          },
          {
            prop: 'fillerAmount',
            label:'填充金额',
            minWidth:180,
            slotName: 'fillerAmount'
          },
          {
            prop: 'fillerTarget',
            label:'填充目标',
            minWidth:180,
            slotName: 'fillerTarget'
          },




          {
            prop: 'partnerFillerTime',
            label:'账单填充时间',
            minWidth:180,
            slotName: 'partnerFillerTime'
          },



        ]
      }


    }
  },
  created() {
    this.$nextTick(()=>{
      this.$refs.pageContentRef.searchTable({loanId:this.loanId})
    })
  },
  methods: {

    handleExpandChange(row,expandedRows ){
      console.log("当前行",row,"展开",expandedRows)
      if (expandedRows&&expandedRows.length>0){
        this.$nextTick(()=>{
          this.$refs.billItemPageContentRef.searchTable({billId: row.billId})
        })

      }
    },
    handlePoppverHide(){
      this.billItemFillerList=[]
    },
    handleReceiptClick(row){
      console.log("当前行",row)
      this.billItemFillerLoading=true
      const billItemId=row.billItemId
      getBillItemFillerList({billItemId}).then(res=>{
        this.billItemFillerList= res.data

      }).finally(()=>{
        this.billItemFillerLoading=false
      })
    },
    //搜索框相关 搜索数据
    handleSearchClick(formData) {
      formData.loanId=this.loanId
      this.$refs.pageContentRef.searchTable(formData)
    },
    //重置搜索
    handleResetClick() {
      this.$refs.pageContentRef.searchTable({loanId:this.loanId})

    },

  }

}
</script>

<style lang="scss" scoped>

</style>
