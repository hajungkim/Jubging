<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="onClick"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
      <font-awesome-icon
        v-if="article.userId===parseInt(userId)"
        :icon="['fas','ellipsis-h']" 
        class="option_button"
        @click="openOption"/>
    </div>
    <div class="article_content">
      <!--유저 정보-->
      <div class="user_info"  @click="moveProfile(article.userId)">
        <div class="profile_img">
          <img class="profile" :src="article.profilePath"> <!--src="@/assets/defaultuserimg.png"--> 
        </div>
        <span style="font-weight:bold; font-size:18px;">{{article.nickname}}</span>
      </div>
      <!--사진들-->
      <carousel-3d v-if="ischange" :width="300" :height="300" bias="right" :count="3">
        <slide v-for="(photo,i) in photos" :index="i" :key="i"> <!-- photos 대신 article.photosPath 다른컴포넌트는 [0]만! -->
          <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
            <img class="article_img" :data-index="index" :class="{current: isCurrent, onLeft:(leftIndex>=0),
            onRight:(rightIndex>=0)}" :src="photo" @dblclick="likeToggle">
          </template>
        </slide>
      </carousel-3d>
      <span class="datetext">
        {{article.date}}
      </span>
      <!--게시글 내용-->
      <div class="content_box">
        {{ content }}
      </div>
      <LikeuserModal v-if="isModal" @close-modal="isModal=false" :article="article">
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
          <div style="display:flex;" @click = moveProfile(comment.userId)>
            <img class="comment_profile" :src="comment.profilePath">
            <div>
              <div>
                <span style="font-weight:bold;">{{comment.nickname}}</span>
                <span class="comment_time">{{comment.time}}</span>
              </div>
              <div class="comment_contents">{{comment.commentContent}}</div>
            </div>
            <button class="comment_delete_button" v-if="comment.userId === parseInt(userId)" @click="commentDelete(comment)">X</button>
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
        <div class="bt_common" @click="moveEdit(article.articleId)">
          <font-awesome-icon
            icon="edit"
            class="fa-2x update_icon"
          />
          <span>게시글 수정하기</span>
        </div>
        <div class="bt_common" style="margin-top:15px;" @click="onDelete(article)">
          <font-awesome-icon
            icon="trash"
            class="fa-2x delete_button"
            style="margin-right:17px"
          />
          <span>게시글 삭제하기</span>
        </div>
      </div>
    </vue-bottom-sheet>
  </div>
</template>

<script>
import { HTTP } from '@/util/http-common'
import {Carousel3d,Slide} from 'vue-carousel-3d'
import VueBottomSheet from "@webzlodimir/vue-bottom-sheet"
import LikeuserModal from '@/views/home/LikeuserModal.vue'
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
      article:[],
      photos: [],
      isModal:false,
      ischange: false,
      content:'',
      date:'',
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
    this.getDetail()
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
      HTTP.get(`comment/${this.$route.params.article_id}`)
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
      const data = {
        articleId: this.$route.params.article_id,
        commentContent: this.comment,
        userId: this.userId,
      }
      if (this.comment) {
        HTTP.post(`comment/`, data)
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
        if (this.article.userId != this.$store.state.userId) {
          const socketData = { 
            userId: this.article.userId,
            pubId: this.$store.state.userId,
            articleId: this.$route.params.article_id,
            nickname: this.article.nickname,
            profilePath: this.article.profilePath,
            category: 'comment'
          };
          this.$store.state.stompClient.send("/pub/" + this.article.userId, JSON.stringify(socketData), {});
        }
      }
    },
    commentDelete(comment){
      const data = {
        comment_id: comment.commentId,
        userId: comment.userId
      }
      HTTP.delete(`comment/${comment.commentId}?userId=${comment.userId}`, data)
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
      else if(this.$store.state.backPage === 3) this.$router.push({name:'Userprofile', params: { user_id: this.article.userId }})
      else if(this.$store.state.backPage === 5) this.$router.push({name:'Logs'})
      else this.$router.push({name:'Home'})
    },
    onDelete(article){
      const data = {
        articleId: this.$route.params.article_id,
        userId: article.userId
      }
      HTTP.delete(`article?articleId=${this.$route.params.article_id}&userId=${article.userId}`, data)
        .then(() => {
          this.$router.push({name:'My'})
        })
        .catch((e) => {
          console.error(e);
        })
    },
    moveProfile(userId){
      if(userId === parseInt(this.userId)){
        this.$router.push({name:'My'})
      }
      else{
        this.$store.state.currentUser = userId
        localStorage.setItem('currentUser', userId)
        localStorage.setItem('articleId', this.article.articleId)
        this.$store.state.backPage = 4
        this.$router.push({name:'Userprofile', params: { user_id: userId }})
      }
    },
    moveEdit(articleId){
      this.$router.push({name:'Editarticle', params: { article_id: articleId }})
    },
    likeToggle(){
      const data = {
        articleId: this.$route.params.article_id,
        userId: this.userId
      }
      HTTP.post(`likelog/`, data)
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
          if (this.article.userId != this.$store.state.userId) {
            const socketData = { 
              userId: this.article.userId,
              pubId: this.$store.state.userId,
              articleId: this.$route.params.article_id,
              nickname: this.article.nickname,
              profilePath: this.article.profilePath,
              category: 'like'
            };
            this.$store.state.stompClient.send("/pub/" + this.article.userId, JSON.stringify(socketData), {});
          }
        }
      }
    },
    getDetail(){
      HTTP.get(`article/detail/${this.$route.params.article_id}`)
        .then((res) => {
          this.article = res.data.data
          this.likeCnt = res.data.data.likeCnt
          this.commentCnt = res.data.data.commentCnt
          this.content = res.data.data.content
          this.ischange = true
          this.getLike()
          this.getImages()
          this.getComment()
        })
        .catch((e) => {
          console.error(e);
        })
    },
    getImages(){
      if (this.article.photosPath.includes('#')){
        this.photos = this.article.photosPath.split('#')
        this.photos.pop()
      }
      else{
        this.photos = [this.article.photosPath]
      }
    },
    getLike(){
      HTTP.get(`likelog/likelist/${this.$route.params.article_id}`)
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
