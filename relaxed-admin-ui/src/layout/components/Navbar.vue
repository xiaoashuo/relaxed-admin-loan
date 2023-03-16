<template>
  <div class="navbar">
    <!--    展开图标-->
    <hamburger
      id="hamburger-container"
      :is-active="sidebar.opened"
      class="hamburger-container"
      @toggleClick="toggleSideBar"
      v-if="showHamburger"
    />
    <!--    面包屑-->
    <breadcrumb
      id="breadcrumb-container"
      class="breadcrumb-container"
      v-if="showBreadCrumb"
    />

    <!--    顶部菜单导航-->
    <top-nav
      id="topmenu-container"
      class="topmenu-container"
      v-if="menuLayout == menuEnum.MENU_LAYOUT.TOP_NAV.value"
    ></top-nav>
    <!--   混合菜单导航-->
    <top-left-nav
      id="topleftmenu-container"
      class="topleftmenu-container"
      v-if="menuLayout == menuEnum.MENU_LAYOUT.TOP_LEFT_NAV.value"
    ></top-left-nav>
    <!--    顶部右侧菜单-->
    <div class="right-menu" :class="rightNavClassName">
      <template v-if="device !== 'mobile'">
        <search id="header-search" class="right-menu-item" />
        <el-tooltip content="源码地址" effect="dark" placement="bottom">
          <git id="git-address" class="right-menu-item hover-effect"> </git>
        </el-tooltip>

        <error-log class="errLog-container right-menu-item hover-effect" />

        <screenfull id="screenfull" class="right-menu-item hover-effect" />

        <el-tooltip content="布局大小" effect="dark" placement="bottom">
          <size-select id="size-select" class="right-menu-item hover-effect" />
        </el-tooltip>
      </template>
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar + '?imageView2/1/w/80/h/80'" class="user-avatar" />
          <span class="user-text">{{ $store.getters.name }}</span>
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link to="/system/user/profile">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>
          <router-link to="/">
            <el-dropdown-item> 项目介绍 </el-dropdown-item>
          </router-link>
          <el-dropdown-item @click.native="showSettings = true">
            <span>布局设置</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span style="display: block">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Screenfull from '@/components/Screenfull'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import SizeSelect from '@/components/SizeSelect'
import Search from '@/components/HeaderSearch'
import ErrorLog from '@/components/ErrorLog'
import { Git } from '@/components/author'
import { TopNav, TopLeftNav } from '@/components/menu'
import MenuEnum from '@/constants/enums/MenuEnum'
export default {
  components: {
    Breadcrumb,
    Hamburger,
    Screenfull,
    SizeSelect,
    Search,
    ErrorLog,
    TopNav,
    TopLeftNav,
    Git
  },
  data() {
    return {
      menuEnum: MenuEnum
    }
  },

  computed: {
    ...mapGetters(['sidebar', 'avatar', 'device',    'settings' ]),
    showSettings: {
      get() {
        return this.$store.state.settings.showSettings
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'showSettings',
          value: val
        })
      }
    },
    rightNavClassName() {
      //右侧导航栏 背景色  为顶部导航时候需设置
      return {
        'top-right-menu':
          this.menuLayout === this.menuEnum.MENU_LAYOUT.TOP_NAV.value &&
          this.settings.sideTheme === 'theme-dark'
      }
    },
    menuLayout: {
      get() {
        return this.$store.state.settings.menuLayout
      }
    },
    showHamburger() {
      return this.menuLayout !== this.menuEnum.MENU_LAYOUT.TOP_NAV.value
    },
    showBreadCrumb() {
      //若不为顶部导航 则显示面包屑
      return (
        this.menuLayout !== this.menuEnum.MENU_LAYOUT.TOP_LEFT_NAV.value &&
        this.menuLayout !== this.menuEnum.MENU_LAYOUT.TOP_NAV.value
      )
    }
  },
  methods: {
    goto() {},
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.$store.dispatch('auth/logout').then(() => {
            // setTimeout(() => {
            //   window.location.reload()
            // }, 16)
            // this.$router.push(`/user/login?redirect=${this.$route.fullPath}`)
          })
        })
        .catch((err) => {
          // this.$message.error({
          //   title: '错误',
          //   description: err.message
          // })
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.user-dropdown {
  top: 50px !important;
}
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background 0.3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, 0.025);
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    width: 500px;
    text-align: right;
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }
        .user-text {
          cursor: pointer;
          display: inline-block;
          line-height: 40px;
          white-space: nowrap;
          font-size: 20px;
          max-width: 102px;
          overflow: hidden;
          text-overflow: ellipsis;
        }
        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 20px;
          font-size: 12px;
        }
      }
    }
  }

  .top-right-menu {
    background-color: rgb(48, 65, 86);
    .el-dropdown {
      height: 50px;
      color: #bfcbd9;
    }
    .right-menu-item {
      color: #bfcbd9;
    }
    ::v-deep .el-input__inner {
      background-color: transparent;
    }
  }
  .topmenu-container {
    position: absolute;
    left: 50px;
    width: calc(100% - 500px);
  }
  .topleftmenu-container {
    position: absolute;
    left: 50px;
    width: calc(100% - 500px);
  }
}
</style>
