<template>
    <div class="templateConfig">

        <div  class="leftCol">
          <div class="sealWay">
            <div>签章方式</div>
            <dict-select v-model="sealWay" v-bind="sealWayConfig"></dict-select>
          </div>



          <div class="fileType">
            <div>文件类型</div>
            <dict-select v-model="fileType" v-bind="fileTypeConfig"></dict-select>
          </div>
          <div class="keystore">
            <span>证书列表</span>
            <yi-select  v-model="keystoreId" v-bind="keyStoreConfig"></yi-select>
          </div>
          <template v-if="sealWay===1">
            <div class="left-title">我的印章</div>
            <div class="seal">

              <draggable v-model="sealList" :group="{ name: 'itext', pull: 'clone' }" :sort="false" @end="end">
                <transition-group type="transition">
                  <li v-for="item in sealList" :key="item.sealId" class="item" style="text-align:center;">
                    <img :src="item.sealAddress" width="100%;" height="100%" class="imgstyle" />
                  </li>
                </transition-group>
              </draggable>
            </div>


          </template>
          <template v-if="sealWay===2">
            <div class="keyword">
              <div>关键字</div>
              <el-input v-model="keywordText" ></el-input>
            </div>
            <div class="sealSelect">
              <div>印章列表</div>
              <yi-select :options="keywordSealList" v-model="sealId" @change="handleValChange" ref="sealListRef"></yi-select>

              <div>
                <img :src="sealPreviewUrl" style="margin-top: 10px" width="100%;" height="100%" class="imgstyle"/>
              </div>
              <div>
                <el-button  class="previewBtn" @click="handlePreviewPdf">预览</el-button>
              </div>
            </div>

          </template>


        </div>
        <div   class="contentCol">

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

        </div>

        <div  class="rightCol">
          <div class="right-item" >
            <div class="item">
              <div class="right-item-title">项目名称</div>
              <div class="detail-item-desc">天机40-2</div>
            </div>
            <div class="item">
              <div class="right-item-title">信托产品</div>
              <div class="detail-item-desc">45010101</div>
            </div>
            <div class="item">
              <div class="right-item-title">信托计划</div>
              <div class="detail-item-desc">450101</div>
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
  import {getListData as getSealListData,getSelectData as getSealSelectData} from '@/api/loan/seal'
  import { getSelectData as getCertificateSelectData } from '@/api/loan/certificate'
  import PdfCanvas from '@/components/pdf/PdfCanvas'
  import FabricCanvas from '@/components/pdf/FabricCanvas'

  import { _debounce } from '@/utils'
  const SIGN_CACHE_KEY="signs";
  export default {
    name: 'ProjectTemplateConfig',
    components:{
      YiSelect,draggable,PdfCanvas,FabricCanvas
    },
    data(){
      return{
        //pdf
        pdfOptions:this.getPdfOptions(),
        //fabric
        fabricPosition: null,

        sealId:null,
        sealPreviewUrl:null,
        sealList:[],


        keywordText:'',
        sealWay:2,
        sealWayConfig:{
          dictCode: 'seal_way',

          otherProps:{
            clearable: true
          }
        },

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

    computed:{
      keywordSealList(){
       return this.sealList.map(item=>{
          const innerItem={}
          innerItem.label=item.sealSubject;
          innerItem.value=item.sealId
          return innerItem
        })
      }
    },
    created() {
      getSealListData().then(res=>{
        this.sealList=res.data
      })

    },
    mounted() {
      this.pdfUrl = 'http://localhost:9401/profile/upload/20221215/d07c9994-3a48-4ec8-b01b-70dfa9e09fd2.pdf';
      this.$refs.yiPdfRef.show(this.pdfUrl)
      window.addEventListener("resize", this.resizeChange)
    },

    beforeDestroy() {
      window.removeEventListener("resize", this.resizeChange)
    },
    methods:{
      handlePreviewPdf(){
        console.log("处理预览pdf")
      },
      handleValChange(val){
        console.log("当前change",val)
       const findItem= this.sealList.find(item=>item.sealId===val)
        if (findItem){
          this.sealPreviewUrl=findItem.sealAddress
        }
      },
       resizeChange() {
        return _debounce(() => {
          try {
            //热加载此处会出现异常
            this.saveSignature()
            this.$refs.fabricCanvasRef.renderAll()
            this.showSign(this.$refs.yiPdfRef.getPageNum(), true)
          } catch (e) {
            console.log('重绘界面异常', e)
          }

        }, 300)
      },

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
        console.log(activeObject)
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
        return {top:'50px',left: innerPdfCenter.offsetLeft+'px'}

      },


      saveSignature(){

          let data = this.$refs.fabricCanvasRef.getObjects(); //获取当前页面内的所有签章信息
          let caches=  this.$storage.local.getCache(SIGN_CACHE_KEY,{})
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
        if(isFirst == false) this.$refs.fabricCanvasRef.clear(); //清空页面所有签章
        let caches = this.$storage.local.getCache(SIGN_CACHE_KEY,{})
        let pageSealData = caches[pageNum];
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
        this.$refs.fabricCanvasRef.clear();
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
          this.$refs.fabricCanvasRef.addImage(sealInfo,this.saveSignature)


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
    position: relative;
    width: 200px;
    margin-right: 20px;

    .keystore{
      span{
        display: inline-block;
        margin: 5px 0;
      }
    }
    .left-title {

      margin-top: 5px;
      padding-bottom: 5px;
      border-bottom: 1px solid #eee;
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

    .previewBtn{
      width: 100%;
      position: absolute;
      bottom: 15px;
    }
  }
  .contentCol{

    flex: 1;

    /*text-align: center;*/
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

      margin-top: 3%;
    }
  }
  .rightCol{

    width: 150px;


    .info{

      height: 150px;

      border: 1px solid red;
    }

    .right-item {
      margin-bottom: 15px;
      margin-left: 10px;
    }
    .right-item-title {
      margin: 5px 0px;

      color: #000000;
      height: 20px;
      line-height: 20px;
      font-size: 15px;
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
  }

</style>
