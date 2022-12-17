<template>
  <div id="elesign" class="elesign">
    <el-row :gutter="10">
      <el-col id="leftCol"  style="margin-top:1%;width: 200px">
        <div class="left-title">我的印章</div>
        <draggable v-model="sealImageList" :group="{ name: 'itext', pull: 'clone' }" :sort="false" @end="end">
          <transition-group type="transition">
            <li v-for="item in sealImageList" :key="item" class="item" style="text-align:center;">
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
          <el-button class="btn-outline-dark">{{ currentPage }}/{{ totalPage }}页</el-button>
          <el-input-number style="margin:0 5px;border-radius:5px;" class="btn-outline-dark"  v-model="justPageNum" :min="1" :max="totalPage" label="输入页码"></el-input-number>
          <el-button class="btn-outline-dark" @click="just()">跳转</el-button>
        </div>
        <canvas id="pdfCanvas" />
        <!-- 盖章部分 -->
        <canvas id="fabricCanvas"></canvas>

        <div class="ele-control" style="margin-bottom:2%;">
          <el-button class="btn-outline-dark" @click="removeSelectedSignature"> 删除签章</el-button>
          <el-button class="btn-outline-dark" @click="clearAllSignature"> 清除所有签章</el-button>
          <el-button class="btn-outline-dark" @click="submitSignature">提交所有签章信息</el-button>
        </div>
      </el-col>
      <el-col   style="margin-top:1%;width: 200px">
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
              <div class="right-item-title">文件名称</div>
              <div class="detail-item-desc">{{ taskInfo.filename }}</div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
    <div

      id="menu"
      class="menu-x"
      v-show="showRightMenu"
      :style="menuPosition"
      @contextmenu.prevent=""
      ref="menu"
    >
      <div>什么都不做</div>

      <div @click="rightDeleteClick">删除</div>
    </div>
  </div>
</template>

