<template>
    <div class="keywordSealConfig">


        <div  class="leftCol">




          <div class="fileType">
            <div>文件类型</div>
            <dict-select v-model="fileType" v-bind="fileTypeConfig"></dict-select>
          </div>
          <div class="keystore">
            <span>证书列表</span>
            <yi-select  v-model="keystoreId" v-bind="keyStoreConfig"></yi-select>
          </div>


            <div class="keyword">
              <div>关键字</div>
              <el-input v-model="keywordText" ></el-input>
            </div>
            <div class="sealSelect">
              <div>印章列表</div>
              <yi-select  v-bind="sealImageConfig" v-model="sealId"  ref="sealListRef"></yi-select>

              <div class="item-preview">
                <img :src="sealPreviewUrl" style="margin-top: 10px" width="100%;" height="100%" class="imgstyle"/>
              </div>
              <div>
                <el-button  class="previewBtn" @click="handlePreviewPdf">预览</el-button>
              </div>
            </div>




        </div>
        <div   class="contentCol">
          <pdf-canvas ref="yiPdfRef" :options="pdfOptions"></pdf-canvas>
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






    </div>
</template>

<script>
  import { YiSelect } from '@/components/select'
  import PdfCanvas from '@/components/pdf/PdfCanvas'
  import { getSelectData as getCertificateSelectData } from '@/api/loan/certificate'
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

          otherProps:{

            clearable: true
          }
        },
        keywordText:'',


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
        },
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
      handlePreviewPdf(){
        this.pdfUrl = 'http://localhost:9401/profile/upload/20221215/d07c9994-3a48-4ec8-b01b-70dfa9e09fd2.pdf';

        const payload={
          keystoreId: 13,
          templateId: 4,
          sealId: 7,
          keyword: this.keywordText,
          fileType: 9
        }
        console.log(payload)
        previewPdf(payload).then(res=>{
          console.log(res)
          this.$refs.yiPdfRef.showBlob(res.data)
        })

        console.log("处理预览pdf")
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
  .leftCol{
    position: relative;
    width: 200px;
    height: 100%;
    line-height: 30px;
    margin-right: 20px;
    .previewBtn{
      width: 200px;
      position: fixed;
      bottom: 15px;
    }
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
    .sealSelect{
      margin-top: 10px;
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
      .item-preview{
        margin-top: 10px;
        height: 150px;
      }
      .imgstyle{
        display: inline-block;
        vertical-align: middle;
        width: 130px;
        border: solid 1px #e8eef2;

        background-repeat:no-repeat;
      }
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
