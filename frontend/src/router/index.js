import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store/index.js'

import Main from '@/views/Main.vue'
import Home from '@/components/home/Home.vue'
import Detail from '@/views/home/Detail.vue'
import Search from '@/views/home/Search.vue'

import Jubging from '@/components/jubging/Jubging.vue'
import JubgingOff from '@/views/jubging/JubgingOff.vue'
import NewArticle from '@/views/jubging/NewArticle.vue'
import Editarticle from '@/views/home/Editarticle.vue'
import Events from '@/views/jubging/Events.vue'
import EventPoster from '@/views/jubging/EventPoster.vue'

import Mission from '@/components/mission/Mission.vue'
import Ranking from '@/components/ranking/Ranking.vue'

import Logs from '@/components/my/Logs.vue'
import My from '@/components/my/My.vue'
import Userprofile from '@/views/user/Userprofile.vue'
import Login from '@/views/user/Login.vue'
import SignUp from '@/views/user/SignUp.vue'
import FindPassword from '@/views/user/FindPassword.vue'
import ChangeSetting from '@/views/user/ChangeSetting.vue'

Vue.use(VueRouter)

const requireAuth = () => (to, from, next) => {
  if (store.state.Token) {
    return next();
  }
  next('/login');
  alert('로그인이 필요합니다.')
};

const requireNoAuth = () => (to, from, next) => {
  if (!store.state.Token) {
    return next();
  }
  next('/home');
};

const routes = [
  {
    path: '*',
    redirect: '/'
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
        beforeEnter: requireAuth()
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
        beforeEnter: requireAuth()
      },
      {
        path:'/Logs/:flag',
        name:'Logs',
        component:Logs,
        beforeEnter: requireAuth()
      },
      {
        path: '/jubging/events',
        name: 'Events',
        component: Events,
        beforeEnter: requireAuth()
      },
    ],
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path:'/article/:article_id',
    name:'Detail',
    component:Detail,
  },
  {
    path: '/article/:article_id/edit',
    name: 'Editarticle',
    component: Editarticle,
    beforeEnter: requireAuth()
  },
  {
    path:'/userprofile/:user_id',
    name:'Userprofile',
    component:Userprofile,
  },
  {
    path: '/my/changesetting',
    name: 'ChangeSetting',
    component: ChangeSetting,
    beforeEnter: requireAuth()
  },
  {
    path: '/jubging/article',
    component: {
      template: `
        <div>
          <router-view></router-view>
        </div>
      `
    },
    children: [
      {
        path: 'register',
        name: 'Register',
        component: JubgingOff,
        props: true
      },
      {
        path: '',
        name: 'NewArticle',
        component: NewArticle
      },
    ],
    beforeEnter: requireAuth()
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    beforeEnter: requireNoAuth()
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp,
    beforeEnter: requireNoAuth()
  },
  {
    path: '/findpassword',
    name: 'FindPassword',
    component: FindPassword,
    beforeEnter: requireNoAuth()
  },
  {
    path:'/jubging/events/:eventId',
    name:'EventPoster',
    component:EventPoster,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
