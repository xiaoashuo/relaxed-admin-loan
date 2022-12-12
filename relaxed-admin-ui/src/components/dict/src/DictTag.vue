<template>
  <el-tag :type="type" :effect="effect" :color="color">{{ showText }}</el-tag>
</template>

<script>
import DictDisplayMixin from '@/components/dict/src/mixin/DictDisplayMixin'
export default {
  name: 'DictTag',
  mixins: [DictDisplayMixin],
  props: {
    type: {
      type: String,
      default: ''
    },

    effect: {
      type: String,
      default: 'light'
    }
  },
  computed: {
    color() {
      let color
      if (this.dictItem.attributes) {
        color = this.dictItem.attributes['tagColor']
      }
      return this.colors[this.value] || color || this.uniformColor
    }
  }
}
</script>

<style scoped></style>
