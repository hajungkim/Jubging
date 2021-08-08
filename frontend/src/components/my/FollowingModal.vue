<template>
  <div class="modal">
		<div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
				<div class="modal_top" style="font-size:20px;">나의 팔로잉<button class="close" @click="$emit('close-modal')">닫기</button></div>
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
  data(){
    return{
      followings:[],
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
      let URL = `http://localhost:8080/follow/findfollow/${this.userId}`
      let params={
        method:'get',
        url:URL,
      }
      axios(params)
        .then((res) => {
          this.followings=res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
    },
    moveProfile(following){
      this.$store.state.currentUser = following.followerUserId
			this.$store.state.backPage = 1
			this.$router.push({name:'Userprofile'})
    }
	}
}
</script>

<style lang="scss" scoped>
@import "@/components/my/FollowModal.scss";
</style>