/**
 * 思路：用户长时间不操作，就没有鼠标点击(window.document.onmousedown)事件、
 * 鼠标滚轮(mousewheel)事件、鼠标移动(mousemove)事件之类的，
 * 我们只需要监听这些事件，如果这些事件长时间没有触发，
 * 就说明用户长时间未操作，然后路由跳转到登录页面即可。
 */
// 引入路由和storage工具函数
import storage from '@/utils/storage'
import Cache from '@/utils/cache'
import router from '@/router'

let lastTime = new Date().getTime()
let currentTime = new Date().getTime()
let timeOut = 2 * 60 * 1000  //设置超时时间: 30分钟
let timer=null;

let listener = function() {
  // 为了方便，我们把点击事件的时间直接存到sessionStorage中去，这样方便获取比较
  storage.session.setCache("lastTime", new Date().getTime());
}
export function openTimeOut() {
  console.log("挂在了")
  if (timer!=null){
    clearInterval(timer)
  }
  window.addEventListener(
    "click",
    listener,
    true
  );
  /* 定时器 间隔30秒检测是否长时间未操作页面 */
  timer=window.setInterval(checkTimeout, 30000);
}

export function closeTimeout() {
 if (timer!=null){
   clearInterval(timer)
 }

  window.removeEventListener(
    "click",
    listener,
    true
  );
}

function checkTimeout() {
  currentTime = new Date().getTime()    //更新当前时间
  lastTime = storage.session.getCache("lastTime")??0;
  console.log("检查",currentTime,lastTime)
  if (currentTime - lastTime > timeOut) { //判断是否超时
    const isLogin=router.currentRoute.name=='login'
    console.log("当前已经超时",isLogin)
    // 清除storage的数据(登陆信息和token)
   // Cache.clearAll()
    // 跳到登陆页
    // if(router.currentRouter.name == 'login')
    //   return // 当前已经是登陆页时不做跳转
    // router.push({ name: 'login' })
  }
}

export default function () {
  /* 定时器 间隔30秒检测是否长时间未操作页面 */
  window.setInterval(checkTimeout, 30000);
}
