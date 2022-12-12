import screenfull from "screenfull";

export default {

  data(){
    return{
      localFullScreen: false,
    }
  },

  mounted(){
    window.addEventListener('resize', this.cancelTableFullScreen)

  },
  beforeDestroy(){
    window.removeEventListener('resize', this.cancelTableFullScreen)
  },
  methods:{
    /**
     * 监听窗口变化  取消全屏 主要针对esc情况
     */
    cancelTableFullScreen(){
      if (!screenfull.isFullscreen){
        this.localFullScreen=false
      }
    },
    /**
     * 提供全屏Dom指向
     * @returns {null}
     */
    getScreenfullDomRef(){
      return null
    },

    handleFullScreen(){
      if (!screenfull.enabled) {
        this.$message({
          message: 'you browser can not work',
          type: 'warning'
        })
        return false
      }
      let fullDomRef = this.getScreenfullDomRef();
      if (this.localFullScreen){
        if (fullDomRef){
          screenfull.exit()
        }else{
          screenfull.exit()
        }
        this.localFullScreen=false
      }else{
        if (fullDomRef){
          screenfull.request(fullDomRef)
        }else{
          screenfull.request()
        }
        this.localFullScreen=true
      }
    },



  }
}
