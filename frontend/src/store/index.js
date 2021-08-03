import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router'
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080/'

axios.interceptors.request.use(config => {
  const Token = localStorage.getItem('token')
  config.headers.common['Authorization'] = Token ? `Bearer ${Token}` : ''
  return config
})

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    currentPage:0,
    photos:[
      {
        title:'0',
        url:'http://placehold.it/185x185',
      },
      {
        title:'1',
        url:'http://placehold.it/185x185',
      },
      {
        title:'2',
        url:'http://placehold.it/185x185',
      },
      {
        title:'3',
        url:'http://placehold.it/185x185',
      },
      {
        title:'4',
        url:'http://placehold.it/185x185',
      },
      // {
      //   title:'5',
      //   url:'http://placehold.it/185x185',
      // },
      // {
      //   title:'6',
      //   url:"@/assets/sample6.png",
      // },
      // {
      //   title:'7',
      //   url:"@/assets/sample7.png",
      // },
      // {
      //   title:'8',
      //   url:"@/assets/sample8.png",
      // },
      // {
      //   title:'9',
      //   url:"@/assets/sample8.png",
      // },
    ],
    Token: localStorage.getItem('token') || '',
    userId: null,
    missions: null,
  },
  mutations: {
    isCurrent(state,page){
      state.currentPage=page
    },

    // 미션 관련
    GET_MISSION(state, missions) {
      state.missions = missions
    },
      
    // 유저 관련
    UPDATE_TOKEN(state, data) {
      state.Token = data.token
      state.userId = data.userId
    },
    DELETE_TOKEN(state) {
      state.Token = ''
      state.userId = null
    }
  },
  actions: {
    isCurrent(context,page){
      context.commit('isCurrent',page)
    },

    // 미션 관련
    getMission(context) {
      axios.get(`mission/${this.state.userId}`)
      .then(res => {
        context.commit('GET_MISSION', res.data.data)
      })
      .catch(err => {
        console.error(err)
      })
    },

    // 유저 관련
    login(context, credentials) {
      axios.post('user/login', credentials)
      .then(res => {
        // 리팩토링할 때 아래로 옮기기 : 로그인 실패할 때도 실행됨
        localStorage.setItem('token', res.data.data.token)
        context.commit('UPDATE_TOKEN', res.data.data)
        return res.data.data
      })
      .then((tf) => {
        if (tf) {
          router.push({ name: 'Home' })
        } else {
          alert('이메일 혹은 비밀번호가 틀렸습니다.')
        }
      })
      .catch(err => {
        console.error(err)
       })
    },
    logout(context) {
      localStorage.removeItem('token')
      context.commit('DELETE_TOKEN')
    },
    signup(context, credentials) {
      axios.post('user/join/', credentials)
      .then(() => {
          context.dispatch('login', credentials)
        })
      .catch(err => {
        console.error(err)
       })
    },
  },
  modules: {
  }
})
