import Vue from 'vue'
import Vuex from 'vuex'

import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
    signup({ dispatch }, credentials) {
      axios.post('accounts/signup/', credentials)
      .then(() => {
        dispatch('login', credentials)
      })
      .catch(err => {
        console.error(err)
      })
    }
  },
  modules: {
  }
})
