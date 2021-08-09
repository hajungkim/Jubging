<template>
  <div>
    <div class="top">
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo">
      <font-awesome-icon icon="bars" class="hamburger" @click="open"/>
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
        <div class="lcbox" @click="isfollower=true">
          <span>팔로워</span>
          <span style="text-align:center">{{user.follower}}</span>
        </div>
        <div class="lcbox" @click="isfollowing=true">
          <span>팔로잉</span>
          <span style="text-align:center">{{user.following}}</span>
        </div>
      </div>
      <FollowerModal v-if="isfollower" @close-modal="isfollower=false" :currentUser = userId>
        </FollowerModal>
      <FollowingModal v-if="isfollowing" @close-modal="isfollowing=false" :currentUser = userId>
        </FollowingModal>  
    </div>
    <!-- 뱃지 리스트 -->
    <div class="badge_box">
      <carousel-3d class="badge_carousel"
        :disable3d="true" :width="50" :height="50" dir="ltr" :clickable="false"
        :display="5" :space="70" :controlsVisible="true"
      >
        <slide v-for="(photo,i) in badgephotos" :index="i" :key="i">
          <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
            <img class="badge_img" :src="photo.url" :data-index="index"
            :class="{current: isCurrent, onLeft:(leftIndex>=0), onRight:(rightIndex>=0)}" >
          </template>
        </slide>
      </carousel-3d>
    </div>
    <!-- 나의 게시글 -->
    <div class="photo_list">
      <div class="photo-grid">
        <span v-for="(article,idx) in articles" :key="idx" style="height:135px; border:1px solid white;">
          <img @click="onClick(article)" class="photo-img"
          :src="article.photosPath">
        </span>
      </div>
    </div>
    <!-- 바텀시트 -->
    <vue-bottom-sheet ref="myBottomSheet" max-height="370px" max-width="412px" >
      <div>
        <router-link :to="{name:'ChangeSetting'}" class="default-link">
          <div class="bt_common">
            <font-awesome-icon icon="user" class="fa-2x icon" style="margin-right:17px"/>
            <span>회원정보변경</span>
          </div>
        </router-link>
        <router-link :to="{name:'Logs'}" class="default-link">
          <div class="bt_common likelog">
            <font-awesome-icon icon="heart" class="fa-2x icon" style="margin-right:15px"/>
            <span>좋아요 로그</span>
          </div>
        </router-link>
        <router-link :to="{name:'#'}" class="default-link">
          <div class="bt_common">
            <font-awesome-icon icon="running" class="fa-2x icon" style="margin-right:19px"/>
            <span>줍깅 로그</span> 
          </div>
        </router-link>
        <router-link :to="{name:'#'}" class="default-link">
          <div class="bt_common">
            <font-awesome-icon icon="sign-out-alt" class="fa-2x icon" style="margin-right:13px"/>
            <span>로그아웃</span> 
          </div>
        </router-link>
      </div>
    </vue-bottom-sheet>
  </div>
</template>

<script>
import axios from 'axios'
import {Carousel3d,Slide} from 'vue-carousel-3d'
import  VueBottomSheet from "@webzlodimir/vue-bottom-sheet";
import FollowerModal from "@/components/my/FollowerModal.vue"
import FollowingModal from "@/components/my/FollowingModal.vue"
import { mapState } from 'vuex'
export default {
  name:'My',
  components:{
    Carousel3d,
    Slide,
    VueBottomSheet,
    FollowerModal,
    FollowingModal,
  },
  data() {
    return {
      user: [],
      articles: [],
      isfollower: false,
      isfollowing: false,
      // photos:[],
      photos: [
        {
          title:'0',
          url:require('@/assets/badge/plastic/sample7.png'),      
        },
        {
          title:'1',
          url:require('@/assets/badge/arround/sample2.png'),
        },
        // {
        //   title:'2',
        //   url:require('@/assets/badge/arround/sample2.png'),
        // },
        // {
        //   title:'3',
        //   url:'http://placehold.it/139x139',
        // },
        // {
        //   title:'4',
        //   url:'http://placehold.it/139x139',
        // },
        // {
        //   title:'5',
        //   url:'http://placehold.it/139x139',
        // },
        // {
        //   title:'6',
        //   url:'http://placehold.it/139x139',
        // },
        // {
        //   title:'7',
        //   url:'http://placehold.it/139x139',
        // },
        ],
    }
  },
  computed:{
		...mapState([
			'userId',
      'badgephotos'
		]),
  },
  created(){
    this.$store.dispatch('getBadge')
    this.getInfo()
    // this.getBadge()
    this.getArticle()
  },
  methods: {
    open() {
      this.$refs.myBottomSheet.open();
    },
    close() {
      this.$refs.myBottomSheet.close();
    },
    getInfo(){
      let URL = `http://localhost:8080/user/${this.userId}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.user=res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
    },
    getArticle(){
      let URL = `http://localhost:8080/article/list/${this.userId}`
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res) => {
          this.articles=res.data.data
          this.articles.reverse()
        })
        .catch((e) => {
          console.error(e);
        })
    },
    onClick(article){
      this.$store.state.selectArticle = article
      this.$store.state.backPage = 1
      this.$router.push({name:'Detail'})
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/components/my/My.scss";
</style>