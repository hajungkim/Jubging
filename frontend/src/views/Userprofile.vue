<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="onClick()"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo">
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
    </div>
    <!-- 뱃지 리스트 -->
    <div class="badge_box">
      <carousel-3d class="badge_carousel"
        :disable3d="true" :width="50" :height="50"
        :display="5" :space="70" :controlsVisible="true"
      >
        <slide v-for="(photo,i) in photos" :index="i" :key="i">
          <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
            <img class="badge_img" :data-index="index" :class="{current: isCurrent, onLeft:(leftIndex>=0), onRight:(rightIndex>=0)}" :src="photo.url">
          </template>
        </slide>
      </carousel-3d>
    </div>
    <!-- 유저 게시글 -->
    <div class="photo_list">
      <div class="photo-grid">
        <router-link :to="{name:'Detail'}">
          <img @click="onClick(article)" class="photo-img" v-for="(article,idx) in articles" :key="idx" :src="article.photosPath">
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import {Carousel3d,Slide} from 'vue-carousel-3d'
export default {
  name:'Userprofile',
  components:{
    Carousel3d,
    Slide,
  },
  data() {
      return {
        user:[],
        articles:[],
        photos:[
        {
          title:'0',
          url:'http://placehold.it/139x139',
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
        {
          title:'5',
          url:'http://placehold.it/139x139',
        },
        {
          title:'6',
          url:'http://placehold.it/139x139',
        },
        // {
        //   title:'7',
        //   url:'http://placehold.it/139x139',
        // },
      ]
    }
  },
  methods: {
    getInfo(){
      let URL = `http://localhost:8080/user/${this.currentUser}`
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
      let URL = `http://localhost:8080/mission/${this.currentUser}`
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res)=>{
          console.log('뱃지갯수',res.data.data)
        })
        .catch((e)=>{
          console.error(e);
        })
    },
    getArticle(){
      let URL = `http://localhost:8080/article/list/${this.currentUser}`
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res)=>{
          console.log('유저아티클',res.data.data)
          this.articles=res.data.data
        })
        .catch((e)=>{
          console.error(e);
        })
    },
    onClick(){
      this.$router.push({name:'Detail'})
    }
  },
  created(){
    this.getInfo()
    this.getBadge()
    this.getArticle()
},
  computed:{
    currentUser(){
      return  this.$store.state.currentUser
    }
  }
}
</script>

<style scoped>
.top{
    display: flex;
    align-items: center;
    height: 50px;
}
.logo{
  margin-left:130px;
  width: 100px;
  transform:scale(1.5);
}
.back_icon{
    margin-left:16px;
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
  /* border: solid 1px; */
  margin-bottom:3vh;

}
.article_img{
  width: 50px;
  height: 50px;
}
.badge_carousel{
  margin: 0;
  
}
/* 피드 부분 */
.photo_list{
  overflow: auto;
  height: 412px;
  width: 412px;
}
.photo-grid {
  display:flex;
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