const steps = [

  {
    element: '#el-menu',
    popover: {
      title: '菜单栏',
      description: '顶部导航,支持左右滚动',
      position: 'right'
    }
  },
  {
    element: '#hamburger-container',
    popover: {
      title: '收缩侧边栏',
      description: '打开或关闭侧边栏',
      position: 'bottom'
    }
  },

  {
    element: '#header-search',
    popover: {
      title: '页面搜索',
      description: '快速导航',
      position: 'left'
    }
  },
  {
    element: '#git-address',
    popover: {
      title: '源码地址',
      description: '当前项目源码地址',
      position: 'bottom'
    }
  },
  {
    element: '#screenfull',
    popover: {
      title: '全屏模式',
      description: '设置页面全屏模式',
      position: 'left'
    }
  },
  {
    element: '#size-select',
    popover: {
      title: '布局大小',
      description: '切换系统布局大小',
      position: 'left'
    }
  },
  {
    element: '#tags-view-container',
    popover: {
      title: '多页签',
      description: '浏览历史',
      position: 'bottom'
    },
    padding: 0
  }
]

export default steps
