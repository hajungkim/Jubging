import Vue from 'vue'
import VueRouter from 'vue-router'

import Signup from '@/views/user/Signup.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
