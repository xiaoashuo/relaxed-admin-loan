<template>
  <el-dialog
    :title="title"
    :visible.sync="visible"
    fullscreen

    :before-close="dialogClose"
  >
    <div class="templateConfig">

      <div class="left">
        <div class="sealWay">
          <div>签章方式</div>
          <dict-select style="width: 190px" v-model="sealWay" dict-code="seal_way" ></dict-select>
        </div>
        <div class="keystore">
          <span>证书列表</span>
          <yi-select  v-model="keystoreId" v-bind="keyStoreConfig"></yi-select>
        </div>

        <template v-if="sealWay===1">

          <div class="left-title">我的印章</div>
          <div class="seal">

            <draggable v-model="sealImageList" :group="{ name: 'itext', pull: 'clone' }" :sort="false" @end="end">
              <transition-group type="transition">
                <li v-for="item in sealImageList" :key="item.sealId" class="item" style="text-align:center;">
                  <img :src="item.previewSealAddress" width="100%;" height="100%" class="imgstyle" />
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
            <yi-select  :nativeProps="{  clearable: true}" :options="sealSelectData" v-model="sealId"  ref="sealListRef"></yi-select>

            <div class="item-preview">
              <img :src="sealPreviewUrl" style="margin-top: 10px" width="100%;" height="100%" class="imgstyle"/>
            </div>

          </div>
        </template>
      </div>

      <div class="content">
        <template v-if="sealWay===1">
          <template-seal-config ref="templateSealRef" :seal-location.sync="sealLocation"  :seal-list="sealImageList"></template-seal-config>
        </template>
        <template v-if="sealWay===2">
          <keyword-seal-config ref="keywordSealRef"></keyword-seal-config>
        </template>

      </div>
      <div class="right">

        <div class="right-top" >

          <div class="item">
            <div class="right-item-title">项目名称</div>
            <div class="detail-item-desc">{{projectData.projectName}}</div>
          </div>
          <div class="item">
            <div class="right-item-title">文件名称</div>
            <div class="detail-item-desc">{{projectData.fileName}}</div>
          </div>
        </div>
        <div class="right-tools">
          <template v-if="sealWay===1">

            <el-button class="right-tools-item-btn" @click="removeSelectedSignature">删除签章</el-button>
            <el-button class="right-tools-item-btn" @click="clearAllSignature"> 清除所有签章</el-button>
            <el-button class="right-tools-item-btn" @click="submitSignature">同步签章信息</el-button>

          </template>
          <template v-if="sealWay===2">
            <div>
              <el-button  class="right-tools-item-btn"  @click="handlePreviewPdf">预览</el-button>
            </div>
          </template>
          <div>
            <el-button  class="right-tools-item-btn"  @click="handleSave">保存签名信息</el-button>
          </div>
        </div>
        <div class="right-tips">
          <template v-if="sealWay===1">
            <h4>模板方式注意事项</h4>
            <p>当前页签章选则完毕后,<br/>
              建议同步签章信息<br/>
              ,在进行下一页操作。</p>
          </template>

          <template v-if="sealWay===2">
            <h4>关键字方式注意事项</h4>
            <p>
              填写关键信息后,<br/>
              先进行预览,确认无误。<br/>
              在进行保存操作。<br/>
              <br/>

              坐标以左下角为起点X,Y(0,0)<br/>
              默认offsetX偏移量为0,<br/>
              offsetY偏移量为20.
            </p>
          </template>
        </div>
      </div>







    </div>
  </el-dialog>
</template>

