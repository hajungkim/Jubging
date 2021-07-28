<template>
  <div style="height:781px">
    <div class="top">
      <router-link :to="{name:'Home'}" >
        <img class="back_icon" src="@/assets/back.png" alt="">
      </router-link>
      <img class="logo" src="@/assets/jubging.png" alt="logo" width="100px;">
    </div>
    <div class="article_content">
        <!--유저 정보-->
        <div class="user_info">
          <div class="profile_img">
            <img class="profile" src="@/assets/sample.png">
          </div>
          <span>usernickname</span>
        </div>
        <!--사진들-->
        <carousel-3d :width="300" :height="300" :bias="right">
          <slide v-for="(photo,i) in photos" :index="i" :key="i">
            <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
              <img class="article_img" :data-index="index" :class="{current: isCurrent, onLeft:(leftIndex>=0), onRight:(rightIndex>=0)}" :src="photo.url">
            </template>
          </slide>
        </carousel-3d>
        <!--게시글 내용-->
        <div class="content_box">
          오늘 줍깅을 했다. 첫게시글~_ ㄷㄹ엄개ddasddad허ㅑㅑㅐ허매허
        </div>
        <!--좋아요 댓글-->
        <div class="like_comment_container">
          <div class="lcbox" >
            <img src="@/assets/heart_empty.png" alt="" style="width:20px; margin-right:5px;"><span style="margin-bottom: 3px;">10</span>
          </div>
          <div class="lcbox" @click="open">
            <img src="@/assets/comments.png" alt="" style="width:20px; margin-right:5px;"> <span style="margin-bottom: 3px;">8</span>
          </div>
        </div>
    </div>
    <vue-bottom-sheet ref="myBottomSheet" max-height="600px" max-width="412px" >
      <div class="comment_container">
        dsadas
      </div>
    </vue-bottom-sheet>
  </div>
</template>

<script>
import "@/assets/css/topbar.css";
import {Carousel3d,Slide} from 'vue-carousel-3d'
import  VueBottomSheet from "@webzlodimir/vue-bottom-sheet";
export default {
  name:'Detail',
  components:{
    Carousel3d,
    Slide,
    VueBottomSheet,
  },
  data(){
    return{
      photos:[
        {
          title:'0',
          url:'http://placehold.it/165x165',
        },
        {
          title:'1',
          url:'http://placehold.it/165x165',
        },
        // {
        //   title:'2',
        //   url:'http://placehold.it/185x185',
        // },
        // {
        //   title:'3',
        //   url:'http://placehold.it/185x185',
        // },
        // {
        //   title:'4',
        //   url:'http://placehold.it/185x185',
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
    }
  },
}
</script>

<style scoped>
.logo{
  margin-right:155px;
}
.article_content{
  display:flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.user_info{
  display: flex;
  align-items: center;
  margin-top: 9vh;
  margin-bottom: 2.5vh;
}
.profile_img{
  width: 50px;
  height: 50px;
  border-radius: 70%;
  overflow: hidden;
  margin-right:20px;
}
.profile{
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.article_img{
  width: 300px;
  height: 300px;
}
.content_box{
  margin-top:2.5vh;
  width: 300px;
  margin-bottom:2.5vh;
  text-align: center;
}
.like_comment_container{
  display: flex;
}
.lcbox{
  display: flex;
  align-items: center;
  padding: 20px;
}
/* 댓글 바텀시트 */
.comment_container{
  height: 700px;
}
</style>