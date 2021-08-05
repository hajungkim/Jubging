<template>
  <div>
    <div class="top3-group">
			<div class="top3">
				<div>메달</div>
				<img :src="rankers[1].profile_path">
				<span>{{ rankers[1].nickname }}</span>
				<span>{{ rankers[1].score }}</span>
			</div>

			<div class="top3">
				<div>메달</div>
				<img :src="rankers[0].profile_path">
				<span>{{ rankers[0].nickname }}</span>
				<span>{{ rankers[0].score }}</span>
			</div>

			<div class="top3">
				<div>메달</div>
				<img :src="rankers[2].profile_path">
				<span>{{ rankers[2].nickname }}</span>
				<span>{{ rankers[2].score }}</span>
			</div>

		</div>
		
		<hr>

		<div class="ranker-group">
			<div v-for="(user, idx) in rankers" :key="idx">
				<div v-if="idx > 2" class="ranker">
					<div class="rank-user-group">
						<span class="ranker-rank">{{ idx }}</span>
						<div class="ranker-user">
							<img :src="user.profilePath">
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
	}
}
</script>

<style lang="scss" scoped>
@import "@/components/ranking/UserRank.scss";

</style>