<template>
    <div class="yi-image">
      <el-image
        :style="imageStyle"
        :src="src"
        :preview-src-list="previewSrcList"
      ></el-image>
    </div>
</template>

<script>
    export default {
        name: "yi-image",
         props:{
           src:{
             type:String,
             required: true
           },
           previewSrcList:{
             type: Array,
             default: ()=>([])
           },
           imageStyle:{
             type:Object,
             default: ()=>{return {
               width: '60px',
               height: '60px'
             }}
           }
         }
    }
</script>

<style scoped>

</style>
