let pdfjsLib =require("pdfjs-dist/legacy/build/pdf.js");
import workerSrc from "pdfjs-dist/legacy/build/pdf.worker.entry";
pdfjsLib.GlobalWorkerOptions.workerSrc = workerSrc;
class YiPdf{
  //当前页码
  pageNum=1;
  //总页数
  totalPage=1;
  elementId=null;
  //当前画布
  canvas=null;
  //画布上下文
  ctx=null;
  //pdf文档
  pdfDoc=null;
  //页面是否在渲染
  pageRendering=false;
  //队列带渲染页面
  pageNumPending=null;
  //当前可选配置
  option={
    scale:1,
    beforeRenderPage:()=>{
      //默认什么都不做
    },
    //渲染页面后的回调函数
   afterRenderPage:(width,height)=>{
     //默认什么都不做
   },
   beforePageChange:(currentPage)=>{

   },
    afterPageChange:(currentPage,toPage)=>{

    }


  }
  constructor(eleId,option) {
    this.elementId = eleId;
    this.option=Object.assign(this.option,option)
  }
  showPdfByBlob(blob){
    //1.渲染pdf画布
    this.canvas = document.getElementById(this.elementId);
    this.ctx = this.canvas.getContext("2d");
    //2.加载pdf文档
    pdfjsLib.getDocument({
      data:blob,
      rangeChunkSize:65536, disableAutoFetch:false}
    ).promise.then((pdfDoc_) => {
      this.pdfDoc = pdfDoc_;
      this.totalPage = this.pdfDoc.numPages;
      this.renderPage(this.pageNum).then(() => {
        //将渲染出来的pdf 宽高 发射给fabric 由fabric渲染画布
        this.option.afterRenderPage(this.pageNum,this.canvas.width,this.canvas.height)
      });
      //3.回显签章信息
      // this.showSign(this.pageNum, true);
    });
  }
   showPdf(url){
     //1.渲染pdf画布
     this.canvas = document.getElementById(this.elementId);
     this.ctx = this.canvas.getContext("2d");
     //2.加载pdf文档
     pdfjsLib.getDocument({
         url:url,
         rangeChunkSize:65536, disableAutoFetch:false}
       ).promise.then((pdfDoc_) => {
       this.pdfDoc = pdfDoc_;
       this.totalPage = this.pdfDoc.numPages;
       this.renderPage(this.pageNum).then(() => {
         //将渲染出来的pdf 宽高 发射给fabric 由fabric渲染画布
         this.option.afterRenderPage(this.pageNum,this.canvas.width,this.canvas.height)
       });
       //3.回显签章信息
      // this.showSign(this.pageNum, true);
     });
   }

   prevPage(){
    this.option.beforePageChange(this.pageNum)
     if (this.pageNum <= 1) {
       return;
     }
     let currentPage=this.pageNum
     const toPage=--this.pageNum;


     // 页面改变前回调 可以做一些 存储操作
     this.option.afterPageChange(currentPage,toPage)
     this.addPageToRenderQueue(this.pageNum)
   }
  nextPage() {
    this.option.beforePageChange(this.pageNum)
    if (this.pageNum >= this.totalPage) {
      return;
    }
    const currentPage=this.pageNum
    const toPage=++this.pageNum;

    this.option.afterPageChange(currentPage,toPage)
    this.addPageToRenderQueue(this.pageNum)
  }
  justPage(num){
    console.log("当前跳转页",num)
    this.option.beforePageChange(this.pageNum)
    if (num <= 1||num >= this.totalPage) {
      return;
    }
    const oldPage=this.pageNum
    const toPage=num
    this.pageNum=num
    this.option.afterPageChange(oldPage,toPage)
    console.log("当前跳转页",this.pageNum)
    this.addPageToRenderQueue(this.pageNum)
  }
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
   addPageToRenderQueue(pageNum){
     if (this.pageRendering) {
       this.pageNumPending = pageNum;
     } else {
       this.renderPage(pageNum);
     }
   }
  /**
   * 获取当前页
   * @returns {number}
   */
   getPageNum(){
    return this.pageNum
   }
  getTotalPage(){
    return this.totalPage
  }
  renderPage(num) {
    this.option.beforeRenderPage()
    this.pageRendering = true;
    return this.pdfDoc.getPage(num).then((page) => {
      let viewport = page.getViewport({ scale: this.option.scale });//设置视口大小
      // 宽高单位为磅pt，渲染时直接写像素px会更清晰一点
      // 例如A4纸：595 x 841pt，但渲染时写的是595 x 841px，
      // 要是做拖拽签章功能时，注意图片单位要和PDF单位保持一致
      this.canvas.height = viewport.height;
      this.canvas.width = viewport.width;
      // 要是做拖拽签章功能时，注意图片单位要和PDF单位保持一致

      // Render PDF page into canvas context
      let renderContext = {
        canvasContext: this.ctx,
        viewport: viewport,
      };
      let renderTask = page.render(renderContext);

      // Wait for rendering to finish
      renderTask.promise.then(() => {
        this.pageRendering = false;
        if (this.pageNumPending !== null) {
          // New page rendering is pending
          this.renderPage(this.pageNumPending);
          this.pageNumPending = null;
        }
      });
    });
  }


}

export default YiPdf
