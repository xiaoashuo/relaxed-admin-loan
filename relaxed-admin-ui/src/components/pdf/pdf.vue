<template>
  <div id="elesign" class="elesign">
    <el-row :gutter="10">
      <el-col :span="4" style="margin-top:1%;">
        <div class="left-title">我的印章</div>
        <draggable v-model="mainImagelist" :group="{ name: 'itext', pull: 'clone' }" :sort="false" @end="end">
          <transition-group type="transition">
            <li v-for="item in mainImagelist" :key="item" class="item" style="text-align:center;">
              <img :src="item" width="100%;" height="100%" class="imgstyle" />
            </li>
          </transition-group>
        </draggable>
      </el-col>
      <el-col :span="16" style="text-align:center;" class="pCenter">
        <div class="page">
          <!-- <el-button class="btn-outline-dark" @click="zoomIn">-</el-button>
          <span style="color:red;">{{(percentage*100).toFixed(0)+'%'}}</span>
          <el-button class="btn-outline-dark" @click="zoomOut">+</el-button> -->
          <el-button class="btn-outline-dark" @click="prevPage">上一页</el-button>
          <el-button class="btn-outline-dark" @click="nextPage">下一页</el-button>
          <el-button class="btn-outline-dark">{{ pageNum }}/{{ numPages }}页</el-button>
          <el-input-number style="margin:0 5px;border-radius:5px;" class="btn-outline-dark"  v-model="pageNum" :min="1" :max="numPages" label="输入页码"></el-input-number>
          <el-button class="btn-outline-dark" @click="cutover">跳转</el-button>
        </div>
        <canvas id="the-canvas" />
        <!-- 盖章部分 -->
        <canvas id="fabricCanvas"></canvas>

        <div class="ele-control" style="margin-bottom:2%;">
          <el-button class="btn-outline-dark" @click="removeSignature"> 删除签章</el-button>
          <el-button class="btn-outline-dark" @click="clearSignature"> 清除所有签章</el-button>
          <el-button class="btn-outline-dark" @click="submitSignature">提交所有签章信息</el-button>
        </div>
      </el-col>
      <el-col :span="4" style="margin-top:1%;">
        <div class="left-title">任务信息</div>
        <div style="text-align:center;">
          <div>
            <div class="right-item">
              <div class="right-item-title">文件主题</div>
              <div class="detail-item-desc">{{ taskInfo.title }}</div>
            </div>
            <div class="right-item">
              <div class="right-item-title">发起方</div>
              <div class="detail-item-desc">{{ taskInfo.operator }}</div>
            </div>
            <div class="right-item">
              <div class="right-item-title">截止时间</div>
              <div class="detail-item-desc">{{ taskInfo.filename }}</div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {fabric} from 'fabric';
  let pdfjsLib =require("pdfjs-dist/legacy/build/pdf.js");
  import workerSrc from "pdfjs-dist/legacy/build/pdf.worker.entry";
  pdfjsLib.GlobalWorkerOptions.workerSrc = workerSrc;
  import draggable from "vuedraggable";
  export default {
    components: {draggable},
    props:{
      taskInfo:{
        type:Object,
        default:()=>({
          title:'默认标题',
          operator:'默认操作者',
          filename:'文件名称'
        })
      }
    },
    data() {
      return {
        //pdf预览
        pdfUrl: '',
        pdfDoc: null,
        numPages: 1,
        pageNum: 1,
        scale: 1,
        pageRendering: false,
        pageNumPending: null,
        sealUrl: '',
        signUrl: '',
        canvas: null,
        ctx: null,
        canvasEle: null,
        whDatas: null,
        mainImagelist: [],

      }
    },
    computed: {
      hasSigna() {
        return this.canvasEle && this.canvasEle.getObjects()[0] ? true : false;
      },
    },
    created(){
      var that = this;
      that.mainImagelist = [require('./sign.png'),require('./seal.png')];

      this.setPdfArea()

    },

    methods: {
      //pdf预览
      // zoomIn() {
      //   console.log("缩小");
      //   if(this.scale<=0.5){
      //     this.$message.error("已经显示最小比例")
      //   }else{
      //     this.scale-=0.1;
      //     this.percentage-=0.1;
      //     this.renderPage(this.pageNum);
      //     this.renderFabric();
      //   }
      // },
      // zoomOut() {
      //   console.log("放大")
      //   if(this.scale>=2.2){
      //     this.$message.error('已经显示最大比例')
      //   }else{
      //     this.scale+=0.1;
      //     this.percentage+=0.1;
      //     this.renderPage(this.pageNum);
      //     this.renderFabric();
      //   }
      // },
      renderPage(num) {
        let _this = this;
        this.pageRendering = true;
        return this.pdfDoc.getPage(num).then((page) => {
          console.log("当前页",page)
          let viewport = page.getViewport({ scale: _this.scale });//设置视口大小
          // 宽高单位为磅pt，渲染时直接写像素px会更清晰一点
          // 例如A4纸：595 x 841pt，但渲染时写的是595 x 841px，
          // 要是做拖拽签章功能时，注意图片单位要和PDF单位保持一致
          console.log("当前视口",viewport)
          _this.canvas.height = viewport.height;
          _this.canvas.width = viewport.width;
          // 要是做拖拽签章功能时，注意图片单位要和PDF单位保持一致

          // Render PDF page into canvas context
          let renderContext = {
            canvasContext: _this.ctx,
            viewport: viewport,
          };
          let renderTask = page.render(renderContext);

          // Wait for rendering to finish
          renderTask.promise.then(() => {
            _this.pageRendering = false;
            if (_this.pageNumPending !== null) {
              // New page rendering is pending
              this.renderPage(_this.pageNumPending);

              _this.pageNumPending = null;
            }
          });
        });
      },
      queueRenderPage(num) {
        if (this.pageRendering) {
          this.pageNumPending = num;
        } else {
          this.renderPage(num);
        }
      },
      prevPage() {
        this.confirmSignature();
        if (this.pageNum <= 1) {
          return;
        }
        this.pageNum--;
      },
      nextPage() {
        this.confirmSignature();
        if (this.pageNum >= this.numPages) {
          return;
        }
        this.pageNum++;
      },
      cutover() {
        this.confirmSignature();
      },
      //渲染pdf，到时还会盖章信息，在渲染时，同时显示出来，不应该在切换页码时才显示印章信息
      showpdf(pdfUrl) {

        let caches = JSON.parse(localStorage.getItem('signs')); //获取缓存字符串后转换为对象
        console.log(caches);
        if (caches!=null){
          let datas = caches[this.pageNum];
          if(datas != null && datas != undefined) {
            for (let index in datas) {
              this.addSeal(datas[index].sealUrl, datas[index].left, datas[index].top, datas[index].index);
            }
          }
        }
        this.canvas = document.getElementById("the-canvas");
        this.ctx = this.canvas.getContext("2d");

        pdfjsLib.getDocument({url:pdfUrl, rangeChunkSize:65536, disableAutoFetch:false}).promise.then((pdfDoc_) => {
          this.pdfDoc = pdfDoc_;
          this.numPages = this.pdfDoc.numPages;

          this.renderPage(this.pageNum).then(() => {
            this.renderPdf({
              width: this.canvas.width,
              height: this.canvas.height,
            });
          });

          this.commonSign(this.pageNum, true);
        });
      },
      /**
       *  盖章部分开始
       */
      // 设置绘图区域宽高
      renderPdf(data) {
        this.whDatas = data;
        // document.querySelector("#elesign").style.width = data.width + "px";
      },
      // 生成绘图区域
      renderFabric() {
        let canvaEle = document.querySelector("#fabricCanvas");

        let pCenter=document.querySelector(".pCenter");
        canvaEle.width = pCenter.clientWidth;
        //canvaEle.width = this.whDatas.width;
        // canvaEle.height = (this.whDatas.height)*(this.scale);
        canvaEle.height = this.whDatas.height;

        this.canvasEle = new fabric.Canvas('fabricCanvas');



        let container = document.querySelector(".canvas-container");
        container.style.position = "absolute";
        container.style.top = "50px";
        // container.style.left = "30%";
      },
      // 相关事件操作哟
      canvasEvents() {
        // 拖拽边界 不能将图片拖拽到绘图区域外
        this.canvasEle.on("object:moving", function (e) {
          var obj = e.target;
          // if object is too big ignore
          if(obj.currentHeight > obj.canvas.height || obj.currentWidth > obj.canvas.width){
            return;
          }
          obj.setCoords();
          // top-left  corner
          if(obj.getBoundingRect().top < 0 || obj.getBoundingRect().left < 0){
            obj.top = Math.max(obj.top, obj.top-obj.getBoundingRect().top);
            obj.left = Math.max(obj.left, obj.left-obj.getBoundingRect().left);
          }
          // bot-right corner
          if(obj.getBoundingRect().top+obj.getBoundingRect().height  > obj.canvas.height || obj.getBoundingRect().left+obj.getBoundingRect().width  > obj.canvas.width){
            obj.top = Math.min(obj.top, obj.canvas.height-obj.getBoundingRect().height+obj.top-obj.getBoundingRect().top);
            obj.left = Math.min(obj.left, obj.canvas.width-obj.getBoundingRect().width+obj.left-obj.getBoundingRect().left);
          }
        });
      },
      // 添加公章
      addSeal(sealUrl, left, top, index) {
        fabric.Image.fromURL(
          sealUrl,
          (oImg) => {
            oImg.set({
              left: left,
              top: top,
              // angle: 10,
              scaleX: 0.8,
              scaleY: 0.8,
              index:index,
            });
            // oImg.scale(0.5); //图片缩小一
            this.canvasEle?.add(oImg);
          }
        );
      },
      // 删除签章
      removeSignature() {
        this.canvasEle.remove(this.canvasEle.getActiveObject())
      },
      //翻页展示盖章信息
      commonSign(pageNum, isFirst = false) {
        if(isFirst == false) this.canvasEle.remove(this.canvasEle.clear()); //清空页面所有签章
        let caches = JSON.parse(localStorage.getItem('signs')); //获取缓存字符串后转换为对象
        console.log(caches);
        if(caches == null) return false;
        let datas = caches[this.pageNum];
        if(datas != null && datas != undefined) {
          for (let index in datas) {
            this.addSeal(datas[index].sealUrl, datas[index].left, datas[index].top, datas[index].index);
          }
        }
      },
      //确认签章位置并保存到缓存
      confirmSignature() {
        let data = this.canvasEle.getObjects(); //获取当前页面内的所有签章信息
        let caches = JSON.parse(localStorage.getItem('signs')); //获取缓存字符串后转换为对象

        let signDatas = {}; //存储当前页的所有签章信息
        let i = 0;
        // let sealUrl = '';
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
            pageNum: this.pageNum,
            sealUrl: this.mainImagelist[val.index],
            index:val.index
          }
          i++;
        }
        if(caches == null) {
          caches = {};
          caches[this.pageNum] = signDatas;
        } else {
          caches[this.pageNum] = signDatas;
        }
        localStorage.setItem('signs', JSON.stringify(caches)); //对象转字符串后存储到缓存
      },
      //提交数据
      submitSignature() {
        this.confirmSignature();
        let caches = localStorage.getItem('signs');
        console.log(JSON.parse(caches));
        return false
      },
      //清空数据
      clearSignature() {
        this.canvasEle.remove(this.canvasEle.clear()); //清空页面所有签章
        localStorage.removeItem('signs'); //清除缓存
      },
      end(e){
        this.addSeal(this.mainImagelist[e.newDraggableIndex], e.originalEvent.layerX, e.originalEvent.layerY, e.newDraggableIndex)
      },
      //设置PDF预览区域高度
      setPdfArea(){
        this.pdfUrl = 'http://localhost:9401/profile/upload/20221215/d07c9994-3a48-4ec8-b01b-70dfa9e09fd2.pdf';
      //  this.pdfurl=res.data.data.pdfurl;
        this.$nextTick(() => {
          this.showpdf(this.pdfUrl);//接口返回的应该还有盖章信息，不只是pdf
        });
      },
    },
    watch: {
      whDatas: {
        handler() {
          const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });

          if (this.whDatas) {
            loading.close();
            this.renderFabric();
            this.canvasEvents();
          }
        },
      },
      pageNum: function() {
        this.commonSign(this.pageNum);
        this.queueRenderPage(this.pageNum);
      }
    }
  }
