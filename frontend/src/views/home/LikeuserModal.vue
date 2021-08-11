<template>
  <div class="modal">
		<div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
				<div class="modal_top" style="font-size:20px;">좋아요 누른 줍깅이<button class="close" @click="$emit('close-modal')">닫기</button></div>
					<div>
						<ul style="padding:0px; margin-top:10px;">
							<li class="comment_container" v-for="(likePeople,idx) in likePeoples" :key="idx" @click="moveProfile(likePeople)">
								<img class="comment_profile" :src="likePeople.profilePath">
								<div style="display:flex; align-items:center;">
										<span style="font-weight:bold; font-size:17px; margin-left:10px;">{{likePeople.nickname}}</span>
										<span class="like_time">{{likePeople.createdDate.slice(0,10)}}</span>
								</div>
							</li>
						</ul>
					</div>
			</div>
	</div>
</template>

<script>
import axios from 'axios'
export default {
  name:'LikeuserModal',
	data(){
		return{
			likePeoples:[],
		}
	},
	props:{
		selectArticle:{
			type: Object,
		}
  },
	created(){
		const URL = `http://localhost:8080/likelog/likelist/${this.$route.params.article_id}`
		const params = {
			method: 'get',
			url: URL,
		}
		axios(params)
			.then((res) => {
				this.likePeoples = res.data.data				
			})
			.catch((e) => {
				console.error(e);
			})
	},
	methods:{
		moveProfile(user){
			console.log(user.userId)
			this.$store.state.currentUser = user.userId;
			console.log(this.$store.state.currentUser)
			this.$store.state.backPage = 4;
			this.$router.push({name:'Userprofile', params: { user_nickname: user.nickname }})
		}
	}
}
</script>

<style lang="scss" scoped>
@import "@/views/home/LikeuserModal.scss";
</style>