<template>
  <div class="modal" style="z-index: 1000;">
    <div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
        <div class="modal-header">
          <span>팔로잉</span>
          <font-awesome-icon class="icon" icon="times" @click="$emit('close-modal')"/>
        </div>
        <div class="modal-content">
          <ul class="follow_container">
            <li class="img_name_contain" v-for="(following, idx) in userFollowings" :key="idx" @click="moveProfile(following.userId)">
              <img class="follow_profile_img" :src="following.profilePath">
              <div class="follow_profile">
                <span>{{following.nickName}}</span>
                <button>언팔로우</button>
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
  name:'FollowingModal',
  props:{
    userId: Number,
  },
  computed:{
		...mapState([
			'userFollowings'
		]),
  },
  created(){
    this.getFollowing(this.userId)
  },
	methods:{
    ...mapActions([
      'getFollowing'
    ]),
    moveProfile(followingUserId){
      if (followingUserId*1 === this.$store.state.userId*1) {
        this.$router.push({name:'My'})
      } else {
        this.$store.state.backPage = 1
        this.$router.push({name:'Userprofile', params: { user_id: followingUserId }})
      }
    },
	}
}
</script>

<style lang="scss" scoped>
@import "@/components/my/FollowModal.scss";
</style>