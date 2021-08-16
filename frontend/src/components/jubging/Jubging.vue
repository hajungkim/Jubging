<template>
  <div>
    <div class="top">
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo">
      <font-awesome-icon :icon="['far','calendar-alt']" class="events" @click="open"/>
    </div>
    <div id="body">
      <img height="550px" src="" alt="줍깅 설명">
      <!-- <button @click="startJubging()" class="btn-jubging">{{ msg }}</button> -->
      <button @click="startJubging()" class="btn">{{msg}}</button>
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
  window.finishJubging = this.finishJubging  // 줍깅이 끝났을 때 호출
  window.onJubging = this.onJubging  // 줍깅을 하고있는지 아닌지

},
mounted() {
  this.msg = "mounted"
	console.log("mounted")
  if (this.$store.state.isJubgingOn) {
    this.msg = "줍깅 중.."
  } else {
    this.msg = "줍깅 시작"
  }
},
methods:{
  open(){
    this.$router.push({name:'Events'})
  },

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
    } else {
      this.msg = "줍깅 시작"
    }
    this.$store.dispatch('jubgingOn', isJubgingOn)
  },

  finishJubging(jubgingInfo) {

    var info = jubgingInfo.split("/")

    this.msg = "줍깅 시작"
    this.$store.dispatch('jubgingOn', false)

    var time = info[1]
    var dist = info[2]
    
    this.$store.dispatch('setAddress', info[0])  // 시작 주소 입력
    this.$store.dispatch('setJubgingInfo', {time, dist})
    this.$router.push({name:'Register'})

  },
},
}
</script>

<style lang="scss" scoped>
@import "@/components/jubging/Jubging.scss";
</style>