</script>
<style lang="scss" scoped>

  /*pdf部分*/

  .pCenter{
    overflow-x: hidden;
  }
  #the-canvas{
    margin-top:10px;
  }

  #fabricCanvas{
    border:1px solid #5ea6ef;
    overflow: hidden;
  }
  html:fullscreen {
    background: white;
  }
  .elesign {
    display: flex;
    flex: 1;
    flex-direction: column;
    position: relative;
    /* padding-left: 180px; */
    margin: auto;
    /* width:600px; */
  }
  .page {
    text-align:center;
    margin:0 auto;
    margin-top: 1%;
  }

  .ele-control {
    text-align: center;
    margin-top: 3%;
  }
  #page-input {
    width: 7%;
  }

  @keyframes ani-demo-spin {
    from { transform: rotate(0deg);}
    50% { transform: rotate(180deg);}
    to { transform: rotate(360deg);}
  }
  /* .loadingclass{
      position: absolute;
      top:30%;
      left:49%;
      z-index: 99;
  } */
  .left {
    position: absolute;
    top: 42px;
    left: -5px;
    padding: 5px 5px;
    /*border: 1px solid #eee;*/
    /*border-radius: 4px;*/
  }
  .left-title {
    text-align:center;
    padding-bottom: 10px;
    border-bottom: 1px solid #eee;
  }
  li {
    list-style-type:none;
    padding: 10px;
  }
  .imgstyle{
    vertical-align: middle;
    width: 130px;
    border: solid 1px #e8eef2;
    background-image: url("tuo.png");
    background-repeat:no-repeat;
  }
  .right {
    position: absolute;
    top: 7px;
    right: -177px;
    margin-top: 34px;
    padding-top: 10px;
    padding-bottom: 20px;
    width: 152px;
    /*border: 1px solid #eee;*/
    /*border-radius: 4px;*/
  }
  .right-item {
    margin-bottom: 15px;
    margin-left: 10px;
  }
  .right-item-title {
    color: #777;
    height: 20px;
    line-height: 20px;
    font-size: 12px;
    font-weight: 400;
    text-align: left !important;
  }
  .detail-item-desc {
    color: #333;
    line-height: 20px;
    width: 100%;
    font-size: 12px;
    display: inline-block;
    text-align: left;
  }
  .btn-outline-dark {
    color: #0f1531;
    background-color: transparent;
    background-image: none;
    border:1px solid #3e4b5b;
  }

  .btn-outline-dark:hover {
    color: #fff;
    background-color: #3e4b5b;
    border-color: #3e4b5b;
  }
</style>
