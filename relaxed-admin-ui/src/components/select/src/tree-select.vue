<template>
  <div id="tree-select">
    <treeselect   v-model="treeValue"  :clearable="clearable" :normalizer="normalizer"
                  :multiple="multiple"
                  :placeholder="placeholder"
                  :noChildrenText="noChildrenText"
                  :options="treeData" >
    </treeselect>
  </div>
</template>

<script>
  // import the component
  import Treeselect from '@riophae/vue-treeselect'
  import {listToTree} from "@/utils/treeUtil";
  // import the styles
  import '@riophae/vue-treeselect/dist/vue-treeselect.css'
  import {modalConfig} from "@/views/system/menu/config/modal.config";
    export default {
        name: "tree-select",
        components:{
          Treeselect
        },
      props:{
        value:{
          required: true
        },
        clearable:{
          type: Boolean,
          default: false
        },
        multiple:{
          type: Boolean,
          default: false
        },
        normalizer:{
          type: Function,
          default: (node)=>({})
        },
        placeholder:{
          type:String,
          default:'请选择'
        },
        noChildrenText:{
          type:String,
          default: ""
        },
        remoteLoad:{
          type: Boolean,
          default: false
        },
        options:{
          type: Array,
          default: ()=>([])
        },
        request:{
          type: Function,
          default:()=>null
        }
      },

      data() {
        return {
         treeValue:this.value,
         treeData:[]
        }
      },
      watch:{
        value(newValue){
          this.treeValue=newValue
        },
        treeValue(newValue){
           this.$emit('input',newValue)
        }
      },
      created(){
          this.loadData()
      },
      methods:{
          getTreeData(){
            this.request().then(res=>{
              this.treeData=res.data
            })
          },
         loadData(){
           if (this.remoteLoad){
             this.getTreeData()
           }else{
             this.treeData=this.options
           }
         },
         refreshData(){
            this.loadData()
         }

      }

    }
</script>

<style scoped>

</style>
