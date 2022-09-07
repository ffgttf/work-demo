import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import enter from '../views/Enter.vue'
import user from '../views/User.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
    children: [
      {
        path: '/character',
        name: '角色管理',
        component: HomeView
      },
      {
        path: '/enter',
        name: '营业厅管理',
        component: enter
      },
      {
        path: '/user',
        name: '人员账号管理',
        component: user
      }
    ]
  },
  {
    path: '/login',
    name: 'loginPage',
    component: Login
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
