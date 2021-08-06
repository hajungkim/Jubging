<template>
  <div>
    <div class="top">
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo">
      <font-awesome-icon icon="bars" class="hamburger" @click="open"/>
    </div>
    <!-- 유저 정보 -->
    <div class="my_info">
      <div class="profile_img">
        <img class="profile" :src="user.profilePath">
      </div>
      <span>{{user.nickname}}</span>
      <!--게시글 팔로워 팔로잉-->
      <div class="user_active_cnt">
        <div class="lcbox" >
          <span>게시물</span>
          <span style="text-align:center">{{user.articleCount}}</span>
        </div>
        <div class="lcbox">
          <span>팔로워</span>
          <span style="text-align:center">{{user.follower}}</span>
        </div>
        <div class="lcbox">
          <span>팔로잉</span>
          <span style="text-align:center">{{user.following}}</span>
        </div>
      </div>
      <!-- <img src="@/assets/badge/can/sample4.png"> -->
    </div>
    <!-- 뱃지 리스트 -->
    <div class="badge_box">
      <carousel-3d class="badge_carousel"
        :disable3d="true" :width="50" :height="50" dir="ltr" :clickable="false"
        :display="5" :space="70" :controlsVisible="true"
      >
        <slide v-for="(photo,i) in photos" :index="i" :key="i">
          <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
            <img class="badge_img" :src="photo.url" :data-index="index" :class="{current: isCurrent, onLeft:(leftIndex>=0), onRight:(rightIndex>=0)}" >
          </template>
        </slide>
      </carousel-3d>
    </div>
    <!-- 나의 게시글 -->
    <div class="photo_list">
      <div class="photo-grid">
        <router-link :to="{name:'Detail'}">
          <img @click="onClick(article)" class="photo-img" v-for="(article,idx) in articles" :key="idx" :src="article.photosPath">
        </router-link>
      </div>
    </div>
    <vue-bottom-sheet ref="myBottomSheet" max-height="370px" max-width="412px" >
      <div>
        <router-link :to="{name:'#'}" class="default-link">
          <div class="bt_common">
            <font-awesome-icon icon="user" class="fa-2x icon" style="margin-right:17px"/>
            <span>회원정보변경</span>
          </div>
        </router-link>
        <router-link :to="{name:'#'}" class="default-link">
          <div class="bt_common likelog">
            <font-awesome-icon icon="heart" class="fa-2x icon" style="margin-right:15px"/>
            <span>좋아요 로그</span>
          </div>
        </router-link>
        <router-link :to="{name:'#'}" class="default-link">
          <div class="bt_common">
            <font-awesome-icon icon="running" class="fa-2x icon" style="margin-right:19px"/>
            <span>줍깅 로그</span> 
          </div>
        </router-link>
        <router-link :to="{name:'#'}" class="default-link">
          <div class="bt_common">
            <font-awesome-icon icon="sign-out-alt" class="fa-2x icon" style="margin-right:13px"/>
            <span>로그아웃</span> 
          </div>
        </router-link>
      </div>
    </vue-bottom-sheet>
  </div>
</template>

