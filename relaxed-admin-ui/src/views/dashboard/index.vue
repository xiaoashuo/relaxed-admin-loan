<template>
  <div class="dashboard-container">
    <git-corner></git-corner>
    <el-card>
      <el-row>

        <el-col :span="12">
          <div class="weather">
            <iframe
              style="pointer-events: none"
              width="100%"
              scrolling="no"
              height="60"
              frameborder="0"
              allowtransparency="true"
              src="//i.tianqi.com/index.php?c=code&id=12&icon=1&num=5&site=12"
            ></iframe>
          </div>
        </el-col>
        <el-col :span="12" style="text-align: right;">
          <el-button icon="el-icon-question" type="primary" style="margin-right: 50px" @click.prevent.stop="guide">
            引导使用
          </el-button>
        </el-col>
      </el-row>
    </el-card>
    <yi-card title="关于">
      <div class="c-left">
        Relaxed admin 是一款便捷的快速开发平台，适用于搭建中小型后台管理系统。<br/><br/>

        开发目的: 仅仅是为了毕设而毕设，内置了一些常用后台管理组件，方便大家二次开发。
      </div>
    </yi-card>
    <yi-card title="技术栈">
      <yi-text-link :text-arrs="technologyStacks" />
    </yi-card>
    <yi-card title="项目结构">
      <div class="c-left">
        <yi-code language="bash" :code="projectDir" />
      </div>
    </yi-card>
    <yi-card title="项目规范">
      <yi-descriptions
        title="文件命名规范"
        :column="1"
        :table-datas="[
          { name: '文件夹', description: '统一小写, 多个单词使用-分割' },
          { name: '文件(.js .vue .json )', description: '统一小写, 多个单词使用-分割' }
        ]"
      />
      <yi-descriptions
        title="编写组件规范"
        :column="1"
        :table-datas="[
          { name: '组件的文件', description: '统一小写, 多个单词使用-分割' },
          {
            name: '组件的目录结构',
            description: '例如 button 组件：button/src/index.vue, 统一在button/index.ts导出'
          },
          {
            name: '组件导包顺序',
            description: '导vue技术栈的包 , 导第三方的工具函数 , 导本地的组件, 导本地的工具函数'
          },
          { name: '组件的名称', description: '统一大写开头，驼峰命名' },
          { name: '组件属性顺序', description: 'name, components, props, emits, setup ...' },
          { name: 'template标签', description: '小写加 - ( 例如：<case-panel/> )' },
          {
            name: 'template标签属性顺序',
            description: 'v-if , v-for , ref, class, style, ... ,事件'
          },
          { name: '组件的props', description: '小写开头，驼峰命名，必须编写类型默认值' },
          {
            name: '组件的样式',
            description: '作用域：scoped, lang = scss / less  ; 类名：统一小写, 多个单词使用-分割'
          }
        ]"
      />
    </yi-card>

    <yi-card title="Git提交规范">
      <yi-descriptions
        :column="1"
        :table-datas="[
          { name: 'add 操作', description: 'git add ' },
          { name: 'commit 操作', description: 'yarn commit ' },
          { name: 'pull 操作', description: 'git pull ' },
          { name: 'push 操作', description: 'git push ' }
        ]"
      />
    </yi-card>
  </div>
</template>

<script>
import YiDescriptions from '@/components/desctiptions'
import YiCard from "@/components/card";
import YiTextLink from '@/components/TextLink'
import YiCode from '@/components/code'
import {GitCorner} from "@/components/author";
import Driver from 'driver.js' // import driver.js
import 'driver.js/dist/driver.min.css' // import driver.js css
import { mapGetters } from 'vuex'


import {technologyStacks} from './config/technology-stacks'
import {projectDir} from './config/project-dir'
import GuideConfig from './config/steps'

export default {
  name: 'Dashboard',
  components:{
    YiDescriptions,YiCard,YiTextLink,YiCode,GitCorner
  },

  data(){
    return{

      technologyStacks,
      projectDir,
      driver: null

    }
  },
  mounted() {
    this.driver = new Driver({
      animate: false,
      doneBtnText: '我知道了',
      closeBtnText: '跳过', //  关闭按钮文案
      nextBtnText: '下一步', // 下一步的按钮文案
      prevBtnText: '上一步', // 上一步的按钮文案
    })
  },
  methods: {
    guide() {
      this.driver.defineSteps(GuideConfig[this.$store.getters.settings.menuLayout])
      this.driver.start()
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
