import Vue from 'vue'
import VueRouter from 'vue-router'
import Jubging from '@/components/jubging/Jubging.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/jubging',
    name: 'Jubging',
    component: Jubging
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
