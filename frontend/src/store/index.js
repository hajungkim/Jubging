import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    currentPage:0,
  },
  mutations: {
    isCurrent(state,page){
      state.currentPage=page
    },
  },
  actions: {
    isCurrent(context,page){
      return context.commit('isCurrent',page)
    },
  },
  modules: {
  }
})
