<template>
  <div class="home_wrap">
    <div class="pdf_down">
      <div class="pdf_set_left" @click="scaleD">放大</div>
      <div class="pdf_set_middle" @click="scaleX">缩小</div>
    </div>

    <div class="pdf-parent-container" ref="scroll">
      <div
        id="pdf-container"
        :style="{ width: pdf_div_width, margin: '0 auto' }"
      >
        <canvas
          v-for="page in pdf_pages"
          :id="'the_canvas' + page"
          :key="page"
        ></canvas>
      </div>
    </div>
  </div>
</template>

<script>
import BScroll from '@better-scroll/core'
import Movable from '@better-scroll/movable'
import Zoom from '@better-scroll/zoom'

import * as PDFJS from 'pdfjs-dist/legacy/build/pdf'
//PDFJS.GlobalWorkerOptions.workerSrc = `https://cdnjs.cloudflare.com/ajax/libs/pdf.js/${PDFJS.version}/pdf.worker.js`
window.pdfjsWorker = require('pdfjs-dist/legacy/build/pdf.worker.js')
BScroll.use(Movable)
BScroll.use(Zoom)
export default {
  name: 'pdf',
  props: {
    defaultSacleDelta: {
      type: Number,
      default: 1.0
    },
    maxScale: {
      type: Number,
      default: 2
    },
    minScale: {
      type: Number,
      default: 0.5
    },
    defaultScale: {
      type: Number,
      default: 1
    },
    pdfSrc: {
      type: String,
      default: 'http://192.168.1.21:9401/user'
    }
  },
  data() {
    return {
      currentScale: 0.5, //pdf放大系数
      pdf_pages: [],
      pdf_div_width: '',
      startX: 0,
      startY: 0,
      moveX: 0,
      moveY: 0,
      eLen: 0,
      touchDistance: null,
      startTime: null,
      previousPinchScale: 1,
      renderMode: false
    }
  },
  created() {
    this.currentScale = this.defaultScale
  },
  mounted() {
    this.get_pdfurl()
  },
  methods: {
    scaleD() {
      //放大
      if (this.currentScale >= this.maxScale) {
        return
      }
      this.currentScale = this.currentScale + 0.1
      this._loadFile(this.pdfSrc)
    },
    scaleX() {
      //缩小
      if (this.currentScale <= this.minScale) {
        return
      }
      this.currentScale = this.currentScale - 0.1
      this._loadFile(this.pdfSrc)
    },
    get_pdfurl() {
      //获得pdf教案
      //加载本地
      this._loadFile(this.pdfSrc)
      //线上请求
      //  this.$axios.get('')
      //  .then((res)=>{
      //  	this.pdfSrc = res.url
      //  	this._loadFile(this.pdfSrc)
      //  })
    },
    _loadFile(url) {
      console.log('_loadFile', this.currentScale)
      this.renderMode = false
      let loadingTask = PDFJS.getDocument(url)
      loadingTask.promise.then((pdf) => {
        this.pdfDoc = pdf
        this.pdf_pages = this.pdfDoc.numPages
        this.$nextTick(() => {
          this._renderPage(1)
        })
      })
    },
    _renderPage(num) {
      const that = this
      this.pdfDoc.getPage(num).then((page) => {
        let canvas = document.getElementById('the_canvas' + num)
        let ctx = canvas.getContext('2d')
        let dpr = window.devicePixelRatio || 1
        let bsr =
          ctx.webkitBackingStorePixelRatio ||
          ctx.mozBackingStorePixelRatio ||
          ctx.msBackingStorePixelRatio ||
          ctx.oBackingStorePixelRatio ||
          ctx.backingStorePixelRatio ||
          1
        let ratio = dpr / bsr
        let viewport = page.getViewport({ scale: this.currentScale })
        canvas.width = viewport.width * ratio
        canvas.height = viewport.height * ratio
        canvas.style.width = viewport.width + 'px'
        that.pdf_div_width = viewport.width + 'px'
        canvas.style.height = viewport.height + 'px'
        ctx.setTransform(ratio, 0, 0, ratio, 0, 0)
        let renderContext = {
          canvasContext: ctx,
          viewport: viewport
        }
        page.render(renderContext)
        if (this.pdf_pages > num) {
          this._renderPage(num + 1)
        } else {
          this.renderMode = true
          this.bs = new BScroll(this.$refs.scroll, {
            bindToTarget: true,
            scrollX: true,
            scrollY: true,
            freeScroll: true,
            movable: true,
            zoom: {
              start: 1,
              min: 0.5,
              max: 5
            }
          })
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.home_wrap {
  width: 100%;
  height: 100%;
}
.pdf_down {
  position: fixed;
  display: flex;
  z-index: 20;
  right: 26px;
  bottom: 7%;
}
.pdf_set_left {
  width: 30px;
  height: 40px;
  color: #408fff;
  font-size: 11px;
  padding-top: 25px;
  text-align: center;
  margin-right: 5px;
  cursor: pointer;
}
.pdf_set_middle {
  width: 30px;
  height: 40px;
  color: #408fff;
  font-size: 11px;
  padding-top: 25px;
  text-align: center;
  margin-right: 5px;
  cursor: pointer;
}
.pdf-parent-container {
  width: 100%;
  height: 100vh;
  border: 2px solid red;
  overflow: scroll;
}
</style>
