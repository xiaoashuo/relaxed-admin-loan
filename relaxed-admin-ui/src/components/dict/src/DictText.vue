<template>
    <span :style="{color: color}">{{ showText }}</span>
</template>

<script>
  import DictDisplayMixin from "@/components/dict/src/mixin/DictDisplayMixin";
    export default {
        name: "DictText",
      mixins: [DictDisplayMixin],
      computed: {
        color() {
          let color
          if(this.dictItem.attributes){
            color = this.dictItem.attributes['textColor']
          }
          return this.colors[this.value] || color || this.uniformColor
        }
      }
    }
</script>

<style scoped>

</style>
