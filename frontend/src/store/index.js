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
    Token: localStorage.getItem('token') || '',
  },
  mutations: {
    UPDATE_TOKEN(state, Token) {
      state.Token = Token
    },
    DELETE_TOKEN(state) {
      state.Token = ''
    }
  },
  actions: {
    login(context, credentials) {
      axios.post('user/login', credentials)
        .then(res => {
          localStorage.setItem('token', res.data.data)
          context.commit('UPDATE_TOKEN', res.data.data)
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
