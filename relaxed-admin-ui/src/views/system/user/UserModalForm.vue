<template>

  <div class="user-modal-form">
    <el-dialog
      :title="title"
      :visible.sync="visible"
      width="40%"
      :before-close="dialogClose"
    >
      <el-form ref="pageForm"  :model="form"  :rules="rules" label-width="80px">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="用户昵称" prop="nickname" >
              <el-input   v-model="form.nickname" placeholder="请输入用户昵称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归属部门" prop="organizationId" >

              <yi-tree-select  v-model="form.organizationId"
                               :remote-load="true"
                       :request="orgReq"

                               v-bind="orgProps"
              ></yi-tree-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10" >
          <el-col :span="12">
            <el-form-item label="登录账号" prop="username"  >
              <el-input   v-model="form.username" placeholder="请输入登录账号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone" >
              <el-input   v-model="form.phone" placeholder="请输入手机号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="角色" prop="roleCodes" >
              <yi-select v-model="form.roleCodes" v-bind="roleProps" ></yi-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email" >
              <el-input   v-model="form.email" placeholder="请输入邮箱"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12" v-if="isCreateForm">
            <el-form-item  label="密码" prop="password"  >
              <el-input v-model="form.password" placeholder="请输入用户密码" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status" >
              <dict-radio-group v-model="form.status" dict-code="status"></dict-radio-group>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row  style="text-align: right" >
          <el-button @click="close">取 消</el-button>
          <el-button type="primary" @click="handleConfirmClick">确 定</el-button>
        </el-row>
      </el-form>
    </el-dialog>
  </div>

</template>

<script>
  import {YiTreeSelect,YiSelect} from "@/components/select"

  import formModalActionMixin from "@/mixins/form/formModalActionMixin";
  import {getUserRoleIds,getRoleSelectData} from "@/api/role";
  import {listOrganization} from "@/api/organization";
    export default {
        name: "UserModalForm",
        mixins:[formModalActionMixin],
        components:{
          YiSelect,YiTreeSelect
        },
        data(){
          return{
            form: {},
            rules:{
              nickname:[
                { required: true, message: '请输入用户昵称', trigger: 'blur' },
              ],
              password:[
                { required: true, message: '请输入用户密码', trigger: 'blur' },
              ],
              organizationId:[
                { required: true, message: '请输入组织部门', trigger: 'blur' },
              ],
              username:[
                { required: true, message: '请输入用户名', trigger: 'blur' },
              ],
              phone:[
                { required: true, message: '请输入手机号', trigger: 'blur' },
              ],
              email:[
                { required: true, message: '请输入邮箱', trigger: 'blur' },
              ],
              status:[
                { required: true, message: '请选择状态', trigger: 'blur' },
              ],
            },
            roleProps:{
              placeholder:'请选择角色',
              remoteLoad: true,
              request: getRoleSelectData,
              nativeProps:{
                multiple: true
              }
            },
            orgReq: listOrganization,
            orgProps:{
              remoteLoad: true,
              request: listOrganization,
              clearable: false,
              multiple: false,
              normalizer: (node)=>{
                return {
                  id: node.id,
                  label: node.name+'-'+node.id,
                  children: node.children && node.children.length ? node.children : 0
                }
              },
              placeholder:"请选择父级组织"
            }


          }
        },
        methods:{
          createdFormCallback(payload){
            this.form=this.defaultForm()
          },
          updatedFormCallback(payload){

            const item= payload.item
            getUserRoleIds(item.userId).then(res=>{
              this.form={...item,roleCodes:res.data.roleCodes}
            })
          },
          defaultForm(){
            return {
              status:  1,
              email:'',
              organizationId:null,
              roleCodes:[]
            }
          },
          dialogClose(done){
            this.close()
            done()
          },
          beforeClose(){
            this.$refs.pageForm.clearValidate()
          },
          handleConfirmClick(){
            //处理表单提交
            this.$refs.pageForm.validate((valid,invalidFields)=>{
              if (!valid){
                return
              }else{
                //通过效验提交数据
                this.handleSubmit({...this.form})
              }
            })
          },


        }
    }
</script>

<style lang="scss" scoped>

</style>
