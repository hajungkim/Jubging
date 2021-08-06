<template>
  <div style="height:781px">
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="onClick"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
      <font-awesome-icon
        v-if="article.userId===parseInt(loginUser)"
        :icon="['fas','ellipsis-h']" 
        class="option_button"
        @click="openOption"/>
    </div>
    <div class="article_content">
        <!--유저 정보-->
        <div class="user_info"  @click="moveProfile(article.userId)">
          <div class="profile_img">
            <img class="profile" :src="article.profilePath">
          </div>
          <span style="font-weight:bold; font-size:18px;">{{article.nickname}}</span>
        </div>
        <!--사진들-->
        <carousel-3d :width="300" :height="300" bias="right">
          <slide v-for="(photo,i) in photos" :index="i" :key="i"> <!-- photos 대신 article.photosPath 다른컴포넌트는 [0]만! -->
            <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
              <img class="article_img" :data-index="index" :class="{current: isCurrent, onLeft:(leftIndex>=0),
              onRight:(rightIndex>=0)}" :src="photo.url" @dblclick="likeToggle">
            </template>
          </slide>
        </carousel-3d>
        <span class="datetext">
          {{article.createdDate.slice(0,10)}}
        </span>
        <!--게시글 내용-->
        <div class="content_box">
          {{content}}
        </div>
        <Likeusermodal v-if="isModal" @close-modal="isModal=false" :likePeoples="likePeoples">
        </Likeusermodal>
        <!--좋아요 댓글-->
        <div class="like_comment_container">
          <div class="lcbox">
            <span v-if="like">
              <font-awesome-icon  @click="likeToggle" :icon="['fas','heart']"/>
              <span style="margin-left:5px;" @click="isModal=true">{{likeCnt}}</span>
            </span>
            <span v-else>
              <font-awesome-icon  @click="likeToggle" :icon="['far','heart']"/>
              <span style="margin-left:5px;" @click="isModal=true">{{likeCnt}}</span>
            </span>
          </div>
          <div class="lcbox" @click="open">
            <font-awesome-icon :icon="['far','comment-dots']"/><span style="margin-left:5px;">{{commentCnt}}</span>
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
    <vue-bottom-sheet ref="articleOption" max-height="280px" max-width="412px" >
      <div class="option_container">
        <router-link :to="{name:'Editarticle'}" class="default-link">
          <div class="bt_common">
            <font-awesome-icon
              icon="edit"
              class="fa-2x update_icon"
            />
            <span>게시글 수정하기</span>
          </div>
        </router-link>
        <div class="bt_common" style="margin-top:15px;">
          <font-awesome-icon
            icon="trash"
            class="fa-2x delete_button"
            @click="onDelete(article)"
            style="margin-right:17px"
          />
          <span>게시글 삭제하기</span>
        </div>
      </div>
    </vue-bottom-sheet>
  </div>
</template>

<script>
import axios from 'axios'
import {Carousel3d,Slide} from 'vue-carousel-3d'
import  VueBottomSheet from "@webzlodimir/vue-bottom-sheet";
import Likeusermodal from '@/views/home/Likeusermodal.vue';
export default {
  name:'Detail',
  components:{
    Carousel3d,
    Slide,
    VueBottomSheet,
    Likeusermodal,
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
      isModal:false,
      content:'',
      comments: [],
      comment: '',
      like: false,
      likeCnt: 0,
      likePeoples:[],
      commentCnt: 0,
    }
  },
  computed:{
    article(){
      return this.$store.state.selectArticle
    },
    loginUser(){
      return this.$store.state.userId
    },
  },
  created(){
    this.getComment()
    this.getDetail()
    this.getLike()
  },
  methods: {
    open(){
      this.$refs.myBottomSheet.open();
    },
    close(){
      this.$refs.myBottomSheet.close();
    },
    openOption(){
      this.$refs.articleOption.open();
    },
    closeOption(){
      this.$refs.articleOption.close();
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
          this.timeforToday()    
        })
        .catch((e) => {
          console.error(e);
        })
    },
    timeforToday(){
      const today = new Date();
      // const timeValue = new Date()
      console.log(this.comments)
      console.log(today)
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
          this.getDetail()
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
    },
    likeToggle(){
      const URL = `http://localhost:8080/likelog`
      const data = {
        articleId: this.article.articleId,
        userId: this.loginUser
      }
      const params = {
        method: 'post',
        url: URL,
        data: data
      }
      axios(params)
        .then(() => {
          this.like = !this.like
          this.getDetail()
        })
        .catch((e) => {
          console.error(e);
        })
    },
    getDetail(){
      const URL = `http://localhost:8080/article/detail/${this.article.articleId}`
      const params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.likeCnt = res.data.data.likeCnt
          this.commentCnt = res.data.data.commentCnt
          this.content = res.data.data.content
        })
        .catch((e) => {
          console.error(e);
        })
    },
    getLike(){
      const URL = `http://localhost:8080/likelog/likelist/${this.article.articleId}`
      const params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.likePeoples = res.data.data
          console.log(this.likePeople)
        })
        .catch((e) => {
          console.error(e);
        })
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/views/home/Detail.scss";
</style>