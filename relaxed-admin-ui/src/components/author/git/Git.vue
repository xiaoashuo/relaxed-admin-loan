<template>
  <div >
    <svg-icon :style="iconStyle" icon-class="github" @click="goto" />
  </div>
</template>

<script>
    export default {
        name: "Git",
      props:{
         iconStyle:{
           type:Object,
           default: ()=>({
             height: '30px',
             'padding-top': '10px'
           })
         }
      },
      data() {
        return {
          url: 'https://github.com/xiaoashuo/relaxed-admin'
        }
      },
      methods: {
        goto() {
          window.open(this.url)
        }
      }
    }
</script>

<style scoped>

</style>
