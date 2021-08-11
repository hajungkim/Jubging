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
    userInfo: [],
    currentUser: 0,
    currentPage: 0,  
    backPage: 0,  //0:home 1:my 2:search 3:userprofile 4:detail 5:logs
    searchflag: false,
    likeflag: false,
    articles: [],
    followarticles: [],
    selectArticle: [],
    missions: [],
    rankers: null,
    badgephotos: [],
    stompClient: null,
    isJubgingOn: false,
    jubgingInfo: {},
    jubgingOption: {},
  },
  mutations: {
    isCurrent(state,page){
      state.currentPage=page
    },

    // 홈
    LOAD_ARTICLES(state,data) {
      state.articles = data;
    },
    LOAD_FOLLOW_ATICLES(state,data) {
      state.followarticles = data;
    },
    ISSELECTARTICLE(state,data){
      state.selectArticle = data
      if (data.profilePath === null){
        state.selectArticle.profilePath = require("@/assets/user_default.png")
      }
    },
    // 미션
    GET_MISSION(state, missions) {
      state.missions = missions
    },
    
    GET_RANKER(state, rankers) { 
      state.rankers = rankers
    },
    // 유저 관련
    UPDATE_TOKEN(state, data) {
      state.Token = data.token
      state.userId = data.userId
    },
    DELETE_TOKEN(state) {
      state.Token = ''
      state.userId = ''
    },
    GET_USER_INFO(state, data) {
      state.userInfo = data
    },
    //마이
  },
  actions: {
    // 기타
    isCurrent(context, page){
      context.commit('IS_CURRENT', page)
    },
    isSelectArticle(context,article){
      context.commit('ISSELECTARTICLE', article)
    },
    // 홈
    loadArticles(context,data){
      return context.commit('LOAD_ARTICLES', data)
    },
    loadfollowArticles(state,data){
      state.followarticles=data;
    },
    JUBGING_ON(state, data) {
      state.isJubgingOn = data
    },
    SET_JUBGING_INFO(state, data) {
      state.jubgingInfo = data
    },
    SET_JUBGING_OPTION(state, data) {
      state.jubgingOption = data
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

    getRanker(context) {
      axios.get('user/score')
      .then(res => {
        context.commit('GET_RANKER', res.data.data)
      })
      .catch(err => {
        console.error(err)
      })
    },
    // 마이

    // 유저
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
    getUserInfo(context) {
      axios.get(`user/${context.state.userId}`)
      .then(res => {
        context.commit('GET_USER_INFO', res.data.data)
      })
      .catch(err => {
        console.error(err)
      })
    },
    changeSetting(context, credentials) {
      axios.put(`user/${context.state.userId}`, credentials)
      .then(() => {
        context.dispatch('getUserInfo')
        alert('회원 정보가 수정되었습니다.')
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
    },
    jubgingOn(context, data) {
      return context.commit('JUBGING_ON', data)
    },
    setJubgingInfo(context, data) {
      return context.commit("SET_JUBGING_INFO", data)
    },
    setJubgingOption(context, data) {
      return context.commit("SET_JUBGING_OPTION", data)
    }

  },
  modules: {
  }
})
