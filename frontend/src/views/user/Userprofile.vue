<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="onClick()"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo">
      <span v-if="follow" class="followbtn">
          <font-awesome-icon class="btnstyle" :icon="['fas','star']" @click="deleteFollow()"/>
      </span>
      <span v-else class="followbtn">
        <font-awesome-icon class="btnstyle" :icon="['far','star']" @click="onFollow()"/>
      </span>
    </div>
    <!-- 유저 정보 -->
    <div class="my_info">
      <div class="profile_img">
        <img class="profile" :src="user.profilePath">
      </div>
      <div>
        <!-- <span v-if="follow" class="followbtn">
          <font-awesome-icon class="btnstyle" :icon="['fas','star']" @click="deleteFollow()"/>
        </span>
        <span v-else class="followbtn">
          <font-awesome-icon class="btnstyle" :icon="['far','star']" @click="onFollow()"/>
        </span> -->
        <span>{{user.nickname}}</span>
      </div>
      <!--게시글 팔로워 팔로잉-->
      <div class="user_active_cnt">
        <div class="lcbox" >
          <span>게시물</span>
          <span style="text-align:center">{{user.articleCount}}</span>
        </div>
        <div class="lcbox" @click="isfollower=true">
          <span>팔로워</span>
          <span style="text-align:center">{{user.follower}}</span>
        </div>
        <div class="lcbox" @click="isfollowing=true">
          <span>팔로잉</span>
          <span style="text-align:center">{{user.following}}</span>
        </div>
      </div>
    </div>
    <FollowerModal v-if="isfollower" @close-modal="isfollower=false" :currentUser = currentUser :usernickname = usernickname>
      </FollowerModal>
    <FollowingModal v-if="isfollowing" @close-modal="isfollowing=false" :currentUser = currentUser :usernickname = usernickname>
      </FollowingModal>  
    <!-- 뱃지 리스트 -->
    <div class="badge_box" v-if="ischange">
      <carousel-3d class="badge_carousel"
        :disable3d="true" :width="60" :height="60" dir="ltr" :startIndex="0" :clickable="false"
        :display="4" :space="70" :controlsVisible="true" style="padding-left:70px;"
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
        <span v-for="(article,idx) in articles" :key="idx" style="height:135px; border:1px solid white;">
          <img @click="onClick(article)" class="photo-img"
          :src="article.photosPath">
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import {Carousel3d,Slide} from 'vue-carousel-3d'
import { mapState } from 'vuex'
import FollowerModal from "@/components/my/FollowerModal.vue"
import FollowingModal from "@/components/my/FollowingModal.vue"
export default {
  name:'Userprofile',
  components:{
    Carousel3d,
    Slide,
    FollowerModal,
    FollowingModal,
  },
  data() {
      return {
        user: [],
        isfollower: false,
        isfollowing: false,
        articles: [],
        photos: [],
        ischange: false,
        follow: false,
        BASEURL: 'http://localhost:8080',
        usernickname: '',
    }
  },
  created(){
    this.$store.state.currentUser = localStorage.getItem('currentUser')
    this.getInfo()
    this.getFollow()
    this.getBadge()
    this.getArticle()
  },
  computed:{
    ...mapState([
      'currentUser',
			'userId',
		]),
  },
  methods: {
    getInfo(){
      let URL = `${this.BASEURL}/user/${parseInt(this.currentUser)}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.user = res.data.data
          this.usernickname = res.data.data.nickname
        })
        .catch((e) => {
          console.error(e);
        })
    },
    getBadge(){
    let URL = `${this.BASEURL}/mission/${parseInt(this.currentUser)}`
    let params = {
      method: 'get',
      url: URL,
    }
    axios(params)
      .then((res) => {
        for(const key in res.data.data)
        {
          if (key === 'bottle' || key === 'can' || key === 'metal' ||
              key === 'paper' ||  key === 'plastic' || key === 'styroform' ||
              key === 'trash' || key === 'vinyl' || key === 'jubging' ||
              key === 'arround' || key === 'mountain' || key === 'ocean' || key === 'river'){
            if (res.data.data[key] >= 3 && res.data.data[key] < 10){
              this.photos.push({url: require(`@/assets/badge/${key}/bronze.png`)})
            }
            else if (res.data.data[key] >= 10 && res.data.data[key] < 20){
              this.photos.push({url: require(`@/assets/badge/${key}/silver.png`)})
            }
            else if (res.data.data[key] >= 20){
                this.photos.push({url: require(`@/assets/badge/${key}/gold.png`)})
            }
          }
          // 여기부터 댓글,좋아요,팔로우,거리
          else if (key === 'comment' || key === 'like' || key === 'follow' || key === 'distance'){
            if (res.data.data[key] >= 10 && res.data.data[key] < 50){
                this.photos.push({url: require(`@/assets/badge/${key}/bronze.png`)})
            }
            else if (res.data.data[key] >=50 && res.data.data[key]<100){
                this.photos.push({url: require(`@/assets/badge/${key}/silver.png`)})
            }
            else if (res.data.data[key] >= 100){
                this.photos.push({url: require(`@/assets/badge/${key}/gold.png`)})
            }
          }
        }
        this.ischange = true
        })
      .catch((e) => {
        console.error(e);
      })
    },
    getFollow(){
      let URL = `${this.BASEURL}/follow/findfollow/${this.currentUser}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          res.data.data.some(element => {
            if (element.followUserId === this.currentUser){
              this.follow = true
            }
            return 0;
          });
        })
        .catch(() => {
          this.follow = false
        })
    },
    getArticle(){
      let URL = `${this.BASEURL}/article/list/${this.currentUser}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.articles = res.data.data
          this.articles.reverse()
        })
        .catch((e) => {
          console.error(e);
        })
    },
    onClick(){
      if (this.$store.state.backPage === 2 || this.$store.state.searchflag === true){
        this.$router.push({ name: 'Search' })
      }
      else if (this.$store.state.backPage === 4){
        // this.$store.state.backPage = 3
        this.$router.push({ name:"Detail"})
      }
      else if (this.$store.state.backPage === 1){
        this.$router.push({ name:"My" })
      }
      else{
      this.$router.push({ name: 'Home' })
      }
    },
    onDetail(article){
      this.$store.state.selectArticle = article
      this.$store.state.backPage = 3
      this.$router.push({ name: 'Detail' })
    },
    onFollow(){
      let URL = `${this.BASEURL}/follow?followUserId=${this.currentUser}&userId=${this.userId}`
      let params = {
        method: 'post',
        url: URL,
      }
      axios(params)
        .then(() => {
          this.follow = true
          this.getInfo()
        })
        .catch((e) => {
          console.error(e);
        })
      if (this.$store.state.stompClient && this.$store.state.stompClient.connected) {
        if (this.user.userId != this.$store.state.userId) {
          const socketData = { 
            userId: this.user.userId,
            pubId: this.$store.state.userId,
            articleId: this.user.articleId,
            nickname: this.user.nickname,
            profilePath: this.user.profilePath,
            category: 'follow'
          };
          this.$store.state.stompClient.send("/pub/" + this.user.userId, JSON.stringify(socketData), {});
        }
      }
    },
    deleteFollow(){
      let URL = `${this.BASEURL}/follow?followUserId=${this.currentUser}&userId=${this.userId}`
      let params = {
        method: 'delete',
        url: URL,
      }
      axios(params)
        .then(() => {
          this.follow = false
          this.getInfo()
        })
        .catch((e) => {
          console.error(e);
        })
    },
  },
}
</script>

<style lang="scss" scoped>
@import "@/views/user/Userprofile.scss";
</style>