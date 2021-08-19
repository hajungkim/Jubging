<template>
	<div class="modal">
		<div class="overlay" @click="$emit('close-modal')"></div>
		<div class="modal-card">
			<div class="modal-header">
				<span>좋아요 누른 줍깅이</span>
				<font-awesome-icon class="icon" icon="times" @click="$emit('close-modal')"/>
			</div>
			<div class="modal-content">

				<ul>
					<li class="comment_container" v-for="(likePeople,idx) in likePeoples" :key="idx" @click="moveProfile(likePeople)">
						<img class="comment_profile" :src="likePeople.profilePath">
						<span>{{likePeople.nickname}}</span>
					</li>
				</ul>

			</div>
			<div class="modal-footer">
			</div>
		</div>
	</div>
</template>

<script>
import { HTTP } from '@/util/http-common'

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
		HTTP.get(`likelog/likelist/${this.$route.params.article_id}`)
			.then((res) => {
				this.likePeoples = res.data.data				
			})
			.catch((e) => {
				console.error(e);
			})
	},
	methods:{
		moveProfile(user){
			this.$store.state.currentUser = user.userId;
			this.$store.state.backPage = 4;
			this.$router.push({name:'Userprofile', params: { user_id: user.userId }})
		}
	}
}
</script>

<style lang="scss" scoped>
@import "@/views/home/LikeuserModal.scss";
</style>