import Vue from 'vue'
import VueRouter from 'vue-router'

import Mission from '@/components/mission/Mission.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/mission',
    name: 'mission',
    component: Mission,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
