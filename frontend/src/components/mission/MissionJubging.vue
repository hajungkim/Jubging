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
					name: '전체거리 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.totalDistance,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '일반쓰레기 뱃지',
					description: '득!',
					currentCnt: this.$store.state.missions.trashCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '고철 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.metalCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '병 뱃지',
					description: '득!',
					currentCnt: this.$store.state.missions.bottleCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '캔 뱃지',
					description: ' 설명',
					currentCnt: this.$store.state.missions.canCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '종이 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.paperCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '플라스틱 뱃지',
					description: '득!',
					currentCnt: this.$store.state.missions.plasticCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '스티로폼 뱃지',
					description: ' 설명',
					currentCnt: this.$store.state.missions.styroformCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '비닐 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.vinylCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '집주변 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.arroundCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
        {
					name: '산 뱃지',
					description: '득!',
					currentCnt: this.$store.state.missions.mountainCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '바다 뱃지',
					description: ' 설명',
					currentCnt: this.$store.state.missions.oceanCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
				{
					name: '강 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.riverCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
					bg_image: 'http://placehold.it/185x185',
					bronze_image: 'http://placehold.it/185x185',
					silver_image: 'http://placehold.it/185x185',
				},
			]
		}
	},
}
</script>

<style lang="scss" scoped>
@import '@/components/mission/MissionComponent.scss';

</style>