<template>
  <div class="lock-container pull-height">
    <div class="lock-form animated bounceInDown">
      <div class="animated" :class="{'shake':passwdError,'bounceOut':pass}">
        <h3 class="text-white">{{name}}</h3>
        <el-input  placeholder="请输入登录密码" type="password" class="input-with-select animated" v-model="passwd" @keyup.enter.native="handleLogin">
          <el-button slot="append" @click="handleLogin" style="padding-right:36px;">
            <svg-icon  class-name='international-icon' icon-class="deblocking"/>
          </el-button>
          <el-button slot="append" @click="handleLogout">
            <svg-icon class-name='international-icon' icon-class="lockOut"/>
          </el-button>
        </el-input>
      </div>

    </div>
  </div>
</template>
<script>
  import { mapGetters } from 'vuex'
  export default {
    name: 'lock',
    data() {
      return {
        passwd: '',
        passwdError: false,
        pass: false
      }
    },
    created() {},
    mounted() {},
    computed: {

      ...mapGetters(['name','tag',])
    },
    props: [],
    methods: {
      handleLogout() {
        this.$confirm('确定注销并退出系统吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            this.$store.dispatch('auth/logout',false).then(() => {
              //不采用刷新模式 直接跳转到登录页
              window.location.href="/#/user/login"
              window.location.reload()
              // setTimeout(() => {
              //   window.location.reload()
              // }, 10)
            })
          })
          .catch((err) => {
            this.$message.error({
              title: '错误',
              description: err.message
            })
          })
      },
      handleLogin() {
        const lockPasswd= this.$storage.session.getCache('lock_password')
        if (this.passwd !== lockPasswd) {
          this.passwd = ''
          this.$message({
            message: '解锁密码错误,请重新输入',
            type: 'error'
          })
          this.passwdError = true
          setTimeout(() => {
            this.passwdError = false
          }, 1000)
          return
        }
        this.pass = true
        setTimeout(() => {
          this.$storage.session.deleteCache('lock_password')
          this.$router.push('/dashboard')
        }, 1000)
      }
    },
  }
</script>

<style lang="scss">
  .lock-container {
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
  }
  .lock-container::before {
    z-index: -999;
    content: "";
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-image: url("~@/assets/img/lockLogin.png");
    background-size: cover;
  }
  .lock-form {
    width: 300px;
  }
</style>
