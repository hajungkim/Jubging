<template>
  <div>
    <div class="top-select">
			<div @click="changeComponent(true)" class="select-item" :class="[flag ? 'active': 'inactive']"><span>줍깅 미션</span></div>
			<div @click="changeComponent(false)" class="select-item" :class="[!flag ? 'active': 'inactive']"><span>활동 미션</span></div>
		</div>

		<div class="mission-screen">
			<MissionActive v-show="!flag" />
			<MissionJubging v-show="flag" />
		</div>
  </div>
</template>

<script>
import MissionActive from '@/components/mission/MissionActive.vue'
import MissionJubging from '@/components/mission/MissionJubging.vue'

export default {
	name: 'Mission',
	components: {
		MissionActive,
		MissionJubging
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

.mission-screen {
	overflow: auto;
  height: 650px;
	margin: 30px 16px;
}

</style>