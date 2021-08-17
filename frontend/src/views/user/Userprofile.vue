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
        <img class="profile" :src="userInfo.profilePath">
      </div>
      <div>
        <span>{{userInfo.nickname}}</span>
      </div>
      
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
    </div>
    <FollowerModal v-if="isfollower" @close-modal="isfollower=false" :profileUserId="profileUserId*1" @update-follow="getUserInfo(userId)">
      </FollowerModal>
    <FollowingModal v-if="isfollowing" @close-modal="isfollowing=false" :profileUserId="profileUserId*1" @update-follow="getUserInfo(userId)">
      </FollowingModal> 

    <!-- 뱃지 리스트 -->
    <div class="badge_box" v-if="ischange && !isbadge">
        <carousel-3d class="badge_carousel"
          :disable3d="true" :width="60" :height="60" dir="ltr" :startIndex="0" :clickable="false" :border="0"
          :display="5" :space="70" :controlsVisible="true" style="width:412px;"
        >
          <slide v-for="(photo,i) in photos" :index="i" :key="i">
            <template slot-scope="{index,isCurrent,leftIndex,rightIndex}">
              <img class="badge_img" :src="photo.url" :data-index="index"
              :class="{current: isCurrent, onLeft:(leftIndex>=0), onRight:(rightIndex>=0)}" >
            </template>
          </slide>
          </carousel-3d>
      </div>
      <div class="badge_box" v-if="isbadge">
        <carousel-3d class="badge_carousel"
          :disable3d="true" :width="60" :height="60" dir="ltr" :startIndex="0" :clickable="false" :border="0"
          :display="4" :space="70" :controlsVisible="true" style="width:412px;"
        >
        </carousel-3d>
          <div class="nobadge_text">
            <span class="nobadge">Do Jubging! Take Badges!</span>
          </div>
      </div>
    <!-- 유저 게시글 -->
    <div class="photo_list">
      <div v-if="userArticles" class="photo-grid">
        <span v-for="(article, idx) in userArticles" :key="idx" style="height:135px; border:1px solid white;">
          <img @click="onDetail(article)" class="photo-img"
          :src="article.photosPath">
        </span>
      </div>
      <div v-else class="no_article">
        게시글이 아직 없어요 ㅜㅠ!
      </div>
    </div>
  </div>
</template>

<script>
import { HTTP } from '@/util/http-common'
import {Carousel3d,Slide} from 'vue-carousel-3d'
import { mapActions, mapState } from 'vuex'
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
      pubUser: [],
      isfollower: false,
      isfollowing: false,
      photos: [],
      ischange: false,
      follow: false,
      isbadge: false,
      profileUserId : '',
    }
  },
  created(){
    this.profileUserId = this.$route.params.user_id
    this.getUserInfo(this.$route.params.user_id)
    this.getFollow()
    this.getBadge()
    this.getArticle(this.$route.params.user_id)
    this.getUser()
  },
  computed:{
    ...mapState([
			'userId',
      'selectArticle',

      'userInfo',
      'userArticles',
      'Token',
		]),
  },
  methods: {
    ...mapActions([
      'getUserInfo', 'getArticle'
    ]),
    getBadge(){
    HTTP.get(`mission/${this.$route.params.user_id}`)
      .then((res) => {
        for(const key in res.data.data)
        {
          if (key === 'bottle' || key === 'can' || key === 'metal' ||
              key === 'paper' ||  key === 'plastic' || key === 'styroform' ||
              key === 'trash' || key === 'vinyl' || key === 'jubging' ||
              key === 'arround' || key === 'mountain' || key === 'ocean' || key === 'river'){
            if (res.data.data[key] >= 3 && res.data.data[key] < 10){
              this.photos.push({url: require(`@/assets/badge/${key}/bronze.jpg`)})
            }
            else if (res.data.data[key] >= 10 && res.data.data[key] < 20){
              this.photos.push({url: require(`@/assets/badge/${key}/silver.jpg`)})
            }
            else if (res.data.data[key] >= 20){
                this.photos.push({url: require(`@/assets/badge/${key}/gold.jpg`)})
            }
          }
          // 여기부터 댓글,좋아요,팔로우,거리
          else if (key === 'comment' || key === 'like' || key === 'follow' || key === 'distance'){
            if (res.data.data[key] >= 10 && res.data.data[key] < 50){
                this.photos.push({url: require(`@/assets/badge/${key}/bronze.jpg`)})
            }
            else if (res.data.data[key] >=50 && res.data.data[key]<100){
                this.photos.push({url: require(`@/assets/badge/${key}/silver.jpg`)})
            }
            else if (res.data.data[key] >= 100){
                this.photos.push({url: require(`@/assets/badge/${key}/gold.jpg`)})
            }
          }
        }
        this.ischange = true
        if (this.photos.length === 0){
          this.isbadge = true;
        }
        })
      .catch((e) => {
        console.error(e);
      })
    },
    getFollow(){
      HTTP.get(`follow/findfollower/${this.$route.params.user_id}`)
        .then((res) => {
          res.data.data.some(e => {
            if (e.userId*1 === this.userId*1){
              this.follow = true
            }
          });
        })
        .catch(() => {
          this.follow = false
        })
    },
    onClick(){
      if (this.$store.state.backPage === 2 || this.$store.state.searchflag === true){
        this.$router.push({ name: 'Search' })
      }
      else if (this.$store.state.backPage === 4){
        this.$router.push({name:'Detail', params: { article_id: localStorage.getItem('articleId') }})
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
      this.$router.push({name:'Detail', params: { article_id: article.articleId }})
    },
    onFollow(){
      if (this.Token) {
        HTTP.post(`follow?followUserId=${this.$route.params.user_id}&userId=${this.userId}`)
          .then(() => {
            this.follow = true
            this.getUserInfo(this.$route.params.user_id)
          })
          .catch((e) => {
            console.error(e);
          })
        // socket 처리
        if (this.$store.state.stompClient && this.$store.state.stompClient.connected) {
          if (this.userInfo.userId != this.pubUser.userId) {
            const socketData = { 
              userId: this.userInfo.userId,
              pubId: this.pubUser.userId,
              articleId: null,
              nickname: this.pubUser.nickname,
              profilePath: this.pubUser.profilePath,
              category: 'follow'
            };
            this.$store.state.stompClient.send("/pub/" + this.userInfo.userId, JSON.stringify(socketData), {});
          }
        }
      }
    },
    getUser(){
      HTTP.get(`user/${this.$store.state.userId}`)
        .then((res) => {
          this.pubUser = res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
    },
    deleteFollow(){
      if (this.Token) {
        HTTP.delete(`follow?followUserId=${this.$route.params.user_id}&userId=${this.userId}`)
        .then(() => {
          this.follow = false
          this.getUserInfo(this.$route.params.user_id)
        })
        .catch((e) => {
          console.error(e);
        })
      }
    },
  },
}
</script>

<style lang="scss" scoped>
@import "@/views/user/Userprofile.scss";
</style>