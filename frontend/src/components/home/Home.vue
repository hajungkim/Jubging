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
          <img src="@/assets/iconlogo4.png" style="opacity:0.2; width:260px;">
          <div style="color:lightgrey">다른 유저를 팔로우 해보세요!</div>
        </div>
      </div>
      <div class="notification-container">
        <transition name="fade">
          <div class="notification" v-for="(value,idx) in successList" :key="idx" v-show="this.isNotice">
            <p>✨ {{value}} 미션 달성!</p>
          </div>
        </transition>
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
      toggle: true,
      isModal: false,
      isAlram: false,
      isfollow: false,
      total: 0,
      mission: [],
      missionSuccess: [],
      isNotice: false,
      successList: [],
      isSuccess: {
        userId: this.$store.state.userId,
        distanceBronze: false,
        distanceSilver: false,
        distanceGold: false,
        plasticBronze: false,
        plasticSilver: false,
        plasticGold: false,
        canBronze: false,
        canSilver: false,
        canGold: false
      }
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
    //this.jubgingUser()
    // socket 연결
    this.connect()
    this.showNotification()
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
    // socket
    connect() {
      const serverURL = "http://localhost:8080/socket"
      // const serverURL = "https://i5b207.p.ssafy.io/api/socket"
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
    },
    showNotification() {
      HTTP.get(`mission/${this.$store.state.userId}`)
      .then((res) => {
        this.mission = res.data.data
      })
      .catch((e) => {
        console.error(e);
      })
      HTTP.get(`missionsuccess/${this.$store.state.userId}`)
      .then((res) => {
        this.missionSuccess = res.data.data
      })
      .catch((e) => {
        console.error(e);
      })
      console.log(this.mission)
      if (this.mission.distance >= 10 && this.missionSuccess.distanceBronze === 0) {
        this.successList.push("거리 동뱃지")
        this.isSuccess.distanceBronze = true;
      } else if (this.mission.distance >= 50 && this.missionSuccess.distanceSilver === 0) {
        this.successList.push("거리 은뱃지")
        this.isSuccess.distanceSilver = true;
      } else if (this.mission.distance >= 100 && this.missionSuccess.distanceSilver === 0) {
        this.successList.push("거리 금뱃지")
        this.isSuccess.distanceGold = true;
      }
      if (this.mission.plastic >= 3 && this.missionSuccess.plasticBronze === 0) {
        this.successList.push("플라스틱 동뱃지")
        this.isSuccess.plasticBronze = 1;
      } else if (this.mission.plastic >= 10 && this.missionSuccess.plasticSilver === 0) {
        this.successList.push("플라스틱 은뱃지")
        this.isSuccess.plasticSilver = 1;
      } else if (this.mission.plastic >= 20 && this.missionSuccess.plasticGold === 0) {
        this.successList.push("플라스틱 금뱃지")
        this.isSuccess.plasticGold = 1;
      }
      if (this.mission.can >= 3 && this.missionSuccess.canBronze === 0) {
        this.successList.push("캔 동뱃지")
        this.isSuccess.canBronze = 1;
      } else if (this.mission.can >= 10 && this.missionSuccess.canSilver === 0) {
        this.successList.push("캔 은뱃지")
        this.isSuccess.canSilver = 1;
      } else if (this.mission.can >= 20 && this.missionSuccess.canGold === 0) {
        this.successList.push("캔 금뱃지")
        this.isSuccess.canGold = 1;
      }

      this.isNotice = true
      setTimeout(() => {
        this.isNotice = false
      }, 1000)

      HTTP.put(`missionsuccess`, this.isSuccess)
      .then(() => {
      })
      .catch((e) => {
        console.error(e);
      })
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/components/home/Home.scss";
</style>
