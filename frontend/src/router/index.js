import Vue from 'vue'
import VueRouter from 'vue-router'
import Jubging from '@/components/jubging/Jubging.vue'
import JubgingOff from '@/views/jubging/JubgingOff.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/jubging',
    name: 'Jubging',
    component: Jubging,
  },
  {
    path: '/jubging/register',
    name: 'Register',
    component: JubgingOff
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
