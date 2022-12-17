<template>
    <div class="pdfCanvas">
      <div class="page">
        <!-- <el-button class="btn-outline-dark" @click="zoomIn">-</el-button>
        <span style="color:red;">{{(percentage*100).toFixed(0)+'%'}}</span>
        <el-button class="btn-outline-dark" @click="zoomOut">+</el-button> -->
        <el-button class="btn-outline-dark" @click="prevPage">上一页</el-button>
        <el-button class="btn-outline-dark" @click="nextPage">下一页</el-button>
        <el-button class="btn-outline-dark">{{ currentPage }}/{{ totalPage }}页</el-button>
        <el-input-number style="margin:0 5px;border-radius:5px;" class="btn-outline-dark"  v-model="justPageNum" :min="1" :max="totalPage" label="输入页码"></el-input-number>
        <el-button class="btn-outline-dark" @click="just()">跳转</el-button>
      </div>
      <canvas id="pdfCanvas" />
    </div>
</template>

<script>
  import PDF from '@/components/pdf/pdf'
  export default {
    name: 'yi-pdf',
    props:{
      options:{
        type:Object,
        required: true
      }
    },
    data(){
      return{
        justPageNum: 1,
        pdfContext:null,
      }
    },
    created() {
      this.pdfContext=this.getPdfContext()
    },
    computed:{
      currentPage(){
        return  this.pdfContext.getPageNum()
      },
      totalPage(){
        return  this.pdfContext.getTotalPage()
      }
    },
    methods:{
      getPdfContext() {
        return new PDF('pdfCanvas', this.options)
      },
      show(pdfUrl){
        this.pdfContext.showPdf(pdfUrl)
      },
      prevPage(){
        this.pdfContext.prevPage()
        this.justPageNum=this.pdfContext.getPageNum()
      },
      nextPage(){
        this.pdfContext.nextPage()
        this.justPageNum=this.pdfContext.getPageNum()
      },

      just() {
        this.pdfContext.justPage(this.justPageNum)
        this.justPageNum=this.pdfContext.getPageNum()
      },
    }
  }
</script>

<style lang="scss" scoped>
  .page{
    text-align: center;
    margin:0 auto;
  }

</style>
