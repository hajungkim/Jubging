import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router'
import axios from 'axios'
import { HTTP } from '@/util/http-common';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

// axios.defaults.baseURL = 'http://localhost:8080/'

axios.interceptors.request.use(config => {
  const Token = localStorage.getItem('token')
  config.headers.common['Authorization'] = Token ? `Bearer ${Token}` : ''
  return config
})

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    Token: localStorage.getItem('token') || '',
    userId: localStorage.getItem('userId') || '',
    userInfo: [],
    userArticles: [],
    userFollowers: [],
    userFollowings: [],
    profileUserFollowers: [],
    profileUserFollowings: [],
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
    jubgingInfo: {tiem: "0:00:00", dist: "0.00"},
    jubgingOption: {},
    address: "",
    event: {},
  },
  mutations: {
    // 기타
    IS_CURRENT(state, page) {
      state.currentPage = page
    },

    // 홈
    LOAD_ARTICLES(state, data) {
      let half = parseInt(data.length / 2)
      let articles = new Array(data.length)
      let k
      for (let i=0; i<data.length; i++) {
        k = parseInt(i/2)
        if (i % 2) {
          articles[k] = data[i]
        } else {
          articles[k+half] = data[i]
        }
      }
      // state.articles = data;
      state.articles = articles;
    },
    LOAD_FOLLOW_ATICLES(state, data) {
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
  
    // 줍깅
    JUBGING_ON(state, data) {
      state.isJubgingOn = data
    },
    SET_JUBGING_INFO(state, data) {
      state.jubgingInfo = data
    },
    SET_JUBGING_OPTION(state, data) {
      state.jubgingOption = data
    },
    SET_ADDRESS(state, data) {
      state.address = data
    },
    ISSELECTEVENT(state, data) {
      state.event = data
    },
    // 랭킹
    GET_RANKER(state, rankers) { 
      state.rankers = rankers
    },

    //마이
    GET_ARTICLE(state, userArticles) {
      state.userArticles = userArticles
      if (state.userArticles){
        state.userArticles.reverse()
      }
    },
    GET_FOLLOWER(state, userFollowers) {
      state.userFollowers = userFollowers
    },
    GET_FOLLOWING(state, userFollowings) {
      state.userFollowings = userFollowings
    },
    GET_PROFILE_FOLLOWER(state, profileUserFollowers) {
      state.profileUserFollowers = profileUserFollowers
    },
    GET_PROFILE_FOLLOWING(state, profileUserFollowings) {
      state.profileUserFollowings = profileUserFollowings
    },
    CHANGE_CURRENT_USER(state, currentUser) {
      state.currentUser = currentUser
    },

    // 유저
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
    SET_SOCKET(state, data) {
      state.stompClient = data
    }
  },
  actions: {
    // 기타
    isCurrent(context, page){
      context.commit('IS_CURRENT', page)
    },
    isSelectArticle(context,article){
      context.commit('ISSELECTARTICLE', article)
    },
    isSelectEvent(context,event){
      context.commit('ISSELECTEVENT', event)
    },
    // 홈
    loadArticles(context,data){
      return context.commit('LOAD_ARTICLES', data)
    },
    
    loadFollowArticles(context,data){
      return context.commit('LOAD_FOLLOW_ATICLES',data)
    },

    // 미션
    getMission(context) {
      HTTP.get(`mission/${this.state.userId}`)
      .then(res => {
        context.commit('GET_MISSION', res.data.data)
      })
      .catch(err => {
        console.error(err)
      })
    },

    // 줍깅
    setJubgingInfo(context, data) {
      return context.commit("SET_JUBGING_INFO", data)
    },
    jubgingOn(context, data) {
      return context.commit("JUBGING_ON", data)
    },
    setJubgingOption(context, data) {
      return context.commit("SET_JUBGING_OPTION", data)
    },
    setAddress(context, data) {
      return context.commit("SET_ADDRESS", data)
    },
    // 랭킹
    getRanker(context) {
      HTTP.get('user/score')
      .then(res => {
        context.commit('GET_RANKER', res.data.data)
      })
      .catch(err => {
        console.error(err)
      })
    },
    // 마이
    getArticle(context, userId) {
      HTTP.get(`article/list/${userId}`)
      .then(res => {
        context.commit('GET_ARTICLE', res.data.data)
      })
      .catch(err => {
        console.error(err)
      })
    },
    getFollower(context, userId){
      HTTP.get(`follow/findfollower/${userId}`)
      .then((res) => {
        context.commit('GET_FOLLOWER', res.data.data)
      })
      .catch((e) => {
        console.error(e);
      })
    },
    getFollowing(context, userId){
      HTTP.get(`follow/findfollow/${userId}`)
      .then((res) => {
        context.commit('GET_FOLLOWING', res.data.data)
      })
      .catch((e) => {
        console.error(e);
      })
    },
    getProfileFollower(context, userId){
      HTTP.get(`follow/findfollower/${userId}`)
      .then((res) => {
        context.commit('GET_PROFILE_FOLLOWER', res.data.data)
      })
      .catch((e) => {
        console.error(e);
      })
    },
    getProfileFollowing(context, userId){
      HTTP.get(`follow/findfollow/${userId}`)
      .then((res) => {
        context.commit('GET_PROFILE_FOLLOWING', res.data.data)
      })
      .catch((e) => {
        console.error(e);
      })
    },
    changeCurrentUser(context, userId) {
      context.commit('CHANGE_CURRENT_USER', userId)
    },

    // 유저
    login(context, credentials) {
      HTTP.post('user/login', credentials)
      .then(res => {
        if (res.data.data) {
          localStorage.setItem('token', res.data.data.token)
          localStorage.setItem('userId', res.data.data.userId)
          context.commit('UPDATE_TOKEN', res.data.data)
          router.push({ name: 'Home' })

          // const serverURL = "http://localhost:8080/socket"
          const serverURL = "https://i5b207.p.ssafy.io/api/socket"
          let socket = new SockJS(serverURL);
          let stompClient = Stomp.over(socket);
          context.commit('SET_SOCKET', stompClient)
          stompClient.connect(
            {},
            frame => {
              this.connected = true;
              console.log('소켓 연결 성공', frame);
              stompClient.subscribe("/sub/" + localStorage.getItem('userId'), res => {
                this.isAlram = true;
                alert(res.body,'@@@@@@@@@@')
              });
            },
            error => {
              console.log('소켓 연결 실패', error);
              this.connected = false;
            }
          );   
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
      HTTP.post('user/join/', credentials)
      .then(() => {
        context.dispatch('login', credentials)
        alert('회원가입이 완료되었습니다.')
      })
      .catch(err => {
        console.error(err)
      })
    },
    getUserInfo(context, userId) {
      HTTP.get(`user/${userId}`)
      .then(res => {
        context.commit('GET_USER_INFO', res.data.data)
      })
      .catch(err => {
        console.error(err)
      })
    },
    changeSetting(context, credentials) {
      HTTP.put(`user/${context.state.userId}`, credentials)
      .then(() => {
        context.dispatch('getUserInfo')
        alert('회원 정보가 수정되었습니다.')
      })
      .catch(err => {
        console.error(err)
      })
    },
  },
  modules: {
  }
})