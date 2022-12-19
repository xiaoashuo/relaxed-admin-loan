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
    props:{
      rectangle:{
        type:Object,
      },

      //原文的位置偏移量
      sourceStyle: Function
    },
    mounted() {
      this.fabricContext=new YiFabric('#fabricCanvas',this.getFabricConfig())

    },
    data(){
      return {
        fabricContext: null,
        position:{}
      }
    },
    watch:{
      rectangle: {
        handler() {
          const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });

          if (this.rectangle) {
            loading.close();
            let fabricCanvasElement=document.querySelector("#fabricCanvas");
            fabricCanvasElement.style="border:1px solid #5ea6ef"
            this.fabricContext.render(this.rectangle)

          }
        },
      },
    },
    methods:{
      renderAll(){
        const remainObjects=this.getObjects()
        this.fabricContext.dispose()
        this.fabricContext=new YiFabric('#fabricCanvas',this.getFabricConfig())
        let fabricCanvasElement=document.querySelector("#fabricCanvas");
        fabricCanvasElement.style="border:1px solid #5ea6ef"
        this.fabricContext.render(this.rectangle)

      },
      // 生成绘图区域
      getFabricConfig(){
        return {
          //实列化canvas 后调用回调
          afterInstance:()=>{
            const positionStyle=this.sourceStyle()
            //canvas 容器
            let container = document.querySelector(".canvas-container");
            container.style.position = "absolute";
            container.style.top = positionStyle.top;
            container.style.left = positionStyle.left;
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
          },

        }
      },

      /**
       * 监听鼠标事件
       * @param opt
       */
      fabricOnMouseDown(opt){
        opt.e.preventDefault()

        // 右键，且在元素上右键 button: 1-左键；2-中键；3-右键
        // 在画布上右键，target 为 null
        if (opt.button === 3 && opt.target) {

          // 获取当前元素
          this.fabricContext.setActiveObject(opt.target)
          this.$nextTick(()=>{
            // 设置右键菜单位置
            // 当前鼠标位置
            //相对于文档的距离event.pageY 相对于浏览器窗口的距离（不包含滚动的距离） event.clientX
            const position={top: opt.e.pageY, left: opt.e.pageX}
            this.$emit('rightMouseClick',opt.e,position)
          })

        } else {

          this.$emit('cancelMouseClick',opt.e)

        }
      },
      getObjects(){
        return  this.fabricContext.getObjects()
      },
      getActiveObject(){
       return this.fabricContext.getActiveObject()
      },
      setActiveObject(object){

      },
      addImage(imageInfo,afterSuccessAdd){
        this.fabricContext.addImage(imageInfo,afterSuccessAdd)
      },
      clear(){
        this.fabricContext.clear();
      },
      remove(object){
        this.fabricContext.remove(object)
      }
    }
  }
</script>

<style scoped>

</style>
