<template>
    <div class="templateConfig">

        <div  class="leftCol">

          <div class="fileType">
            <div>文件类型</div>
            <dict-select v-model="fileType" v-bind="fileTypeConfig"></dict-select>
          </div>
          <div class="keystore">
            <span>证书列表</span>
            <yi-select  v-model="keystoreId" v-bind="keyStoreConfig"></yi-select>
          </div>
          <div class="seal">
            <div class="left-title">我的印章</div>
            <draggable v-model="sealList" :group="{ name: 'itext', pull: 'clone' }" :sort="false" @end="end">
              <transition-group type="transition">
                <li v-for="item in sealList" :key="item.sealId" class="item" style="text-align:center;">
                  <img :src="item.sealAddress" width="100%;" height="100%" class="imgstyle" />
                </li>
              </transition-group>
            </draggable>
          </div>

        </div>
        <div   class="contentCol">

          <pdf-canvas ref="yiPdfRef" :options="pdfOptions"></pdf-canvas>
          <!-- 盖章部分 -->
          <canvas id="fabricCanvas"></canvas>

          <div class="ele-control" style="margin-bottom:2%;">
            <el-button class="btn-outline-dark" @click="removeSelectedSignature"> 删除签章</el-button>
            <el-button class="btn-outline-dark" @click="clearAllSignature"> 清除所有签章</el-button>
            <el-button class="btn-outline-dark" @click="submitSignature">提交所有签章信息</el-button>
          </div>
        </div>
        <div  class="rightCol">
          <div class="info" >
            <div class="item">
              <div class="right-item-title">文件主题</div>
              <div class="detail-item-desc">操作项目名称</div>
            </div>
            <div class="item">
              <div class="right-item-title">文件主题</div>
              <div class="detail-item-desc">操作项目名称</div>
            </div>

          </div>
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
  import {getListData as getSealListData} from '@/api/loan/seal'

  import { getSelectData as getCertificateSelectData } from '@/api/loan/certificate'
  import PdfCanvas from '@/components/pdf/PdfCanvas'

  import YiFabric from '@/components/pdf/YiFabric'
  const SIGN_CACHE_KEY="signs";
  export default {
    name: 'ProjectTemplateConfig',
    components:{
      YiSelect,draggable,PdfCanvas
    },
    data(){
      return{
        //pdf
        pdfOptions:this.getPdfOptions(),
        //fabric
        whDatas: null,

        fabricContext: null,
        sealList:[],
        fileType: null,
        fileTypeConfig:{
          dictCode: 'file_type',

          otherProps:{
            clearable: true
          }
        },
        keystoreId:null,
        keyStoreConfig:{
          remoteLoad: true,
          request: getCertificateSelectData,

          otherProps:{

            clearable: true
          }
        }
      }
    },
    created() {
      getSealListData().then(res=>{
        this.sealList=res.data
      })
    },
    mounted() {
      this.setPdfArea()
      this.fabricContext=new YiFabric('#fabricCanvas',this.getFabricConfig())
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
            let fabricCanvasElement=document.querySelector("#fabricCanvas");
            fabricCanvasElement.style="border:1px solid #5ea6ef"
            this.fabricContext.render(this.whDatas)

          }
        },
      },

    },
    methods:{

      handleShow (e,position) {
        this.$refs.contextmenu.show({ top:position.top,left:position.left })
      },
      handleHide() {
        this.$refs.contextmenu.hide()
      },
      // 生成绘图区域
      getFabricConfig(){
        return {
          //实列化canvas 后调用回调
          afterInstance:()=>{
            let innerPdfCenter=document.querySelector("#pdfCanvas");
            //canvas 容器
            let container = document.querySelector(".canvas-container");
            container.style.position = "absolute";
            container.style.top = "50px";
            container.style.left = innerPdfCenter.offsetLeft+'px';
          },
          addCanvasEvent:(fabricCanvas)=>{
            // 按下鼠标
            fabricCanvas.on('mouse:down', this.fabricOnMouseDown)

            // 拖拽边界 不能将图片拖拽到绘图区域外
            fabricCanvas.on("object:moving", function (e) {
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
          }
        }
      },


      /**
       * 监听鼠标事件
       * @param opt
       */
      fabricOnMouseDown(opt){
        // 右键，且在元素上右键 button: 1-左键；2-中键；3-右键

        console.log('====',opt)
        // 在画布上右键，target 为 null
        if (opt.button === 3 && opt.target) {
          opt.e.preventDefault()
          // 获取当前元素
          this.fabricContext.setActiveObject(opt.target)
          this.$nextTick(()=>{
            // 设置右键菜单位置
            // 当前鼠标位置
            //相对于文档的距离event.pageY 相对于浏览器窗口的距离（不包含滚动的距离） event.clientX
            const position={top: opt.e.pageY, left: opt.e.pageX}
            this.handleShow(opt.e,position)
          })

        } else {
          opt.e.preventDefault()
          this.handleHide()

        }
      },
      showLocation(){
        const activeObject=this.fabricContext.getActiveObject()
        const position={left: activeObject.left,top:activeObject.top,width:activeObject.width,
        height:activeObject.height}
        this.$message.info(JSON.stringify(position))
        console.log(activeObject)
        this.handleHide()
      },

      //设置PDF预览区域高度
      setPdfArea(){
        this.pdfUrl = 'http://localhost:9401/profile/upload/20221215/d07c9994-3a48-4ec8-b01b-70dfa9e09fd2.pdf';
        this.$refs.yiPdfRef.show(this.pdfUrl)
      },
      getPdfOptions() {
        return {
          beforeRenderPage: () => {
            console.log("执行类渲染")
            // this.clearRightMenuInfo()
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
      // 设置绘图区域宽高
      fabricRectangle(data) {
        this.whDatas = data;
      },
      saveSignature(){

        let data = this.fabricContext.getObjects(); //获取当前页面内的所有签章信息
        let caches=  this.$storage.local.getCache(SIGN_CACHE_KEY,{})
        const pageNum=this.$refs.yiPdfRef.currentPage
        console.log("当前页面的所有签章信息",this.fabricContext,pageNum,this.fabricContext.getObjects())
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
            url: this.sealList[val.index].sealAddress,
            index:val.index,
            uid: val.uid
          }
          i++;
        }

        if (signDatas&&Object.keys(signDatas).length>0){
          caches[pageNum] = signDatas;
          this.$storage.local.setCache(SIGN_CACHE_KEY,caches)
        }


      },
      /**
       * 展示盖章信息
       * @param pageNum 当前页码
       * @param isFirst 是否为第一次渲染 pdf文档
       * @returns {boolean}
       */
      showSign(pageNum, isFirst = false) {
        if(isFirst == false) this.fabricContext.clear(); //清空页面所有签章
        let caches = this.$storage.local.getCache(SIGN_CACHE_KEY,{})
        let pageSealData = caches[pageNum];
        if(pageSealData) {
          console.log("显示",pageSealData)
          for (let index in pageSealData) {
            this.fabricContext.addImage(pageSealData[index])
          }
        }
      },
      // 删除签章
      removeSelectedSignature() {
        let activeObject = this.fabricContext.getActiveObject()
        if (!activeObject){
          this.$message.error("请选中签章")
          return
        }
        this.fabricContext.remove(activeObject)
        const caches=this.$storage.local.getCache(SIGN_CACHE_KEY,{})
        const pageNum=  this.$refs.yiPdfRef.currentPage
        const pageSeals=caches[pageNum]
        const sealIdIndex=activeObject['uid']
        let newPageSeals={}
        for(const key in pageSeals){
          const item=pageSeals[key]
          if (item.uid!=sealIdIndex){
            newPageSeals[key]=item
          }
        }
        console.log("页面章数量",activeObject,sealIdIndex,newPageSeals)
        if(Object.keys(newPageSeals)<=0){
          //页面章小于等于0
          delete caches[pageNum]
        }else{
          caches[pageNum]=newPageSeals
        }

        this.$storage.local.setCache(SIGN_CACHE_KEY,caches)
      },
      //提交数据
      submitSignature() {
        //保存当前页面签章事件,防止当前移动坐标不更新
        this.saveSignature();
        let caches = localStorage.getItem(SIGN_CACHE_KEY);
        console.log(caches);
        return false

      },
      //清空数据
      clearAllSignature() {
        //清空页面所有签章
        this.fabricContext.clear();
        //清除缓存
        this.$storage.local.deleteCache(SIGN_CACHE_KEY)
      },
      end(e){

        let sealListElement = this.sealList[e.newDraggableIndex]

        const sealInfo={url: sealListElement.sealAddress,
          uid:new Date().getTime(),
          left: e.originalEvent.layerX,
          top: e.originalEvent.layerY,
          index: e.newDraggableIndex }
          this.fabricContext.addImage(sealInfo,this.saveSignature)


      },
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
  .leftCol{
    width: 200px;
    margin-right: 20px;

    .keystore{
      span{
        display: inline-block;
        margin: 5px 0;
      }
    }
    .seal{
      margin-top: 20px;
      height: 600px;
      border: 1px solid darkgray;
      overflow-y: auto;
      li {
        list-style-type:none;
        padding: 10px;
      }
      .imgstyle{
        vertical-align: middle;
        width: 130px;
        border: solid 1px #e8eef2;
        background-image: url("~@/assets/img/tuo.png");
        background-repeat:no-repeat;
      }
    }

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
    .ele-control {
      text-align: center;
      margin-top: 3%;
    }
  }
  .rightCol{

    width: 200px;


    .info{

      height: 150px;

      border: 1px solid red;
    }
    .left-title {
      text-align:center;
      padding-bottom: 10px;
      border-bottom: 1px solid #eee;
    }
  }

</style>
