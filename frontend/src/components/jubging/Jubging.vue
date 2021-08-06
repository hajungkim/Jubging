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
        <button @click="startJubging()" class="btn">줍깅 시작</button>
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
        btnMessage: this.$store.state.jubgingMessage,
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
},
mounted() {
    console.log("mounted")
},
methods:{
    startJubging() {
        window.Android.startJubging()
    },
},
}
</script>

<style lang="scss" scoped>
@import "@/components/jubging/Jubging.scss";
</style>