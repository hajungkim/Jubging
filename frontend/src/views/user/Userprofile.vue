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
    <FollowerModal v-if="isfollower" @close-modal="isfollower=false" :currentUser = currentUser>
      </FollowerModal>
    <FollowingModal v-if="isfollowing" @close-modal="isfollowing=false" :currentUser = currentUser>
      </FollowingModal>  
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
          <img @click="onDetail(article)" class="photo-img" v-for="(article,idx) in articles" :key="idx" :src="article.photosPath">
        </router-link>
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
        photos: [
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
        ],
        follow: false,
        // followCnt: 0,
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
      let URL = `http://localhost:8080/user/${this.currentUser}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.user = res.data.data
          // this.followCnt = res.data.data.follower
        })
        .catch((e) => {
          console.error(e);
        })
    },
    getBadge(){
      let URL = `http://localhost:8080/mission/${this.currentUser}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          console.log('뱃지갯수', res.data.data)
        })
        .catch((e) => {
          console.error(e);
        })
    },
    getFollow(){
      let URL = `http://localhost:8080/follow/findfollow/${this.currentUser}`
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
      let URL = `http://localhost:8080/article/list/${this.currentUser}`
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
      let URL = `http://localhost:8080/follow?followUserId=${this.currentUser}&userId=${this.userId}`
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
    },
    deleteFollow(){
      let URL = `http://localhost:8080/follow?followUserId=${this.currentUser}&userId=${this.userId}`
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