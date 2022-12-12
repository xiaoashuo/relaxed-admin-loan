<template>
  <div class="account">
    <el-form>
      <el-form-item label="昵称">
        <el-input v-model.trim="user.nickname" />
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model.trim="user.email" />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input v-model="user.phone" maxlength="11" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">保存</el-button>
      </el-form-item>

    </el-form>
  </div>

</template>

<script>

export default {
  data() {
    return {
      user: this.$store.getters.userInfo
    }
  },

  created() {
    console.log('属性', this.user)
  },
  methods: {
    submit() {
      const payload = { ...this.user }

      this.$store.dispatch('auth/updateUserInfo', payload)
        .then(() => {
          this.$message({
            message: '信息更新成功',
            type: 'success',
            duration: 5 * 1000
          })
        })
    }
  }
}
</script>
