<template>
  <div class="mission-component-wrap">
		<div v-for="badge in badges" :key="badge.id" class="mission-card ">
			<div class="mission-description" :style="{ backgroundImage: 'url(' + require(`@/assets/bg/${badge.bg_image}`) + ')'}">
				<h2 class="title">{{ badge.name }}</h2>
				<span class="sub">{{ badge.description }}</span>
			</div>

			
			<div class="current_badge" :id="`current-badge-${badge.imgFolder_name}`">
				<span>{{ badge.current }}</span>
				<span><font-awesome-icon icon="sort-down" class="icon"/></span>
			</div>

			<div class="badges">
				<div class="badge-group">
					<img class="badge" :src="require(`@/assets/badge/${badge.imgFolder_name}/bronze.jpg`)" alt="">
					<div class="badge-bridge-bronze"></div>
				</div>
				<div class="badge-group">
					<img class="badge" :src="require(`@/assets/badge/${badge.imgFolder_name}/silver.jpg`)" alt="">
					<div class="badge-bridge-silver"></div>
				</div>
					<img class="badge" :src="require(`@/assets/badge/${badge.imgFolder_name}/gold.jpg`)" alt="">
			</div>
			<div class="texts">
				<span>{{ badge.bronze }}</span>
				<span>{{ badge.silver }}</span>
				<span>{{ badge.gold }}</span>
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

			let elem
			let k, a, b
			Object.values(this.badges).map((badge) => {
				elem = document.querySelector(`#current-badge-${badge.imgFolder_name}`)
				if (badge.current < badge.bronze) {
					k = 5
				} else if (badge.current < badge.silver) {
					a = (badge.current - badge.bronze)
					b = (badge.silver - badge.bronze)
					k = 85 + parseInt((a/b) * 55)
				} else if (badge.current < badge.gold) {
					a = (badge.current - badge.silver)
					b = (badge.gold - badge.silver)
					k = 208 + parseInt((a/b) * 55)
				} else {
					k = 340
				}
				elem.style.marginLeft = `${k}px`
			})
		}
	},
}
</script>

<style lang="scss" scoped>
@import '@/components/mission/MissionComponent.scss';

</style>