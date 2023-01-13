<template>
    <div class="keywordSealConfig">



        <div   class="contentCol">
          <pdf-canvas ref="yiPdfRef" :options="pdfOptions"></pdf-canvas>
        </div>







    </div>
</template>

<script>
  import { YiSelect } from '@/components/select'
  import PdfCanvas from '@/components/pdf/PdfCanvas'
  import {getSelectData as getSealSelectData,previewPdf} from '@/api/loan/seal'
  export default {
    name: 'KeywordSealConfig',
    components:{
      YiSelect,PdfCanvas
    },
    data(){
      return {
        sealId: null,
        sealPreviewUrl:null,
        sealImageConfig:{
          remoteLoad: true,
          request: getSealSelectData,

          nativeProps:{

            clearable: true
          }
        },
        keywordText:'',




        //pdf
        pdfOptions:this.getPdfOptions(),
      }
    },
    watch:{
      sealId(val){

        const allItem=this.$refs.sealListRef.getSelectData()
        console.log(val,allItem)
       const findItem=  allItem.find(item=>item.value===val)
        if (findItem){
          this.sealPreviewUrl=findItem.extendObj.url
        }
      }
    },
    methods:{
      getPdfOptions() {
        return {}
      },
      showPreviewPdf(blob){
        this.$refs.yiPdfRef.showBlob(blob)
      },

    }

  }
</script>

<style lang="scss" scoped>

  .keywordSealConfig{
    display: flex;
    margin: auto;
    height: 100%;
    position: relative;
    padding-left: 10px;
    padding-top: 10px;
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

</style>
