<template>
  <el-card style="margin-bottom:20px;">
    <div slot="header" class="clearfix">
      <span>个人信息</span>
    </div>

    <div class="user-profile">
      <div class="box-center">
        <user-avatar />
      </div>
      <div class="box-center">
        <div class="user-name text-center">{{ user.nickname }}</div>
        <div class="user-role text-center text-muted">{{ user.nickname }}</div>
      </div>
    </div>

    <div class="user-bio">
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header" />
        <el-descriptions :column="1">
          <el-descriptions-item label="登录账号">{{ user.username }}</el-descriptions-item>
          <el-descriptions-item label="昵称">{{ user.nickname }}</el-descriptions-item>
          <el-descriptions-item label="部门信息">{{ organization.name }}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{ user.createdTime }}</el-descriptions-item>

          <el-descriptions-item label="角色信息">
            <el-tag
              v-for="(role,index) in user.roleList"
                    :key="index"
                    size="mini"
              style="margin: 2px 5px"
            >{{ role.name }}</el-tag>
          </el-descriptions-item>

        </el-descriptions>
      </div>

    </div>
  </el-card>
</template>

<script>
import userAvatar from '@/views/system/profile/components/userAvatar'
import { profile } from '@/api/user'

export default {
  components: {  userAvatar },

  data() {
    return {
      user: {},
      organization: {}
    }
  },
  created() {
    profile().then(res => {
      this.user = res.data
      this.organization = this.user.organization
    })
  }

}
</script>

<style lang="scss" scoped>
.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}

.user-profile {
  .user-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 10px;
  }

  .user-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }

  .box-social {
    padding-top: 30px;

    .el-table {
      border-top: 1px solid #dfe6ec;
    }
  }

  .user-follow {
    padding-top: 20px;
  }
}

.user-bio {
  margin-top: 20px;
  color: #606266;

  span {
    padding-left: 4px;
  }

  .user-bio-section {
    font-size: 14px;
    padding: 5px 0;

    .user-bio-section-header {
      border-bottom: 1px solid #dfe6ec;

      margin-bottom: 10px;
      font-weight: bold;
    }
  }
}
</style>
