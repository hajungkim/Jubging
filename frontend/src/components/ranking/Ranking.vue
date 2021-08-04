<template>
  <div class="ranking-wrap">
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

<style lang="scss" scoped>
@import "@/components/ranking/Ranking.scss";

</style>