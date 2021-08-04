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
    articles:[],
    followarticles:[],
    selectArticle:[],
    Token: localStorage.getItem('token') || '',
    userId: null,
  },
  mutations: {
    isCurrent(state,page){
      state.currentPage=page
    },

    UPDATE_TOKEN(state, data) {
      state.Token = data.token
      state.userId = data.userId
    },
    DELETE_TOKEN(state) {
      state.Token = ''
      state.userId = null
    },
    loadArticles(state,data){
      state.articles=data;
    },
    loadfollowArticles(state,data){
      state.followarticles=data;
    }
  },
  actions: {
    isCurrent(context,page){
      context.commit('isCurrent',page)
    },

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
    loadArticles(context,data){
      return context.commit('loadArticles',data)
    },
    loadFollowArticles(context,data){
      return context.commit('loadfollowArticles',data)
    }
  },
  modules: {
  }
})
