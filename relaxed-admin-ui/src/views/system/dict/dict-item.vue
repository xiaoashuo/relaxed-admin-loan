<template>
    <div class="dictItem">
      <el-dialog :title="modalTitle" :visible.sync="modalVisible"  >
        <!--表格组件-->
        <pro-table ref="pageContentRef" :content-table-config="contentTableConfig"
                   :lazy-load="true"
                   :request="tableRequest"
                   @newBtnClick="showNewModal" @editBtnClick="showEditModal"
                   @delBtnClick="handleDelClick"

        >
          <template #extStatus="scope">
            <el-switch
              v-model="scope.row.status"
              active-color="#13ce66"
              inactive-color="#ff4949"
              :active-value="1"
              :inactive-value="0"
              @change="handleStatusChange($event,scope.row.id)">
            </el-switch>
          </template>
        </pro-table>

        <!--模态表单组件-->
        <form-modal :append-to-body="true" ref="formModal" :modal-config="modalConfig"
                    :req-function="reqFunction"
                    @submitSuccess="handleSubmit"
        >
        </form-modal>

      </el-dialog>


    </div>
</template>

<script>


  import {contentTableConfig} from "./config/content.item.config";

  import {modalConfig} from "./config/modal.item.config";

  import {getDictItemPageListData, addDictItemObj, putDictItemObj, delDictItemObj, modifyDictStatus} from "@/api/dict";

  export default {
        name: "dict-item",
      props:{

      },
      data(){
          return {
            contentTableConfig:contentTableConfig,
            modalConfig:modalConfig,
            tableRequest: getDictItemPageListData,
            //表单请求
            reqFunction:{
              create: addDictItemObj,
              update: putDictItemObj
            },
            modalTitle:"",
            modalVisible: false,
            dictGroup:{},

          }
      },
      methods:{

          show(item){
            this.dictGroup={...item}
            this.modalTitle=`字典项: ${item.code}`
            this.modalVisible=true
            this.$nextTick(()=>{
              this.$refs.pageContentRef.searchTable({dictCode:this.dictGroup.code},false)
            })
          },
         handleStatusChange(value,id){
             modifyDictStatus(id,value).then(res=>{
               this.$message({
                 message: '状态修改成功',
                 type: 'success'
               });
               this.$refs.pageContentRef.refreshTable(false)
             })
        },

        showNewModal(){
          const defaultInfo={title:'新增字典项'+this.dictGroup.code,item:{dictCode:this.dictGroup.code,sort:0}}
          this.$refs.formModal.add(defaultInfo)
        },
        showEditModal(item){
          this.$refs.formModal.update({title:'编辑字典项',item})
        },
        handleDelClick(item){
          delDictItemObj(item.id).then(res=>{
            this.$refs.pageContentRef.refreshTable(false)
          })
        },
        //模态框相关
        handleSubmit(res){
          this.$refs.pageContentRef.refreshTable(false)
        },


      }
    }
</script>

<style scoped>

</style>
