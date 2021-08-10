<template>
  <div class="modal" style="z-index:1050;">
		<div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
				<div class="modal_top" style="font-size:20px;">{{usernickname}}'s Followings<button class="close" @click="$emit('close-modal')">닫기</button></div>
					<div>
						<ul style="padding:0px; margin-top:20px;">
							<li class="comment_container" v-for="(following,idx) in followings" :key="idx" @click="moveProfile(following)">
								<img class="comment_profile" :src="following.profilePath">
								<div style="display:flex; align-items:center;">
										<span style="font-weight:bold; font-size:20px; margin-left:5px;">{{following.nickName}}</span>
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
  name:'FollowingModal',
  props:{
    currentUser: String,
    usernickname: String,
  },
  data(){
    return{
      nickname: '',
      followings:[],
      BASEURL: 'http://localhost:8080',
    }
  },
  computed:{
		...mapState([
			'userId'
		]),
  },
  created(){
    this.getFollowing()
  },
	methods:{
    getFollowing(){
      let URL = `${this.BASEURL}/follow/findfollow/${this.currentUser}`
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res) => {
          console.log(res.data.data)
          this.followings=res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
    },
    moveProfile(following){
      if(following.followUserId === this.userId){
        this.$router.push({name:'My'})
        return
      }
      this.$store.state.currentUser = following.userId
      localStorage.setItem('currentUser', following.userId)
			this.$store.state.backPage = 1
      if (this.$route.path === '/userprofile'){
        this.$router.go(this.$router.currentRoute)
      }
      else{
        this.$router.push({name:'Userprofile'})
      }
    }
	}
}
</script>

<style lang="scss" scoped>
@import "@/components/my/FollowModal.scss";
</style>