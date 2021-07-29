<template>
  <div style="height:781px">
    <div class="top">
      <router-link :to="{name:'Home'}" >
        <font-awesome-icon icon="angle-left" class="fa-2x back_icon"/>
      </router-link>
      <img class="logo" src="@/assets/textlogo.png" alt="logo" width="100px;">
    </div>
    <div class="article_content">
        <!--유저 정보-->
        <div class="user_info">
          <div class="profile_img">
            <img class="profile" src="@/assets/sample.png">
          </div>
          <span style="font-weight:bold;">usernickname</span>
        </div>
        <!--사진들-->
        <carousel-3d :width="300" :height="300">
          <slide v-for="(photo,i) in photos" :index="i" :key="i">
            <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
              <img class="article_img" :data-index="index" :class="{current: isCurrent, onLeft:(leftIndex>=0), onRight:(rightIndex>=0)}" :src="photo.url">
            </template>
          </slide>
        </carousel-3d>
        <!--게시글 내용-->
        <div class="content_box">
          오늘 줍깅을 했다. 첫게시글입니다욧
        </div>
        <!--좋아요 댓글-->
        <div class="like_comment_container">
          <div class="lcbox" >
            <font-awesome-icon :icon="['far','heart']"/><span style="margin-left:5px;">10</span>
          </div>
          <div class="lcbox" @click="open">
            <font-awesome-icon :icon="['far','comment-dots']"/><span style="margin-left:5px;">8</span>
          </div>
        </div>
    </div>
    <vue-bottom-sheet ref="myBottomSheet" max-height="600px" max-width="412px" >
      <ul>
        <li class="comment_container">
          <img class="comment_profile" src="@/assets/sample.png">
          <div>
            <span style="font-weight:bold;">user_name</span>
            <div class="comment_contents">조깅을 하면서 쓰레기를 줍는 운동.ddasdadddasdasdasdddasasdasddassadasdasdadsddasdas</div>
          </div>
        </li>
      </ul>
      <div class="input_container">
        <input type="text" class="comment_input" placeholder="댓글을 입력하세요.">
        <font-awesome-icon :icon="['fas','comment']" class="comment_icon"/>
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
/* 상단바 */
.back_icon{
  margin-left:15px;
}
.logo{
  margin-right:155px;
  transform: scale(1.5);
}
/* 유저정보 및 게시글 */
.article_content{
  display:flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.user_info{
  display: flex;
  align-items: center;
  margin: 9vh 10px 2.5vh 0;
}
.profile_img{
  width: 50px;
  height: 50px;
  border-radius: 70%;
  overflow: hidden;
  margin-right:10px;
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
  word-break:break-all;
}
/* 좋아요 댓글 */
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
  display: flex;
  margin:0px 20px 10px 20px;
}
.input_container{
  display: flex;
  justify-content: center;
}
.comment_input{
  width:330px;
  height:30px;
  background-color: gainsboro;
  border:1px solid;
  border-radius: 15px 15px 15px 15px;
  padding:10px;
}
.comment_icon{
  transform: scale(1.5);
  margin : 7px 0px 0px 10px;
}
.comment_profile{
  width: 35px;
  height: 35px;
  border-radius: 50%;
  overflow: hidden;
  margin:5px 10px 0px 0px;
}
.comment_contents{
  width: 300px;
  font-size:15px;
  word-break:break-all;
}
</style>