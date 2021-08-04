<template>
  <div>
    <div class="top3-group">
			<div class="top3">
				<div>메달</div>
				<img :src="top20[1].profile_path">
				<span>{{ top20[1].nickname }}</span>
				<span>{{ top20[1].score }}</span>
			</div>

			<div class="top3">
				<div>메달</div>
				<img :src="top20[0].profile_path">
				<span>{{ top20[0].nickname }}</span>
				<span>{{ top20[0].score }}</span>
			</div>

			<div class="top3">
				<div>메달</div>
				<img :src="top20[2].profile_path">
				<span>{{ top20[2].nickname }}</span>
				<span>{{ top20[2].score }}</span>
			</div>

		</div>
		
		<hr>

		<div class="top20-group">
			<div v-for="(user, idx) in top20" :key="idx">
				<div v-if="idx > 2" class="top20">
					<div class="rank-user-group">
						<span class="top20-rank">{{ idx }}</span>
						<div class="top20-user">
							<img :src="user.profile_path">
							<span>{{ user.nickname }}</span>
						</div>
					</div>
					<span class="top20-score">{{ user.score }}</span>
				</div>
			</div>
		</div>
  </div>
</template>

<script>
export default {
	name: 'UserRank',
	data() {
		return {
			top20: [
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
				{
					nickname: 'daeun',
					score: '320',
					profile_path: 'http://placehold.it/185x185',
				},
			]
		}
	}
}
</script>

<style lang="scss" scoped>
@import "@/components/ranking/UserRank.scss";

</style>