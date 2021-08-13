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
		if (this.$route.params.flag === true){
			this.flag = true
		}
		else{
			this.flag = false
		}
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
@import "@/components/my/Logs.scss";
</style>