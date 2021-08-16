<template>
  <div class="mission-component-wrap">
		<div v-for="badge in badges" :key="badge.id" class="mission-card ">
			<div class="mission-description" :style="{ backgroundImage: 'url(' + require(`@/assets/bg/${badge.bg_image}`) + ')'}">
				<h2 class="title">{{ badge.name }}</h2>
				<span class="sub">{{ badge.description }}</span>
				<span class="sub">{{ badge.current }}</span>
			</div>
			<div class="badges">
				<div class="badge-group">
					<img class="badge" :src="require(`@/assets/badge/${badge.imgFolder_name}/bronze.jpg`)" alt="">
					<span>{{ badge.bronze }}</span>
				</div>
				<div class="badge-group">
					<img class="badge" :src="require(`@/assets/badge/${badge.imgFolder_name}/silver.jpg`)" alt="">
					<span>{{ badge.silver }}</span>
				</div>
				<div class="badge-group">
					<img class="badge" :src="require(`@/assets/badge/${badge.imgFolder_name}/gold.jpg`)" alt="">
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
					imgFolder_name: 'like',
					description: '좋아요를 눌러 뱃지를 획득하세요!',
					current: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'like.jpg',
				},
				{
					name: '댓글 뱃지',
					imgFolder_name: 'comment',
					description: '댓글을 작성하여 뱃지를 획득하세요!',
					current: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'comments.jpg',
				},
				{
					name: '팔로우 뱃지',
					imgFolder_name: 'follow',
					description: '팔로우하여 뱃지를 획득하세요!',
					current: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'follow.jpg',
				},
				{
					name: '줍깅 횟수 뱃지',
					imgFolder_name: 'jubging',
					description: '줍깅을 실천하고 뱃지를 획득하세요!',
					current: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'jubging.jpg',
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
			this.badges[0].current = this.missions.like
			this.badges[1].current = this.missions.comment
			this.badges[2].current = this.missions.follow
			this.badges[3].current = this.missions.jubging
		}
	},
}
</script>

<style lang="scss" scoped>
@import '@/components/mission/MissionComponent.scss';

</style>