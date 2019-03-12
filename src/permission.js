import router from './router'
import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
import {getToken} from "./utils/auth";//验证是否登录
import {Message} from "element-ui";

const whiteList = ['/login'] //设置白名单

router.beforeEach((to,from,next) => {
  NProgress.start();
  if (getToken()) {
    if (to.path === '/login') {
      next({path: '/'});
    } else {
      if (store.getters.roles.length === 0) {
        store.dispatch('GetInfo').then(response => { //获取登录用户信息
          next();
        }).catch(error => {
          store.dispatch('FedLogOut').then(() => {
            Message.error(error || 'Verification failed, please login again');
            next({path: '/'});
          })
        })
      } else {
        next();
      }
    }
  } else  {
    if (whiteList.indexOf(to.path) !== -1) {
      next();
    } else {
      next({path: '/login'});
    }
  }
});

router.afterEach(() => {
  NProgress.done(); //跳转结束关闭进度条nprogress
})