<script>
import axios from 'axios'
import {Carousel3d,Slide} from 'vue-carousel-3d'
import  VueBottomSheet from "@webzlodimir/vue-bottom-sheet";
export default {
  name:'My',
  components:{
    Carousel3d,
    Slide,
    VueBottomSheet,
  },
  data() {
      return {
        user:[],
        articles:[],
        badges:{},
        badge_photo:[],
        photos:[
        {
          title:'0',
          url:require('@/assets/badge/can/sample4.png'),
        },
        {
          title:'1',
          url:'http://placehold.it/139x139',
        },
        {
          title:'2',
          url:'http://placehold.it/139x139',
        },
        {
          title:'3',
          url:'http://placehold.it/139x139',
        },
        {
          title:'4',
          url:'http://placehold.it/139x139',
        },
        // {
        //   title:'5',
        //   url:'http://placehold.it/139x139',
        // },
        // {
        //   title:'6',
        //   url:'http://placehold.it/139x139',
        // },
        // {
        //   title:'7',
        //   url:'http://placehold.it/139x139',
        // },
      ]
    }
  },
  methods: {
    open() {
      this.$refs.myBottomSheet.open();
    },
    close() {
      this.$refs.myBottomSheet.close();
    },
    getInfo(){
      let URL = `http://localhost:8080/user/${this.loginUser}`
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res)=>{
          this.user=res.data.data
        })
        .catch((e)=>{
          console.error(e);
        })
    },
    getBadge(){
      let URL = `http://localhost:8080/mission/${this.loginUser}`
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res)=>{
          this.badges=res.data.data
          this.getBadgeImg()
        })
        .catch((e)=>{
          console.error(e);
        })
    },
    getArticle(){
      let URL = `http://localhost:8080/article/list/${this.loginUser}`
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res)=>{
          this.articles=res.data.data
        })
        .catch((e)=>{
          console.error(e);
        })
    },
    getBadgeImg(){
      const word = 'can'
      const medal = 'sample4.png'
      this.badge_photo.push({url:require(`@/assets/badge/${word}/${medal}`)})
      this.badge_photo.push({url:require(`@/assets/badge/${word}/${medal}`)})
      console.log(this.badge_photo)
      console.log(this.photos)
      // for(const key in this.badges){
      //   if(key ==='canCnt' || key ==='plasticCnt' || key ==='vinylCnt' || key ==='bottleCnt' ||
      //    key ==='metal_cnt' || key === 'styroformCnt' || key === 'paperCnt' || key ==='trashCnt'){
      //      if(2<this.badges[key] && this.badges[key]<10){
      //        this.badge_photo.push('@/assets/badge/textlogo.png')
      //      }
      //    }
      // }
    },
    onClick(article){
      this.$store.state.selectArticle=article
      this.$store.state.backPage=1
      this.$router.push({name:'Detail'})
    }
  },
  created(){
    this.getInfo()
    this.getBadge()
    this.getArticle()
},
  computed:{
    loginUser(){
      return this.$store.state.userId
    },
  }
}
</script>

<style scoped>
.top{
    display: flex;
    align-items: center;
    height: 50px;
}
.hamburger{
  margin-right:10px;
}
.logo{
  display: block;
  margin:0px auto;
  width: 100px;
  transform:scale(1.5);
  padding-left:20px;
}
/* 유저 정보 */
.my_info{
  display:flex;
  flex-direction: column;
  justify-content: center;
  align-items:center;
}
.profile_img{
  display: flex;
  align-items: center;
  width: 80px;
  height: 80px;
  border-radius: 70%;
  overflow: hidden;
  margin-top:3vh;
  margin-bottom:1vh;
}
.profile{
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.user_active_cnt{
  display: flex;
  width: 350px;
  justify-content: space-around;
  margin-top:3vh;
}
.lcbox{
  display: flex;
  flex-direction: column;
}
/* 뱃지부분 */
.badge_box{
  margin-top: 3vh;
  margin-bottom:3vh;

}
.badge_img{
  height: 50px;
}
.badge_carousel{
  margin: 0;
}

/* 피드 부분 */
.photo_list{
  overflow: auto;
  height: 365px;
  width: 412px;
}
.photo-grid {
  display:flex;
  /* height: auto; */
}
.photo-img {
  width: 135px;
  height: 135px;
  margin-left:2px;
}
/* 바텀시트 */
.bt_common{
  display: flex;
  margin-left:40px;
  align-items: center;
  height: 60px;
}
.likelog{
  margin-left:38px;
}
.icon{
  margin-right:10px;
}
/* 라우터 링크 색 변경x */
.default-link{
  color:black;
  text-decoration:none;
}
</style>