<script>
  import formModalMixin from "@/mixins/form/formModalMixin";
  import { getDetail } from '@/api/loan/project-template'
  import { getListData as getSealListData } from '@/api/loan/seal'
  import { YiSelect } from '@/components/select'

  import draggable from "vuedraggable";

  import TemplateSealConfig from '@/views/loan/project/TemplateSealConfig'
  import KeywordSealConfig from '@/views/loan/project/KeywordSealConfig'
  import {modifySealConfig} from '@/api/loan/project-template'
  import { getSelectData as getCertificateSelectData } from '@/api/loan/certificate'
  import {  previewPdf,previewTemplatePdf } from '@/api/loan/seal'
  export default {
    name: 'SealConfigModal',
    components:{
     YiSelect,TemplateSealConfig,KeywordSealConfig,draggable
    },
    mixins:[formModalMixin],
    data(){
      return{
        item: {},
        projectData:{},
        sealImageList:[],
        sealWay:null,
        keystoreId:null,
        keyStoreConfig:{
          remoteLoad: true,
          request: getCertificateSelectData,
          nativeProps:{
            clearable: true
          }
        },
        //关键字
        sealId: null,
        keywordText:'',
        sealPreviewUrl:null,
        sealLocation:{}
      }
    },
    created() {
      getSealListData().then(res=>{
        this.sealImageList=res.data
      })
    },
    computed:{
      sealSelectData(){
        let map = this.sealImageList.map(item=>({label:item.sealSubject,value:item.sealId}))
        return  map
      }
    },
    watch:{

      sealId:{
        handler(val){
          const findItem=  this.sealImageList.find(item=>item.sealId===val)
          if (findItem){
            this.sealPreviewUrl=findItem.previewSealAddress
          }
        },
      },
      sealWay:{
        handler(val){
          if (this.sealWay===1) {
            previewTemplatePdf(this.projectData.templateId).then(res=>{
              this.$refs.templateSealRef.showPdf(res.data)
            })
          }
        },
      }
    },
    methods:{

      dialogClose(done){
        this.close()
        done()
      },
      /**
       * 构建创建表单成功后的回调
       * @param payload
       */
      createdFormCallback(payload){
        this.item=payload.item
        getDetail(this.item.projectTemplateId).then(res=>{
          console.log("当前结果",res)
          this.projectData=res.data
          this.sealWay=this.projectData.sealWay??1
          this.sealId=this.projectData.sealId
          this.keystoreId=this.projectData.keystoreId
          this.keywordText=this.projectData.sealKeyword
          this.sealLocation=this.projectData.sealLocation?JSON.parse(this.projectData.sealLocation):{}
        })

      },
      end(e){
        let sealListElement = this.sealImageList[e.newDraggableIndex]
        const sealInfo={url: sealListElement.previewSealAddress,
          uid:new Date().getTime(),
          left: e.originalEvent.layerX,
          top: e.originalEvent.layerY,
          index: e.newDraggableIndex }
        this.$refs.templateSealRef.saveImage(sealInfo)

      },
      handlePreviewPdf(){
        if (!this.paramValid()){
          return
        }
        const payload={
          keystoreId: this.keystoreId,
          templateId: this.projectData.templateId,
          sealId: this.sealId,
          keyword: this.keywordText,
          fileType: this.projectData.fileType
        }
        previewPdf(payload).then(res=>{
          this.$refs.keywordSealRef.showPreviewPdf(res.data)
        })

      },
      //模板
      removeSelectedSignature(){
        this.$refs.templateSealRef.removeSelectedSignature()

      },
      clearAllSignature(){
        this.$refs.templateSealRef.clearAllSignature()
      },
      submitSignature(){
        this.$refs.templateSealRef.submitSignature()
      },
      paramValid(){
        if (!this.sealWay){
          this.$message.error("签章方式不能为空");
          return false
        }
        if (!this.keystoreId){
          this.$message.error("证书不能为空");
          return false
        }

        if (this.sealWay===1){
          //模板
        }else if (this.sealWay===2){
          if (!this.sealId){
            this.$message.error("签章不能为空");
            return false
          }
          if (!this.keywordText){
            this.$message.error("关键字不能为空");
            return false
          }
        }
        return true
      },
      handleSave(){
        console.log("存储签名信息")
        if (!this.paramValid()){
          return
        }
        let reqInfo={projectTemplateId:this.projectData.projectTemplateId,
          sealWay:this.sealWay,keystoreId:this.keystoreId}
        if (this.sealWay===1){
          //同步一次签署信息
          this.$refs.templateSealRef.submitSignature()
          if (!this.sealLocation||Object.keys(this.sealLocation).length===0){
            this.$message.error("签约坐标信息不存在")
            return
          }
          //模板
          reqInfo.sealLocation=JSON.stringify(this.sealLocation)
        }
        else if (this.sealWay===2){
          //关键字
          reqInfo.sealKeyword=this.keywordText
          reqInfo.sealId=this.sealId
        }
        modifySealConfig(reqInfo).then(res=>{
          this.$message.success("保存签名成功")
        }).catch(err=>{
          this.$message.success("保存签名失败"+err)
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  ::v-deep .el-dialog__header{
   border-bottom: 1px solid #e8e8e8;
 }
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

      .right-top {
        margin-bottom: 15px;
        margin-left: 10px;
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
      .right-tools{
        margin-top: 20px;
        .right-tools-item-btn{
          width: 120px;
          margin: 3px 0;
        }
      }

    }
  }

</style>
