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
    backPage:0,
    currentUser:0,
    Token: localStorage.getItem('token') || '',
    userId: localStorage.getItem('userId') || '',
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
      console.log(data)
      state.Token = data.token
      state.userId = data.userId
    },
    DELETE_TOKEN(state) {
      state.Token = ''
      state.userId = ''
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
        if (res.data.data) {
          localStorage.setItem('token', res.data.data.token)
          localStorage.setItem('userId', res.data.data.userId)
          context.commit('UPDATE_TOKEN', res.data.data)
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
      localStorage.removeItem('userId')
      context.commit('DELETE_TOKEN')
    },
    signup(context, credentials) {
      axios.post('user/join/', credentials)
      .then(() => {
          context.dispatch('login', credentials)
          alert('회원가입이 완료되었습니다.')
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
