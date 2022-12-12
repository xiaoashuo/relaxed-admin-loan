
module.exports = {

  title: 'Relaxed-admin',
  /**
   * 侧边栏主题 深色主题theme-dark，浅色主题theme-light
   */
  sideTheme: 'theme-dark',
  /**
   * @type {boolean} true | false
   * @description Whether show the settings right-panel
   */
  showSettings: false,
  /**
   * @type {boolean} true | false
   * @description Whether fix the header
   */
  fixedHeader: false,

  /**
   * @type {boolean} true | false
   * @description Whether show the logo in sidebar
   */
  sidebarLogo: false,
  /**
   * 菜单类型
   * @param default 默认菜单 topNav 顶部导航 leftPlus 左侧菜单增强
   */
//  menuType:menuEnum.default.MENU_LAYOUT.LEFT_NAV.value,
  menuLayout:'LEFT_NAV',

  /**
   * @type {boolean} true | false
   * @description Whether need tagsView
   */
  tagsView: true,
  /**
   * @type {boolean} true | false
   * @description 是否开启水印
   */
  watermark: true,
  /**
   * @type {string | array} 'production' | ['production', 'development']
   * @description Need show err logs component.
   * The default is only used in the production env
   * If you want to also use it in dev, you can pass ['production', 'development']
   */
  errorLog: ['production', 'development']
}
