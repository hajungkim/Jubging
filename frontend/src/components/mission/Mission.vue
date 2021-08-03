<template>
  <div class="mission-wrap">
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

<style lang="scss" scoped>
@import '@/components/mission/Mission.scss';

</style>