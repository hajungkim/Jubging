<template>
  <div>
    <div class="main_top">
      <img src="@/assets/logo/textlogo.png" alt="logo" class="text_logo">
      <div class="search_alarm_follow">
      <font-awesome-icon icon="search" style="transform:scale(1.4); margin:3px 5px 0px 0px;" @click="toSearch"/>
      <div class="alram_container" v-if="Token">
        <font-awesome-icon :icon="['fas','bell']" class="alram_button" @click="isModal=true; isAlram=false"/>
        <div v-show="isAlram" class="red_dot"></div>
      </div>
        <label class="switch" v-if="Token">
          <input type="checkbox" @click="followToggle()">
          <span class="slider round"></span>
        </label>
      </div>
    </div>
    <AlarmModal v-if="isModal" @close-modal="isModal=false">
    </AlarmModal>
    <div class="photo_list">
      <div class="photo-grid" v-show="this.toggle">
        <div class="today-jubging" v-show="this.toggle" style="height:70px;">
          <img src="@/assets/today_jubging.png" alt="" style="width:40px; height:40px;">
          <span style="margin-left:5px;">오늘의 줍깅</span>
        </div>
        <div style="display:flex; justify-content:center;">
        <h2 style="margin-top:-10px;">{{this.total}}</h2> 
        </div>
        <PhotoList
          v-for="(article,idx) in articles"
          :key="idx"
          :article="article"
          v-show="toggle"
        />
      </div>
      <div class="follow_photo_container" v-show="!this.toggle">
        <FollowList
          v-for="(followarticle,idx) in followarticles"
          :key="idx"
          :followarticle="followarticle"
          v-show="!toggle"
        />
        <div v-if="isfollow" class="emptyfollow">
          <div style="color:grey;">다른 유저를 팔로우 해보세요!</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PhotoList from '@/components/home/PhotoList.vue'
import FollowList from '@/components/home/FollowList.vue'
import AlarmModal from '@/components/home/AlarmModal.vue'
import { HTTP } from '@/util/http-common'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

import { mapState } from 'vuex'

export default {
  components:{
    PhotoList,
    AlarmModal,
    FollowList,
  },
  data(){
    return {
      cnt: 0,
      toggle: true,
      isModal: false,
      isAlram: false,
      isfollow: false,
      total: 0,
    }
  },
  computed:{
    ...mapState([
      'Token',
      'articles',
      'followarticles',
    ]),
  },
  created(){
    this.$store.state.backPage = 0
    this.allArticles()
    if (this.Token) {
      this.followArticles()
    }
    this.todayJubging()
    this.jubgingUser()
    // socket 연결
    this.connect()
  },
  methods:{
    followToggle(){
      this.toggle = !this.toggle
    },
    toSearch(){
      this.$router.push({name:'Search'})
    },
    allArticles(){
      HTTP.get(`article/list`)
        .then((res) => {
          this.$store.dispatch('loadArticles',res.data.data)           
        })
        .catch((e) => {
          console.error(e);
        })
    },
    followArticles(){
      HTTP.get(`follow/findarticle/${this.$store.state.userId}`)
      .then((res) => {
        if (res.data.data === null){
          this.isfollow = true
        }
        this.$store.dispatch('loadFollowArticles',res.data.data)    
      })
      .catch((e) => {
        console.error(e);
      })
    },
    todayJubging(){
      HTTP.get(`jubginglog/total`)
        .then((res) => {
          this.total = res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
    },
    jubgingUser(){
      HTTP.get(`user/cnt`)
      .then((res) => {
        this.cnt = res.data.data
      })
      .catch((e) => {
        console.error(e);
      })
    },
    // socket
    connect() {
      //const serverURL = "http://localhost:8080/socket"
      const serverURL = "https://i5b207.p.ssafy.io/api/socket"
      let socket = new SockJS(serverURL);
      this.$store.state.stompClient = Stomp.over(socket);
      this.$store.state.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log('소켓 연결 성공', frame);
          this.$store.state.stompClient.subscribe("/sub/" + this.$store.state.userId, res => {
            this.isAlram = true;
            alert(res.body,'@@@@@@@@@@')
          });
        },
        error => {
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/components/home/Home.scss";
</style>
