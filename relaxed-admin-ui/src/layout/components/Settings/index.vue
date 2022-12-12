<template>
  <el-drawer size="280px" :visible="visible" :with-header="false" :append-to-body="true" :show-close="false">

  <div class="drawer-container">
    <div>

      <div class="setting-drawer-content">
        <div class="setting-drawer-title">
          <h3 class="drawer-title">主题风格设置</h3>
        </div>
        <div class="setting-drawer-block-checbox">
          <el-tooltip  effect="dark" content="暗色菜单风格" placement="top" >

          <div class="setting-drawer-block-checbox-item" @click="handleTheme('theme-dark')">
            <img src="@/assets/img/dark.svg" alt="dark">
            <div v-if="sideTheme === 'theme-dark'" class="setting-drawer-block-checbox-selectIcon" style="display: block;">
              <i aria-label="图标: check" class="anticon anticon-check">
                <svg viewBox="64 64 896 896" data-icon="check" width="1em" height="1em" :fill="theme" aria-hidden="true"
                     focusable="false" class="">
                  <path
                    d="M912 190h-69.9c-9.8 0-19.1 4.5-25.1 12.2L404.7 724.5 207 474a32 32 0 0 0-25.1-12.2H112c-6.7 0-10.4 7.7-6.3 12.9l273.9 347c12.8 16.2 37.4 16.2 50.3 0l488.4-618.9c4.1-5.1.4-12.8-6.3-12.8z"/>
                </svg>
              </i>
            </div>
          </div>
          </el-tooltip>
          <el-tooltip  effect="dark" content="亮色菜单风格" placement="top" >
          <div class="setting-drawer-block-checbox-item" @click="handleTheme('theme-light')">
            <img src="@/assets/img/light.svg" alt="light">
            <div v-if="sideTheme === 'theme-light'" class="setting-drawer-block-checbox-selectIcon" style="display: block;">
              <i aria-label="图标: check" class="anticon anticon-check">
                <svg viewBox="64 64 896 896" data-icon="check" width="1em" height="1em" :fill="theme" aria-hidden="true"
                     focusable="false" class="">
                  <path
                    d="M912 190h-69.9c-9.8 0-19.1 4.5-25.1 12.2L404.7 724.5 207 474a32 32 0 0 0-25.1-12.2H112c-6.7 0-10.4 7.7-6.3 12.9l273.9 347c12.8 16.2 37.4 16.2 50.3 0l488.4-618.9c4.1-5.1.4-12.8-6.3-12.8z"/>
                </svg>
              </i>
            </div>
          </div>
          </el-tooltip>
        </div>

        <div class="drawer-item">
          <span>主题颜色</span>
          <theme-picker style="float: right;height: 26px;margin: -3px 8px 0 0;" @change="themeChange" />
        </div>
      </div>

      <el-divider/>
      <div class="setting-drawer-content">
        <div class="setting-drawer-title">
          <h3 class="drawer-title">导航模式</h3>
        </div>
        <div class="setting-drawer-block-checbox">
          <el-row>
            <el-col :span="8" style="margin-bottom: 10px" v-for="item in menuLayouts">
              <el-tooltip  effect="dark" :content="item.label" placement="bottom-start" >
                <div class="setting-drawer-block-checbox-item" @click="handleLayout(item.value)">
                  <div class=" setting-drawer-index-item-com-style" :class="[layoutClassName(item.value)]" />
                  <div v-if="menuLayout === item.value" class="setting-drawer-block-checbox-selectIcon" style="display: block;">
                    <i aria-label="图标: check" class="anticon anticon-check">
                      <svg viewBox="64 64 896 896" data-icon="check" width="1em" height="1em" :fill="theme" aria-hidden="true"
                           focusable="false" class="">
                        <path
                          d="M912 190h-69.9c-9.8 0-19.1 4.5-25.1 12.2L404.7 724.5 207 474a32 32 0 0 0-25.1-12.2H112c-6.7 0-10.4 7.7-6.3 12.9l273.9 347c12.8 16.2 37.4 16.2 50.3 0l488.4-618.9c4.1-5.1.4-12.8-6.3-12.8z"/>
                      </svg>
                    </i>
                  </div>
                </div>
              </el-tooltip>
            </el-col>
          </el-row>




        </div>


      </div>
      <el-divider/>

      <h3 class="drawer-title">系统布局配置</h3>



      <div class="drawer-item">
        <span>开启Tags-View</span>
        <el-switch v-model="tagsView" class="drawer-switch" />
      </div>

      <div class="drawer-item">
        <span>固定 Header</span>
        <el-switch v-model="fixedHeader" class="drawer-switch" />
      </div>

      <div class="drawer-item">
        <span>显示 Logo</span>
        <el-switch v-model="sidebarLogo" class="drawer-switch" />
      </div>
      <div class="drawer-item">
        <span>显示水印</span>
        <el-switch v-model="watermark" class="drawer-switch" />
      </div>

    </div>
    <el-divider/>
    <el-row>
      <el-col>
        <el-button size="small"  type="primary" plain icon="el-icon-document-add" @click="copySetting">拷贝配置</el-button>
        <el-button size="small" plain icon="el-icon-refresh" @click="resetSetting">重置配置</el-button>
      </el-col>
    </el-row>


  </div>
  </el-drawer>
