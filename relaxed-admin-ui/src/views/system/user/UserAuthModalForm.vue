<template>
  <el-dialog :title="title" :visible.sync="visible"
             width="30%"
             center destroy-on-close>
    <yi-form v-model="form" v-bind="modalAuthConfig" />
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="handAuthConfirmClick">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import YiForm from '@/components/form'

import { modalAuthConfig } from './config/modal.auth.config'
import formModalMixin from '@/mixins/form/formModalMixin'
import { putUserRoleIds } from '@/api/role'

export default {
  name: 'UserAuthModalForm',
  components: {
    YiForm
  },
  mixins: [formModalMixin],
  data() {
    return {
      modalAuthConfig: modalAuthConfig,
      form: {}
    }
  },
  methods: {

    updatedFormCallback(payload) {
      const item = payload.item
      this.form = { ...item }
    },
    afterClose() {
      this.form = Object.assign({})
    },
    handAuthConfirmClick() {
      const roleCodes = this.form.roleCodes
      putUserRoleIds(this.form.userId, { roleCodes }).then(res => {
        this.$emit('submitSuccess', res)
        this.close()
      })
    }
  }

}
</script>

<style scoped>

</style>
