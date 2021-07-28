import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/views/Main.vue'
import Detail from '@/views/Detail.vue'
import Home from '@/components/home/Home.vue'
import Mission from '@/components/mission/Mission.vue'
import Jubging from '@/components/jubging/Jubging.vue'
import Ranking from '@/components/ranking/Ranking.vue'
import My from '@/components/my/My.vue'
import Setting from '@/components/my/Setting.vue'
Vue.use(VueRouter)

const routes = [
  {
    path:'/main',
    name:'Main',
    component:Main,
    children:[
      {
        path:'/home',
        name:'Home',
        component:Home,
      },
      {
        path:'/mission',
        name:'Mission',
        component:Mission,
      },
      {
        path:'/jubging',
        name:'Jubging',
        component:Jubging,
      },
      {
        path:'/ranking',
        name:'Ranking',
        component:Ranking,
      },
      {
        path:'/my',
        name:'My',
        component:My,
      },
    ],
  },
  {
    path:'/detail',
    name:'Detail',
    component:Detail,
  },
  {
    path:'/setting',
    name:'Setting',
    component:Setting,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
