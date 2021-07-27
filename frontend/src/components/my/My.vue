<template>
  <div>
    <div class="top">
      <router-link :to="{name:'Home'}" >
        <img class="back_icon" src="@/assets/back.png" alt="back_icon">
      </router-link>
      <img class="logo" src="@/assets/jubging.png" alt="logo" width="100px;">
      <img class="hamburger" src="@/assets/hamburger.png" alt="burger" style="margin-right:10px">
    </div>
    <!-- 유저 정보 -->
    <div class="my_info">
      <div class="profile_img">
        <img class="profile" src="@/assets/sample.png">
      </div>
      <span>usernickname</span>
      <!--게시글 팔로워 팔로잉-->
      <div class="user_active_cnt">
        <div class="lcbox" >
          <span>게시물</span>
          <span style="text-align:center">22</span>
        </div>
        <div class="lcbox">
          <span>팔로워</span>
          <span style="text-align:center">33</span>
        </div>
        <div class="lcbox">
          <span>팔로잉</span>
          <span style="text-align:center">11</span>
        </div>
      </div>
    </div>
    <!-- 뱃지 리스트 -->
    <div class="badge_box">
      <carousel-3d class="badge_carousel"
        :disable3d="true" :width="50" :height="50"
        :display="5" :space="70" :controlsVisible="true" :controlsPrevHtml="ajwl"
      >
        <slide v-for="(photo,i) in photos" :index="i" :key="i">
          <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
            <img class="badge_img" :data-index="index" :class="{current: isCurrent, onLeft:(leftIndex>=0), onRight:(rightIndex>=0)}" :src="photo.url">
          </template>
        </slide>
      </carousel-3d>
    </div>
    <!-- 나의 게시글 -->
    <div class="photo_list">
      <div class="photo-grid">
        <router-link :to="{name:'Detail'}" >
          <img class="photo-img" v-for="photo in photos" :key="photo.id" :src="photo.url">
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import "@/assets/css/topbar.css"; // 없어도 되는 이유?
import {Carousel3d,Slide} from 'vue-carousel-3d'
export default {
  name:'My',
  components:{
    Carousel3d,
    Slide
  },
  data() {
      return {
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
  }
}
</script>

<style scoped>
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
  height: 365px;
  width: 412px;
}
.photo-grid {
  column-count: 3;
  column-gap:0;
}
.photo-img {
  width: 135px;
  height: 135px;
}
</style>