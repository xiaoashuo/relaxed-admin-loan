import router from './router'
import store from './store'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import {TOKEN_KEY} from "@/constants/UserConstant";
import CACHE from '@/utils/cache' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/user/login'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = CACHE.getCache(TOKEN_KEY)

  if (hasToken) {
    if (to.path === '/user/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done()
    } else {
      const asyncRouters=  store.getters.asyncRouters
      if (asyncRouters&&asyncRouters.length!=0) {
        next()
      } else {


        const asyncRoutes = await store.dispatch('router/generateRoutes')
        router.addRoutes(asyncRoutes)
        next({ ...to, replace: true })
        // try {
        //   // get user info
        //   await store.dispatch('user/getInfo')
        //
        //   next()
        // } catch (error) {
        //   // remove token and go to login page to re-login
        //   await store.dispatch('user/resetToken')
        //
        //   NProgress.done()
        // }
        NProgress.done()
      }
    }
  } else {
    /* has no token*/

    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/user/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
