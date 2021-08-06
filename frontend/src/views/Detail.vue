<template>
  <div style="height:781px">
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="onClick()"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
      <font-awesome-icon v-if="article.userId===parseInt(loginUser)" icon="trash" class="delete_button" @click="onDelete(article)"/>
    </div>
    <div class="article_content">
        <!--유저 정보-->
        <div class="user_info"  @click="moveProfile(article.userId)">
          <div class="profile_img">
            <img class="profile" :src="article.profilePath">
          </div>
          <span style="font-weight:bold;">{{article.nickname}}</span>
        </div>
        <!--사진들-->
        <carousel-3d :width="300" :height="300" bias="right">
          <slide v-for="(photo,i) in photos" :index="i" :key="i"> <!-- photos 대신 article.photosPath 다른컴포넌트는 [0]만! -->
            <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
              <img class="article_img" :data-index="index" :class="{current: isCurrent, onLeft:(leftIndex>=0), onRight:(rightIndex>=0)}" :src="photo.url">
            </template>
          </slide>
        </carousel-3d>
        <!--게시글 내용-->
        <div class="content_box">
          {{article.content}}
        </div>
        <!--좋아요 댓글-->
        <div class="like_comment_container">
          <div class="lcbox" >
            <font-awesome-icon :icon="['far','heart']"/><span style="margin-left:5px;">{{article.likeCnt}}</span>
          </div>
          <div class="lcbox" @click="open">
            <font-awesome-icon :icon="['far','comment-dots']"/><span style="margin-left:5px;">{{article.commentCnt}}</span>
          </div>
        </div>
    </div>
    <vue-bottom-sheet ref="myBottomSheet" max-height="600px" max-width="412px" >
      <ul style="padding:0px;">
        <li class="comment_container"
          v-for="(comment,idx) in comments"
          :key="idx"
        >
          <img class="comment_profile" :src="comment.profilePath">
          <div>
            <span style="font-weight:bold;">{{comment.nickname}}</span>
            <div class="comment_contents">{{comment.commentContent}}</div>
          </div>
          <div class="btn_div" v-if="comment.userId===loginUser">
            <button @click="commentDelete(comment)" class="comment_delete_button">X</button>
          </div>
        </li>
      </ul>
      <div class="input_container">
        <input
          type="text"
          class="comment_input"
          v-model="comment"
          @keyup.enter="commentSubmit"
          placeholder="댓글을 입력하세요.">
        <font-awesome-icon @click="commentSubmit" :icon="['fas','comment']" class="comment_icon"/>
      </div>
    </vue-bottom-sheet>
  </div>
</template>

<script>
import axios from 'axios'
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
      photos: [
        {
          title:'0',
          url:'http://placehold.it/165x165',
        },
        {
          title:'1',
          url:require('@/assets/badge/can/sample4.png'),
        },
      ],
      comments: [],
      comment: '',
    }
  },
  computed:{
    article(){
      return this.$store.state.selectArticle
    },
    loginUser(){
      return this.$store.state.userId
    }
  },
  created(){
    this.getComment()
  },
  methods: {
    open(){
      this.$refs.myBottomSheet.open();
    },
    close(){
      this.$refs.myBottomSheet.close();
    },
    getComment(){
      let URL = `http://localhost:8080/comment/${this.article.articleId}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.comments = res.data.data          
        })
        .catch((e) => {
          console.error(e);
        })
    },
    commentSubmit(){
      const URL = 'http://localhost:8080/comment/'
      const data = {
        articleId: this.article.articleId,
        commentContent: this.comment,
        userId: this.loginUser,
      }
      const params = {
        method: 'post',
        url: URL,
        data: data
      }
      axios(params)
        .then(() => {
          this.comment = ''
          this.getComment()
        })
        .catch((e) => {
          console.error(e);
        })
    },
    commentDelete(comment){
      const URL = `http://localhost:8080/comment/${comment.commentId}?userId=${comment.userId}`
      const data = {
        comment_id: comment.commentId,
        userId: comment.userId
      }
      const params = {
        method: 'delete',
        url: URL,
        data: data
      }
      axios(params)
        .then(() => {
          this.getComment()
        })
        .catch((e) => {
          console.error(e);
        })
    },
    onClick(){
      if(this.$store.state.backPage == 1)this.$router.push({name:'My'})
      else this.$router.push({name:'Home'})
    },
    onDelete(article){
      const URL = `http://localhost:8080/article?articleId=${article.articleId}&userId=${article.userId}`
      const data = {
        articleId: article.articleId,
        userId: article.userId
      }
      const params = {
        method: 'delete',
        url: URL,
        data: data
      }
      axios(params)
        .then(() => {
          this.$router.push({name:'My'})
        })
        .catch((e) => {
          console.error(e);
        })
    },
    moveProfile(userId){
      if(userId === parseInt(this.loginUser)){
        this.$router.push({name:'My'})
      }
      else{
        this.$store.state.currentUser = userId
        this.$router.push({name:'Userprofile'})
      }
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/views/Detail.scss";
</style>