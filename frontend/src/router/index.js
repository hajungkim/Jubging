import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/views/Main.vue'
import Detail from '@/views/Detail.vue'
import Home from '@/components/home/Home.vue'
import Mission from '@/components/mission/Mission.vue'
import Jubging from '@/components/jubging/Jubging.vue'
import Ranking from '@/components/ranking/Ranking.vue'
import My from '@/components/my/My.vue'
import Userprofile from '@/views/Userprofile.vue'
import Setting from '@/components/my/Setting.vue'
import JubgingOff from '@/views/jubging/JubgingOff.vue'
import NewArticle from '@/views/jubging/NewArticle.vue'
import Login from '@/views/user/Login.vue'
import SignUp from '@/views/user/SignUp.vue'
import Search from '@/views/Search.vue'
import FindPassword from '@/views/user/FindPassword.vue'
import ChangeSetting from '@/views/user/ChangeSetting.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '*',
    redirect: '/404'
  },
  {
    path: '/404',
    name: 'PageNotFound',
    // component: 
  },
  {
    path: '/',
    redirect: '/home',
  },
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
    path:'/userprofile',
    name:'Userprofile',
    component:Userprofile,
  },
  {
    path:'/setting',
    name:'Setting',
    component:Setting,
  },
  {
    path: '/jubging/register',
    name: 'Register',
    component: JubgingOff,
    props: true
  },
  {
    path: '/jubging/article',
    name: 'NewArticle',
    component: NewArticle
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/findpassword',
    name: 'FindPassword',
    component: FindPassword,
  },
  {
    path: '/changesetting',
    name: 'ChangeSetting',
    component: ChangeSetting,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
