import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/login/index'
import Layout from '../views/layout/layout'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
      hidden: true
    },
    {
      path: '',
      component: Layout,
      redirect: '/home',
      children: [{
        path: 'home',
        name: 'home',
        component: () => import('../views/home/index'),
        meta: {title: '首页', icon: 'home'}
      }]
    },
    {
      path: '/oms',
      name: 'Oms',
      component: Layout,
      redirect: '/oms/order',
      meta: {title: '订单',icon: 'order'},
      children: [
        {
          path: 'order',
          name: 'Order',
          component: () => import('../views/oms/order/order'),
          meta: {title: '订单列表',icon: 'product-list'}
        },
        {
          path: 'orderSetting',
          name: 'orderSetting',
          component: () => import('../views/oms/order/setting'),
          meta: {title: '订单设置', icon: 'order-setting'}
        },
      ]
    }
  ]
})
