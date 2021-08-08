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
import { mapState } from 'vuex'

export default {
	name: 'MissionActive',
	data() {
		return {
			badges: [
				{
					name: '좋아요 뱃지',
					description: '좋아요를 눌러 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '댓글 뱃지',
					description: '댓글을 작성하여 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'http://placehold.it/170x170',
					bronze_image: 'http://placehold.it/170x170',
					silver_image: 'http://placehold.it/170x170',
				},
				{
					name: '팔로우 뱃지',
					description: '팔로우하여 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'http://placehold.it/170x170',
					bronze_image: 'http://placehold.it/170x170',
					silver_image: 'http://placehold.it/170x170',
				},
				{
					name: '줍깅 횟수 뱃지',
					description: '줍깅을 실천하고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'http://placehold.it/170x170',
					bronze_image: 'http://placehold.it/170x170',
					silver_image: 'http://placehold.it/170x170',
				},
			]
		}
	},
	computed: {
		...mapState([
			'missions',
		])
	},
	watch: {
		missions() {
			this.badges[0].currentCnt = this.missions.likeCnt
			this.badges[1].currentCnt = this.missions.commentCnt
			this.badges[2].currentCnt = this.missions.followCnt
			this.badges[3].currentCnt = this.missions.jubgingCnt
		}
	},
}
</script>

<style lang="scss" scoped>
@import '@/components/mission/MissionComponent.scss';

</style>