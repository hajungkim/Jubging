<template>
  <div class="modal" style="z-index: 1000;">
    <div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
        <div class="modal-header">
          <span>팔로워</span>
          <font-awesome-icon class="icon" icon="times" @click="$emit('close-modal')"/>
        </div>
        <div class="modal-content">
          <ul class="follow_container">
            <li class="img_name_contain" v-for="(follower, idx) in userFollowers" :key="idx" @click="moveProfile(follower.userId)">
              <img class="follow_profile_img" :src="follower.profilePath">
              <div class="follow_profile">
                <span>{{follower.nickName}}</span>
                <button>팔로우</button>
              </div>
            </li>
          </ul>
        </div>
        <div class="modal-footer">
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