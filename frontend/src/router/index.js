import Vue from 'vue'
import VueRouter from 'vue-router'
<<<<<<< HEAD
import Jubging from '@/components/jubging/Jubging.vue'
import JubgingOff from '@/views/jubging/JubgingOff.vue'
import NewArticle from '@/views/jubging/NewArticle.vue'

=======
import Main from '@/views/Main.vue'
>>>>>>> a45ea954293c1af9d9ecc6e1fb3741a283475a2d
Vue.use(VueRouter)

const routes = [
  {
<<<<<<< HEAD
    path: '/jubging',
    name: 'Jubging',
    component: Jubging,
  },
  {
    path: '/jubging/register',
    name: 'Register',
    component: JubgingOff
  },
  {
    path: '/jubging/article',
    name: 'NewArticle',
    component: NewArticle
  }
=======
    path:'',
    name:'Main',
    component:Main,
  },
>>>>>>> a45ea954293c1af9d9ecc6e1fb3741a283475a2d
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
