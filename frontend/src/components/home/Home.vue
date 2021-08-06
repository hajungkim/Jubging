<template>
  <div>
    <div class="main_top">
      <img src="@/assets/logo/textlogo.png" alt="logo" class="text_logo">
      <div class="search_alarm_follow">
      <font-awesome-icon icon="search" style="transform:scale(1.4); margin:3px 5px 0px 0px;" @click="toSearch"/>
      <font-awesome-icon :icon="['fas','bell']" style="margin: 3px 15px 0px 13px; transform:scale(1.5);" @click="isModal=true"/>
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
      toggle:true,
      isModal:false,
      total:0,
    }
  },
  methods:{
    followToggle(){
      this.toggle = !this.toggle
    },
    toSearch(){
      this.$router.push({name:'Search'})
    },
    allArticles(){
      let URL = 'http://localhost:8080/article/list'
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res)=>{
          this.$store.dispatch('loadArticles',res.data.data)           
        })
        .catch((e)=>{
          console.error(e);
        })
    },
    followArticles(){
    let URL = `http://localhost:8080/follow/findarticle/${this.$store.state.userId}`
    let params={
      method:'get',
      url:URL,
    }
    axios(params)
      .then((res)=>{
        this.$store.dispatch('loadFollowArticles',res.data.data)    
      })
      .catch((e)=>{
        console.error(e);
      })
    },
    todayJubging(){
      let URL = 'http://localhost:8080/jubginglog/total'
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res)=>{
          this.total=res.data.data
        })
        .catch((e)=>{
          console.error(e);
        })
    },
  },
  computed:{
    ...mapState([
      'articles',
      'followarticles',
    ])
  },
  created(){
    console.log("현재 로그인 유저",this.$store.state.userId)
    this.$store.state.backPage=0
    this.allArticles()
    this.followArticles()
    this.todayJubging()
  }
}
</script>

<style scoped>
.main_top{
  display: flex;
  height: 50px;
  align-items: center;
  margin-bottom:10px;
}
.text_logo{
  width:100px;
  margin: 10px 10px 0px 35px;
  transform: scale(1.4);
}
.search_button{
  margin: 7px 0px 0px -20px;
}
.search_input{
  border:1px solid;
  width: 160px;
}
/* 오늘의 줍깅 */
.today-jubging{
  display:flex;
  justify-content: center;
  align-items: center;
}
.photo_list{
  overflow: auto;
  height: 660px;
}
.follow_photo_container{
  display: flex;
  flex-direction: column;
}
/* 스크롤바 제거 */
.photo_list::-webkit-scrollbar{
  display: none;
}

/* 팔로우 토글 */
.search_alarm_follow{
  display: flex;
  margin-left: auto;
  margin-right:10px;
  justify-content: center;
  align-items: center;
}
.switch{
  position:relative;
  display: inline-block;
  width:40px;
  height:20px;
  margin: 5px 5px 0px 0px;
}
.switch input{
  opacity: 0;
  width: 0;
  height: 0;
}
.slider{
  position: absolute;
  cursor: pointer;
  top: 0;
  left:0;
  right:0;
  bottom:0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}
.slider:before{
  position: absolute;
  content:"";
  height: 15px;
  width: 15px;
  left:4px;
  bottom:2px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}
input:checked + .slider{
  background-color: #2196F3;
}
input:focus + .slider{
  box-shadow: 0 0 1px #2196F3;
}
input:checked +.slider:before{
  -webkit-transform: translateX(17px);
  -ms-transform: translateX(17px);
  transform: translateX(17px);
}
.slider.round{
  border-radius: 34px;
}
.slider.round:before{
  border-radius: 50%;
}
.today-jubging {
  width: 185px;
  height: 90px;
}
.photo-grid {
  column-count: 2;
  column-gap: 0px;
  margin-left: 14px;
}
</style>