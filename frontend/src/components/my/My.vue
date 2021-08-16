<template>
  <div>
    <div class="top">
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo">
      <font-awesome-icon icon="bars" class="hamburger" @click="open"/>
    </div>

    <div class="my-wrap">
      <!-- 유저 정보 -->
      <div class="my_info">
        <div class="profile_img">
          <img class="profile" :src="userInfo.profilePath">
        </div>
        <span>{{userInfo.nickname}}</span>
        <!--게시글 팔로워 팔로잉-->
        <div class="user_active_cnt">
          <div class="lcbox" >
            <span>게시물</span>
            <span style="text-align:center">{{userInfo.articleCount}}</span>
          </div>
          <div class="lcbox" @click="isfollower=true">
            <span>팔로워</span>
            <span style="text-align:center">{{userInfo.follower}}</span>
          </div>
          <div class="lcbox" @click="isfollowing=true">
            <span>팔로잉</span>
            <span style="text-align:center">{{userInfo.following}}</span>
          </div>
        </div>
        <FollowerModal v-if="isfollower" @close-modal="isfollower=false" :profileUserId="userId*1">
          </FollowerModal> 
        <FollowingModal v-if="isfollowing" @close-modal="isfollowing=false" :profileUserId="userId*1">
          </FollowingModal>  
      </div>
      <!-- 뱃지 리스트 -->
      <div class="badge_box">
        <carousel-3d class="badge_carousel"
          :disable3d="true" :width="60" :height="60" dir="ltr" :startIndex="0" :clickable="false"
          :display="4" :space="70" :controlsVisible="true" style="width:412px;"
        >
          <div v-if="ischange && isbadge">
            <slide v-for="(photo,i) in photos" :index="i" :key="i">
              <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
                <img class="badge_img" :src="photo.url" :data-index="index"
                :class="{current: isCurrent, onLeft:(leftIndex>=0), onRight:(rightIndex>=0)}" >
              </template>
            </slide>
          </div>
        </carousel-3d>
      </div>
      <div v-if="!isbadge" class="nobadge_text">
        <span class="nobadge">Do Jubging! Take Badges!</span>
      </div>
      <!-- 나의 게시글 -->
      <div class="photo_list">
        <div v-if="userArticles" class="photo-grid">
          <span v-for="(article,idx) in userArticles" :key="idx" style="height:135px; border:1px solid white;">
            <img @click="onClick(article)" class="photo-img" :src="article.photosPath">
          </span>
        </div>
        <div v-if="!userArticles" class="no_article">
          <div>게시글이 없습니다</div>
        </div>
      </div>
    </div>
    <!-- 바텀시트 -->
    <vue-bottom-sheet ref="myBottomSheet" max-height="400px" max-width="412px">
      <div>
        <router-link :to="{name:'ChangeSetting'}" class="default-link">
          <div class="bt_common">
            <font-awesome-icon icon="user" class="icon" style="margin-right:17px"/>
            <span>회원정보변경</span>
          </div>
        </router-link>
          <div class="bt_common likelog" @click="onLikelog">
            <font-awesome-icon icon="heart" class="icon" style="margin-right:16px"/>
            <span>좋아요 로그</span>
          </div>
          <div class="bt_common"  @click="onJublog">
            <font-awesome-icon icon="running" class="icon" style="margin-right:19px"/>
            <span>줍깅 로그</span> 
          </div>
        <div class="bt_common" @click="onlogout">
          <font-awesome-icon icon="sign-out-alt" class="icon" style="margin-right:15px"/>
          <span>로그아웃</span> 
        </div>
      </div>
    </vue-bottom-sheet>
  </div>
</template>

<script>
import { HTTP } from '@/util/http-common';
import {Carousel3d,Slide} from 'vue-carousel-3d'
import  VueBottomSheet from "@webzlodimir/vue-bottom-sheet";
import FollowerModal from "@/components/my/FollowerModal.vue"
import FollowingModal from "@/components/my/FollowingModal.vue"
import { mapActions, mapState } from 'vuex'

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
      articles: [],
      isfollower: false,
      isfollowing: false,
      ischange: false,
      isbadge: true,
      photos:[],
    }
  },
  computed:{
		...mapState([
			'userId', 'userInfo', 'userArticles'
		]),
  },
  created(){
    this.getUserInfo(this.userId)
    this.getArticle(this.userId)
    this.getBadge()
  },
  methods: {
    ...mapActions([
      'getUserInfo', 'getArticle',
    ]),
    onlogout(){
      this.$store.dispatch('logout')  
      this.$router.push({name:"Login"})
    },
    onJublog(){
      this.$router.push({name:'Logs', params: { flag: true }})
    },
    onLikelog(){
      this.$router.push({name:'Logs', params: { flag: false }})
    },
    open() {
      this.$refs.myBottomSheet.open();
    },
    close() {
      this.$refs.myBottomSheet.close();
    },
    getBadge(){
      HTTP.get(`mission/${this.userId}`)
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
        if (this.photos.length === 0){
          this.isbadge = false;
        }
        })
      .catch((e) => {
        console.error(e);
      })
    },
    onClick(article){
      this.$store.state.selectArticle = article
      this.$store.state.backPage = 1
      this.$router.push({name:'Detail', params: { article_id: article.articleId }})   
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/components/my/My.scss";
</style>