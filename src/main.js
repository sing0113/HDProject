import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'

import ElementUI from 'element-ui';   //引入element
import 'element-ui/lib/theme-chalk/index.css';

import './icons' //引入svg图片

import 'normalize.css/normalize.css'  //引入格式化css，需npm安装
import '@/styles/index.scss' //引入全局样式

// import './permission' //权限验证

Vue.use(ElementUI);

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
