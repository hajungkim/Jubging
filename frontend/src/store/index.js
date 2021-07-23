import Vue from 'vue'
import Vuex from 'vuex'

import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080/'

axios.interceptors.request.use(config => {
  const accessToken = localStorage.getItem('access_token')
  config.headers.common['Authorization'] = accessToken ? `Bearer ${accessToken}` : ''
  return config
})

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: localStorage.getItem('access_token') || '',
  },
  mutations: {
    UPDATE_TOKEN(state, accessToken) {
      state.accessToken = accessToken
    },
    DELETE_TOKEN(state) {
      state.accessToken = ''
    }
  },
  actions: {
    login(context, credentials) {
      axios.post('token/', credentials)
        .then(res => {
          localStorage.setItem('access_token', res.data.access)
          context.commit('UPDATE_TOKEN', res.data.access)
        })
        .catch(err => {
          console.error(err)
        })
    },
    logout(context) {
      context.commit('DELETE_TOKEN')
      localStorage.removeItem('access_token')
    }
  },
  modules: {
  }
})