</template>

<script>
import ThemePicker from '@/components/ThemePicker'
import variables from "@/styles/variables.scss";
import clipboard from '@/utils/clipboard'
import MenuEnum from "@/constants/enums/MenuEnum";
import WaterMark from '@/utils/watermark'
export default {
  components: { ThemePicker },
  data() {
    return {
      theme: this.$store.state.settings.theme,
      sideTheme: this.$store.state.settings.sideTheme,
      menuLayout: this.$store.state.settings.menuLayout,
      menuLayouts: this.$enums.getEnumList('MENU_LAYOUT')
    }
  },
  computed: {
    visible: {
      get() {
        return this.$store.state.settings.showSettings
      }
    },
    fixedHeader: {
      get() {
        return this.$store.state.settings.fixedHeader
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'fixedHeader',
          value: val
        })
      }
    },
    watermark: {
      get() {
        return this.$store.state.settings.watermark
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'watermark',
          value: val
        })
        if (val){
          WaterMark.set('main-content',this.$store.getters.name)
        }else{
          WaterMark.clear()
        }
      }
    },
    tagsView: {
      get() {
        return this.$store.state.settings.tagsView
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'tagsView',
          value: val
        })
      }
    },
    sidebarLogo: {
      get() {
        return this.$store.state.settings.sidebarLogo
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'sidebarLogo',
          value: val
        })
      }
    },

  },
  methods: {
    copySetting(event){
      const layoutSetting=`export default {
            "sideTheme":"${this.sideTheme}",
            "showSettings":"${this.$store.state.settings.showSettings}",
            "tagsView":${this.tagsView},
            "fixedHeader":${this.fixedHeader},
            "sidebarLogo":${this.sidebarLogo},
            "menuLayout":${this.menuLayout},
            "theme":"${this.theme}"
          }`
      clipboard(layoutSetting, event)

    },
    resetSetting(){
      //此处调用刷新页面 所以不需要显示关闭  loading.close()
      const loading = this.$loading({
        lock: true,
        text: '正在清除设置缓存并刷新，请稍候...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      this.$store.dispatch("settings/removeLocalSettings")
      .then(res=>{
        setTimeout("window.location.reload()", 600)
      })
    },
    layoutClassName(layoutType){
      let layoutClassName;
      switch (layoutType) {
        case MenuEnum.MENU_LAYOUT.LEFT_NAV.value:
          layoutClassName='setting-drawer-index-item-side'
          break
        case MenuEnum.MENU_LAYOUT.TOP_NAV.value:
          layoutClassName='setting-drawer-index-item-top'
          break
        case MenuEnum.MENU_LAYOUT.TOP_LEFT_NAV.value:
          layoutClassName='setting-drawer-index-item-mix'
          break
        case MenuEnum.MENU_LAYOUT.LEFT_PLUS_NAV.value:
          layoutClassName='setting-drawer-index-item-left-plus'
          break
      }
      return layoutClassName
    },
    handleLayout(val){
      this.$store.dispatch('settings/changeSetting', {
        key: 'menuLayout',
        value: val
      }).then(res=>{
        if (val==MenuEnum.MENU_LAYOUT.LEFT_NAV.value||val==MenuEnum.MENU_LAYOUT.TOP_NAV.value){
          //恢复侧边栏
          this.$store.commit("router/SET_SIDEBAR_ROUTES", this.$store.getters.allRouters);
        }
      })
      this.menuLayout = val;
    },
    handleTheme(val) {
      this.$store.dispatch('settings/changeSetting', {
        key: 'sideTheme',
        value: val
      })
      this.sideTheme = val;
    },
    themeChange(val) {
      this.$store.dispatch('settings/changeSetting', {
        key: 'theme',
        value: val
      })
    }
  }
}
</script>

<style lang="scss" scoped>

  .setting-drawer-index-item-com-style {
    position: relative;
    width: 44px;
    height: 36px;
    margin-right: 16px;
    overflow: hidden;
    background-color: #f0f2f5;
    border-radius: 4px;
    box-shadow: 1px 2px 3px #d2d2d2;
    cursor: pointer;
  }
  .setting-drawer-index-item-com-style:before {
    position: absolute;
    top: 0;
    left: 0;
    width: 33%;
    height: 100%;
    background-color: #fff;
    content: '';
  }
  .setting-drawer-index-item-com-style:after {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 25%;
    background-color: #fff;
    content: '';
  }
  .setting-drawer-index-item-left-plus:before {

    content: '';

    background: linear-gradient(
        to right,
        #001529 0%,
        #001529 60%,
        #fff 60%,
        #fff 100%
    );
    z-index: 1;
  }
  .setting-drawer-index-item-left-plus:after {
    background-color:  #f0f2f5;
    content: '';
  }
  .setting-drawer-index-item-mix:before {
    background-color: #001529;

    content: '';
    z-index: 1;
  }
  .setting-drawer-index-item-mix:after {
    /*position: absolute;*/
    /*left: 3px;*/
    background-color: #fff ;

    content: '';
  }
  .setting-drawer-index-item-top:before {
    background-color: #f0f2f5;
    content: '';

  }
  .setting-drawer-index-item-top:after {
    /*position: absolute;*/
    /*left: 3px;*/
    background-color: #001529 ;

    content: '';
    z-index: 1;
  }
  .setting-drawer-index-item-side:before {
    background-color: #001529;
    content: '';
    z-index: 1;
  }
  .setting-drawer-index-item-side:after {
    background-color:  #f0f2f5;
    content: '';
  }
  .setting-drawer-content {
    .setting-drawer-title {
      margin-bottom: 12px;
      color: rgba(0, 0, 0, .85);
      font-size: 14px;
      line-height: 22px;
      font-weight: bold;
    }

    .setting-drawer-block-checbox {
      display: flex;
      justify-content: flex-start;
      align-items: center;
      margin-top: 10px;
      margin-bottom: 20px;

      .setting-drawer-block-checbox-item {
        position: relative;
        margin-right: 16px;
        border-radius: 2px;
        cursor: pointer;

        img {
          width: 48px;
          height: 48px;
        }

        .setting-drawer-block-checbox-selectIcon {
          position: absolute;
          top: 0;
          right: 0;
          width: 100%;
          height: 100%;
          padding-top: 15px;
          padding-left: 24px;
          color: #1890ff;
          font-weight: 700;
          font-size: 14px;
        }
      }
    }
  }
.drawer-container {
  padding: 24px;
  font-size: 14px;
  line-height: 1.5;
  word-wrap: break-word;

  .drawer-title {
    margin-bottom: 12px;
    color: rgba(0, 0, 0, .85);
    font-size: 14px;
    line-height: 22px;
  }

  .drawer-item {
    color: rgba(0, 0, 0, .65);
    font-size: 14px;
    padding: 12px 0;
  }

  .drawer-switch {
    float: right
  }

}
</style>
