<template>
  <div class="modal" style="z-index:1050;">
		<div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
				<div class="modal_top" style="font-size:20px; font-weight:bold;">팔로워<button class="close" @click="$emit('close-modal')">X</button></div>
					<div>
						<ul class="follow_container">
							<li class="img_name_contain" v-for="(follower, idx) in userFollowers" :key="idx" @click="moveProfile(follower.userId)">
								<img class="follow_profile" :src="follower.profilePath">
								<div style="display:flex; align-items:center;">
										<span style="margin-left:5px;">{{follower.nickName}}</span>
								</div>
							</li>
						</ul>
					</div>
			</div>
	</div>
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
  name:'FollowerModal',
  props:{
    userId: Number,
  },
  computed:{
		...mapState([
			'userFollowers'
		]),
  },
  created(){
    this.getFollower(this.userId)
  },
	methods:{
    ...mapActions([
      'getFollower'
    ]),
    moveProfile(followerUserId){
      if (followerUserId*1 === this.$store.state.userId*1) {
        this.$router.push({name:'My'})
      } else {
        this.$store.state.backPage = 1
        this.$router.push({name:'Userprofile', params: { user_id: followerUserId }})
      }
    }
	}
}
</script>

<style lang="scss" scoped>
@import "@/components/my/FollowModal.scss";
</style>