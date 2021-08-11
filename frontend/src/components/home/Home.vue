<template>
  <div>
    <div class="main_top">
      <img src="@/assets/logo/textlogo.png" alt="logo" class="text_logo">
      <div class="search_alarm_follow">
      <font-awesome-icon icon="search" style="transform:scale(1.4); margin:3px 5px 0px 0px;" @click="toSearch"/>
      <font-awesome-icon :icon="['fas','bell']" style="margin: 3px 0px 0px 13px; transform:scale(1.4);" @click="isModal=true"/>
      <div class="red_dot"></div>
        <label class="switch">
          <input type="checkbox" @click="followToggle()">
          <span class="slider round"></span>
        </label>
      </div>
    </div>
    <AlarmModal v-if="isModal" @close-modal="isModal=false">
    </AlarmModal>
    <div class="photo_list">
      <div class="photo-grid" v-show="this.toggle">
        <div class="today-jubging" v-show="this.toggle">오늘의 줍깅 : {{this.total}}</div>
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
      </div>
    </div>
  </div>
</template>

<script>
import PhotoList from '@/components/home/PhotoList.vue'
import FollowList from '@/components/home/FollowList.vue'
import AlarmModal from '@/components/home/AlarmModal.vue'
import axios from 'axios'

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
      total: 0,
      BASEURL: 'http://localhost:8080',
    }
  },
  computed:{
    ...mapState([
      'articles',
      'followarticles',
    ]),
  },
  created(){
    console.log("현재 로그인 유저",this.$store.state.userId)
    this.$store.state.backPage = 0
    this.allArticles()
    this.followArticles()
    this.todayJubging()
  },
  methods:{
    followToggle(){
      this.toggle = !this.toggle
    },
    toSearch(){
      this.$router.push({name:'Search'})
    },
    allArticles(){
      let URL = `${this.BASEURL}/article/list`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.$store.dispatch('loadArticles',res.data.data)           
        })
        .catch((e) => {
          console.error(e);
        })
    },
    followArticles(){
    let URL = `${this.BASEURL}/follow/findarticle/${this.$store.state.userId}`
    let params = {
      method: 'get',
      url: URL,
    }
    axios(params)
      .then((res) => {
        this.$store.dispatch('loadFollowArticles',res.data.data)    
      })
      .catch((e) => {
        console.error(e);
      })
    },
    todayJubging(){
      let URL = `${this.BASEURL}/jubginglog/total`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.total = res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
    },
  },
}
</script>

<style lang="scss" scoped>
@import "@/components/home/Home.scss";
</style>