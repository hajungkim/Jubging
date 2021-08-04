<template>
  <div class="mission-component-wrap">
		<div v-for="badge in badges" :key="badge.id" class="mission-card ">
			<div class="mission-description" :style="{ backgroundImage: 'url(' + badge.bg_image + ')'}">
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
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '댓글 뱃지',
					description: '댓글 설명',
					currentCnt: this.$store.state.missions.commentCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/170x170',
					bronze_image: 'http://placehold.it/170x170',
					silver_image: 'http://placehold.it/170x170',
				},
				{
					name: '게시글 뱃지',
					description: '게시글 설명',
					currentCnt: this.$store.state.missions.articleCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/170x170',
					bronze_image: 'http://placehold.it/170x170',
					silver_image: 'http://placehold.it/170x170',
				},
				{
					name: '팔로우 뱃지',
					description: '팔로우 설명',
					currentCnt: this.$store.state.missions.followCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/170x170',
					bronze_image: 'http://placehold.it/170x170',
					silver_image: 'http://placehold.it/170x170',
				},
				{
					name: '줍깅 몇회 뱃지',
					description: '팔로우 설명',
					currentCnt: this.$store.state.missions.jubgingCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/170x170',
					bronze_image: 'http://placehold.it/170x170',
					silver_image: 'http://placehold.it/170x170',
				},
			]
		}
	},
}
</script>

<style lang="scss" scoped>
@import '@/components/mission/MissionComponent.scss';

</style>