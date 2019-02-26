import Vue from 'vue'
import Router from 'vue-router'
import Header from '../views/login/index'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/qwe',
      name: 'Header',
      component: Header
    }
  ]
})
