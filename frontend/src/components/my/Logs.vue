<template>
  <div class="mission-wrap">
    <div class="top-select">
			<div @click="changeComponent(true)" class="select-item" :class="[flag ? 'active': 'inactive']"><span>줍깅 로그</span></div>
			<div @click="changeComponent(false)" class="select-item" :class="[!flag ? 'active': 'inactive']"><span>좋아요 로그</span></div>
		</div>

		<div class="mission-screen">
			<Jubginglog v-show="flag" />
			<Likelog v-show="!flag" />
		</div>
  </div>
</template>

<script>
import Jubginglog from '@/components/my/Jubginglog.vue'
import Likelog from '@/components/my/Likelog.vue'

export default {
	name: 'Logs',
	components: {
		Jubginglog,
		Likelog
	},
	data() {
		return {
			flag: true
		}
	},
	created() {
		this.$store.dispatch('getMission')
	},
	methods: {
		changeComponent(bool) {
			this.flag = bool
		}
	}
}
</script>

<style scoped>

.mission-wrap {
	height: 721px;
}

.top-select {
	display: flex;
	justify-content: space-evenly;
}
.select-item {
	width: 180px;
	height: 50px;
	text-align: center;

	display: flex;
	flex-direction: column;
	justify-content: center;
}	
.active {
	color: #00bfa5;
	border-bottom: 5px solid #00bfa5;
}

.inactive {
	color: black;
}

.mission-screen {
	overflow: auto;
  height: 640px;
	margin: 25px 16px;
}
</style>