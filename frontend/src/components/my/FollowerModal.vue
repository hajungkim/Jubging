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
            <li class="img_name_contain" v-for="(follower, idx) in profileUserFollowers" :key="idx">
              <img class="follow_profile_img" :src="follower.profilePath" @click="moveProfile(follower.userId)">
              <div class="follow_profile">
                <div class="follow-text-group">
                  <span>{{follower.nickName}}</span>
                  <span class="follow-text" v-if="(userId*1 === follower.userId*1) || !checkFollwer(follower.userId)"> </span>
                  <span class="follow-text" v-else>나를 팔로우합니다.</span>
                </div>
                <div v-if="userId*1 !== follower.userId*1">
                  <button class="following-btn" v-if="checkFollowing(follower.userId)" @click="deleteFollow(follower.userId)">언팔로우</button>
                  <button class="follow-btn" v-else @click="onFollow(follower.userId)">팔로우</button>
                </div>
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
import { HTTP } from '@/util/http-common'

export default {
  name:'FollowerModal',
  props:{
    profileUserId: Number,
  },
  computed:{
		...mapState([
			'userFollowers', 'userFollowings', 'userId',
      'profileUserFollowers', 'profileUserFollowings'
		]),
  },
  created(){
    this.getFollower(this.userId)
    this.getFollowing(this.userId)
    this.getProfileFollower(this.profileUserId)
    this.getProfileFollowing(this.profileUserId)
  },
	methods:{
    ...mapActions([
      'getFollower', 'getFollowing',
      'getProfileFollower', 'getProfileFollowing'
    ]),
    moveProfile(followerUserId){
      if (followerUserId*1 === this.userId*1) {
        this.$router.push({name:'My'})
      } else {
        this.$store.state.backPage = 1
        this.$router.push({name:'Userprofile', params: { user_id: followerUserId }})
      }
    },
    checkFollowing(targetId) {
      if (this.userFollowings) {
        return this.userFollowings.some(userInfo => {
          return userInfo.userId === targetId
        })
      }
    },
    checkFollwer(targetId) {
      if (this.userFollowers) {
        return this.userFollowers.some(userInfo => {
          return userInfo.userId === targetId
        })
      }
    },
    deleteFollow(targetId){
      HTTP.delete(`follow?followUserId=${targetId}&userId=${this.userId}`)
        .then(() => {
          this.getFollowing(this.profileUserId)
        })
        .catch((e) => {
          console.error(e);
        })
    },
    onFollow(targetId){
      HTTP.post(`follow?followUserId=${targetId}&userId=${this.userId}`)
        .then(() => {
          this.getFollowing(this.profileUserId)
        })
        .catch((e) => {
          console.error(e);
        })
    },
	}
}
</script>

<style lang="scss" scoped>
@import "@/components/my/FollowModal.scss";
</style>