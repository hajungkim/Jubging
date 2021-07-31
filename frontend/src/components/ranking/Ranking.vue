<template>
  <div>
		<div class="top-select">
			<div @click="changeComponent(true)" class="select-item" :class="[flag ? 'active': 'inactive']"><span>유저 랭크</span></div>
			<div @click="changeComponent(false)" class="select-item" :class="[!flag ? 'active': 'inactive']"><span>지역 랭크</span></div>
		</div>

		<div class="rank-screen">
			<UserRank v-show="flag" />
			<RegionRank v-show="!flag" />
		</div>

	</div>
</template>

<script>
import UserRank from '@/components/ranking/UserRank.vue'
import RegionRank from '@/components/ranking/RegionRank.vue'

export default {
	name: 'Ranking',
	components: {
		UserRank,
		RegionRank
	},
	data() {
		return {
			flag: true
		}
	},
	methods: {
		changeComponent(bool) {
			this.flag = bool
		}
	}
}
</script>

<style scoped>

.top-select {
	display: flex;
	justify-content: space-evenly;
}

.top-select > .select-item {
	width: 180px;
	height: 50px;
	text-align: center;

	display: flex;
	flex-direction: column;
	justify-content: center;
}

.active {
	color: red;
	border-bottom: 5px solid red;
}

.inactive {
	color: black;
}

.rank-screen {
	overflow: auto;
    height: 650px;
	margin: 30px 16px;
}

</style>