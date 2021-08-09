<template>
  <div class="modal">
		<div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
				<div class="modal_top" style="font-size:20px;">나의 팔로워들<button class="close" @click="$emit('close-modal')">닫기</button></div>
					<div>
						<ul style="padding:0px; margin-top:20px;">
							<li class="comment_container" v-for="(follwer,idx) in followers" :key="idx" @click="moveProfile(follwer)">
								<img class="comment_profile" :src="follwer.profilePath">
								<div style="display:flex; align-items:center;">
										<span style="font-weight:bold; font-size:20px; margin-left:5px;">{{follwer.nickName}}</span>
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
  data(){
    return{
      followers:[],
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
      let URL = `http://localhost:8080/follow/findfollower/${this.userId}`
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res) => {
          this.followers=res.data.data
          console.log(this.followers,"사진?")
        })
        .catch((e) => {
          console.error(e);
        })
    },
    moveProfile(follower){
      this.$store.state.currentUser = follower.followerUserId
			this.$store.state.backPage = 1
			this.$router.push({name:'Userprofile'})
    }
	}
}
</script>

<style lang="scss" scoped>
@import "@/components/my/FollowModal.scss";
</style>