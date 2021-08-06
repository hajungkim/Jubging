<template>
  <div>
    <div class="follow_container">
      <router-link :to="{name:'Detail'}" class="default-link">
        <img class="photo-img" :src="followarticle.photosPath">
      </router-link>
      <div class="article_info">
        <router-link :to="{name:'My'}" class="default-link">
          <div style="display:flex; justify-content: center;">
            <img class="follow_profile" :src="user.profilePath">
            <span style="margin-top:20px; font-weight:bold">{{followarticle.nickname}}</span>
          </div>
        </router-link>
        <router-link :to="{name:'Detail'}" class="default-link">
          <div class="hashtag_container">
            <span v-for="(hash,idx) in followarticle.hashlist" :key="idx">#{{hash}}</span>
          </div>
          <div class="like_comment_container">
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
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name:'FollowList',
  data(){
    return {
      user:{
        profilePath:'',
        follower:0,
      }
    }
  },
  props:{
    followarticle:{
      type:Object,
    },
  },
  method:{
  },
  created(){
    let URL = `http://localhost:8080/user/${this.followarticle.userId}`
    let params={
      method:'get',
      url:URL,
    }
    axios(params)
      .then((res)=>{
        this.user.profilePath=res.data.data.profilePath
        this.user.follower=res.data.data.follower          
      })
      .catch((e)=>{
        console.error(e);
    })
  }
}
</script>

<style scoped>
.follow_container{
  display:flex;
  margin: 20px 0px 10px 16px;
  /* justify-content: space-around;*/
}
.photo-img{
  width: 185px;
  height: 185px;
}
.follow_profile{
  width: 60px;
  height: 60px;
  border-radius: 50%;
  overflow: hidden;
  margin:5px 10px 0px 0px;
}
.article_info{
 display: flex;
 flex-direction: column;
 margin-top:8px;
 width: 200px;
}
.hashtag_container{
  width: 180px;
  height: 50px;
  margin:10px 16px 10px 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  word-break:break-all;
}
.like_comment_container{
  display: flex;
  justify-content: center;
  margin-left:10px;
}
.lcbox{
  display: flex;
  align-items: center;
  padding: 10px;
}
.default-link{
  color:black;
  text-decoration:none;
}
</style>