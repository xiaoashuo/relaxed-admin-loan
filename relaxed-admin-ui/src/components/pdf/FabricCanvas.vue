<template>
    <div>
      <!-- 盖章部分 -->
      <canvas id="fabricCanvas"></canvas>
    </div>
</template>

<script>
  import YiFabric from '@/components/pdf/YiFabric'
  export default {
    name: 'FabricCanvas',
    mounted() {
      this.fabricContext=new YiFabric('#fabricCanvas',this.getFabricConfig())
    },
    methods:{
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
    }
  }
</script>

<style scoped>

</style>
