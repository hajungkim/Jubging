<template>
  <div>
    <div class="top3-group" v-if="rankers">
			<div class="top3-group-silver">
				<font-awesome-icon icon="trophy" class="silver"/>
				<div class="top3">
					<img :src="rankers[1].profilePath" @click="switchProfile(rankers[1].userId)">
					<span>{{ rankers[1].nickname }}</span>
					<span class="score">{{ rankers[1].score }}</span>
				</div>
			</div>

			<div class="top3-group-gold">
				<font-awesome-icon icon="trophy" class="gold"/>
				<div class="top3">
					<img :src="rankers[0].profilePath" @click="switchProfile(rankers[0].userId)">
					<span>{{ rankers[0].nickname }}</span>
					<span class="score">{{ rankers[0].score }}</span>
				</div>
			</div>

			<div class="top3-group-bronze">
				<font-awesome-icon icon="trophy" class="bronze"/>
				<div class="top3">
					<img :src="rankers[2].profilePath" @click="switchProfile(rankers[2].userId)">
					<span>{{ rankers[2].nickname }}</span>
					<span class="score">{{ rankers[2].score }}</span>
				</div>
			</div>
		</div>
		
		<div class="ranker-group">
			<div v-for="(user, idx) in rankers" :key="idx">
				<div v-if="idx > 2" class="ranker">
					<div class="rank-user-group">
						<span class="ranker-rank">{{ idx+1 }}</span>
						<div class="ranker-user">
							<img :src="user.profilePath" @click="switchProfile(user.userId)">
							<span>{{ user.nickname }}</span>
						</div>
					</div>
					<span class="ranker-score">{{ user.score }}</span>
				</div>
			</div>
		</div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
	name: 'UserRank',
	computed: {
		...mapState([
			'rankers'
		])
	},
	created() {
		this.$store.dispatch('getRanker')
	},
	methods: {
		switchProfile(userId) {
			this.$router.push({ name:'Userprofile', params: { user_id: userId } })
		}
	}
}
</script>

<style lang="scss" scoped>
@import "@/components/ranking/UserRank.scss";

</style>