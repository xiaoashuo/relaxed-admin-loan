<template>
    <div class="role-detail">
<!--      <el-button type="primary" @click="goBack">返回上级</el-button>-->
<!--      我是一个详情页面-->
<!--      上级携带信息-->
<!--      {{ this.$route.query}}-->


      <el-card class="box-card" style="margin-bottom: 20px">
        <div slot="header" class="clearfix">
          <span>角色基本信息</span>
          <el-button  style="float: right; padding: 3px 0" type="text" @click="goBack">返回上级</el-button>

        </div>
        <el-descriptions title="">
          <el-descriptions-item label="角色名称">{{roleInfo.name}}</el-descriptions-item>
          <el-descriptions-item label="角色标识">{{roleInfo.code}}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{roleInfo.createdTime}}</el-descriptions-item>
          <el-descriptions-item label="备注">
            {{roleInfo.remarks}}
          </el-descriptions-item>
        </el-descriptions>

      </el-card>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-card class="box-card" >
            <div slot="header" class="clearfix">
              <span>角色关联用户</span>
            </div>
            <pro-table ref="pageContentRef"
                       :content-table-config="userContentTableConfig"
                       :request="tableRequest"
                       :lazy-load="true"
            >
              <template #customHandler="scope">
                <el-popconfirm
                  title="您确定要解绑吗？"
                  @confirm="unbindUser(scope.row)"
                >
                  <el-button type="text"  slot="reference">解绑</el-button>
                </el-popconfirm>
              </template>

            </pro-table>


          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card class="box-card" >
            <div slot="header" class="clearfix">
              <span>角色挂载属性</span>
            </div>

            <el-empty description="暂无内容"></el-empty>
          </el-card>
        </el-col>
      </el-row>


    </div>
</template>

<script>



  import {userContentTableConfig} from "./config/detail.user.config";
  import {pageByRole} from "@/api/user";
  import {getRoleInfo,unbindUser} from "@/api/role";

  export default {
        name: "role-detail",
      components:{


      },
    data(){
          return{
            userContentTableConfig:userContentTableConfig,
            tableRequest: pageByRole,
            roleCode:'',
            roleInfo:{}
          }
    },

    computed:{


    },
    created(){
      const{id,roleCode}= this.$route.query
      this.roleCode=roleCode;
      this.getRoleInfoById(id)
      this.$nextTick(()=>{
        this.$refs.pageContentRef.searchTable({roleCode:this.roleCode},false)
      })
    },
      methods:{

        unbindUser(row){
          //解绑用户
          unbindUser(row.userId,this.roleCode).then(res=>{
            this.$message({
              message: '解绑用户信息成功',
              type: 'success'
            });
            this.$refs.pageContentRef.searchTable({roleCode:this.roleCode})
          }).catch(error=>{
            this.$message({
              message: '解绑用户信息失败'+error,
              type: 'error'
            });
          })
        },
         getRoleInfoById(id){
           getRoleInfo(id).then(res=>{
             this.roleInfo=res.data
           })
         } ,

        goBack() {
          console.log('go back');
          this.$router.back()
        },

      }
    }
</script>

<style scoped>

</style>
