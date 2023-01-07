<template>
  <div class="templateConfig"  ref="templateConfig">


    <div   class="contentCol">

      <pdf-canvas ref="yiPdfRef" :options="pdfOptions"></pdf-canvas>
      <!-- 盖章部分 -->

      <fabric-canvas ref="fabricCanvasRef"
                     :source-style="getSourceStyle"
                     :rectangle="fabricPosition"
                     @rightMouseClick="handleShow"
                     @cancelMouseClick="handleHide"
      ></fabric-canvas>


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
  import { YiSelect } from '@/components/select'
  import draggable from "vuedraggable";

  import PdfCanvas from '@/components/pdf/PdfCanvas'
  import FabricCanvas from '@/components/pdf/FabricCanvas'


  import { debounce } from '@/utils'

  export default {
    name: 'TemplateSealConfig',
    components:{
      YiSelect,draggable,PdfCanvas,FabricCanvas
    },
    props:{

      sealList:{
        type: Array,
        default:()=>([])
      },
      sealLocation:{
        type:Object,
        default:()=>({})
      }
    },
    data(){
      return{
        //pdf
        pdfOptions:this.getPdfOptions(),
        //fabric
        fabricPosition: null,
        /**
         * {
         *   pageNo:{
         *     sealIndex: sealInfo
         *   }
         * }
         *
         */
        signLocations: this.sealLocation

      }
    },


    mounted() {
      // this.pdfUrl = 'http://localhost:9401/profile/upload/20221215/d07c9994-3a48-4ec8-b01b-70dfa9e09fd2.pdf';
      // this.$refs.yiPdfRef.show(this.pdfUrl)
      window.addEventListener("resize",   this.resizeChange)
    },

    beforeDestroy() {
      window.removeEventListener("resize", this.resizeChange)
    },
    methods:{
      showPdf(blob){
        this.$refs.yiPdfRef.showBlob(blob)
      },
      resizeChange:debounce(function(){
        try {

          //热加载此处会出现异常,需要将pdfcanvas设置居中对齐 resize才会重新渲染
          this.saveSignature()
          this.$refs.fabricCanvasRef.renderAll()
          this.showSign(this.$refs.yiPdfRef.getPageNum(), true)
        } catch (e) {
          console.log('重绘界面异常', e)
        }
      }, 300,false),

      //右键菜单
      handleShow (e,position) {
        this.$refs.contextmenu.show({ top:position.top,left:position.left })
      },
      handleHide() {
        this.$refs.contextmenu.hide()
      },

      showLocation(){
        const activeObject=this.$refs.fabricCanvasRef.getActiveObject()
        const position={left: activeObject.left,top:activeObject.top,width:activeObject.width,
          height:activeObject.height}
        this.$message.info(JSON.stringify(position))
        this.handleHide()
      },



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
        return {top:'60px',left: innerPdfCenter.offsetLeft+'px'}

      },


      saveSignature(){

        let data = this.$refs.fabricCanvasRef.getObjects(); //获取当前页面内的所有签章信息

        const pageNum=this.$refs.yiPdfRef.currentPage
        let signDatas = {}; //存储当前页的所有签章信息
        let i = 0;
        for(var val of data) {
          signDatas[i] =  {
            width: val.width,
            height: val.height,
            top: val.top,
            left: val.left,
            angle: val.angle,
            translateX: val.translateX,
            translateY: val.translateY,
            scaleX: val.scaleX,
            scaleY: val.scaleY,
            pageNum: pageNum,
            url: this.sealList[val.index].sealAddress,
            index:val.index,
            uid: val.uid
          }
          i++;
        }

        if (signDatas&&Object.keys(signDatas).length>0){
          this.signLocations[pageNum] = signDatas;
        }
      },
      /**
       * 展示盖章信息
       * @param pageNum 当前页码
       * @param isFirst 是否为第一次渲染 pdf文档
       * @returns {boolean}
       */
      showSign(pageNum, isFirst = false) {
        if(isFirst == false) this.$refs.fabricCanvasRef.clear(); //清空页面所有签章
        let pageSealData = this.signLocations[pageNum];
        if(pageSealData) {
          for (let index in pageSealData) {
            this.$refs.fabricCanvasRef.addImage(pageSealData[index])
          }
        }
      },
      // 删除签章
      removeSelectedSignature() {
        let activeObject = this.$refs.fabricCanvasRef.getActiveObject()
        if (!activeObject){
          this.$message.error("请选中签章")
          return
        }
        this.$refs.fabricCanvasRef.remove(activeObject)

        const pageNum=  this.$refs.yiPdfRef.currentPage
        const pageSeals=this.signLocations[pageNum]
        const sealIdIndex=activeObject['uid']

        let newPageSeals={}
        for(const key in pageSeals){
          const item=pageSeals[key]
          if (item.uid!=sealIdIndex){
            newPageSeals[key]=item
          }
        }
        if(Object.keys(newPageSeals).length<=0){
          //页面章小于等于0
          delete this.signLocations[pageNum]
        }else{
          this.signLocations[pageNum]=newPageSeals
        }

      },
      //提交数据
      submitSignature() {
        //保存当前页面签章事件,防止当前移动坐标不更新
        this.saveSignature();
        console.log(this.signLocations);
      },

      //清空数据
      clearAllSignature() {
        //清空页面所有签章
        this.$refs.fabricCanvasRef.clear();
        //清除缓存
        this.signLocations= {}
        this.$emit("update:sealLocation",this.signLocations)
      },
      saveImage(sealInfo){
        this.$refs.fabricCanvasRef.addImage(sealInfo,this.saveSignature)
      }

    }
  }
</script>

<style lang="scss" scoped>

  .templateConfig{
    display: flex;
    margin: auto;
    position: relative;
    padding-left: 10px;
    padding-top: 10px;
  }

  .contentCol{

    flex: 1;

    text-align: center;
    /*pdf部分*/


    .pCenter{
      overflow-x: hidden;
    }

    #fabricCanvas{
      /*border:1px solid #5ea6ef;*/
      overflow: hidden;
    }
    html:fullscreen {
      background: white;
    }
    @keyframes ani-demo-spin {
      from { transform: rotate(0deg);}
      50% { transform: rotate(180deg);}
      to { transform: rotate(360deg);}
    }

  }


</style>
