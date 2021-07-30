import Vue from 'vue'
import Vuex from 'vuex'

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
  },
  mutations: {
    isCurrent(state,page){
      state.currentPage=page
    },
    
    UPDATE_TOKEN(state, Token) {
      state.Token = Token
    },
    DELETE_TOKEN(state) {
      state.Token = ''
    }
  },
  actions: {
    isCurrent(context,page){
      context.commit('isCurrent',page)
    },

    login(context, credentials) {
      axios.post('user/login', credentials)
      .then(res => {
        localStorage.setItem('token', res.data.data)
        context.commit('UPDATE_TOKEN', res.data.data)
      })
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
    logout(context) {
      context.commit('DELETE_TOKEN')
      localStorage.removeItem('token')
    }
  },
  modules: {
  }
})
