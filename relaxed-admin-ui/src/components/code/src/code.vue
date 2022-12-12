<template>
  <div class="yi-code">
    <pre class="bg">
      <code :class="'language'+language" v-html="highlightedCode" />
    </pre>
  </div>
</template>

<script>
  import hljs from 'highlight.js'
  import 'highlight.js/styles/github.css'
    export default {
        name: "yi-code",
      props: {
        language: {
          type: String,
          default: 'html'
        },
        code: {
          type: String,
          default: ''
        }
      },
      data(){
          return {
            highlightedCode:''
          }
      },
      created() {
        this.highlightedCode=hljs.highlight(this.code, { language: this.language }).value
      },
      watch:{
        code(newValue){
          this.highlightedCode=hljs.highlight(newValue, { language: this.language }).value
        }
      }
    }
</script>

<style scoped>

</style>
