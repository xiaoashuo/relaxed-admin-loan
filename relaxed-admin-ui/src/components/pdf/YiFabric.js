import {fabric} from 'fabric';
//https://blog.csdn.net/guo_qiangqiang/article/details/127259900
//框选对象间的内边距
fabric.Object.prototype.padding = 10;

//旋转控制点和主体之间一般没有那条控制线,这里我们对它进行隐藏。
fabric.Object.prototype.controls.mtr.withConnection = false;
//修改控制线颜色
fabric.Object.prototype.borderColor = 'dodgerblue';

// 修改控制点的形状，默认为`rect`矩形，可选的值还有`circle`圆形
fabric.Object.prototype.cornerStyle = "circle";
// 修改控制点的填充色为白色
fabric.Object.prototype.cornerColor = "white";
// 修改控制点的大小为10px
fabric.Object.prototype.cornerSize = 10;
// 设置控制点不透明，即可以盖住其下的控制线
fabric.Object.prototype.transparentCorners = false;
// 修改控制点的边框颜色为`gray`灰色
fabric.Object.prototype.cornerStrokeColor = "gray";

// 单独修改旋转控制点距离主体的纵向距离为-20px
fabric.Object.prototype.controls.mtr.offsetY = -20;
// 单独修改旋转控制点，光标移动到该点上时的样式为`pointer`，一个手的形状
fabric.Object.prototype.controls.mtr.cursorStyle = "pointer";
class YiFabric{

  //当前画布元素
  fabricEle=null
  //当前fabricCanvas
  fabricCanvas=null

  config={
    //实列化canvas 后调用回调
    afterInstance:()=>{

    },
    //添加画布事件
    addCanvasEvent:(fabricCanvas)=>{

    }
  }

  constructor(eleId,config) {
    this.fabricEle = document.querySelector(eleId);
    this.config=Object.assign(this.config,config)
  }

  dispose(){
    this.fabricCanvas.dispose()
  }

  /**
   * 渲染画布
   * @param option
   */
  render(option){
    this.fabricEle.width = option.width;
    this.fabricEle.height = option.height;
    this.fabricCanvas = new fabric.Canvas('fabricCanvas',{
      fireRightClick: true, // 左键button1 滚轮2 启用右键，button的数字为3
      stopContextMenu: true, // 禁止默认右键菜单
    });

    this.config.afterInstance()
    this.config.addCanvasEvent(this.fabricCanvas)
  }


  addImage(imageInfo,afterImgToCanvas){

    //引入图片
    fabric.Image.fromURL(
      imageInfo.url,
      (oImg) => {
        //等比例缩放到150
        const scaleX=imageInfo.scaleX??150/ oImg.width
        const scaleY=imageInfo.scaleY??150/oImg.height
        oImg.set({
          left: imageInfo.left,
          top: imageInfo.top,
          // angle: 10,
          scaleX: scaleX,
          scaleY: scaleY,
          index:imageInfo.index,
          uid:imageInfo.uid
        });
        this.fabricCanvas.add(oImg);
        if (afterImgToCanvas){
          afterImgToCanvas()
        }
      }
    );
  }

  /**
   * 获取当前画布上选中对象
   * @returns {*}
   */
  getActiveObject(){
    return this.fabricCanvas.getActiveObject()
  }
  setActiveObject(object){
    this.fabricCanvas.setActiveObject(object)
  }

  /**
   * 获取当前画布上所有对象
   * @returns {*}
   */
  getObjects(){
    return this.fabricCanvas.getObjects()
  }

  /**
   * 清空画布
   */
  clear(){
    this.remove(this.fabricCanvas.clear())
  }

  /**
   * 删除当前画布对象
   * @param activeObject
   */
  remove(activeObject){
    this.fabricCanvas.remove(activeObject)
  }
}
export default YiFabric
