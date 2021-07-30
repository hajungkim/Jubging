import Vue from 'vue'
import Vuex from 'vuex'

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
    ]
  },
  mutations: {
    isCurrent(state,page){
      state.currentPage=page
    },
  },
  actions: {
    isCurrent(context,page){
      context.commit('isCurrent',page)
    },
  },
  modules: {
  }
})
