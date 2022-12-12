<template>
  <div class="icon-body">
    <el-input v-model="filterText" style="position: relative;" clearable placeholder="请输入图标名称" @clear="_debounceFilterIcons" @input.native="_debounceFilterIcons">
      <i slot="suffix" class="el-icon-search el-input__icon" />
    </el-input>
    <div ref="icon-list" class="icon-list">
      <el-collapse v-model="currentExpand">
        <el-collapse-item v-for="item in iconList" :title="item.label" :name="item.key">
          <el-row class="icon-select--class-row">
            <el-col v-for="(iconName, iconIndex) in item.value" :key="iconIndex" class="icon-select--class-col" :span="8">
              <template v-if="item.type==='normal'">

                <div class="icon-item" :class="{isActive: filterData[iconName]}" @click="selectedIcon(item.type,iconName)">
                  <i :class="'el-icon-' + iconName" />
                  <span>{{ iconName }}</span>
                </div>

              </template>
              <template v-if="item.type==='svg'">

                <div class="icon-item" :class="{isActive: filterData[iconName]}" @click="selectedIcon(item.type,iconName)">
                  <svg-icon :icon-class="iconName" />
                  <span>{{ iconName }}</span>
                </div>

              </template>

            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>
import elementIcons from './element-icons'
import svgIcons from './svg-icons'

import { debounce } from '@/utils/index'
export default {
  name: 'IconSelector',

  data() {
    return {
      // 当前过滤文本
      filterText: '',
      // 当前展开项
      currentExpand: [this.getDefaultIcons()[0]?.key],
      // 符合过滤条件的值
      filterData: {},
      // 所有图标数据
      iconList: this.getDefaultIcons()
    }
  },

  methods: {
    getDefaultIcons() {
      return elementIcons.concat(svgIcons)
    },
    _debounceFilterIcons() {
      debounce(this.filterIcons(), 300, false)
    },
    filterIcons() {
      this.filterData = {}
      const defaultIcons = this.getDefaultIcons()
      if (!this.filterText) {
        this.iconList = defaultIcons
        this.currentExpand = defaultIcons[0]?.key
        this.$refs['icon-list'].scrollTo({
          top: 0, // 需要父元素设置postion(relative、absolute、fixed)
          behavior: 'smooth'
        })
        return
      }
      const filterIcons = []
      defaultIcons.map(item => {
        // const isFind=item.value.find(current=>current.indexOf(this.filterText)>=0)
        let existFilterItem = false
        item.value.forEach(current => {
          if (current.indexOf(this.filterText) >= 0) {
            this.filterData[current] = true
            if (!existFilterItem) {
              existFilterItem = true
            }
          }
        })
        if (existFilterItem) {
          filterIcons.push(item)
        }
      })
      this.iconList = filterIcons
      this.currentExpand = filterIcons[0]?.key
    },
    selectedIcon(iconType, name) {
      let value = ''
      if (iconType === 'normal') {
        value = `el-icon-${name}`
      } else if (iconType === 'svg') {
        value = `${name}`
      }
      // 修改后的图标单位
      this.$emit('change', { type: iconType, value })
      // document.body.click()
    },
    reset() {
      this.filterText = ''
      const defaultIcons = this.getDefaultIcons()
      this.iconList = defaultIcons
      this.currentExpand = defaultIcons[0].key
      this.filterData = {}
    },
    generateElementIconCode(symbol) {
      return `<i class="el-icon-${symbol}" />`
    },
    generateIconCode(symbol) {
      return `<svg-icon icon-class="${symbol}" />`
    }

  }
}
</script>

<style lang="scss" scoped>
  .icon-body{
    width: 100%;
    padding: 10px;
    .isActive{
      color: rgb(24, 144, 255);
    }
    .icon-list{
      height: 200px;
      overflow-y: scroll;
      border-top: none;
      border-bottom: none;
      .icon-item{

        height: 30px;
        line-height: 30px;
        cursor: pointer;
      }
      ::v-deep{
        .el-collapse-item__header{
          padding-left: 10px;
          font-size: 16px;
          font-weight: bold;
        }
      }
      .icon-select--class-row{
        padding-left: 10px;
        .icon-select--class-col{
          /*width: 33%;*/

          .icon-item{
            font-size: 15px;
          }
        }
      }

    }

  }

</style>

<!--<style rel="stylesheet/scss" lang="scss" scoped>-->
<!--  .icon-body {-->
<!--    width: 100%;-->
<!--    padding: 10px;-->
<!--    .icon-list {-->
<!--      height: 200px;-->
<!--      overflow-y: scroll;-->
<!--      div {-->
<!--        height: 30px;-->
<!--        line-height: 30px;-->
<!--        margin-bottom: -5px;-->
<!--        cursor: pointer;-->
<!--        width: 33%;-->
<!--        float: left;-->
<!--      }-->
<!--      span {-->
<!--        display: inline-block;-->
<!--        vertical-align: -0.15em;-->
<!--        fill: currentColor;-->
<!--        overflow: hidden;-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--</style>-->
