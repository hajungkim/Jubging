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
					name: '전체거리 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.totalDistance,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '일반쓰레기 뱃지',
					description: '득!',
					currentCnt: this.$store.state.missions.trashCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '고철 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.metalCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
        {
					name: '병 뱃지',
					description: '득!',
					currentCnt: this.$store.state.missions.bottleCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '캔 뱃지',
					description: ' 설명',
					currentCnt: this.$store.state.missions.canCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '종이 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.paperCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
        {
					name: '플라스틱 뱃지',
					description: '득!',
					currentCnt: this.$store.state.missions.plasticCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '스티로폼 뱃지',
					description: ' 설명',
					currentCnt: this.$store.state.missions.styroformCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '비닐 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.vinylCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
        {
					name: '집주변 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.arroundCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
        {
					name: '산 뱃지',
					description: '득!',
					currentCnt: this.$store.state.missions.mountainCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '바다 뱃지',
					description: ' 설명',
					currentCnt: this.$store.state.missions.oceanCnt,
					bronze: 10,
					silver: 20,
					gold: 30,
				},
				{
					name: '강 뱃지',
					description: '  설명',
					currentCnt: this.$store.state.missions.riverCnt,
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