<script>
  import {_debounce} from '@/utils'
  import {fabric} from './fabric';
  import PDF from './pdf'
  import draggable from "vuedraggable";
  const SIGN_CACHE_KEY="signs";
  export default {
    name:'pdf',
    components: {draggable},
    props:{
      taskInfo:{
        type:Object,
        default:()=>({
          title:'默认标题',
          operator:'默认操作者',
          filename:'文件名称'
        })
      },
      //签章图片列表 img 标签src使用 base64 或地址
      sealImageList:{
        type: Array,
        default:()=>([require('./sign.png'),require('./seal.png')])
      }

    },
    data() {
      return {
        showRightMenu:false,
        menuPosition:'',
        //pdf预览
        pdfUrl: '',
        justPageNum: 1,
        sealUrl: '',
        signUrl: '',
        canvasEle: null,
        whDatas: null,
        pdfContext:null


      }
    },
    computed: {
      hasSigna() {
        return this.canvasEle && this.canvasEle.getObjects()[0] ? true : false;
      },
      currentPage(){
       return  this.pdfContext.getPageNum()
      },
      totalPage(){
        return  this.pdfContext.getTotalPage()
      }
    },
    created(){


      this.pdfContext=this.getPdfContext()
      window.addEventListener("resize",_debounce(()=>{
        if (this.canvasEle){
          this.canvasEle.dispose()
        }
        if (this.pdfContext){
          this.pdfContext=this.getPdfContext()
        }
        this.setPdfArea()
      },300))
      this.setPdfArea()
    },


    methods: {

       getPdfContext() {
        return new PDF('pdfCanvas', {
          beforeRenderPage: () => {
            this.clearRightMenuInfo()
          },
          afterRenderPage: (pageNum, width, height) => {
            this.fabricRectangle({ width, height })
            //3.回显签章信息
            this.showSign(pageNum, true)
          },
          beforePageChange: (currentPage) => {
            this.saveSignature()

          },
          afterPageChange: (currentPage, toPage) => {
            //此处等同于清楚本页签章显示
            this.showSign(toPage)
          }

        })
      },
      //pdf相关
      //设置PDF预览区域高度
      setPdfArea(){
        this.pdfUrl = 'http://localhost:9401/profile/upload/20221215/d07c9994-3a48-4ec8-b01b-70dfa9e09fd2.pdf';
        //  this.pdfurl=res.data.data.pdfurl;
        this.$nextTick(() => {
          this.pdfContext.showPdf(this.pdfUrl)
        });
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

      //fabric 相关
      // 生成绘图区域
      renderFabric() {

        let canvaEle = document.querySelector("#fabricCanvas");
        //let pCenter=document.querySelector(".pCenter");
        let pdfCenter=document.querySelector("#pdfCanvas");
     //   console.log(pdfCenter.offsetLeft,pdfCenter.offsetLeft,pCenter.clientWidth)
        //canvaEle.width = pCenter.clientWidth;
        canvaEle.width = this.whDatas.width;
        // canvaEle.height = (this.whDatas.height)*(this.scale);
        canvaEle.height = this.whDatas.height;
        this.canvasEle = new fabric.Canvas('fabricCanvas',{
          fireRightClick: true, // 左键button1 滚轮2 启用右键，button的数字为3
          stopContextMenu: true, // 禁止默认右键菜单
        });
        //canvas 容器
        let container = document.querySelector(".canvas-container");
        container.style.position = "absolute";
        container.style.top = "50px";
        container.style.left = pdfCenter.offsetLeft+'px';
      },
      /**
       * 监听鼠标事件
       * @param opt
       */
      fabricOnMouseDown(opt){


// 右键，且在元素上右键
        // button: 1-左键；2-中键；3-右键
        // 在画布上右键，target 为 null
        if (opt.button === 3 && opt.target) {
          // 获取当前元素
          console.log(opt.target.left)
          this.canvasEle.setActiveObject(opt.target)
          // 显示菜单
          this.showRightMenu = true
          this.$nextTick(()=>{
            // 设置右键菜单位置
            // 1. 获取菜单组件的宽高
            const menuWidth = this.$refs.menu.offsetWidth
            const menuHeight = this.$refs.menu.offsetHeight

            const canvasContainer=document.querySelector('.canvas-container')
            // 当前鼠标位置
           // let pointX = opt.pointer.x+200
            let pointX = opt.target.left+canvasContainer.offsetLeft
            let pointY = opt.pointer.y

            console.log("菜单组件宽高",menuWidth,menuHeight,"当前鼠标位置",pointX,pointY,
              "当前画布宽高",this.canvasEle.width,this.canvasEle.height,opt.target)
            if (this.canvasEle.width - pointX <= menuWidth) {
              pointX -= menuWidth
            }
            if (this.canvasEle.height - pointY <= menuHeight) {
              pointY -= menuHeight
            }

            this.menuPosition = ` left: ${pointX}px; top: ${pointY}px;`

          })

        } else {
          this.hiddenRightMenu()
        }
      },
      // 相关事件操作哟
      fabricCanvasEvents() {
        // 按下鼠标
        this.canvasEle.on('mouse:down', this.fabricOnMouseDown)

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
      /**
       * 添加公章
       * @param sealInfo 签章信息
       */
      addSeal(sealInfo,callback) {
        console.log("-----",sealInfo)
        //引入图片
        fabric.Image.fromURL(
          sealInfo.sealUrl,
          (oImg) => {
            oImg.set({
              left: sealInfo.left,
              top: sealInfo.top,
              // angle: 10,
              scaleX: 1,
              scaleY: 1,
              index:sealInfo.index,
              sealId:sealInfo.sealId
            });
            // oImg.scale(0.5); //图片缩小一
            this.canvasEle.add(oImg);
            if (callback){
              callback()
            }
          }
        );
      },
      /**
       *  盖章部分开始
       */
      // 设置绘图区域宽高
      fabricRectangle(data) {
        this.whDatas = data;
      },

      hiddenRightMenu() {
        this.showRightMenu = false
     },
      clearRightMenuInfo(){
        this.menuPosition= ''
        this.showRightMenu=false
      },

      rightDeleteClick(){
        this.hiddenRightMenu()
        this.removeSelectedSignature()
      },

      // 删除签章
      removeSelectedSignature() {
        let activeObject = this.canvasEle.getActiveObject()
        console.log("当前选中",activeObject)
        this.canvasEle.remove(activeObject)
        const caches=this.$storage.local.getCache(SIGN_CACHE_KEY,{})
        const pageNum=  this.pdfContext.getPageNum()
        const pageSeals=caches[pageNum]
        const sealIdIndex=activeObject['sealId']
        delete pageSeals[sealIdIndex]
        console.log("页面章数量",pageSeals)
        if(Object.keys(pageSeals)<=0){
          //页面章小于等于0
          delete caches[pageNum]
        }else{
          caches[pageNum]=pageSeals
        }

        this.$storage.local.setCache(SIGN_CACHE_KEY,caches)
      },
      /**
       * 展示盖章信息
       * @param pageNum 当前页码
       * @param isFirst 是否为第一次渲染 pdf文档
       * @returns {boolean}
       */
      showSign(pageNum, isFirst = false) {
        if(isFirst == false) this.canvasEle.remove(this.canvasEle.clear()); //清空页面所有签章
        let caches = this.$storage.local.getCache(SIGN_CACHE_KEY,{})
        let pageSealData = caches[pageNum];
        if(pageSealData) {
          console.log("显示",pageSealData)
          for (let index in pageSealData) {
            this.addSeal(pageSealData[index]);
          }
        }
      },
      /**
       * 存储当前页所有签名 到缓存
       */
      saveSignature() {
        console.log(this.canvasEle.getObjects())
        let data = this.canvasEle.getObjects(); //获取当前页面内的所有签章信息
        let caches=  this.$storage.local.getCache(SIGN_CACHE_KEY,{})
        const pageNum=this.pdfContext.getPageNum()
        console.log("当前页面的所有签章信息",this.canvasEle,pageNum,data)
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
            pageNum: pageNum,
            cacheKey: val.cacheKey,
            sealUrl: this.sealImageList[val.index],
            index:val.index,
            sealId: i
          }
          i++;
        }

        if (signDatas&&Object.keys(signDatas).length>0){
          caches[pageNum] = signDatas;
          this.$storage.local.setCache(SIGN_CACHE_KEY,caches)
        }


      },
      //提交数据
      submitSignature() {
        //保存当前页面签章事件,防止当前移动坐标不更新
        this.saveSignature();
        let caches = localStorage.getItem(SIGN_CACHE_KEY);
        console.log(JSON.parse(caches));
        return false
      },
      //清空数据
      clearAllSignature() {
        //清空页面所有签章
        this.canvasEle.remove(this.canvasEle.clear());
        //清除缓存
        this.$storage.local.deleteCache(SIGN_CACHE_KEY)
      },
      //拖拽结束事件
      end(e){
         const sealInfo={sealUrl: this.sealImageList[e.newDraggableIndex],
         left: e.originalEvent.layerX,
         top: e.originalEvent.layerY,
         index: e.newDraggableIndex }

         this.addSeal(sealInfo,this.saveSignature)

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
            let eleCanvas=document.querySelector("#fabricCanvas");
            eleCanvas.style="border:1px solid #5ea6ef"
            this.renderFabric();
            this.fabricCanvasEvents();

          }
        },
      },

    }
  }
</script>
<style lang="scss" scoped>

  /*pdf部分*/

  .pCenter{
    overflow-x: hidden;
  }
  #pdfCanvas{
    margin-top:10px;
  }

  #fabricCanvas{
    /*border:1px solid #5ea6ef;*/
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


  .menu-x {
    width: 80px;
    position: absolute;
    background-color: #fff;
    border-radius: 4px;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);

    div {
      box-sizing: border-box;
      padding: 4px 8px;
      border-bottom: 1px solid #ccc;
      cursor: pointer;

      &:hover {
        background-color: antiquewhite;
      }

      &:first-child {
        border-top-left-radius: 4px;
        border-top-right-radius: 4px;
      }

      &:last-child {
        border-bottom: none;
        border-bottom-left-radius: 4px;
        border-bottom-right-radius: 4px;
      }
    }
  }
</style>
