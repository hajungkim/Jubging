<template>
  <div>
    <div>
			<div class="gold">
				<div>메달</div>
				<img :src="top20[0].profile_path">
				<span>{{ top20[0].nickname }}</span>
				<span>{{ top20[0].score }}</span>
			</div>
		</div>
		
		<hr>

		<div class="top20-wrap">
			<div v-for="(user, idx) in top20" :key="idx" class="top20">
				<span>{{ idx }}</span>
				<img :src="user.profile_path">
				<span>{{ user.nickname }}</span>
				<span>{{ user.score }}</span>
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
			]
		}
	}
}
</script>

<style scoped>

.gold {
	width: 100px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}


.top20-wrap {
	padding: 5px 20px;
}

.top20 {
	display: flex;
	align-items: center;
}

.top20 > img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
}

</style>