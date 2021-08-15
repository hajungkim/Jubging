<template>
  <div class="mission-component-wrap">
		<div v-for="badge in badges" :key="badge.id" class="mission-card ">
			<div class="mission-description" :style="{ backgroundImage: 'url(' + require(`../../assets/bg/${badge.bg_image}`) + ')'}">
				<h2 class="title">{{ badge.name }}</h2>
				<span class="sub">{{ badge.description }}</span>
				<!-- <span class="sub">{{ badge.current }}</span> -->
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
					name: '거리 뱃지',
					description: '전체 줍깅 거리를 달성하고 뱃지를 획득하세요!',
					current: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'totalDistance.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '일반쓰레기 뱃지',
					description: '일반쓰레기를 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'totalDistance.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '고철 뱃지',
					description: '고철을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'metal.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '병 뱃지',
					description: '병을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'bottle.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '캔 뱃지',
					description: '캔을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'can.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '종이 뱃지',
					description: '종이를 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'paper.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '플라스틱 뱃지',
					description: '플라스틱을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'plastic.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '스티로폼 뱃지',
					description: '스티로폼을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'styroform.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '비닐 뱃지',
					description: '비닐을 줍고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'totalDistance.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '집 주변 뱃지',
					description: '집 주변에서 줍깅하고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'totalDistance.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '산 뱃지',
					description: '산에서 줍깅하고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'mountain.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '바다 뱃지',
					description: '바다에서 줍깅하고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'ocean.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '강 뱃지',
					description: '강에서 줍깅하고 뱃지를 획득하세요!',
					current: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'river.jpg',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
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
			this.badges[0].current = this.missions.distance
			this.badges[1].current = this.missions.trash
			this.badges[2].current = this.missions.metal
			this.badges[3].current = this.missions.bottle
			this.badges[4].current = this.missions.can
			this.badges[5].current = this.missions.paper
			this.badges[6].current = this.missions.plastic
			this.badges[7].current = this.missions.styroform
			this.badges[8].current = this.missions.vinyl
			this.badges[9].current = this.missions.arround
			this.badges[10].current = this.missions.mountain
			this.badges[11].current = this.missions.ocean
			this.badges[12].current = this.missions.river
		}
	},
}
</script>

<style lang="scss" scoped>
@import '@/components/mission/MissionComponent.scss';

</style>