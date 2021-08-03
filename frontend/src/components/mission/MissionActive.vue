<template>
  <div>
		<div v-for="badge in badges" :key="badge.id" class="mission-card ">
			<div class="mission-description">
				<h2 class="title">{{ badge.name }}</h2>
				<span class="sub">{{ badge.description }}</span>
				<span class="sub">{{ badge.currentCnt }}</span>
			</div>
			<div class="badges">
				<div class="badge-group">
					<div class="badge"></div>
					<span>{{ badge.bronze }}</span>
				</div>
				<div class="badge-group">
					<div class="badge"></div>
					<span>{{ badge.silver }}</span>
				</div>
				<div class="badge-group">
					<div class="badge"></div>
					<span>{{ badge.gold }}</span>
				</div>
			</div>
		</div>
  </div>
</template>

<script>
export default {
	name: 'MissionActive',
	data() {
		return {
			badges: [
				{
					name: '좋아요 뱃지',
					description: 'n개 더 좋아요를 누르면 뱃지 획득!',
					currentCnt: this.$store.state.missions.likeCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '댓글 뱃지',
					description: '댓글 설명',
					currentCnt: this.$store.state.missions.commentCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '게시글 뱃지',
					description: '게시글 설명',
					currentCnt: this.$store.state.missions.articleCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '팔로우 뱃지',
					description: '팔로우 설명',
					currentCnt: this.$store.state.missions.followCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '줍깅 몇회 뱃지',
					description: '팔로우 설명',
					currentCnt: this.$store.state.missions.jubgingCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
			]
		}
	},
}
</script>

<style scoped>

.mission-card {
	width: 380px;
	height: 250px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	margin-bottom: 16px;
}

.mission-description {
	width: 380px;
	height: 100px;
	background-color: gray;
	border-radius: 10px 10px 0px 0px;
	text-align: center;

	display: flex;
	flex-direction: column;
	justify-content: center;
}

.mission-description > .title {
	color: white;
	margin: 0;
}

.mission-description > .sub {
	color: white;
}

.badges {
	display: flex;
	justify-content: space-evenly;
	margin-top: 40px;
}

.badge {
	width: 50px;
	height: 50px;
	background-color: silver;
}

.badge-group {
	display: flex;
	flex-direction: column;
	justify-content: center;
	text-align: center;
}

</style>