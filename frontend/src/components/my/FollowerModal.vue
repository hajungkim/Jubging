<template>
  <div class="modal" style="z-index:1050;">
		<div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
				<div class="modal_top" style="font-size:20px;">{{usernickname}}'s Followers<button class="close" @click="$emit('close-modal')">닫기</button></div>
					<div>
						<ul style="padding:0px; margin-top:20px;">
							<li class="comment_container" v-for="(follower,idx) in followers" :key="idx" @click="moveProfile(follower)">
								<img class="comment_profile" :src="follower.profilePath">
								<div style="display:flex; align-items:center;">
										<span style="font-weight:bold; font-size:20px; margin-left:5px;">{{follower.nickName}}</span>
								</div>
							</li>
						</ul>
					</div>
			</div>
	</div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'
export default {
  name:'FollowerModal',
  props:{
    currentUser: String,
    usernickname: String,
  },
  data(){
    return{
      followers:[],
      BASEURL: 'http://localhost:8080',
    }
  },
  computed:{
		...mapState([
			'userId'
		]),
  },
  created(){
    this.getFollower()
  },
	methods:{
    getFollower(){
      let URL = `${this.BASEURL}/follow/findfollower/${parseInt(this.currentUser)}`
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res) => {
          this.followers=res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
    },
    moveProfile(follower){      
      if(follower.followerUserId == this.userId){
        this.$router.push({name:'My'})
        return
      }
      this.$store.state.currentUser = follower.userId
      localStorage.setItem('currentUser', follower.userId)
			this.$store.state.backPage = 1
      if (this.$route.path === '/userprofile'){
        this.$router.go(this.$router.currentRoute)
      }
      else{
        this.$router.push({name:'Userprofile', params: { user_nickname: this.follower.nickName }})
      }
    }
	}
}
</script>

<style lang="scss" scoped>
@import "@/components/my/FollowModal.scss";
</style>