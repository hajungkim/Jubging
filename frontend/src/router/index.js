import Vue from 'vue'
import VueRouter from 'vue-router'

import Ranking from '@/components/ranking/Ranking.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/ranking',
    name: 'Ranking',
    component: Ranking,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
