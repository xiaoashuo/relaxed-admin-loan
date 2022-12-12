<template>
    <div class="json-viewer">
      <json-viewer :value="jsonData" :expand-depth=5 copyable
                   boxed></json-viewer>
    </div>
</template>

<script>
  import JsonViewer from 'vue-json-viewer'
    export default {
        name: "yi-json-viewer",
       components:{
        JsonViewer
       },
      props:{
        jsonData:{

          default: ()=>( {
            lat: 43.614624,
            lng: 3.879995
          })
        }
      },


    }
</script>

<style scoped>

</style>
