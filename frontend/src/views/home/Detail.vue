<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="onClick"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
      <font-awesome-icon
        v-if="selectArticle.userId===parseInt(userId)"
        :icon="['fas','ellipsis-h']" 
        class="option_button"
        @click="openOption"/>
    </div>
    <div class="article_content">
      <!--유저 정보-->
      <div class="user_info"  @click="moveProfile(selectArticle.userId)">
        <div class="profile_img">
          <img class="profile" :src="selectArticle.profilePath"> <!--src="@/assets/defaultuserimg.png"--> 
        </div>
        <span style="font-weight:bold; font-size:18px;">{{selectArticle.nickname}}</span>
      </div>
      <!--사진들-->
      <carousel-3d :width="300" :height="300" bias="right">
        <slide v-for="(photo,i) in photos" :index="i" :key="i"> <!-- photos 대신 article.photosPath 다른컴포넌트는 [0]만! -->
          <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
            <img class="article_img" :data-index="index" :class="{current: isCurrent, onLeft:(leftIndex>=0),
            onRight:(rightIndex>=0)}" :src="photo" @dblclick="likeToggle">
          </template>
        </slide>
      </carousel-3d>
      <span class="datetext">
        {{selectArticle.createdDate.slice(0,10)}}
      </span>
      <!--게시글 내용-->
      <div class="content_box">
        {{ content }}
      </div>
      <LikeuserModal v-if="isModal" @close-modal="isModal=false" :selectArticle="selectArticle">
      </LikeuserModal>
      <!--좋아요 댓글-->
      <div class="like_comment_container">
        <div class="lcbox">
          <span v-if="like">
            <font-awesome-icon size="lg" @click="likeToggle" :icon="['fas','heart']"/>
          </span>
          <span v-else>
            <font-awesome-icon size="lg" @click="likeToggle" :icon="['far','heart']"/>
          </span>
          <span style="margin-left:5px; font-size:18px;" @click="isModal=true">{{likeCnt}}</span>
        </div>
        <div class="lcbox" @click="open">
          <font-awesome-icon size="lg" :icon="['far','comment-dots']"/><span style="margin-left:5px; font-size:18px;">{{commentCnt}}</span>
        </div>
      </div>
    </div>

    <vue-bottom-sheet ref="myBottomSheet" max-height="800px" max-width="412px" id="comment_bottom" >
      <ul style="padding:0px;" id="ul-content">
        <li class="comment_container"
          v-for="(comment,idx) in comments"
          :key="idx"
        >
          <img class="comment_profile" :src="comment.profilePath">
          <div>
            <div>
              <span style="font-weight:bold;">{{comment.nickname}}</span>
              <span class="comment_time">{{comment.time}}</span>
            </div>
            <div class="comment_contents">{{comment.commentContent}}</div>
          </div>
          <div class="btn_div" v-if="comment.userId===userId">
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
            @click="onDelete(selectArticle)"
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
import LikeuserModal from '@/views/home/LikeuserModal.vue';
import { mapState } from 'vuex'
export default {
  name:'Detail',
  components:{
    Carousel3d,
    Slide,
    VueBottomSheet,
    LikeuserModal,
  },
  data(){
    return{
      photos: [],
      isModal:false,
      content:'',
      comments: [],
      comment: '',
      like: false,
      likeCnt: 0,
      commentCnt: 0,
      BASEURL: 'http://localhost:8080',
    }
  },
  computed:{
    ...mapState([
      'selectArticle',
			'userId',
      'userInfo',
      'likeflag',
		]),
  },
  created(){
    this.getImages()
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
      let URL = `${this.BASEURL}/comment/${this.selectArticle.articleId}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => { 
          this.comments = res.data.data  
          this.comments.forEach(element => {          
            if (element.profilePath === null) {
              element.profilePath = require("@/assets/user_default.png")
            }
          });
        })
        .then(() => {
          if (this.comments.length > 0) {
            var ul_content = document.querySelector('#ul-content').offsetHeight + 32
            var max_hight = 800
            if (ul_content + 40 < max_hight) {
              document.querySelector('.bottom-sheet__content').style.height=`${ul_content + 40}px`
            }
          }
        })
        .catch((e) => {
          console.error(e);
        })
    },
    commentSubmit(){
      const URL = `${this.BASEURL}/comment/`
      const data = {
        articleId: this.selectArticle.articleId,
        commentContent: this.comment,
        userId: this.userId,
      }
      const params = {
        method: 'post',
        url: URL,
        data: data
      }
      if (this.comment) {
        axios(params)
          .then(() => {
            this.comment = ''
            this.getComment()
            this.getDetail()
          })
          .catch((e) => {
            console.error(e);
          })
      }
      // socket 처리
      if (this.$store.state.stompClient && this.$store.state.stompClient.connected) {
        if (this.selectArticle.userId != this.$store.state.userId) {
          const socketData = { 
            userId: this.selectArticle.userId,
            pubId: this.$store.state.userId,
            articleId: this.selectArticle.articleId,
            nickname: this.selectArticle.nickname,
            profilePath: this.selectArticle.profilePath,
            category: 'comment'
          };
          this.$store.state.stompClient.send("/pub/" + this.selectArticle.userId, JSON.stringify(socketData), {});
        }
      }
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
      if(this.$store.state.backPage === 1)this.$router.push({name:'My'})
      else if(this.$store.state.backPage === 2) this.$router.push({name:'Search'})
      else if(this.$store.state.backPage === 3) this.$router.push({name:'Userprofile'})
      else if(this.$store.state.backPage === 5) this.$router.push({name:'Logs'})
      else this.$router.push({name:'Home'})
    },
    onDelete(article){
      const URL = `${this.BASEURL}/article?articleId=${article.articleId}&userId=${article.userId}`
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
      if(userId === this.userId){
        this.$router.push({name:'My'})
      }
      else{
        this.$store.state.currentUser = userId
        localStorage.setItem('currentUser', userId)
        this.$store.state.backPage = 4
        this.$router.push({name:'Userprofile'})
      }
    },
    likeToggle(){
      const URL = `${this.BASEURL}/likelog/`
      const data = {
        articleId: this.selectArticle.articleId,
        userId: this.userId
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
      // socket 처리
      if (!this.like) {
        if (this.$store.state.stompClient && this.$store.state.stompClient.connected) {
          if (this.selectArticle.userId != this.$store.state.userId) {
            const socketData = { 
              userId: this.selectArticle.userId,
              pubId: this.$store.state.userId,
              articleId: this.selectArticle.articleId,
              nickname: this.selectArticle.nickname,
              profilePath: this.selectArticle.profilePath,
              category: 'like'
            };
            this.$store.state.stompClient.send("/pub/" + this.selectArticle.userId, JSON.stringify(socketData), {});
          }
        }
      }
    },
    getDetail(){
      const URL = `${this.BASEURL}/article/detail/${this.selectArticle.articleId}`
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
    getImages(){
      if (this.selectArticle.photosPath !== null){
      this.photos = this.selectArticle.photosPath.split('#')
      }
      else{
        this.photos = []
      }
    },
    getLike(){
    const URL = `${this.BASEURL}/likelog/likelist/${this.selectArticle.articleId}`
		const params = {
			method: 'get',
			url: URL,
		}
		axios(params)
			.then((res) => {
        this.likePeoples = res.data.data
				this.likePeoples.some(element => {
					if(element.userId === parseInt(this.userId)){
						this.like = true
					}
					return 0;
				});
				
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
