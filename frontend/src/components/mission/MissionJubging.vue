<template>
  <div class="mission-component-wrap">
		<div v-for="badge in badges" :key="badge.id" class="mission-card ">
			<div class="mission-description" :style="{ backgroundImage: 'url(' + require(`../../assets/bg/${badge.bg_image}`) + ')'}">
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
					name: '거리 뱃지',
					imgFolder_name: 'distance',
					description: '전체 줍깅 거리를 달성하고 뱃지를 획득하세요!',
					current: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'distance.jpg',
				},
				{
					name: '일반쓰레기 뱃지',
					imgFolder_name: 'trash',
					description: '일반쓰레기를 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'trash.jpg',
				},
				{
					name: '고철 뱃지',
					imgFolder_name: 'metal',
					description: '고철을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'metal.jpg',
				},
        {
					name: '병 뱃지',
					imgFolder_name: 'bottle',
					description: '병을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'bottle.jpg',
				},
				{
					name: '캔 뱃지',
					imgFolder_name: 'can',
					description: '캔을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'can.jpg',
				},
				{
					name: '종이 뱃지',
					imgFolder_name: 'paper',
					description: '종이를 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'paper.jpg',
				},
        {
					name: '플라스틱 뱃지',
					imgFolder_name: 'plastic',
					description: '플라스틱을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'plastic.jpg',
				},
				{
					name: '스티로폼 뱃지',
					imgFolder_name: 'styroform',
					description: '스티로폼을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'styroform.jpg',
				},
				{
					name: '비닐 뱃지',
					imgFolder_name: 'vinyl',
					description: '비닐을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'vinyl.jpg',
				},
        {
					name: '집 주변 뱃지',
					imgFolder_name: 'arround',
					description: '집 주변에서 줍깅하고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'around.jpg',
				},
        {
					name: '산 뱃지',
					imgFolder_name: 'mountain',
					description: '산에서 줍깅하고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'mountain.jpg',
				},
				{
					name: '바다 뱃지',
					imgFolder_name: 'ocean',
					description: '바다에서 줍깅하고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'ocean.jpg',
				},
				{
					name: '강 뱃지',
					imgFolder_name: 'river',
					description: '강에서 줍깅하고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'river.jpg',
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
			this.badges[0].current = parseInt(this.missions.distance)
			this.badges[1].current = parseInt(this.missions.trash)
			this.badges[2].current = parseInt(this.missions.metal)
			this.badges[3].current = parseInt(this.missions.bottle)
			this.badges[4].current = parseInt(this.missions.can)
			this.badges[5].current = parseInt(this.missions.paper)
			this.badges[6].current = parseInt(this.missions.plastic)
			this.badges[7].current = parseInt(this.missions.styroform)
			this.badges[8].current = parseInt(this.missions.vinyl)
			this.badges[9].current = parseInt(this.missions.arround)
			this.badges[10].current = parseInt(this.missions.mountain)
			this.badges[11].current = parseInt(this.missions.ocean)
			this.badges[12].current = parseInt(this.missions.river)

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