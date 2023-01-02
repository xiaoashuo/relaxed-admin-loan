<template>
    <div class="templateConfig">

       <div class="left">
         <div class="sealWay">
           <div>签章方式</div>
           <dict-select v-model="sealWay" v-bind="sealWayConfig"></dict-select>
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
             <yi-select  v-bind="sealImageConfig" v-model="sealId"  ref="sealListRef"></yi-select>

             <div class="item-preview">
               <img :src="sealPreviewUrl" style="margin-top: 10px" width="100%;" height="100%" class="imgstyle"/>
             </div>

           </div>
         </template>
       </div>

      <div class="content">
        Relaxed电子用印系统配置平台页

        <template v-if="sealWay===1">
          <template-seal-config ref="templateSealRef" :seal-list="sealList"></template-seal-config>
        </template>
        <template v-if="sealWay===2">
          <keyword-seal-config ref="keywordSealRef"></keyword-seal-config>
        </template>

      </div>
      <div class="right">

        <div class="right-item" >
          <div class="item">
            当前时间:{{new Date()|formatUtcString}}
          </div>
          <div class="item">
            <div class="right-item-title">项目名称</div>
            <div class="detail-item-desc">{{projectData.projectName}}</div>
          </div>
          <div class="item">
            <div class="right-item-title">文件名称</div>
            <div class="detail-item-desc">{{projectData.fileName}}</div>
          </div>
          <div class="tools">
            <template v-if="sealWay===1">
              <div class="ele-control" style="margin-bottom:2%;">
                <el-button class="btn-outline-dark" @click="removeSelectedSignature"> 删除签章</el-button>
                <el-button class="btn-outline-dark" @click="clearAllSignature"> 清除所有签章</el-button>
                <el-button class="btn-outline-dark" @click="submitSignature">提交所有签章信息</el-button>
              </div>
            </template>
            <template v-if="sealWay===2">
              <div>
                <el-button  class="previewBtn" @click="handlePreviewPdf">预览</el-button>
              </div>
            </template>
            <div>
              <el-button   @click="handleSave">保存签名信息</el-button>
            </div>
          </div>

        </div>
      </div>







    </div>
</template>

<script>
  import { YiSelect } from '@/components/select'

  import draggable from "vuedraggable";

  import TemplateSealConfig from '@/views/loan/project/TemplateSealConfig'
  import KeywordSealConfig from '@/views/loan/project/KeywordSealConfig'
  import {getDetail} from '@/api/loan/project-template'
  import { getSelectData as getCertificateSelectData } from '@/api/loan/certificate'
  import { getListData as getSealListData, getSelectData as getSealSelectData, previewPdf } from '@/api/loan/seal'

  export default {
    name: 'ProjectTemplateConfig',
    components:{
      YiSelect,TemplateSealConfig,KeywordSealConfig,draggable
    },
    created() {
      console.log(this.$route.query.projectTemplateId)
      getDetail(this.projectTemplateId).then(res=>{
        console.log("当前结果",res)
        this.projectData=res.data
      })
      getSealListData().then(res=>{
        this.sealList=res.data
      })
    },
    data(){
      return {
        projectTemplateId:this.$route.query.projectTemplateId,
        projectData:{},
        sealWay:1,
        sealWayConfig:{
          dictCode: 'seal_way',

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
        sealList:[],
        //关键字
        sealId: null,
        keywordText:'',
        sealPreviewUrl:null,
        sealImageConfig:{
          remoteLoad: true,
          request: getSealSelectData,

          otherProps:{

            clearable: true
          }
        },
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
      end(e){
        let sealListElement = this.sealList[e.newDraggableIndex]
        const sealInfo={url: sealListElement.sealAddress,
          uid:new Date().getTime(),
          left: e.originalEvent.layerX,
          top: e.originalEvent.layerY,
          index: e.newDraggableIndex }
        this.$refs.templateSealRef.saveImage(sealInfo)

      },
      handlePreviewPdf(){
        this.pdfUrl = 'http://localhost:9401/profile/upload/20221215/d07c9994-3a48-4ec8-b01b-70dfa9e09fd2.pdf';

        const payload={
          keystoreId: this.keystoreId,
          templateId: 4,
          sealId: this.sealId,
          keyword: this.keywordText,
          fileType: this.projectData.fileType
        }
        console.log(payload)
        previewPdf(payload).then(res=>{
          console.log(res)
          this.$refs.keywordSealRef.showPreviewPdf(res.data)
        })

        console.log("处理预览pdf")
      },
      //模板
      removeSelectedSignature(){

      },
      clearAllSignature(){

      },
      submitSignature(){

      },
      handleSave(){
        console.log("存储签名信息")
      }
    }
  }
</script>

<style lang="scss" scoped>
.templateConfig{
  position: relative;
  padding-top: 10px;

  display: flex;
  .left{
    padding-left: 10px;



    position: relative;
    width: 200px;
    margin-right: 20px;

    .sealWay{
      width: 200px;
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

  .content{
    flex: 1;
    color: red;
    font-weight: 500;
    font-size: 30px;
    text-align: center;
    align-content: center;

  }

  .right{

    width: 200px;


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
}

</style>
