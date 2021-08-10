<template>
  <div>
    <div class="follow_container">
      <img class="photo-img" :src="followarticle.photosPath" @click="moveDetail(followarticle)">
      <div class="article_info">
        <div style="display:flex; justify-content: center;" @click="moveProfile(followarticle.userId)">
          <img class="follow_profile" :src="user.profilePath">
          <span style="margin-top:20px; font-weight:bold">{{followarticle.nickname}}</span>
        </div>
        <div class="hashtag_container" @click="moveDetail(followarticle)">
          <span v-for="(hash,idx) in followarticle.hashlist" :key="idx">#{{hash}}</span>
        </div>
        <div class="like_comment_container" @click="moveDetail(followarticle)">
          <div class="lcbox">
            <font-awesome-icon :icon="['fas','users']"/><span style="margin-left:5px;">{{user.follower}}</span>
          </div>
          <div class="lcbox">
            <font-awesome-icon :icon="['far','heart']"/><span style="margin-left:5px;">{{followarticle.likeCnt}}</span>
          </div>
          <div class="lcbox">
            <font-awesome-icon :icon="['far','comment-dots']"/><span style="margin-left:5px;">{{followarticle.commentCnt}}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name:'FollowList',
  props:{
    followarticle:{
      type: Object,
    },
  },
  data(){
    return {
      user:{
        profilePath: '',
        follower: 0,
      },
      BASEURL: 'http://localhost:8080'
    }
  },
  created(){
    let URL = `${this.BASEURL}/user/${this.followarticle.userId}`
    let params = {
      method: 'get',
      url: URL,
    }
    axios(params)
      .then((res) => {
        if (res.data.data.profilePath == null) {
          this.user.profilePath = require("@/assets/user_default.png")
        }
        else{
          this.user.profilePath = res.data.data.profilePath
        }
        this.user.follower = res.data.data.follower          
      })
      .catch((e) => {
        console.error(e);
    })
  },
  methods:{
    moveDetail(article){
      this.$store.state.selectArticle = article
      this.$router.push({name:'Detail'})
    },
    moveProfile(userId){
      this.$store.state.currentUser = userId
      this.$store.state.backPage = 0
      this.$router.push({name:'Userprofile'})
    }
  }
}
</script>

<style lang='scss' scoped>
@import "@/components/home/Followlist.scss";
</style>