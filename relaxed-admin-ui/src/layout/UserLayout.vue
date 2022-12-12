<template>
  <div id="userLayout" :class="['user-layout-wrapper']">
    <el-container>
      <el-header>
        <div class="head-title">
          <a href="/">
            <!--            <LogoSvg class="logo" />-->
            <span class="title">{{ projectTitle }}</span>
          </a>
        </div>
        <div class="desc">
          {{ projectDesc }}
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
      <el-footer>
        <div class="links">
          <a href="_self">帮助</a>
          <a href="_self">隐私</a>
          <a href="_self">条款</a>
        </div>
        <div class="copyright">Copyright &copy; 2019 云吸🐱</div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'UserLayout',
  data() {
    return {
      projectTitle: 'Relaxed Admin',
      projectDesc: 'Relaxed Admin通用管理平台'
    }
  }
}
</script>

<style lang="scss" scoped>
@import '~@/styles/mixin.scss';
.user-layout-wrapper {
  @include clearfix;
  position: relative;
  height: 100%;
  width: 100%;
}
.el-container {
  width: 100%;
  min-height: 100%;
  background: #f0f2f5 url(~@/assets/img/background.svg) no-repeat 50%;
  background-size: 100%;
  padding: 110px 0 144px;
  position: relative;

  a {
    text-decoration: none;
  }

  .el-header {
    text-align: center;

    .badge {
      position: absolute;
      display: inline-block;
      line-height: 1;
      vertical-align: middle;
      margin-left: -12px;
      margin-top: -10px;
      opacity: 0.8;
    }

    .logo {
      height: 44px;
      vertical-align: top;
      margin-right: 16px;
      border-style: none;
    }

    .title {
      font-size: 33px;
      color: rgba(0, 0, 0, 0.85);
      font-family: Avenir, 'Helvetica Neue', Arial, Helvetica, sans-serif;
      font-weight: 600;
      /*position: relative;*/
      /*top: 2px;*/
    }
    .desc {
      font-size: 14px;
      color: rgba(0, 0, 0, 0.45);
      margin-top: 12px;
    }
  }
  .el-footer {
    position: absolute;
    width: 100%;
    bottom: 0;
    padding: 0 16px;
    margin: 48px 0 24px;
    text-align: center;
    .links {
      margin-bottom: 8px;
      font-size: 14px;
      a {
        color: rgba(0, 0, 0, 0.45);
        transition: all 0.3s;
        &:not(:last-child) {
          margin-right: 40px;
        }
      }
    }
    .copyright {
      color: rgba(0, 0, 0, 0.45);
      font-size: 14px;
    }
  }
}
</style>
