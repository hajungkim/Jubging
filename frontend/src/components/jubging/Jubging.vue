<template>
  <div>
      <div id="header">
          <div class="goback to-center">
            <img src="" alt="뒤로가기">
          </div>
          <div class="to-center">
            <img src="" alt="줍깅 로고">
          </div>
      </div>
      <div id="body">
        <img height="550px" src="" alt="줍깅 설명">
        <button id="btn-jubging" @click="startJubging()" class="btn">{{ msg }}</button>
      </div>
  </div>
</template>

<script>
export default {

name: "Jubging",
components:{
},
props: {
},
data() {
	return{
    msg: "줍깅 시작",
		myKey: "8774c36051efa950c0ca483b2578a15c",
		latitude: 0.0,
		longitude: 0.0,
	}
},
computed:{
},
watch:{
},
created() {
  window.onJubging = this.onJubging
  window.finishJubging = this.finishJubging
},
mounted() {
	console.log("mounted")
  if (this.$store.state.isJubgingOn) {
    this.msg = "줍깅 중.."
  } else {
    this.msg = "줍깅 시작"
  }
},
methods:{
	startJubging() {
    if (this.$store.state.isJubgingOn) {
      window.Android.startJubgingActivity()
    }
    else {
      window.Android.startCameraActivity()
    }
	},
  onJubging(isJubgingOn) {
    if (isJubgingOn) {
      this.msg = "줍깅 중.."
    }
    this.$store.dispatch('jubgingOn', isJubgingOn)
  },
  finishJubging(time, dist) {
    this.msg = "줍깅 시작"
    this.$store.dispatch('jubgingOn', false)
    this.$store.dispatch('setJubgingInfo', {time, dist})
    this.$router.push({name:'Register'})
  }
},
}
</script>

<style lang="scss" scoped>
@import "@/components/jubging/Jubging.scss";
</style>