<template>
  <div class="app-container">
    <div v-if="userInfo">
      <el-row :gutter="20">

        <el-col :span="6" :xs="24">
          <user-card />
        </el-col>

        <el-col :span="18" :xs="24">
          <el-card>
            <el-tabs v-model="activeTab">
              <el-tab-pane label="基本设置" name="account">
                <account />
              </el-tab-pane>
              <el-tab-pane label="修改密码" name="resetPwd">
                <reset-pwd />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>

      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import UserCard from './components/UserCard'
import Account from './components/Account'
import resetPwd from '@/views/system/profile/components/resetPwd'

export default {
  name: 'Profile',
  components: { UserCard, Account, resetPwd },
  data() {
    return {
      activeTab: 'account'
    }
  },
  computed: {
    ...mapGetters([
      'userInfo'
    ])
  },

  methods: {

  }
}
</script>
<style lang="scss" scoped>
  .app-container{
    padding-top: 20px;
  }
</style>
