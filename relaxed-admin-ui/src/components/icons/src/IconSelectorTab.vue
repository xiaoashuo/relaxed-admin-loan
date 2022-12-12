<template>
  <div class="icons-container">
    <el-tabs v-model="currentTab" stretch @tab-click="handleClick">
      <el-tab-pane  v-for="element in elementIcons"  :name="element.key" :label="element.label" >
         <ul>
           <li v-for="(item,k) in element.value"
             :key="`${element.key}-${k}`"
              :class="{'active':selectedIcon==item}"
               @click="handleSelectedIcon('icon',item,$event)"
           >
             <el-tooltip placement="top" >
               <div slot="content">
                 {{ generateElementIconCode(item) }}
               </div>
               <div class="icon-item">
                 <i :class="'el-icon-' + item" />
                 <span>{{ item }}</span>
               </div>
             </el-tooltip>
           </li>
         </ul>
      </el-tab-pane>
      <el-tab-pane label="项目图标"  key="project" name="project" >
        <ul>
          <li v-for="(item,k) of svgIcons.value"
              :key="`project-${k}`"
              :class="{'active':selectedIcon==item}"
              @click="handleSelectedIcon('svg',item,$event)"
          >
            <el-tooltip placement="top">
              <div slot="content">
                {{ generateIconCode(item) }}
              </div>
              <div class="icon-item">
                <svg-icon :icon-class="item" class-name="disabled" />
                <span>{{ item }}</span>
              </div>
            </el-tooltip>
          </li>
        </ul>

      </el-tab-pane>

    </el-tabs>

  </div>
</template>

<script>
import clipboard from '@/utils/clipboard'
import svgIcons from './svg-icons'
import elementIcons from  './element-icons';



export default {
  name: 'IconSelectorTab',
  props:{
    openClipboard:{
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      svgIcons,
      elementIcons,
      currentTab:'directional',
      selectedIcon:''

    }
  },
  methods: {
    handleClick(tab,event){

        this.currentTab=tab.name
    },
    handleSelectedIcon(flag,item,$event){
      //原始图标单位
      this.selectedIcon = item
      if (flag==='icon'){
        item=`el-icon-${item}`
      }else if (flag==='svg'){
        item=`${item}`
      }
      //修改后的图标单位
      this.$emit('change', item)
    },
    generateIconCodeClass(symbol) {
      return `${symbol}`
    },
    generateIconCode(symbol) {
      return `<svg-icon icon-class="${symbol}" />`
    },
    generateElementIconCodeClass(symbol) {
      return `el-icon-${symbol}`
    },
    generateElementIconCode(symbol) {
      return `<i class="el-icon-${symbol}" />`
    },
    handleClipboard(text, event) {
      console.log("文本，事件",text,event)
      if (this.openClipboard){
        clipboard(text, event)
      }
      this.$emit("icon-click",text)

    }
  }
}
</script>

<style lang="scss" scoped>


  @import "src/styles/variables";

.icons-container {
  /*margin: 10px 20px 0;*/
  /*overflow: hidden;*/

  ul {
    list-style: none;
    padding: 0;
    overflow-y: scroll;
    height: 250px;

    li {
      width: 100px;
      height: 100px;

      display: inline-block;
      padding: 3px;
      margin: 3px 3px;
      border-radius: 5px;


      &:hover, &.active {
        // box-shadow: 0px 0px 5px 2px @primary-color;
        cursor: pointer;
        color: white;
        background-color: $menuActiveText;
      }
    }
  }

  .icon-item {

    height: 60px;
    text-align: center;
    width: 60px;
    font-size: 25px;
    color: #24292e;
    cursor: pointer;
  }

  span {
    display: block;
    font-size: 15px;
    margin-top: 10px;
  }

  .disabled {
    pointer-events: none;
  }
}
</style>
