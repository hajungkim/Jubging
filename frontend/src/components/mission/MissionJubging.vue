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
					name: '총 거리 뱃지',
					description: '총 줍깅 거리를 달성하고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 10,
					silver: 50,
					gold: 100,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '일반쓰레기 뱃지',
					description: '일반쓰레기를 줍고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '고철 뱃지',
					description: '고철을 줍고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '병 뱃지',
					description: '병을 줍고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '캔 뱃지',
					description: '캔을 줍고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '종이 뱃지',
					description: '종이를 줍고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '플라스틱 뱃지',
					description: '플라스틱을 줍고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '스티로폼 뱃지',
					description: '스티로폼을 줍고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '비닐 뱃지',
					description: '비닐을 줍고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '집 주변 뱃지',
					description: '집 주변에서 줍깅하고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '산 뱃지',
					description: '산에서 줍깅하고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '바다 뱃지',
					description: '바다에서 줍깅하고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '강 뱃지',
					description: '강에서 줍깅하고 뱃지를 획득하세요!',
					currentCnt: '',
					bronze: 3,
					silver: 10,
					gold: 20,
					bg_image: 'http://placehold.it/185x185',
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
			this.badges[0].currentCnt = this.missions.totalDistance
			this.badges[1].currentCnt = this.missions.trashCnt
			this.badges[2].currentCnt = this.missions.metalCnt
			this.badges[3].currentCnt = this.missions.bottleCnt
			this.badges[4].currentCnt = this.missions.canCnt
			this.badges[5].currentCnt = this.missions.paperCnt
			this.badges[6].currentCnt = this.missions.plasticCnt
			this.badges[7].currentCnt = this.missions.styroformCnt
			this.badges[8].currentCnt = this.missions.vinylCnt
			this.badges[9].currentCnt = this.missions.arroundCnt
			this.badges[10].currentCnt = this.missions.mountainCnt
			this.badges[11].currentCnt = this.missions.oceanCnt
			this.badges[12].currentCnt = this.missions.riverCnt
		}
	},
}
</script>

<style lang="scss" scoped>
@import '@/components/mission/MissionComponent.scss';

</style>