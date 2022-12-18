<template>
    <div class="templateSealConfig">
      <pdf-canvas ref="yiPdfRef" :options="pdfOptions"></pdf-canvas>
      <!-- 盖章部分 -->

      <fabric-canvas ref="fabricCanvasRef"
                     :source-style="getSourceStyle"
                     :rectangle="fabricPosition"
                     @rightMouseClick="handleShow"
                     @cancelMouseClick="handleHide"
      ></fabric-canvas>
      <div class="ele-control" style="margin-bottom:2%;">
        <el-button class="btn-outline-dark" @click="removeSelectedSignature"> 删除签章</el-button>
        <el-button class="btn-outline-dark" @click="clearAllSignature"> 清除所有签章</el-button>
        <el-button class="btn-outline-dark" @click="submitSignature">提交所有签章信息</el-button>
      </div>
      <div :class="['box', 'default']" >
        <v-contextmenu  ref="contextmenu" theme="default">
          <v-contextmenu-item :auto-hide="false" @click="showLocation">显示坐标</v-contextmenu-item>
          <v-contextmenu-item divider></v-contextmenu-item>
          <v-contextmenu-item  @click="removeSelectedSignature">删除</v-contextmenu-item>

        </v-contextmenu>
      </div>
    </div>
</template>

<script>
  export default {
    name: 'TemplateSealConfig',
    data(){
      return {
        //pdf
        pdfOptions:this.getPdfOptions(),
        //fabric
        fabricPosition: null,
      }
    },
    methods:{

      getPdfOptions() {
        return {
          beforeRenderPage: () => {

          },
          afterRenderPage: (pageNum, width, height) => {
            this.fabricRectangle({ width, height })
            // //3.回显签章信息
            this.showSign(pageNum, true)
          },
          beforePageChange: (currentPage) => {
            this.saveSignature()

          },
          afterPageChange: (currentPage, toPage) => {
            //此处等同于清楚本页签章显示
            this.showSign(toPage)
          }
        }
      },
      //fabric
      // 设置绘图区域宽高
      fabricRectangle(data) {
        this.fabricPosition = data;
      },
      getSourceStyle(){
        let innerPdfCenter=document.querySelector("#pdfCanvas");
        return {top:'50px',left: innerPdfCenter.offsetLeft+'px'}

      },
    }
  }
</script>

<style scoped>

</style>
